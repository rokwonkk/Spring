<%@page import="ssg.com.a.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	List<MemberDto> list = (List<MemberDto>)request.getAttribute("list");
%>
<h1>Hello</h1>

<table border="1">
<tr>
	<th>번호</th><th>아이디</th><th>패스워드</th><th>이름</th><th>이메일</th>
</tr>
<%
if(list != null && list.size() > 0){

	for(int i = 0;i < list.size(); i++){
		MemberDto dto = list.get(i);
		%>
		<tr>
			<th><%=(i + 1) %></th>
			<td><%=dto.getId() %></td>
			<td><%=dto.getPw() %></td>
			<td><%=dto.getName() %></td>
			<td><%=dto.getEmail() %></td>		
		</tr>		
		<%
	}	
}
%>
</table>

<br/>
<hr>
<br/>

<form action="findmember.do">
id:<input type="text" name="id" size="20">
<input type="submit" value="회원정보의 비번찾기">
</form>

<%
	MemberDto dto = (MemberDto)request.getAttribute("dto");
	if(dto != null){
		%>
		<p><%=dto.getName()%>님의 비밀번호는 <%=dto.getPw() %>입니다.</p>
		<%
	}
%>

<br/>
<hr>
<br/>

<%-- ajax(axios) --%>

<%--id확인 --%>
id:<input type="text" id="id"><br/><br/>
<button type="button" onclick="idcheck()">아이디체크</button>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
function idcheck() {
	$.ajax({
		url : "./idcheck.do",
		type : "get",
		data : { id : $("#id").val() },
		success:function(str){
			// alert("success");
			alert("str:" + str);
		},
		error:function(){
			alert("error");
		}
	});
}
</script>

<br/>
<hr>
<br/>

id:<input type="text" id="userid"><br/><br/>
pw:<input type="text" id="userpw"><br/><br/>
<button type="button" id="login">login</button>

<script type="text/javascript">
	$(document).ready(function(){
		$("#login").click(function(){
			$.ajax({
				url:"./login.do",
				type:"post",
				data:{
					id:$("#userid").val(),
					pw:$('#userpw').val()
				},
				success:function(dto){
					/* alert('success'); */
					alert(JSON.stringify(dto));
				},
				error:function(){
					alert('error');
				}
			});
		});
	});
</script>

<br/>
<hr>
<br/>

<button type="button" id="getmap">map</button>
<script type="text/javascript">
$('#getmap').click(function(){
	
	$.ajax({
		url:"getmap.do",
		type:"get",
		success:function(map){
			/* alert('success'); */
			/* alert(JSON.stringify(map)); */
			/* alert(map.message); */
			alert(map.list[0].name);
		},
		error:function(){
			alert('error');
		}
	});
	
});
</script>
</body>
</html>