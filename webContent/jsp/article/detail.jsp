<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%
Map<String, Object> articleRow = (Map<String, Object>) request.getAttribute("articleRow");
// �������� ���� String => ����ȯ�������
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խù� ��</title>
</head>
<body>
	<h1>�Խù� ����Ʈ</h1>
	
	<div>��ȣ : <%=(int)articleRow.get("id")%></div>
	<div>��¥ : <%=(String)articleRow.get("regDate")%></div>
	<div>���� : <%=(String)articleRow.get("title")%></div>
	<div>���� : <%=(String)articleRow.get("body")%></div>
	<div><a href="list">����Ʈ�� ���ư���</a></div>

</body>
</html>