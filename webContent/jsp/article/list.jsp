<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%
List<Map<String, Object>> articleRows = (List<Map<String, Object>>) request.getAttribute("articleRows");
// 가져오는 값은 String => 형변환해줘야함
int nowPage = (int) request.getAttribute("page");
int totalPage = (int) request.getAttribute("totalPage");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시물 리스트</title>
</head>
<body>
	<h1>게시물 리스트</h1>
	
	<div>
		<a href="write">게시물 작성</a>
	</div>

	<table>
		<colgroup>
			<col width="100" />
			<col width="200" />
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>날짜</th>
				<th>제목</th>
				<th>비고</th>
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
				<td><a href="doDelete?id=<%=articleRow.get("id")%>">삭제하기</a></td>
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