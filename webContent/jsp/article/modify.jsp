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
<title>게시물 수정</title>
</head>
<body>
	<h1>게시물 수정</h1>

	<form action="doModify" method="POST">
		<input type="hidden" name="id"
			value="<%=Integer.parseInt(request.getParameter("id"))%>" />
		<div>
			제목 : <input placeholder="제목을 입력해주세요." type="text" name="title"
				value="<%=(String)articleRow.get("title")%>" />
		</div>
		<div>
			내용 :
			<textarea placeholder="내용을 입력해주세요." name="body" /><%=(String) articleRow.get("body")%></textarea>
		</div>
		<div>
			<button type="submit">작성</button>
		</div>
		<div>
			<a href="list">리스트로 돌아가기</a>
		</div>
	</form>
</body>
</html>