<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 - 올캠퍼스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jy.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#checkPw_form').submit(function(){
		if($('#email').val().trim()==''){
			alert('이메일을 입력해주세요.');
			$('#email').val('').focus();
			return false;
		}
		if($('#id').val().trim()==''){
			alert('아이디를 입력해주세요.');
			$('#id').val('').focus();
			return false;
		}
	});
});
</script>
</head>
<body>
<div class="page-main">
	<div class="content-main">
		<div class="align-center">
			<a href="${pageContext.request.contextPath}/main/main.do">
			<img src="${pageContext.request.contextPath}/images/logo_symbol_231208.png" width="100">
			</a>
		<div>
			<a href="checkIdForm.do">아이디 찾기</a>
			<a href="checkPwForm.do">비밀번호 찾기</a>
		</div>
		<form id="checkPw_form" action="checkPw.do" method="post">
			<ul>
				<li class="floating-label">
					<input type="text" placeholder="가입된 아이디" 
						name="id" id="id" class="form-input"
						maxlength="12" autocomplete="off">
					<label for="id">가입된 아이디</label>	
				</li>
				<li class="floating-label">
					<input type="email" placeholder="가입된 이메일" 
						name="email" id="email" class="form-input"
						maxlength="30" autocomplete="off">
					<label for="email">가입된 이메일</label>	
				</li>
			</ul>
			<input type="submit" value="비밀번호 찾기">
		</form>
		<p>
		올캠퍼스가 처음이신가요?
		<a href="registerUserForm.do">회원가입</a>
	</div>
</div>
<div class="page-sub">
	<a href="#">문의하기</a>
	<a href="#">이용약관</a>
	<br>
	<div class="copyright">copyright(c) 2023. 올캠퍼스. All rights reserved</div>
</div>
</div>
</body>
</html>