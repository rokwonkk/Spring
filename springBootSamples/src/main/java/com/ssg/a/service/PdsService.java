package com.ssg.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssg.a.dao.PdsDao;
import com.ssg.a.dto.BbsParam;
import com.ssg.a.dto.PdsDto;

@Service
@Transactional
public class PdsService {

	@Autowired
	PdsDao dao;
	
	public List<PdsDto> pdsList(BbsParam param){
		return dao.pdsList(param);
	}
	
	public int getAllPds(BbsParam param) {
		return dao.getAllPds(param);
	}
	
	public PdsDto getPds(PdsDto dto) {
		return dao.getPds(dto);
	}
	
	public boolean writePds(PdsDto dto) {
		return dao.writePds(dto) > 0 ? true : false;
	}
}
