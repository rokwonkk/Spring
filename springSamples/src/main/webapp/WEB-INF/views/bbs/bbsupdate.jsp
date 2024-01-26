<%@page import="ssg.com.a.dto.BbsDto"%>
<%@ page import="ssg.com.a.dto.MemberDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberDto login = (MemberDto) session.getAttribute("login");
    if (login == null) {
%>
<script>
    alert("로그인을 해 주십시오")
    location.href = "login.jsp";
</script>
<% } %>
<%
	BbsDto dto = (BbsDto)request.getAttribute("dto");
%>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        body {
            padding: 40px;
        }

        .mytable {
            width: 800px;
            border: 1px solid lightgray;
        }

        th {
            border: 1px solid lightgray;
            text-align: center;
            vertical-align: middle;
        }

        button {
            margin: 0 80px;
        }
    </style>
<body>
<h1 style="text-align: center">글 상세보기 수정</h1>
<br/>
<div align="center">
    <form action="bbsupdateAf.do" method="post">
        <input type="hidden" name="seq" value="<%=dto.getSeq()%>">

        <table class="table mytable">
            <col width="200"/>
            <col width="400"/>
            <tr>
                <th>작성자</th>
                <td>
                    <%=dto.getId()%>
                </td>
            </tr>
            <tr>
                <th>작성일</th>
                <td><%=dto.getWdate()%>
            </tr>
            <tr>
                <th>조회수</th>
                <td><%=dto.getReadcount()%>
                </td>
            </tr>
            <tr>
                <th>제목</th>
                <td><input type="text" name="title" size="60" value="<%=dto.getTitle()%>">
                </td>
            </tr>
            <tr>
                <th>내용</th>
                <td><textarea name="content" class="form-control" cols="50" rows="10"><%=dto.getContent()%></textarea>
                </td>
            </tr>
        </table>
        <button type="submit" class="btn btn-primary">수정완료</button>
    </form>
</div>
</body>
</html>