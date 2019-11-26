package com.useful.person.core.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.useful.person.core.vo.FileInfo;

/**
 * 
 * @author peter
 *
 */
@RestController
@RequestMapping("/file")
public class FileController {

	String folder = "/Users/peter/Documents/okrm repo";

	@PostMapping
	public FileInfo upload(MultipartFile file) throws Exception {
		File localFile = new File(folder, System.currentTimeMillis() + ".txt");
//		file.getInputStream()
		file.transferTo(localFile);
		return new FileInfo(localFile.getAbsolutePath());
	}

	@GetMapping("/{filename}")
	public void download(@PathVariable String filename, HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException, IOException {
		try (InputStream inputStream = new FileInputStream(new File(folder, filename + ".txt"));
				OutputStream outputStream = response.getOutputStream();) {
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=test.txt");

			IOUtils.copy(inputStream, outputStream);
			outputStream.flush();
		}
	}
}
