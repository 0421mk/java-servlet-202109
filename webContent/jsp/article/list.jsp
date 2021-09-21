<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
// �������� ���� String => ����ȯ�������
int nowPage = (int) request.getAttribute("page");
int totalPage = (int) request.getAttribute("totalPage");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խù� ����Ʈ</title>
</head>
<body>
	<h1>�Խù� ����Ʈ</h1>
	
	<div>
		<a href="write">�Խù� �ۼ�</a>
	</div>

	<table>
		<colgroup>
			<col width="100" />
			<col width="200" />
		</colgroup>
		<thead>
			<tr>
				<th>��ȣ</th>
				<th>��¥</th>
				<th>����</th>
				<th>���</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (Map<String, Object> articleRow : articleRows) {
			%>
			<tr>
				<td><%=articleRow.get("id")%></td>
				<td><%=articleRow.get("regDate")%></td>
				<td><a href="detail?id=<%=articleRow.get("id")%>"><%=articleRow.get("title")%></a></td>
				<td><a href="doDelete?id=<%=articleRow.get("id")%>">�����ϱ�</a></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<div class="page">
		
		<% for(int i = 1; i <= totalPage; i++) { %>
		<a class="<%=nowPage == i ? "red" : "" %>" href="list?page=<%=i%>"><%=i%></a>
		<% } %>
		
	</div>
	<style>
	.red {
		color: red;
		font-weight: bold;
	}
	</style>
</body>
</html>