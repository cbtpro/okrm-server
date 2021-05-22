package com.useful.person.core.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {

    public static boolean storageFile(MultipartFile mpf, String filePath, String fileName) {
        File file = new File(filePath + fileName);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            mpf.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            log.debug("临时存储文件到本地失败" + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean deleteFiles(File files[]) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
        return true;
    }

    public static boolean deleteFile(File file) {
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return FileUtil.deleteFile(file);
    }
}