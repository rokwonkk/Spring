<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
            charset="utf-8"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
<script type="text/javascript">
    var naver_id_login = new naver_id_login("yoXKfaZjwzL4vDVoP2_S", "http://localhost:8080/springSamples/naverloginaf.do");
    // 접근 토큰 값 출력
    //alert(naver_id_login.oauthParams.access_token);
    // 네이버 사용자 프로필 조회
    naver_id_login.get_naver_userprofile("naverSignInCallback()");

    // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
    function naverSignInCallback() {
    	
    	var email = naver_id_login.getProfileData('email');
    	var name = naver_id_login.getProfileData('name');
        //alert(naver_id_login.getProfileData('email'));
        //alert(naver_id_login.getProfileData('name'));
        
        alert("환영합니다 "+ name + "님 접속하신 이메일은 " + email + "입니다");
       location.href = "bbslist.do";
    }
</script>
</body>
</html>