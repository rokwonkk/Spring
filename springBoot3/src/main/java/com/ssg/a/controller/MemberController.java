package com.ssg.a.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssg.a.dto.MemberDto;
import com.ssg.a.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	MemberService service;
	
	@GetMapping("allmember")
	public List<MemberDto> allMember(){
		System.out.println("MemberController allMember() " + new Date());
		return service.allMember();
	}
	
	@PostMapping("login")
	public MemberDto login(MemberDto dto) {
		System.out.println("MemberController login() " + new Date());
		
		return service.login(dto);
	}
}
