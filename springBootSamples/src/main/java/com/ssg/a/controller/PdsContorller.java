package com.ssg.a.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssg.a.dto.BbsParam;
import com.ssg.a.dto.PdsDto;
import com.ssg.a.service.PdsService;

@RestController
public class PdsContorller {

	@Autowired
	PdsService service;
	
	@GetMapping("pdslist")
	public Map<String, Object> pdsList(BbsParam param) {
		System.out.println("PdsContorller pdsList() " + new Date());
		
		//글목록
		List<PdsDto> list = service.pdsList(param);
		
		//글의 총 갯수
		int count = service.getAllPds(param);
		int pagePds = count / 10;
		if ((count % 10) > 0) {
			pagePds = pagePds + 1;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("pagePds", pagePds);
		
		return map;
	}
	
	@GetMapping("pdsdetail")
	public PdsDto pdsDetail(PdsDto dto) {
		System.out.println("PdsContorller pdsDetail() " + new Date());
		
		return service.getPds(dto);
	}
}
