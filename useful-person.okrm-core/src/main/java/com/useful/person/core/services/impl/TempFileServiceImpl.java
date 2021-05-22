package com.useful.person.core.services.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.useful.person.core.domain.TempFile;
import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.exception.OSSException;
import com.useful.person.core.oss.IUploadFile;
import com.useful.person.core.properties.AppProperties;
import com.useful.person.core.properties.OSSConfig;
import com.useful.person.core.properties.SecurityProperties;
import com.useful.person.core.properties.TempProperties;
import com.useful.person.core.repository.TempFileRepository;
import com.useful.person.core.services.TempFileService;
import com.useful.person.core.utils.FileUtil;

import net.coobird.thumbnailator.Thumbnails;

@Service("tempFileService")
public class TempFileServiceImpl implements TempFileService {

    @Autowired
    private TempFileRepository tempFileRepository;

    @Autowired
    private IUploadFile ossUploadFile;

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    @Transactional
    public TempFile uploadFile(UserInfo currentUser, MultipartFile file, boolean compress) {
        OSSConfig ossConfig = securityProperties.getOss().getConfig();
        String uuid = UUID.randomUUID().toString();
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.') + 1,
                originalFileName.length());
        TempProperties tempProperties = appProperties.getFile().getTemp();
        String tempDir = tempProperties.getDir();
        int expireIn = tempProperties.getExpireIn();
        String fileName = uuid + "." + fileExtension;
        String outFile = tempDir + "/" + fileName;
        if (compress) {
            try {
                Thumbnails.of(file.getInputStream()).size(480, 480).outputFormat(fileExtension).toFile(outFile);
            } catch (IOException e) {
                e.printStackTrace();
                throw new OSSException("上传头像失败！");
            }
        }
        String path = ossConfig.getAvatarDir() + "/" + fileName;
        ossUploadFile.uploadToOSS(outFile, path);
        String url = ossConfig.getResourceUrl() + "/" + path;
        TempFile tempFile = TempFile.builder().user(currentUser).url(url)
                .expireTime(LocalDateTime.now().plusSeconds(expireIn)).build();
        TempFile returnTempFile = tempFileRepository.save(tempFile);
        FileUtil.deleteFile(outFile);
        return returnTempFile;
    }

}
