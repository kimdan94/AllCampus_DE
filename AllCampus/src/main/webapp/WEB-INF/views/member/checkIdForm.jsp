<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 - 올캠퍼스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jy.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#checkId_form').submit(function(){
		if($('#email').val().trim()==''){
			alert('이메일을 입력해주세요.');
			$('#email').val('').focus();
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
		<form id="checkId_form" action="checkId.do" method="post">
			<ul>
				<li>
					<label for="email">이메일</label>
					<input type="email" placeholder="가입된 이메일" 
						name="email" id="email"
						maxlength="30" autocomplete="off">
				</li>
			</ul>
			<input type="submit" value="아이디 찾기">
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