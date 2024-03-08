<%@page import="ssg.com.a.dto.StudentDto"%>
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
	String name = (String)request.getAttribute("name");
%>
이름: <%=name %>

<br/><br/>

<form action="info.do" method="post">
이름:<input type="text" name="name"><br/>
나이:<input type="text" name="age"><br/>
<input type="submit" value="info">
</form>

<br/><br/>

<a href="student.do?name=홍두께&age=23&height=173.5">student.do로 이동</a>

<br/><br/>

<form action="student.do">
이름:<input type="text" name="name"><br/>
나이:<input type="text" name="age"><br/>
신장:<input type="text" name="height"><br/>
<input type="submit" value="info">
</form>

<br/><br/>

<button type="button" onclick="func()">학생목록</button>

<script type="text/javascript">
function func() {
	location.href = "list.do?message=" + "학생데이터를 요청합니다";	
}
</script>

<br/><br/>

<%
	List<StudentDto> list = (List<StudentDto>)request.getAttribute("list");
	if(list != null && list.size() > 0){
	%>
		<table border="1">
		<tr>
			<th>이름</th><th>나이</th><th>신장</th>
		</tr>
		<%
		for(int i = 0;i < list.size(); i++){
			StudentDto s = list.get(i);
			%>
			<tr>
				<td><%=s.getName() %></td>
				<td><%=s.getAge() %></td>
				<td><%=s.getHeight() %></td>
			</tr>
			<%
		}
		%>
		</table>
	<%
	}
%>



</body>
</html>



