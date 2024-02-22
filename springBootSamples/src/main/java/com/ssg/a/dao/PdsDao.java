package com.ssg.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ssg.a.dto.BbsParam;
import com.ssg.a.dto.PdsDto;

@Mapper
@Repository
public interface PdsDao {

	int getAllPds(BbsParam param);
	
	List<PdsDto> pdsList(BbsParam param);
	
	PdsDto getPds(PdsDto dto);
}
