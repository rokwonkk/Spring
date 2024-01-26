package ssg.com.a.service;

import ssg.com.a.dto.MemberDto;

public interface MemberService {

	boolean idcheck(String id);
	
	boolean addmember(MemberDto dto);
	
	MemberDto login(MemberDto dto);
}