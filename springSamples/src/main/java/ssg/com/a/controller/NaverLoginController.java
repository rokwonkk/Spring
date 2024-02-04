package ssg.com.a.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NaverLoginController {
	@RequestMapping(value="naverlogin.do", method = RequestMethod.GET)
	public String naverLogin() {
		System.out.println("NaverLoginController naverLogin " + new Date());
		
		return "apinaverlogin";
	}
	
    @RequestMapping(value="naverloginaf.do", method=RequestMethod.GET)
    public String loginPOSTNaver(HttpSession session) {
		System.out.println("NaverLoginController naverLogin " + new Date());
		
        return "callback";
    }
}
