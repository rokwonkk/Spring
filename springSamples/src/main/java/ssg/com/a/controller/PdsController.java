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

import ssg.com.a.dto.PdsDto;
import ssg.com.a.service.PdsService;
import util.PdsUtil;

@Controller
public class PdsController {

	@Autowired
	PdsService service;
	
	@GetMapping("pdslist.do")
	public String pdsList(Model model) {
		System.out.println("PdsController pdsList " + new Date());
		
		List<PdsDto> list = service.pdsList();
		model.addAttribute("list", list);
		
		return "pds/pdslist";
	}
	
	@GetMapping("pdswrite.do")
	public String pdsWrite() {
		System.out.println("PdsController pdswrite " + new Date());
		
		return "pds/pdswrite";
	}
	
	@PostMapping("pdsupload.do")
	public String pdsUpload(PdsDto dto, 
								@RequestParam(value = "fileupload", required = false)
								MultipartFile fileupload,
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
		
		//폴더(테스트용)
//		String fupload = "/Users/rokwon/Desktop";
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
			} else {
				System.out.println("파일업로드 실패");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/pdslist.do";
	}
	
	@GetMapping("pdsdetail.do")
	public String pdsDetail(int seq, Model model) {
		System.out.println("PdsController pdsDetail " + new Date());
		
		PdsDto dto = service.getPds(seq);
		model.addAttribute("dto", dto);
		
		return "pds/pdsdetail";
	}
	
	@GetMapping("filedownload.do")
	public String filedownload(int seq, String newfilename, String filename, Model model, HttpServletRequest req) {
		System.out.println("PdsController filedownload " + new Date());
		
		//경로 
		//tomcat
		String fupload = req.getServletContext().getRealPath("/upload");
		
		//local
//		String fupload = "Users/Desktop";
		
		//다운로드 받을 파일
		File downloadFile = new File(fupload + "/" + newfilename);
		
		model.addAttribute("downloadFile", downloadFile);
		model.addAttribute("filename", filename);
		model.addAttribute("seq", seq);
		
		return "downloadView";
	}
}
