package com.ssg.a.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssg.a.dto.BbsDto;
import com.ssg.a.dto.BbsParam;
import com.ssg.a.service.BbsService;

@RestController
public class BbsController {

	@Autowired
	BbsService service;
	
	@GetMapping("bbslist")
	public Map<String, Object> bbsList(BbsParam param) {
		System.out.println("BbsController bbsList() " + new Date());
		
		//글목록
		List<BbsDto> list = service.bbsList(param);
		
		//글의 총 갯수
		int count = service.getAllBbs(param);
		int pageBbs = count / 10;
		if ((count % 10) > 0) {
			pageBbs = pageBbs + 1;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("pageBbs", pageBbs);
		
		return map;
	}
	
	@PostMapping("bbswrite")
	public String bbsWrite(BbsDto dto) {
		System.out.println("BbsController bbsWrite() " + new Date());

		boolean b = service.bbsWrite(dto);
		
		if(!b) {
			return "fail";
		}
		return "success";
	}
	
	@GetMapping("bbsdetail")
	public BbsDto bbsDetail(
			@RequestParam int seq) {
		System.out.println("BbsController bbsDetail() " + new Date());
		
		return service.getBbs(seq);
	}
	
	@PostMapping("bbsupdate")
	public String bbsUpdate(BbsDto dto) {
		System.out.println("BbsController bbsUpdate() " + new Date());
		
		boolean b = service.bbsUpdate(dto);
		
		if(!b) {
			return "fail";
		}
		return "success";
	}
	
	@GetMapping("bbsdelete")
	public String bbsDelete(
			@RequestParam int seq) {
		System.out.println("BbsController bbsDelete() " + new Date());
		
		boolean b = service.bbsDelete(seq);
		
		if(!b) {
			return "fail";
		}
		return "success";
	}
}
