package com.ssg.a.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.ssg.a.dto.BbsParam;
import com.ssg.a.dto.PdsDto;
import com.ssg.a.service.PdsService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.coobird.thumbnailator.Thumbnails;
import util.PdsUtil;

@RestController
public class PdsContorller {

	@Autowired
	PdsService service;
	
	@Autowired
	ServletContext servletContext;
	
	@GetMapping("pdslist")
	public Map<String, Object> pdsList(BbsParam param) {
		System.out.println("PdsContorller pdsList() " + new Date());
		
		//글목록
		List<PdsDto> list = service.pdsList(param);
		
		//글의 총 갯수
		int count = service.getAllPds(param);
		int pagePds = count / 10;
		if ((count % 10) > 0) {
			pagePds = pagePds + 1;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("pagePds", pagePds);
		
		return map;
	}
	
	@GetMapping("pdsdetail")
	public PdsDto pdsDetail(PdsDto dto) {
		System.out.println("PdsContorller pdsDetail() " + new Date());
		
		return service.getPds(dto);
	}

	@PostMapping("pdswrite")
	public String pdsWrite(PdsDto dto,
								@RequestParam(value = "fileupload", required = false) MultipartFile uploadfile,
								HttpServletRequest req) {
		System.out.println("PdsController pdsUpload " + new Date());

		/** upload의 경로 **/
		//tomcat(server)
		String path = req.getServletContext().getRealPath("/upload");
		System.out.println("파일업로드 경로: " + path);
		
		//폴더(테스트용) - 록원스 다이렉트 폴더 경로
		//String path = "/Users/rokwon/Desktop/springframeworksts/springSamples/src/main/webapp/upload";
		
		//filename 취득
		String filename = uploadfile.getOriginalFilename();
		System.out.println("filename:" + filename);
		
		//파일명 변경 abc.txt -> 324234324.txt
		String newfilename = PdsUtil.getNewFileName(filename);
		System.out.println("newfilename:" +newfilename);
		
		//파일을 업로드
		File file = new File(path + "/" + newfilename);
		
		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));

			os.write(uploadfile.getBytes());	// 실제 업로드 되는 부분
			os.close();
			
			//db에 저장하기 위해서
			dto.setFilename(filename);
			//db에 저장하기 위해서
			dto.setNewfilename(newfilename);
			
			//db에 저장
			boolean b = service.writePds(dto);
			if(b) {
				System.out.println("파일업로드 성공");
	
				//확장자명 확인
				String extcheck = newfilename.substring(newfilename.indexOf('.'));

				//이미지 파일 일 경우 썸네일 파일 만들어줌.
				if(extcheck.equals(".png") || extcheck.equals(".jpg") || extcheck.equals(".gif") || extcheck.equals(".bmp")) {
					
					File thumbnailFile = new File(path + "/s_" + newfilename);
					
					System.out.println("썸네일 경로 및 파일명 : " + thumbnailFile);
					
					//파일 업로드 성공시에 썸네일 파일 생성
					Thumbnails.of(file)
					//.size(width, height)
					.size(120, 120)
					.toFile(thumbnailFile);
				}
			} else {
				return "fail";
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	
	@GetMapping("filedownload")
	public ResponseEntity<InputStreamResource> fileDownload(PdsDto dto, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("PdsController filedownload " + new Date());
		
		PdsDto pds = service.getPds(dto);
		
		// 경로
		String path = request.getServletContext().getRealPath("/upload");
		// String path = "d:\tmp";
		
		// 파일의 타입을 조사
		MediaType mediaType = PdsUtil.getMediaTypeForFileName(servletContext, pds.getFilename());
		System.out.println("filename:" + pds.getFilename());
		System.out.println("mediaType:" + mediaType);
		
		File file = new File(path + File.separator + pds.getNewfilename());
		
		InputStreamResource is = new InputStreamResource(new FileInputStream(file));
		
		// db download count를 증가
		
		// 한글 파일의 경우 적용(없으면 파일명이 정상적으로 나오지 않음)
		String filename = URLEncoder.encode(pds.getFilename(), "utf-8");
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + filename + "\"") // 원본파일명
				.contentType(mediaType)
				.contentLength(file.length()).body(is);
	}
}
