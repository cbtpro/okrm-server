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
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.useful.person.core.oss.IUploadFile;
import com.useful.person.core.properties.OSSConfig;
import com.useful.person.core.properties.SecurityProperties;

import lombok.extern.slf4j.Slf4j;

/**
 * @author peter
 *
 */
@Order(1)
@Component("ossUploadFile")
@Slf4j
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
		try {
			ossClient.putObject(putObjectRequest);
		} catch (OSSException e) {
			log.error("头像上传失败：" + e.getMessage());
			throw new com.useful.person.core.exception.OSSException("头像上传失败！");
		} finally {
			ossClient.shutdown();
		}
	}

}
