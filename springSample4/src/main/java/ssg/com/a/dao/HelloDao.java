package ssg.com.a.dao;

import java.util.List;

import ssg.com.a.dto.MemberDto;

public interface HelloDao {

	List<MemberDto> allmember();
	
	MemberDto getmember(String id);
	
	int idcheck(String id);
	
	MemberDto login(MemberDto dto);
}
