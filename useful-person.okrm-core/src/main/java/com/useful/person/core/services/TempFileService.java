package com.useful.person.core.services;

import org.springframework.web.multipart.MultipartFile;

import com.useful.person.core.domain.TempFile;
import com.useful.person.core.domain.UserInfo;

public interface TempFileService {

	TempFile uploadFile(UserInfo currentUser, MultipartFile file, boolean compress);

}
