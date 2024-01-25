<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!--  애러잡음. 톰캣 10은 javax를 지원안하기때문에 애러가 났음. 그래서 톰캣9로 낮춰줌. -->
<!--  다른 애러는 spring-webmvc와 선생님이 주신 pom의 spring 버전이 달라서 톰캣이 실행이 안됐음. 
	  그래서 선생님이 주신 버전으로 내가 넣은 webmvc버전을 맞춰줌. 그렇게 일단 해결함. -->

<%
	response.sendRedirect("hello.do");
%>