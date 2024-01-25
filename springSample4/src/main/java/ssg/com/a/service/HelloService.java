package ssg.com.a.service;

import java.util.List;

import ssg.com.a.dto.MemberDto;

public interface HelloService {

	List<MemberDto> allmember();

	MemberDto getmember(String id);
	
	boolean idcheck(String id);
	
	MemberDto login(MemberDto dto);
}
