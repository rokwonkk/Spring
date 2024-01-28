package ssg.com.a.dao;

import java.util.List;

import ssg.com.a.dto.BbsDto;
import ssg.com.a.dto.BbsParam;

public interface BbsDao {
	
	List<BbsDto> bbslist(BbsParam param);
	
	int allbbs(BbsParam param);
	
	int writebbs(BbsDto dto);
	
	BbsDto getbbs(int seq);
	
	int bbsupdate(BbsDto dto);
	
	int bbsdelete(int seq);
	
	int answerInsert(BbsDto dto);
	
	void readcount(int seq);
}
