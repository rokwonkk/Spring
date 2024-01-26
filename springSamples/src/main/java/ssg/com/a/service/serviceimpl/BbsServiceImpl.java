package ssg.com.a.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssg.com.a.dao.BbsDao;
import ssg.com.a.dto.BbsDto;
import ssg.com.a.dto.BbsParam;
import ssg.com.a.service.BbsService;

@Service
public class BbsServiceImpl implements BbsService {

	@Autowired
	BbsDao dao;

	@Override
	public List<BbsDto> bbslist(BbsParam param) {
		return dao.bbslist(param);
	}

	@Override
	public int allbbs(BbsParam param) {
		return dao.allbbs(param);
	}

	@Override
	public int writebbs(BbsDto dto) {
		return dao.writebbs(dto);
	}
}
