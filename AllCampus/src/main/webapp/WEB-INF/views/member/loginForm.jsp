<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 - 올캠퍼스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jy.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#login_form').submit(function(){
		if($('#id').val().trim()==''){
			alert('아이디를 입력해주세요');
			$('#id').val('').focus();
			return false;
		}
		if($('#passwd').val().trim()==''){
			alert('비밀번호를 입력하세요');
			$('#passwd').val('').focus();
			return false;
		}
	});
});
</script>
</head>
<body>
<div class="page-main align-center">
<div class="member-style">
	<a href="${pageContext.request.contextPath}/main/main.do">
	<img src="${pageContext.request.contextPath}/images/logo_symbol_231208.png" width="100">
	</a>
	<form id="login_form" action="login.do" method="post">
		<ul>
			<li class="floating-label">
				<input type="text" class="form-input"
					placeholder="아이디" name="id" id="id"
					maxlength="12" autocomplete="off">
				<label for="id">아이디</label>
			</li>
			<li class="floating-label">
				<input type="password" class="form-input"
					placeholder="비밀번호" name="passwd"
					id="passwd" maxlength="20">
				<label for="passwd">비밀번호</label>
			</li> 
		</ul>
		<input type="submit" value="올캠퍼스 로그인" class="input-button2" style="font-size:15px;">
		<br>
		<a href="checkIdForm.do" class="a-style">아이디/비밀번호 찾기</a>
	</form>
	<p>
	올캠퍼스가 처음이신가요? 
	<a href="registerUserForm.do">회원가입</a>
<div style="margin-bottom:3px;">
	<a href="#" class="bottom-style">문의하기</a>
	<a href="#" class="bottom-style">이용약관</a>
	<br>
	<div class="copyright">copyright(c) 2023. 올캠퍼스. All rights reserved</div>
</div>
</div>
</div>
</body>
</html>