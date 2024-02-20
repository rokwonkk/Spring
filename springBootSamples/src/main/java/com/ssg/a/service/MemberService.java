package com.ssg.a.service;

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
	
	public int idcheck(String id) {
		
		//boolean으로 리턴할때.
		//return dao.idcheck(id)>0?true:false;
		return dao.idcheck(id);
	}
	
	public boolean addMember(MemberDto dto) {
		return dao.addMember(dto) > 0 ? true : false;
	}
	
	public MemberDto login(MemberDto dto) {
		return dao.login(dto);
	}
}