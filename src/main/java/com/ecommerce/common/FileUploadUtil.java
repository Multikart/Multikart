package com.ecommerce.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	public static void saveFile(String uploadDir , String fileName, MultipartFile multipartFile) throws IOException {
		// Duong Dan Folder = tenDuongDan = src\\main\\resource\\static\\user-photos\\27062021\\
		String uploadDirResource = new File("src\\main\\resources\\static").getAbsolutePath() + "\\" + uploadDir;
		Path uploadPath = Paths.get(uploadDirResource);
		
		//Kiem tra ton tai va create folder
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath); 
		}
		try(InputStream inputStream = multipartFile.getInputStream()){
			//Them Image vao Duong Dan Folder = tenDuongDan/image.jpg
			Path filePath = uploadPath.resolve(fileName);
			
			//Copy Image vao Folder
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			
		}catch(IOException ioe) {
			throw new IOException("Could not save image file: " + fileName, ioe);
		}
	}
}
