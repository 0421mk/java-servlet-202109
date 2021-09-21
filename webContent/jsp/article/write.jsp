<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성</title>
</head>
<body>
	<h1>게시물 작성</h1>
	
	<form action="doWrite" method="POST">
		<div>제목 : <input placeholder="제목을 입력해주세요." type="text" name="title" /></div>
		<div>내용 : <textarea placeholder="내용을 입력해주세요." name="body" /></textarea></div>
		<div>
			<button type="submit">작성</button>
		</div>
		<div><a href="list">리스트로 돌아가기</a></div>
	</form>
</body>
</html>