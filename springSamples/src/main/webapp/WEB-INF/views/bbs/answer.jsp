<%@page import="ssg.com.a.dto.MemberDto"%>
<%@ page import="ssg.com.a.dao.BbsDao" %>
<%@ page import="ssg.com.a.dto.BbsDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BbsDto dto = (BbsDto) request.getAttribute("dto");
%>

<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .mytable{
            width: 800px;
            border: 1px solid lightgray;
        }
        th {
            border: 1px solid lightgray;
        }
    </style>
</head>
<body>

<%--
        기본글
            작성자
            작성일
            조회수
            제목
            내용

        답글
            로그인id
            제목
            내용
--%>

<div align="center">
    <h1> 기본글 </h1>
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
            <td><%=dto.getTitle()%>
            </td>
        </tr>
        <tr>
            <th>내용</th>
            <td><textarea name="content" class="form-control" cols="50" rows="10"
                          readonly><%=dto.getContent()%></textarea>
            </td>
        </tr>
    </table>

    <h1> 답글 </h1>

    <%
        MemberDto login = (MemberDto)session.getAttribute("login");
    %>

    <form action="answerAf.do" method="post">

        <%-- 보이진 않지만 파라미터 날려주고 싶을 때 ( 기본글의 시퀀스 ) --%>
        <input type="hidden" name="seq" value="<%=dto.getSeq()%>">

        <table class="table mytable">
            <col width="200"/>
            <col width="400"/>
            <tr>
                <th>id</th>
                <td>
                    <input type="text" class="form-control" name="id" size="50px" value="<%=login.getId()%>" readonly>
                </td>
            </tr>
            <tr>
                <th>제목</th>
                <td><input type="text" class="form-control" name="title" size="50px"></td>
            </tr>
            <tr>
                <th>내용</th>
                <td><textarea name="content" class="form-control" cols="50" rows="2" placeholder="내용을 작성해주세요"></textarea></td>
            </tr>
        </table>
        <br/>
        <button type="submit" class="btn btn-primary">글작성완료</button>
    </form>
</div>
</body>
</html>
