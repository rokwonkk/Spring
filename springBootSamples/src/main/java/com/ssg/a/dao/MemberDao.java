package com.ssg.a.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ssg.a.dto.MemberDto;

@Mapper
@Repository
public interface MemberDao {

	int idcheck(String id);
	
	int addMember(MemberDto dto);
	
	MemberDto login(MemberDto dto);
}
