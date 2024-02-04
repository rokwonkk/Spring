package ssg.com.a.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssg.com.a.dao.PdsDao;
import ssg.com.a.dto.PdsDto;

@Repository
public class PdsDaoImpl implements PdsDao {

	@Autowired
	SqlSessionTemplate session;
	
	String ns = "Pds.";

	@Override
	public List<PdsDto> pdsList() {
		return session.selectList(ns + "pdslist");
	}

	@Override
	public int pdsupload(PdsDto dto) {
		return session.insert(ns + "pdsupload", dto);
	}

	@Override
	public PdsDto getPds(int seq) {
		return session.selectOne(ns + "getpds", seq);
	}

	@Override
	public int downloadCount(int seq) {
		return session.update(ns + "downloadcount", seq);
	}

	@Override
	public int readCount(int seq) {
		return session.update(ns + "readcount", seq);
	}
	
}
