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
    
   	<!-- jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	
    <!-- 섬머노트 추가 -->
    <script src="./js/summernote-lite.js"></script>
	<script src="./js/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="./css/summernote-lite.css">
    
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
            
      		--bs-table-color: white;
   			--bs-table-bg: royalblue;
        }

        button {
            /* margin: 0 80px; */
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
                <td><input type="text" id="title" name="title" size="60" value="<%=dto.getTitle()%>">
                </td>
            </tr>
            <tr>
                <!-- <th>내용</th> -->
                <td colspan="2"><textarea id="summernote" name="content" class="form-control" cols="50" rows="10"><%=dto.getContent()%></textarea>
                </td>
            </tr>
        </table>
        <button type="submit" id="bbsUpdateSubmit" class="btn btn-primary">수정완료</button>
       	<button type="button" class="btn btn-primary" onclick="returnlist()">글목록으로</button>
    </form>
</div>

<script type="text/javascript">
	$('#bbsUpdateSubmit').click(function(){
		let title = $('#title').val();
		
		if(title.trim() === ''){
			alert('제목을 입력해주세요');
			return false;	//전송보류시에는 false를 반환해주면 된다.
		}
		
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#summernote').summernote({
			height : 150, // 에디터 높이
			minHeight : null, // 최소 높이
			maxHeight : null, // 최대 높이
			focus : true, // 에디터 로딩후 포커스를 맞출지 여부
			lang : "ko-KR", // 한글 설정
			placeholder : '최대 2048자까지 쓸 수 있습니다' //placeholder 설정
		});
		
		$('.note-view').remove();
		$('.note-fontname').remove();
		$('.note-color').remove();
	});

	function returnlist() {
		location.href = "bbslist.do";
	}
</script>
</body>
</html>