package ssg.com.a.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.scribejava.core.model.OAuth2AccessToken;

import ssg.com.a.dto.MemberDto;
import ssg.com.a.naverlogin.NaverLoginBo;
import ssg.com.a.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
	/* NaverLoginBO */
	private NaverLoginBo naverLoginBo;
	private String apiResult = null;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBo naverLoginBo) {
		this.naverLoginBo = naverLoginBo;
	}
	
	@RequestMapping(value = "login.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model, HttpSession session) {
		System.out.println("MemberController login " + new Date());
		
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl 메소드 호출 */
		String naverAuthUrl =  naverLoginBo.getAuthorizationUrl(session);
		
		/* 인증요청문 확인 */
		System.out.println("네이버:" + naverAuthUrl);
		
		/* 객체 바인딩 */
		model.addAttribute("url", naverAuthUrl);
		
		return "member/login";
	}
	
	@GetMapping("regi.do")
	public String regi() {
		System.out.println("MemberController regi " + new Date());

		return "member/regi";
	}
	
	@ResponseBody
	@RequestMapping(value = "idcheck.do", method = RequestMethod.GET, 
											produces = "application/String; charset=utf-8") // 문자열을 리턴할 때만 필요하다
	public String idcheck(String id) {
		System.out.println("HelloController idcheck " + new Date());
		System.out.println("id : " + id);
		
		boolean b = service.idcheck(id);
//		String r = "YES";
//		if(b) {
//			r = "NO";
//		}
//		return r;
		if(b) {
			return "NO";
		}
		return "YES";
	}
	
	@PostMapping("regiAf.do")
	public String regiAf(MemberDto dto, Model model) {
		System.out.println("HelloController regiAf " + new Date());
		System.out.println(dto.toString());
		
		boolean b = service.addmember(dto);
		String regiMsg = "MEMBER_YES";
		if(!b) {
			regiMsg = "MEMBER_NO";
		}
		
		model.addAttribute("regiMsg", regiMsg);
		
		return "message";
	}
	
	@PostMapping("loginAf.do")
	public String login(MemberDto dto, Model model, HttpServletRequest req) {
		System.out.println("HelloController loginAf " + new Date());

		MemberDto login = service.login(dto);
		String loginMsg = "LOGIN_FAIL";
		if(login != null) {											//로그인 성공
			req.getSession().setAttribute("login", login);			//로그인한 정보 세션에 저장
//			req.getSession().setMaxInactiveInterval(60 * 60 * 24);
			loginMsg = "LOGIN_SUCCESS";	
		}

		model.addAttribute("loginMsg", loginMsg);
		return "message";
	}
	
	//네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "naverloginAf.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String callBackNaver(
			@RequestParam String code, 
			@RequestParam String state, 
			Model model, 
			HttpSession session,
			HttpServletRequest req) throws Exception {
		System.out.println("로그인 성공 콜백");
		OAuth2AccessToken oauthToken;
		
		oauthToken = naverLoginBo.getAccessToken(session, code, state);
		//로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBo.getUserProfile(oauthToken);
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj;
		
		jsonObj = (JSONObject) jsonParser.parse(apiResult);
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
	
		/* json형식으로 넘어오는 내 프로필 확인 */
		System.out.println(response_obj);
		
		//프로필 조회
		//유니크아이디
		String id = (String) response_obj.get("id");
		//이메일
		String email = (String) response_obj.get("email");
		//이름
		String name = (String) response_obj.get("name");
		//성별
		String gender = (String) response_obj.get("gender");
		//생일
		String birthyear = (String) response_obj.get("birthyear");
		String birthday = (String) response_obj.get("birthday");
		
		String birthdayFull = birthyear + " " + birthday;
		//닉네임
		String nickname = (String) response_obj.get("nickname");
		//폰번호
		String mobile = (String) response_obj.get("mobile");
		//세션에 사용자 정보 등록
		//session.setAttribute("islogin_r", "Y");
		session.setAttribute("signIn", apiResult);
		session.setAttribute("email", email);
		session.setAttribute("id", id);
		session.setAttribute("name", name);
		session.setAttribute("gender", gender);
		session.setAttribute("birthdayFull", birthdayFull);
		session.setAttribute("nickname", nickname);
		session.setAttribute("mobile", mobile);

//		MemberDto findNaverId = service.naverLogin(email);
//		if(findNaverId != null) {
//			System.out.println("찾았다.");
//		} else {
//			System.out.println("못찾았다.");
//		}
		
		MemberDto login = service.naverLogin(email);
		String loginNaverMsg = "NAVER_LOGIN_FAIL";
		if(login != null) {											//로그인 성공
			req.getSession().setAttribute("login", login);			//로그인한 정보 세션에 저장
//			req.getSession().setMaxInactiveInterval(60 * 60 * 24);
			loginNaverMsg = "NAVER_LOGIN_SUCCESS";	
		} else {
			//여기서 네이버 메일로 가입한 회원이 없을 때 
			//회원가입이나 로직 추가 해야하는 부분
		}

		model.addAttribute("loginNaverMsg", loginNaverMsg);
		return "message";
		
		
//		insert into
//	    sns_info(id, sns_id, sns_type, sns_name, sns_profile,
//	    sns_connect_date)
//	    values (#{id}, #{snsId}, #{snsType}, #{snsName}, #{snsProfile}, now() )
		
		
		/* 네이버 로그인 성공 페이지 View 호출 */
		//return "member/naverlogin";
	}
}
