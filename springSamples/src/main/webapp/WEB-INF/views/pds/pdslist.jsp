<%@page import="java.util.List"%>
<%@page import="ssg.com.a.dto.PdsDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
   	List<PdsDto> list = (List<PdsDto>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>자료실 목록</h2>

	<br />

	<div align="center">
		<table border="1">
			<colgroup>
				<col width="50">
				<col width="100">
				<col width="120">
				<col width="500">
				<col width="100">
				<col width="100">
				<col width="200">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>썸네일</th>
					<th>제목</th>
					<th>조회수</th>
					<th>다운수</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
			<%
				for(int i = 0; i < list.size(); i++){
					PdsDto dto = list.get(i);
			%>
				<tr>
					<td><%=(i + 1) %></td>
					<td><%=dto.getId() %></td>
					<td><img src="./upload/s_<%=dto.getNewfilename()%>"></td>
					<td><a href="pdsdetail.do?seq=<%=dto.getSeq() %>"><%=dto.getTitle() %></a>
					</td>
					<td><%=dto.getReadcount() %></td>
					<td><%=dto.getDowncount() %></td>
					<td><%=dto.getRegdate() %></td>
				</tr>
				<%
			}
%>
			</tbody>
		</table>

		<br /> <a href="pdswrite.do">자료추가</a>

	</div>

</body>
</html>