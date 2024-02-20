package com.ssg.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssg.a.dao.MemberDao;
import com.ssg.a.dto.MemberDto;

@Service
@Transactional
public class MemberService {

	@Autowired
	MemberDao dao;
	
	public List<MemberDto> allMember(){
		return dao.allMember();
	}

	public MemberDto login(MemberDto dto) {
		return dao.login(dto);
	}
	
}