package ssg.com.a.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ssg.com.a.dto.HumanDto;

@RestController // @Controller + @ResponseBody -> Restful
public class HelloController {

	@GetMapping("idcheck")
	public String idcheck(String id){
		System.out.println("HelloController hello() " + new Date());
		System.out.println("id : " + id);
		return "예";
	}	
	
	@GetMapping("/")
	public String hello() {
		System.out.println("HelloController hello() " + new Date());
		return "안녕하세요";
	}
	
	@GetMapping("test")
	public int test() {
		System.out.println("HelloController test() " + new Date());
		
		return 1024;
	}
	
	@GetMapping("human")
	public HumanDto human() {
		System.out.println("HelloController human() " + new Date());
		
		HumanDto dto = new HumanDto("aaa", "홍길동", 123.2);
		return dto;
	}
	
	@GetMapping("conn_param")
	public String conn_param(int number, String name) {
		System.out.println("HelloController conn_param() " + new Date());
		
		System.out.println("number : " + number);
		System.out.println("name : " + name);
		
		return "success";
	}
	
	@GetMapping("param_obj")
	public String param_obj(HumanDto dto) {
		System.out.println("HelloController param_obj() " + new Date());
		
		System.out.println(dto.toString());
		return "성공!";
	}
	
	@PostMapping("getlist")
	public List<HumanDto> getlist(HumanDto dto){
		System.out.println("HelloController getlist() " + new Date());
		System.out.println(dto.toString());
		
		List<HumanDto> list = new ArrayList<HumanDto>();
		list.add(new HumanDto("aaa", "안녕", 111.11));
		list.add(new HumanDto("bbb", "안녕하세요", 122.22));
		list.add(new HumanDto("ccc", "안녕하십니까?", 133.33));
		
		return list;
	}
	
	@GetMapping("getmap")
	public Map<String, Object> getmap(){
		System.out.println("HelloController getmap() " + new Date());
		
		String title = "나는 반드시 성공 한다.";
				
		List<HumanDto> list = new ArrayList<HumanDto>();
		list.add(new HumanDto("aaa", "안녕", 111.11));
		list.add(new HumanDto("bbb", "안녕하세요", 122.22));
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("title", title);
		map.put("list", list);
		
		return map;
	}
}