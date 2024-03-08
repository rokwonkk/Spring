package ssg.com.a.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import ssg.com.a.dto.PdsDto;
import ssg.com.a.service.PdsService;
import util.PdsUtil;

@Controller
public class PdsController {

	@Autowired
	PdsService service;
	
	@GetMapping("pdslist.do")
	public String pdsList(Model model, HttpServletRequest req) {
		System.out.println("PdsController pdsList " + new Date());
		
		List<PdsDto> list = service.pdsList();
		
		model.addAttribute("list", list);
		
		return "pds/pdslist";
	}
	
	@GetMapping("pdswrite.do")
	public String pdsWrite() {
		System.out.println("PdsController pdswrite() " + new Date());
		
		return "pds/pdswrite";
	}
	
	@PostMapping("pdsupload.do")
	public String pdsUpload(PdsDto dto,
								@RequestParam(value = "fileupload", required = false) MultipartFile fileupload,
								HttpServletRequest req) {
		System.out.println("PdsController pdsUpload " + new Date());
		
		//filename 취득
		String filename = fileupload.getOriginalFilename();
		System.out.println("filename:" + filename);
		
		//db에 저장하기 위해서
		dto.setFilename(filename);
		
		/** upload의 경로 **/
		//tomcat(server)
		String fupload = req.getServletContext().getRealPath("/upload");
		
		//폴더(테스트용) - 록원스 다이렉트 폴더 경로
		//String fupload = "/Users/rokwon/Desktop/springframeworksts/springSamples/src/main/webapp/upload";
		System.out.println("파일업로드 경로: " + fupload);
		
		//파일명 변경 abc.txt -> 324234324.txt
		String newfilename = PdsUtil.getNewFileName(filename);
		System.out.println("newfilename:" +newfilename);
		
		//db에 저장하기 위해서
		dto.setNewfilename(newfilename);
		
		//파일을 업로드
		File file = new File(fupload + "/" + newfilename);
		
		try {
			// 실제 파일을 업로드
			FileUtils.writeByteArrayToFile(file, fileupload.getBytes());
			
			//db에 저장
			boolean b = service.pdsupload(dto);
			if(b) {
				System.out.println("파일업로드 성공");
	
				//확장자명 확인
				String extcheck = newfilename.substring(newfilename.indexOf('.'));

				//이미지 파일 일 경우 썸네일 파일 만들어줌.
				if(extcheck.equals(".png") || extcheck.equals(".jpg") || extcheck.equals(".gif") || extcheck.equals(".bmp")) {
					
					File thumbnailFile = new File(fupload + "/s_" + newfilename);
					
					System.out.println("썸네일 경로 및 파일명 : " + thumbnailFile);
					
					//파일 업로드 성공시에 썸네일 파일 생성
					Thumbnails.of(file)
					//.size(width, height)
					.size(120, 120)
					.toFile(thumbnailFile);
				}
			} else {
				
				System.out.println("파일업로드 실패");
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/pdslist.do";
		//return "pds/pdslist";
	}
	
	@GetMapping("pdsdetail.do")
	public String pdsDetail(int seq, Model model) {
		System.out.println("PdsController pdsDetail " + new Date());
		
		service.readCount(seq);
		
		PdsDto dto = service.getPds(seq);
		model.addAttribute("dto", dto);
		
		return "pds/pdsdetail";
	}
	
	@GetMapping("filedownload.do")
	public String filedownload(int seq, String newfilename, String filename, Model model, HttpServletRequest req) {
		System.out.println("PdsController filedownload " + new Date());
		
		//tomcat
		String fupload = req.getServletContext().getRealPath("/upload");
		
		//local
//		String fupload = "Users/Desktop";
		
		//다운로드 받을 파일
		File downloadFile = new File(fupload + "/" + newfilename);
		
		model.addAttribute("downloadFile", downloadFile);
		model.addAttribute("filename", filename);
		model.addAttribute("seq", seq);
		
		//다운로드 카운트 증가
		service.downloadCount(seq);
		
		return "downloadView";
	}
}
