package ssg.com.a.dao;

import java.util.List;

import ssg.com.a.dto.PdsDto;

public interface PdsDao {
	
	List<PdsDto> pdsList();
	
	int pdsupload(PdsDto dto);
	
	PdsDto getPds(int seq);
	
	int downloadCount(int seq);
	
	int readCount(int seq);
}
