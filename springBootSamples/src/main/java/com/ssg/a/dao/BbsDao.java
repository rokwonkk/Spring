package com.ssg.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ssg.a.dto.BbsComment;
import com.ssg.a.dto.BbsDto;
import com.ssg.a.dto.BbsParam;
import com.ssg.a.dto.ReadCountDto;

@Mapper
@Repository
public interface BbsDao {

	List<BbsDto> bbsList(BbsParam param);
	int getAllBbs(BbsParam param);
	BbsDto getBbs(int seq);
	int bbsWrite(BbsDto dto);
	int bbsUpdate(BbsDto dto);
	int bbsDelete(int seq);
	
	void readCount(int seq);
	int getReadCountId(ReadCountDto dto);
	int insertCheckReadCountId(ReadCountDto dto);
	
	int writeAnswer(BbsDto dto);
	
	List<BbsComment> commentList(BbsComment com);
	int commentWrite(BbsComment com);
}