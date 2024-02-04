<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
// 회원가입
String regiMsg = (String)request.getAttribute("regiMsg");
if(regiMsg != null && regiMsg.equals("") == false){
	if(regiMsg.equals("MEMBER_YES")){
		%>
		<script type="text/javascript">
		alert("성공적으로 가입되었습니다");
		location.href = "./login.do";
		</script>
		<%
	}else{
		%>
		<script type="text/javascript">	
		alert("다시 가입해 주십시오");
		location.href = "./regi.do";
		</script>
		<%
	}
}

// 로그인
String loginMsg = (String)request.getAttribute("loginMsg");
if(loginMsg != null && loginMsg.equals("") == false){
	if(loginMsg.equals("LOGIN_SUCCESS")){
		%>
		<script type="text/javascript">
		alert("로그인되었습니다");
		location.href = "./bbslist.do";
		</script>
		<%		
	}else{
		%>
		<script type="text/javascript">
		alert("아이디나 패스워드를 확인해 주세요");
		location.href = "./login.do";
		</script>
		<%		
	}	
}

// 게시판 글추가
String bbswriteMsg = (String)request.getAttribute("bbswriteMsg");
if(bbswriteMsg != null && bbswriteMsg.equals("") == false){
	if(bbswriteMsg.equals("BBSWRITE_SUCCESS")){
		%>
		<script type="text/javascript">
		alert("성공적으로 추가되었습니다");
		location.href = "./bbslist.do";
		</script>
		<%
	}else{
		%>
		<script type="text/javascript">
		alert("다시 작성해 주십시오");
		location.href = "./bbswrite.do";
		</script>
		<%
	}
}

// 답글
String answerMsg = (String)request.getAttribute("answerMsg");
if(answerMsg != null && answerMsg.equals("") == false){
	if(answerMsg.equals("ANSWER_SUCCESS")){
		%>
		<script type="text/javascript">
		alert("답글이 성공적으로 추가되었습니다");
		location.href = "bbslist.do";
		</script>
		<%
	}else{
		Integer seq = (Integer)request.getAttribute("seq");
		%>
		<script type="text/javascript">
		alert("답글을 다시 작성해 주십시오");
		location.href = "answer.do?seq=" + seq;
		</script>
		<%
	}
}

// 글수정
String bbsupdateMsg = (String)request.getAttribute("bbsupdateMsg");
if(bbsupdateMsg != null && bbsupdateMsg.equals("") == false){
	if(bbsupdateMsg.equals("UPDATE_SUCCESS")){
		%>
		<script type="text/javascript">
		alert("글이 성공적으로 수정되었습니다");
		location.href = "bbslist.do";
		</script>
		<%
	}else{
		Integer seq = (Integer)request.getAttribute("seq");
		%>
		<script type="text/javascript">
		alert("글을 다시 수정해 주십시오");
		location.href = "bbsupdate.do?seq=" + <%=seq %>;
		</script>
		<%
	}
}

String bbsdeleteMsg = (String)request.getAttribute("bbsdeleteMsg");
if(bbsdeleteMsg != null && bbsdeleteMsg.equals("") == false){
	if(bbsdeleteMsg.equals("DELETE_SUCCESS")){
		%>
		<script type="text/javascript">
		alert("글이 성공적으로 삭제되었습니다");
		location.href = "bbslist.do";
		</script>
		<%
	}else{
		Integer seq = (Integer)request.getAttribute("seq");
		%>
		<script type="text/javascript">
		alert("글이 삭제되지 않았습니다");
		location.href = "bbslist.do?seq=" + <%=seq %>;
		</script>
		<%
	}	
}

String calwrite = (String)request.getAttribute("calwrite");
if(calwrite != null && !calwrite.equals("")){
	if(calwrite.equals("CAL_WRITE_OK")){
		%>
		<script type="text/javascript">
		alert("성공적으로 추가되었습니다");
		location.href = "calendar?param=calendarList";
		</script>
		<%
	}
	else{
		%>
		<script type="text/javascript">
		alert("추가되지 않았습니다");		
		location.href = "calendar?param=calendarList";
		</script>
		<%
	}	
}

String calupdate = (String)request.getAttribute("calupdate");
if(calupdate != null && !calupdate.equals("")){
	if(calupdate.equals("CAL_UPDATE_OK")){
		%>
		<script type="text/javascript">
		alert("성공적으로 수정되었습니다");
		location.href = "calendar?param=calendarList";
		</script>
		<%
	}
	else{
		%>
		<script type="text/javascript">
		alert("수정되지 않았습니다");		
		location.href = "calendar?param=calendarList";
		</script>
		<%
	}	
}

String caldelete = (String)request.getAttribute("caldelete");
if(caldelete != null && !caldelete.equals("")){
	if(caldelete.equals("CAL_DELETE_OK")){
		%>
		<script type="text/javascript">
		alert("성공적으로 삭제되었습니다");
		location.href = "calendar?param=calendarList";
		</script>
		<%
	}
	else{
		%>
		<script type="text/javascript">
		alert("삭제되지 않았습니다");		
		location.href = "calendar?param=calendarList";
		</script>
		<%
	}	
}
%>









