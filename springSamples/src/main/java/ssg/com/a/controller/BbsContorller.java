package ssg.com.a.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	public String bbswriteAf(BbsDto dto, Model model) {
		System.out.println("BbcContorller bbsWriteAf " + new Date());
		
		int b = service.writebbs(dto);
		String bbswriteMsg = "BBSWRITE_SUCCESS";
		if(b == 0) {
			bbswriteMsg = "BBSWRITE_FAIL";
		}
		
		model.addAttribute("bbswriteMsg", bbswriteMsg);
		
		return "message";
	}
	
	@GetMapping("bbsdetail.do")
	public String bbsDetail(HttpServletRequest req, Model model) {
		System.out.println("BbcContorller bbsDetail " + new Date());
		
		int seq = Integer.parseInt(req.getParameter("seq"));
		
		//접속한 이력을 조사 !참고!
        //조회수 증가
        service.readcount(seq);
		
		BbsDto dto = service.getbbs(seq);
		
		model.addAttribute("dto", dto);
		
		return "bbs/bbsdetail";
	}
	
	@GetMapping("bbsupdate.do")
	public String bbsUpdate(HttpServletRequest req, Model model) {
		System.out.println("BbcContorller bbsUpdate " + new Date());

		int seq = Integer.parseInt(req.getParameter("seq"));
		
		BbsDto dto = service.getbbs(seq);
		
		model.addAttribute("dto", dto);
		
		return "bbs/bbsupdate";
	}
	
	@PostMapping("bbsupdateAf.do")
	public String bbsUpdateAf(HttpServletRequest req, Model model) {
		
		int seq = Integer.parseInt(req.getParameter("seq"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		BbsDto updateDto = new BbsDto(seq, title, content);
		
		boolean b = service.bbsupdate(updateDto);
		
		String bbsupdateMsg = "UPDATE_SUCCESS";
        if (!b) {
        	bbsupdateMsg = "UPDATE_FAIL";
        }
		
        model.addAttribute("bbsupdateMsg", bbsupdateMsg);
        
		return "message";
	}
	
	//데이터 수정이라 PostMapping을 사용하려 했으나. 폼이 아니라서 405애러를 봄. 처음봄.. ㅋㅋ POST로 보낸게 아니라서 GET으로만 되나봄.
	@GetMapping("bbsdelete.do")
	public String bbsDelete(HttpServletRequest req, Model model) {
		int seq = Integer.parseInt(req.getParameter("seq"));

		boolean b = service.bbsdelete(seq);
		
        String bbsdeleteMsg = "DELETE_SUCCESS";
        if (!b) {
        	bbsdeleteMsg = "DELETE_FAIL";
        }
		
        model.addAttribute("bbsdeleteMsg", bbsdeleteMsg);
        
		return "message";
	}
}