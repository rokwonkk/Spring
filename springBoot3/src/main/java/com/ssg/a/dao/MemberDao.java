package com.ssg.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ssg.a.dto.MemberDto;

@Mapper
@Repository
public interface MemberDao {

	List<MemberDto> allMember();
	
	MemberDto login(MemberDto dto);
	
	//MemberDto addMember(MemberDto dto);
	
}
