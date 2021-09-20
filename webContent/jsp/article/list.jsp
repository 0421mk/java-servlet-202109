<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
// 가져오는 값은 String => 형변환해줘야함
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시물 리스트</title>
</head>
<body>
	<h1>게시물 리스트</h1>

	<ul>
		<%
		for (Map<String, Object> articleRow : articleRows) {
		%>
		<li><%=(int) articleRow.get("id")%>번, <%=(String) articleRow.get("regDate")%>,
			<%=(String) articleRow.get("title")%></li>
		<%
		}
		%>
	</ul>
</body>
</html>