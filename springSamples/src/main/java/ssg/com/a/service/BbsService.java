package ssg.com.a.service;

import java.util.List;

import ssg.com.a.dto.BbsDto;
import ssg.com.a.dto.BbsParam;

public interface BbsService {

	List<BbsDto> bbslist(BbsParam param);
	
	int allbbs(BbsParam param);
	
	int writebbs(BbsDto dto);
	
}
