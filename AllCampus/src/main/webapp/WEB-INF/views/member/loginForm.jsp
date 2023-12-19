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
<div class="page-main-custom align-center">
	<a href="${pageContext.request.contextPath}/main/main.do">
		<img src="${pageContext.request.contextPath}/images/logo_symbol_231208.png" width="100" class="mem-logo">
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
		<input type="submit" value="올캠퍼스 로그인" class="input-button2" style="margin-left:13px;">
		<br>
		<a href="checkIdForm.do" class="a-style" style="margin-left:10px;">아이디/비밀번호 찾기</a>
	</form>
	<p style="margin-left:-50px;">
	올캠퍼스가 처음이신가요? 
	<a href="registerUserForm.do" class="important">회원가입</a>
<div class="bottom">
	<a href="${pageContext.request.contextPath}/faq/faq.do" class="bottom-style">문의하기</a>
	<a href="${pageContext.request.contextPath}/mymember/termForm.do" class="bottom-style">이용약관</a>
	<br>
	<div class="copyright">copyright(c) 2023. 올캠퍼스. All rights reserved</div>
</div>
</div>
</body>
</html>