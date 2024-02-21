package com.ssg.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ssg.a.dto.BbsDto;
import com.ssg.a.dto.BbsParam;

@Mapper
@Repository
public interface BbsDao {

	List<BbsDto> bbsList(BbsParam param);

	int getAllBbs(BbsParam param);
	
	int bbsWrite(BbsDto dto);
	
	BbsDto getBbs(int seq);
	
	int bbsUpdate(BbsDto dto);
	
	int bbsDelete(int seq);
}