package ssg.com.a.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssg.com.a.dao.BbsDao;
import ssg.com.a.dto.BbsComment;
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
	public boolean writebbs(BbsDto dto) {
		return dao.writebbs(dto)>0? true:false;
	}

	@Override
	public BbsDto getbbs(int seq) {
		return dao.getbbs(seq);
	}

	@Override
	public boolean bbsupdate(BbsDto dto) {
		int count = dao.bbsupdate(dto);
		return count > 0 ? true : false;
	}
	
	@Override
	public boolean bbsdelete(int seq) {
		int count = dao.bbsdelete(seq);
		return count > 0 ? true : false;
	}
	
	@Override
	public boolean answerInsert(BbsDto dto) {
		int count = dao.answerInsert(dto);
		return count > 0 ? true : false;
	}
	
	@Override
	public void readcount(int seq) {
		dao.readcount(seq);
	}

	@Override
	public boolean commentWrite(BbsComment dto) {
		int count = dao.commentWrite(dto);
		return count > 0 ? true : false;
	}

	@Override
	public List<BbsComment> commentlist(int seq) {
		return dao.commentlist(seq);
	}
}
