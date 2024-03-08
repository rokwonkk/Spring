package com.ssg.a.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssg.a.MediaTypeUtils;
import com.ssg.a.dto.PdsDto;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

	@PostMapping("fileupload")
	private String fileupload(PdsDto dto, 
									@RequestParam("uploadfile") MultipartFile uploadfile,
									HttpServletRequest req) { //파일 업로드 경로
		
		System.out.println("HelloController fileupload() " + new Date());
		System.out.println(dto.toString());
		
		// 파일업로드 경로 server경로
		String path = req.getServletContext().getRealPath("/upload");
		
		// local경로
		//String path = "d:\temp";
		
		String filename = uploadfile.getOriginalFilename();
		
		//파일명을 변경
		
		String filepath = path + "/" + filename;
		System.out.println(filepath);
		
		File file = new File(filepath);
		
		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
			
			os.write(uploadfile.getBytes());  //실제 업로드 되는 부분

			os.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	catch (IOException e) {
			e.printStackTrace();
		}
		
		return "파일 업로드 성공!";
	}
	
	@Autowired
	ServletContext servletContext;

	@GetMapping("filedownload")
	public ResponseEntity<InputStreamResource> filedownload(String filename, HttpServletRequest req) throws FileNotFoundException{
		System.out.println("HelloController filedownload() " + new Date());
		
		// 파일업로드 경로 server경로
		String path = req.getServletContext().getRealPath("/upload");
		
		//파일 타입을 조사
		MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(servletContext, filename);
		
		//파일생성
		File file = new File(path + "/" + filename);
		
		InputStreamResource is = new InputStreamResource(new FileInputStream(file));
		
		//download counter 증가 여기
		
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName()) //원본 파일명을 넣어주면됨
				.contentType(mediaType)
				.contentLength(file.length())
				.body(is);
	}
}