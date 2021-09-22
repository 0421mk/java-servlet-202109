<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
	<h1>회원 가입</h1>
	
	<script>
		var JoinForm__submitDone = false;
		
		function JoinForm__submit(form) {
			if (JoinForm__submitDone) {
				alert('처리중 입니다.');
				return;
			}
			
			form.loginId.value = form.loginId.value.trim();
			
			if (form.loginId.value.length == 0) {
				alert('로그인 아이디를 입력해주세요.');
				form.loginId.focus();
				
				return;
			}
			
			if (form.loginPw.value.length == 0) {
				alert('비밀번호를 입력해주세요.');
				form.loginPw.focus();
				
				return;
			}
			
			if (form.loginPwConfirm.value.length == 0) {
				alert('비밀번호 확인을 입력해주세요.');
				form.loginPwConfirm.focus();
				
				return;
			}
			
			if (form.loginPw.value != form.loginPwConfirm.value) {
				alert('로그인 비밀번호가 일치하지 않습니다.');
				form.loginPwConfirm.focus();
				
				return;
			}
			
			if (form.name.value.length == 0) {
				alert('이름을 입력해주세요.');
				form.loginPwConfirm.focus();
				
				return;
			}
			
			form.submit();
			JoinForm__submitDone = true;
			alert('당신은 모든 시련을 통과했습니다.');
		}
	</script>
	
	<form action="doJoin" method="POST" onsubmit="JoinForm__submit(this); return false;">
		<div>로그인ID : <input placeholder="로그인 아이디를 입력해주세요." type="text" name="loginId" /></div>
		<div>비밀번호 : <input placeholder="비밀번호를 입력해주세요." type="password" name="loginPw" /></div>
		<div>비밀번호 확인 : <input placeholder="비밀번호 확인을 입력해주세요." type="password" name="loginPwConfirm" /></div>
		<div>이름 : <input placeholder="이름을 입력해주세요." type="text" name="name" /></div>
		<div>
			<button type="submit">가입</button>
		</div>
		<div><a href="../home/main">돌아가기</a></div>
	</form>
</body>
</html>