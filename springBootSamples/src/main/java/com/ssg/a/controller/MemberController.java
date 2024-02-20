package com.ssg.a.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssg.a.dto.MemberDto;
import com.ssg.a.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	MemberService service;

	@GetMapping("test")
	public String test() {
		System.out.println("MemberController test() " + new Date());
		
		return "OK";
	}
	
	@PostMapping("idcheck")
	public String idcheck(String id) {
		System.out.println("MemberController idcheck() " + new Date());
		
		int idcheck = service.idcheck(id);
		
		if(idcheck == 0) {
			return "yes";
		}

		return "no";	
	}
	
	@PostMapping("addmember")
	public String addMember(MemberDto dto) {
		System.out.println("MemberController addMember() " + new Date());
		
		boolean b = service.addMember(dto);
		if(b) {
			return "addsuccess";
		} else {
			return "addfail";
		}
	}
	
	@PostMapping("login")
	public MemberDto login(MemberDto dto) {
		System.out.println("MemberController login() " + new Date());

		return service.login(dto);
	}
}
