package ssg.com.a.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssg.com.a.dao.MemberDao;
import ssg.com.a.dto.MemberDto;
import ssg.com.a.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao dao;

	@Override
	public boolean idcheck(String id) {
		int count = dao.idcheck(id);
		return count > 0 ? true : false;
	}

	@Override
	public boolean addmember(MemberDto dto) {
		int count = dao.addmember(dto);
		return count > 0 ? true : false;
	}

	@Override
	public MemberDto login(MemberDto dto) {
		return dao.login(dto);
	}
}