package com.useful.person.core.startup;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.useful.person.core.properties.AppProperties;
import com.useful.person.core.properties.TempProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class InitTempDir implements CommandLineRunner {

  @Autowired
  private AppProperties appProperties;

  @Override
  public void run(String... args) throws Exception {
    TempProperties tempProperties = appProperties.getFile().getTemp();
    String tempDirPath = tempProperties.getDir();
    File tempDir = new File(tempDirPath);
    if (!tempDir.exists()) {
      tempDir.mkdir();
      log.info("图片临时文件夹不存在，创建临时文件夹");
    }
  }

}
