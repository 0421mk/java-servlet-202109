<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
// �������� ���� String => ����ȯ�������
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խù� ����Ʈ</title>
</head>
<body>
	<h1>�Խù� ����Ʈ</h1>

	<ul>
		<%
		for (Map<String, Object> articleRow : articleRows) {
		%>
		<li><%=(int) articleRow.get("id")%>��, <%=(String) articleRow.get("regDate")%>,
			<%=(String) articleRow.get("title")%></li>
		<%
		}
		%>
	</ul>
</body>
</html>