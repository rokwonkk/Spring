package ssg.com.a.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
		String r = "YES";
		if(b) {
			r = "NO";
		}
		return r;
	}
}
