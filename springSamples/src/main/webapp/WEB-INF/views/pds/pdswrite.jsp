<%@page import="ssg.com.a.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	MemberDto login = (MemberDto)session.getAttribute("login");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료올리기</title>
</head>
<body>

<h2>자료추가</h2>
<br/>

<div align="center">

<%-- 파일(byte) + 제목, 내용, 작성자(String) --%>
<form action="pdsupload.do" method="post" enctype="multipart/form-data">

<table border="1">
<tr>
	<th>아이디</th>
	<td>
		<input type="text" name="id" value="<%=login.getId() %>" readonly />
	</td>
</tr>
<tr>
	<th>제목</th>
	<td>
		<input type="text" name="title" size="50">
	</td>
</tr>
<tr>
	<th>파일업로드</th>
	<td>
		<input type="file" name="fileupload">		
	</td>
</tr>
<tr>
	<th>내용</th>
	<td>
		<textarea rows="10" cols="50" name="content"></textarea>
	</td>
</tr>
</table>

<input type="submit" value="자료올리기"/>

</form>

</div>

</body>
</html>







