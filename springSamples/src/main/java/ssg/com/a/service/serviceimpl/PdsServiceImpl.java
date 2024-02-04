package ssg.com.a.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssg.com.a.dao.PdsDao;
import ssg.com.a.dto.PdsDto;
import ssg.com.a.service.PdsService;

@Service
public class PdsServiceImpl implements PdsService {

	@Autowired
	PdsDao dao;

	@Override
	public List<PdsDto> pdsList() {
		return dao.pdsList();
	}

	@Override
	public boolean pdsupload(PdsDto dto) {
		int count = dao.pdsupload(dto);
		return count > 0 ? true : false;
	}

	@Override
	public PdsDto getPds(int seq) {
		return dao.getPds(seq);
	}

	@Override
	public void downloadCount(int seq) {
		dao.downloadCount(seq);
	}

	@Override
	public void readCount(int seq) {
		dao.readCount(seq);
	}
}
