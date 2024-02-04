package ssg.com.a.service;

import java.util.List;

import ssg.com.a.dto.PdsDto;

public interface PdsService {

	List<PdsDto> pdsList();
	
	boolean pdsupload(PdsDto dto);
	
	PdsDto getPds(int seq);
	
	void downloadCount(int seq);
	
	void readCount(int seq);
}
