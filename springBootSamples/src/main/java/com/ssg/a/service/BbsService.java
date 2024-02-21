package com.ssg.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssg.a.dao.BbsDao;
import com.ssg.a.dto.BbsDto;
import com.ssg.a.dto.BbsParam;

@Service
@Transactional
public class BbsService {

	@Autowired
	BbsDao dao;
	
	public List<BbsDto> bbsList(BbsParam param) {
		return dao.bbsList(param);
	}
	
	public int getAllBbs(BbsParam param) {
		return dao.getAllBbs(param);
	}
	
	public boolean bbsWrite(BbsDto dto) {
		return dao.bbsWrite(dto) > 0 ? true:false;
	}
	
	public BbsDto getBbs(int seq) {
		return dao.getBbs(seq);
	}
	
	public boolean bbsUpdate(BbsDto dto) {
		return dao.bbsUpdate(dto) > 0 ? true:false;
	}
	
	public boolean bbsDelete(int seq) {
		return dao.bbsDelete(seq) > 0 ? true :false;
	}
}
