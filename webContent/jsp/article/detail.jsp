<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%
Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
// 가져오는 값은 String => 형변환해줘야함
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시물 상세</title>
</head>
<body>
	<h1>게시물 리스트</h1>
	
	<div>번호 : <%=(int)articleRow.get("id")%></div>
	<div>날짜 : <%=(String)articleRow.get("regDate")%></div>
	<div>제목 : <%=(String)articleRow.get("title")%></div>
	<div>내용 : <%=(String)articleRow.get("body")%></div>
	<div><a href="list">리스트로 돌아가기</a></div>

</body>
</html>