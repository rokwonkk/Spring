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
    
   	<!-- Jquery 추가 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    
    <!-- 섬머노트 추가 -->
    <script src="./js/summernote-lite.js"></script>
	<script src="./js/summernote-ko-KR.js"></script>
	<link rel="stylesheet" href="./css/summernote-lite.css">
    
    <style>
        .mytable{
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
        /* 이미지 드래그 무한 증식 막음. */
		img {
			/* 선택불가 */
			-webkit-user-select: none;
			-khtml-user-select: none;
			-moz-user-select: none;
			-o-user-select: none;
			user-select: none;
			/* 드래그 불가 */
			-webkit-user-drag: none;
			-khtml-user-drag: none;
			-moz-user-drag: none;
			-o-user-drag: none;
			user-drag: none;
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
            <!-- <th>제목</th> -->
            <td colspan="2"><%=dto.getTitle()%>
            </td>
        </tr>
        <tr>
            <!-- <th>내용</th> -->
            <td colspan="2"><textarea id="summernote" name="content" class="form-control" cols="50" rows="10"
                          readonly><%=dto.getContent()%></textarea>
            </td>
        </tr>
    </table>

    <h1> 답글 </h1>

    <%
        MemberDto login = (MemberDto)session.getAttribute("login");
    %>

    <form id="form1" action="answerAf.do" method="post">

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
                <!-- <th>제목</th> -->
                <td colspan="2"><input type="text" class="form-control" id="title" name="title" size="50px" placeholder="제목을 작성해주세요"></td>
            </tr>
            <tr>
                <!-- <th>내용</th> -->
                <td colspan="2"><textarea id="summernoteanswer" name="content" class="form-control" cols="50" rows="2" placeholder="내용을 작성해주세요"></textarea></td>
            </tr>
        </table>
        <br/>
        <button id="answerSubmit" type="submit" class="btn btn-primary">글작성완료</button>
        <button type="button" class="btn btn-primary" onclick="returnlist()">글목록으로</button>
    </form>
</div>

<script type="text/javascript">
	$('#answerSubmit').click(function(){
		let title = $('#title').val();
		
		if(title.trim() === ''){
			alert('제목을 입력해주세요');
			return false;	//전송보류시에는 false를 반환해주면 된다.
		}
		
	});
</script>

<script>
$(document).ready(function(){
	
	$('#summernote').summernote('disable');

	$('.note-editable').css('background-color','white');
	$('.note-toolbar').remove();
	
	$('#summernoteanswer').summernote({
		  height: 150,              				    // 에디터 높이
		  minHeight: null,        					    // 최소 높이
		  maxHeight: null,    					        // 최대 높이
		  focus: true,           						// 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",								// 한글 설정
		  placeholder: '최대 2048자까지 쓸 수 있습니다'	//placeholder 설정
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
