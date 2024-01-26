package ssg.com.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssg.com.a.dao.BbsDao;
import ssg.com.a.dto.BbsDto;
import ssg.com.a.dto.BbsParam;

@Repository
public class BbsDaoImpl implements BbsDao {

	@Autowired
	SqlSession session;

	String ns = "Bbs.";

	@Override
	public List<BbsDto> bbslist(BbsParam param) {
		return session.selectList(ns + "bbslist", param);
	}

	@Override
	public int allbbs(BbsParam param) {
		return session.selectOne(ns + "allbbs", param);
	}

	@Override
	public int writebbs(BbsDto dto) {
		return session.insert(ns + "bbswrite", dto);
	}

	@Override
	public BbsDto getbbs(int seq) {
		return session.selectOne(ns + "getbbs", seq);
	}

	@Override
	public int bbsupdate(BbsDto dto) {
		return session.update(ns + "bbsupdate", dto);
	}

	@Override
	public int bbsdelete(int seq) {
		return session.update(ns + "bbsdelete", seq);
	}
	
	@Override
	public void readcount(int seq) {
		session.update(ns + "readcount", seq);
	}
}
