package ssg.com.a.service.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssg.com.a.dto.MemberDto;
import ssg.com.a.dao.HelloDao;
import ssg.com.a.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {

	// @Autowired => HelloDao dao = new HelloDaoImpl();
	@Autowired
	HelloDao dao;

	@Override
	public List<MemberDto> allmember() {
		System.out.println("HelloServiceImpl allmember " + new Date());

		return dao.allmember();
	}

	@Override
	public MemberDto getmember(String id) {
		return dao.getmember(id);
	}

	@Override
	public boolean idcheck(String id) {
		int count = dao.idcheck(id);
		return count > 0 ? true : false;
	}

	@Override
	public MemberDto login(MemberDto dto) {
		return dao.login(dto);
	}
}
