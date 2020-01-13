/**
 * 
 */
package com.useful.person.core.oss.aliyun;

import java.io.File;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.Callback;
import com.aliyun.oss.model.Callback.CalbackBodyType;
import com.aliyun.oss.model.PutObjectRequest;
import com.useful.person.core.oss.IUploadFile;
import com.useful.person.core.properties.OSSConfig;
import com.useful.person.core.properties.SecurityProperties;

/**
 * @author peter
 *
 */
@Order(1)
@Component("ossUploadFile")
public class UploadFile implements IUploadFile {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public void uploadToOSS(InputStream sourceInputStream, String remotePath) {
		OSSConfig ossConfig = securityProperties.getOss().getConfig();
		String endpoint = ossConfig.getEndpoint();
		String accessKeyId = ossConfig.getAccessKeyId();
		String accessKeySecret = ossConfig.getAccessKeySecret();
		String bucketName = ossConfig.getBucketName();
		
		CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
		ClientConfiguration config = new ClientConfiguration();
		// 创建OSSClient实例
		OSS ossClient = new OSSClient(endpoint, credentialsProvider, config);
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, remotePath, sourceInputStream);
		ossClient.putObject(putObjectRequest);
		ossClient.shutdown();
	}

	@Override
	public void uploadToOSS(File sourceFile, String remotePath) {
		OSSConfig ossConfig = securityProperties.getOss().getConfig();
		String endpoint = ossConfig.getEndpoint();
		String accessKeyId = ossConfig.getAccessKeyId();
		String accessKeySecret = ossConfig.getAccessKeySecret();
		String bucketName = ossConfig.getBucketName();
		
		CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
		ClientConfiguration config = new ClientConfiguration();
		// 创建OSSClient实例
		OSS ossClient = new OSSClient(endpoint, credentialsProvider, config);
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, remotePath, sourceFile);
		ossClient.putObject(putObjectRequest);
		ossClient.shutdown();
	}

	@Override
	public void uploadToOSS(String sourceFilePath, String remotePath) {
		OSSConfig ossConfig = securityProperties.getOss().getConfig();
		String endpoint = ossConfig.getEndpoint();
		String accessKeyId = ossConfig.getAccessKeyId();
		String accessKeySecret = ossConfig.getAccessKeySecret();
		String bucketName = ossConfig.getBucketName();
		
		CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
		ClientConfiguration config = new ClientConfiguration();
		// 创建OSSClient实例
		OSS ossClient = new OSSClient(endpoint, credentialsProvider, config);
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, remotePath, new File(sourceFilePath));
		// 设置回调
		Callback callback = new Callback();
		callback.setCallbackHost("https://api.useful-person.com");
		callback.setCallbackUrl("/oss/callback");
		// 设置发起回调时请求body的值。
		callback.setCallbackBody("{\\\"mimeType\\\":${mimeType},\\\"size\\\":${size}}");
		// 设置发起回调请求的Content-Type。
		callback.setCalbackBodyType(CalbackBodyType.JSON);
		// 设置发起回调请求的自定义参数，由Key和Value组成，Key必须以x:开始。
		callback.addCallbackVar("x:var1", "value1");
		callback.addCallbackVar("x:var2", "value2");
		putObjectRequest.setCallback(callback);
		ossClient.putObject(putObjectRequest);
	}

}
