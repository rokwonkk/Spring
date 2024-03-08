package com.ssg.a.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssg.a.naver.NaverCloud;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class NaverCloudController {

	// STT
	// 음성 인식(파일) wav -> String
	@PostMapping("fileupload")
	public String fileUpload(@RequestParam("uploadfile") MultipartFile uploadfile,
								HttpServletRequest req) {
			System.out.println("NaverCloudController fileUpload() " + new Date());
			
			String uploadPath = req.getServletContext().getRealPath("/upload");
			
			//파일명 가져옴
			String filename = uploadfile.getOriginalFilename();
			//File.separator -> 경로/파일명
			String filepath = uploadPath + File.separator + filename;
			
			System.out.println(filepath);
						
			try {
				BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(filepath));

				os.write(uploadfile.getBytes());	// 실제 업로드 되는 부분
				os.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return "file upload fail";
			}
					
			
			//Naver cloud
			String responce = NaverCloud.sttProc(filepath);
			return responce;
	}
	
	// TTS
	@PostMapping("tts")
	public String tts(String str, HttpServletRequest res) {
		
		System.out.println("NaverCloudController tts() " + new Date());
		
		//경로
		String uploadPath = res.getServletContext().getRealPath("/upload");
		System.out.println("uploadPath :" + uploadPath);
		
		// (mp3)파일 저장 후에 메세지
		String message = NaverCloud.ttsProc(str, uploadPath);
		
		return message;
	}
	
	//ocr
	@PostMapping("pictureupload")
	public StringBuffer ocr(@RequestParam("uploadfile") MultipartFile uploadfile,
			HttpServletRequest req){
	
		System.out.println("NaverCloudController ocr() " + new Date());
		
		String uploadPath = req.getServletContext().getRealPath("/upload");
		
		//파일명 가져옴
		String filename = uploadfile.getOriginalFilename();
		
		//File.separator -> 경로/파일명
		String filepath = uploadPath + File.separator + filename;
		
		System.out.println(filepath);

		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(filepath));

			os.write(uploadfile.getBytes());	// 실제 업로드 되는 부분
			os.close();
		} catch (Exception e) {
			return null;
		}
				
		//Naver cloud
		StringBuffer responce = NaverCloud.ocrProc(filepath);
		System.out.println( "responce2222222222 : " + responce );
		return responce;
	}
	
	@GetMapping("chatbot")
	public String chatBot(String msg) {
		System.out.println("NaverCloudController chatBot()" + new Date());
		
		String chatbotMessage = NaverCloud.chatBot(msg);
		return chatbotMessage;
	}
}
