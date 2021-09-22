<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
// 가져오는 값은 String => 형변환해줘야함
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세</title>
</head>
<body>
	<h1>게시물 리스트</h1>
	
	<%@ include file="../part/header.jspf" %>
	
	<div>번호 : <%=(int)articleRow.get("id")%></div>
	<div>날짜 : <%=(String)articleRow.get("regDate")%></div>
	<div>제목 : <%=(String)articleRow.get("title")%></div>
	<div>내용 : <%=(String)articleRow.get("body")%></div>
	<div>
	<a href="modify?id=<%=(int)articleRow.get("id")%>">수정</a>
	<a href="doDelete?id=<%=(int)articleRow.get("id")%>">삭제</a>
	<a href="list">리스트로 돌아가기</a>
	</div>

</body>
</html>