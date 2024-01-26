<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>
  	.center {
  		margin: auto;
  		width: 400px;
  		border: 1px solid #a1a1a1;
  		padding: 10px;
  		border-radius: 30px
  	}
  </style>
</head>
<body>

<h2>회원가입</h2>

<div class="center">

<form action="regiAf.do" id="frm" method="post">
<br/>
<table class="table">
<tr>
	<th>아이디</th>
	<td>
		<input type="text" class="form-control" name="id" id="id" size="20">
		<p id="idcheck" style="font-size: 16px"></p>
		<input type="button" class="btn btn-primary" id="id_chk_btn" value="id확인">
	</td>
</tr>
<tr>
	<th>패스워드</th>
	<td>
		<input type="text" class="form-control" name="pw" size="20">
	</td>
</tr>
<tr>
	<th>이름</th>
	<td>
		<input type="text" class="form-control" name="name" size="20">
	</td>
</tr>
<tr>
	<th>이메일</th>
	<td>
		<input type="text" class="form-control" name="email" size="20">
	</td>
</tr>
<tr>
	<td colspan="2">
		<div align="center">
			<input type="button" id="regibtn" class="btn btn-primary" value="회원가입">
		</div>
	</td>
</tr>
</table>
</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#id_chk_btn').click(function(){
		
		//빈칸검사
		if($("#id").val() === ""){
			alert("아이디를 기입해주세요.");
			return;
		}

		// id 글자의 갯수 > ? 
				
		// id 규칙 : 대소문자 포함 + 특수기호 로그인API - ( 카카오API, 구글API, 네이버API )
				
		// id 가 사용중? 
		$.ajax({
			url : "./idcheck.do",
			type : "get",
			data : { 
				"id" : $("#id").val()
			},
			success:function(str){
				// alert("success");
				if(str === "YES"){
			/* 		alert("가입이 가능한 아이디입니다."); */
					$("#idcheck").css("color","#0000ff");
					$("#idcheck").text("사용할수 있는 아이디입니다.");
					
				} else {
					/* alert("이미 가입이 되어있는 아이디입니다."); */
					$("#idcheck").css("color","#ff0000");
					$("#idcheck").text("사용중인 아이디입니다.");
					$("#id").val("");
				}
			},
			error:function(){
				alert("error");
			}
		});
	});
	
	$("#regibtn").click(function(){
		
		// 빈칸검사
		// id, pw, name
		
		
		$("#frm").submit();
		
	});
});
</script>
</body>
</html>