package ssg.com.a;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ssg.com.a.dto.StudentDto;

@Controller
public class WorldController {

    @RequestMapping("/world")
    public String world() {
        System.out.println("WorldController world() " + new Date());
        return "Hello";
    }

    @RequestMapping(value = "home.do", method = RequestMethod.GET)
    public String home(Model model) {
        System.out.println("WorldController home() " + new Date());

        String name = "성춘향";

        // request.setAttribute("name", name);
        model.addAttribute("name", name);    // 짐싸!

        return "home";    // home.jsp로 잘가
    }

    @RequestMapping(value = "info.do", method = RequestMethod.POST)
    public String info(String name, int age) {
        System.out.println("WorldController info() " + new Date());

        System.out.println("name:" + name);
        System.out.println("age:" + age);

        return "home";
    }

    @RequestMapping(value = "student.do", method = RequestMethod.GET)
    public String student(StudentDto stu) {
        System.out.println("WorldController student() " + new Date());
        System.out.println(stu.toString());

        return "home";
    }

	/*
		message : front에서 받아서 출력
		student 3명의 데이터를 보내서 테이블로 출력
	*/

    @RequestMapping(value = "list.do", method = RequestMethod.GET)
    public String list(String message, Model model) {
        System.out.println("WorldController list() " + new Date());

        System.out.println("message:" + message);

        List<StudentDto> list = new ArrayList<>();
        list.add(new StudentDto("홍길동", 24, 172.1));
        list.add(new StudentDto("성춘향", 16, 156.3));
        list.add(new StudentDto("일지매", 22, 181.4));

        model.addAttribute("list", list);

        return "home";
    }
}