package ssg.com.a.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ssg.com.a.dto.MemberDto;
import ssg.com.a.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login() {
		System.out.println("MemberController login " + new Date());
		
		return "member/login";
	}
	
	@GetMapping("regi.do")
	public String regi() {
		System.out.println("MemberController regi " + new Date());

		return "member/regi";
	}
	
	@ResponseBody
	@RequestMapping(value = "idcheck.do", method = RequestMethod.GET, 
											produces = "application/String; charset=utf-8") // 문자열을 리턴할 때만 필요하다
	public String idcheck(String id) {
		System.out.println("HelloController idcheck " + new Date());
		System.out.println("id : " + id);
		
		boolean b = service.idcheck(id);
//		String r = "YES";
//		if(b) {
//			r = "NO";
//		}
//		return r;
		if(b) {
			return "NO";
		}
		return "YES";
	}
	
	@PostMapping("regiAf.do")
	public String regiAf(MemberDto dto, Model model) {
		System.out.println("HelloController regiAf " + new Date());
		System.out.println(dto.toString());
		
		boolean b = service.addmember(dto);
		String regiMsg = "MEMBER_YES";
		if(!b) {
			regiMsg = "MEMBER_NO";
		}
		
		model.addAttribute("regiMsg", regiMsg);
		
		return "message";
	}
	
	@PostMapping("loginAf.do")
	public String login(MemberDto dto, Model model, HttpServletRequest req) {
		System.out.println("HelloController loginAf " + new Date());

		MemberDto login = service.login(dto);
		String loginMsg = "LOGIN_FAIL";
		if(login != null) {											//로그인 성공
			req.getSession().setAttribute("login", login);			//로그인한 정보 세션에 저장
//			req.getSession().setMaxInactiveInterval(60 * 60 * 24);
			loginMsg = "LOGIN_SUCCESS";	
		}

		model.addAttribute("loginMsg", loginMsg);
		return "message";
	}
}
