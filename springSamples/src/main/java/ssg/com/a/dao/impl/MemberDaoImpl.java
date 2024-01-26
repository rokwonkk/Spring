package ssg.com.a.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssg.com.a.dao.MemberDao;
import ssg.com.a.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	SqlSession session;
	
	String ns = "Member.";
	
	@Override
	public int idcheck(String id) {
		return session.selectOne(ns + "idcheck", id);
	}

	@Override
	public int addmember(MemberDto dto) {
		return session.insert(ns + "addmember", dto);
	}

	@Override
	public MemberDto login(MemberDto dto) {
		return session.selectOne(ns + "login", dto);
	}
}
