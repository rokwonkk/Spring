package ssg.com.a.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssg.com.a.dao.HelloDao;
import ssg.com.a.dto.MemberDto;

@Repository
public class HelloDaoImpl implements HelloDao{
		//seesion하고 연결해줌.
		@Autowired
		SqlSession session;
		
		@Override
		public List<MemberDto> allmember() {
			System.out.println("HelloDaoImpl allmember " + new Date());
			
			List<MemberDto> list = session.selectList("Hello.allmember");
			return list;
		}

		@Override
		public MemberDto getmember(String id) {
			MemberDto dto = session.selectOne("Hello.getmember", id);
			return dto;
		}

		@Override
		public int idcheck(String id) {
			return session.selectOne("Hello.idcheck", id);
		}

		@Override
		public MemberDto login(MemberDto dto) {
			MemberDto mem = session.selectOne("Hello.login", dto);
			return mem;
		}
}
