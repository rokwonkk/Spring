package ssg.com.a.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ssg.com.a.dto.BbsDto;
import ssg.com.a.dto.BbsParam;
import ssg.com.a.service.BbsService;

@Controller
public class BbsContorller {

	@Autowired
	BbsService service;
	
	@GetMapping("bbslist.do")
	public String bbslist(Model model, BbsParam param) {
		System.out.println("BbcContorller bbslist" + new Date());
		
//		if(param == null || param.getSearch() == null || param.getChoice() == null) {
//			param = new BbsParam("","",0);
//		}
		
		//글 목록
		List<BbsDto> list = service.bbslist(param);
		
		//글 총수
		int count = service.allbbs(param);
		
		//페이지 계산
		int pageBbs = count / 10;
		if((count % 10) > 0) {
			pageBbs = pageBbs + 1;
		}
				
		model.addAttribute("list", list);
		model.addAttribute("pageBbs", pageBbs);
		model.addAttribute("param", param);
		
		return "bbs/bbslist";
	}
	
	@GetMapping("bbswrite.do")
	public String bbswrite() {
		System.out.println("MemberController bbswrite " + new Date());

		return "bbs/bbswrite";
	}
	
	@PostMapping("bbswriteAf.do")
	public String postMethodName(BbsDto dto,Model model) {
		System.out.println("BbcContorller bbsWriteAf" + new Date());
		
		int b = service.writebbs(dto);
		String regiMsg = "MEMBER_YES";
		if(b == 0) {
			regiMsg = "MEMBER_NO";
		}
		
		model.addAttribute("regiMsg", regiMsg);
		
		return "message";
	}
	
}