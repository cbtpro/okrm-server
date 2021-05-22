/**
 * 
 */
package com.useful.person.core.oss;

import java.io.File;
import java.io.InputStream;

import com.useful.person.core.exception.OSSException;

/**
 * @author peter
 *
 */
public interface IUploadFile {

    /**
     * 上传文件到OSS
     * 
     * @param inputStream
     * @param remotePath
     */
    public void uploadToOSS(InputStream sourceInputStream, String remotePath) throws OSSException;

    /**
     * 上传文件到OSS
     * 
     * @param file
     * @param remotePath
     */
    public void uploadToOSS(File sourceFile, String remotePath) throws OSSException;

    /**
     * 上传文件到OSS
     * 
     * @param sourceFilePath
     * @param remotePath
     */
    public void uploadToOSS(String sourceFilePath, String remotePath) throws OSSException;
}
