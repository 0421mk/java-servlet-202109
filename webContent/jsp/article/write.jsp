<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խù� �ۼ�</title>
</head>
<body>
	<h1>�Խù� �ۼ�</h1>
	
	<form action="doWrite" method="POST">
		<div>���� : <input placeholder="������ �Է����ּ���." type="text" name="title" /></div>
		<div>���� : <textarea placeholder="������ �Է����ּ���." name="body" /></textarea></div>
		<div>
			<button type="submit">�ۼ�</button>
		</div>
		<div><a href="list">����Ʈ�� ���ư���</a></div>
	</form>
</body>
</html>