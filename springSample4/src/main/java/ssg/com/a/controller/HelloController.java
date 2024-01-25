package ssg.com.a.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ssg.com.a.dto.MemberDto;
import ssg.com.a.service.HelloService;



@Controller
public class HelloController {

	@Autowired
	HelloService service;
	
	@RequestMapping(value = "hello.do", method = RequestMethod.GET)
	public String hello(Model model) {
		System.out.println("HelloController hello " + new Date());
		
		List<MemberDto> list = service.allmember();
		model.addAttribute("list", list);
		
		return "hello";
	}
	
	@GetMapping("findmember.do")
	public String findmember(String id, Model model) {
		System.out.println("HelloController findmember " + new Date());

		MemberDto dto = service.getmember(id);
		model.addAttribute("dto", dto);
		
		return "hello";
	}
	
	//ajax 사용할때 responsebody 애노테이션 붙여줘야함.
	//ResponseBody 데이터를 가지고 가라. 위에 링크 - 링크와는 다름.
//	@ResponseBody
//	@RequestMapping(value = "idcheck.do", method = RequestMethod.GET, 
//											produces = "application/String; charset=utf-8") // 문자열을 리턴할 때만 필요하다
//	public String idcheck(String id) {
//		System.out.println("HelloController idcheck " + new Date());
//		System.out.println("id : " + id);
//		
//		return "오케이";
//	}
	
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
	
	@ResponseBody
	@PostMapping("login.do")
	public MemberDto login(MemberDto dto) {
		System.out.println("HelloController login " + new Date());
		System.out.println(dto.toString());
		
		MemberDto mem = service.login(dto);
		return mem;
	}
	
	@ResponseBody
	@GetMapping("getmap.do")
	public Map<String, Object> getmap(){
		System.out.println("HelloController getmap " + new Date());
		
		List<MemberDto> list = service.allmember();
		String message = "나는 성공할 것이다";
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("message", message);
		
		return map;
	}
}
