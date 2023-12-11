<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>올캠퍼스</title>
</head>
<body>
<!-- 올캠퍼스 사이트 소개 시작 -->
<!-- 올캠퍼스 사이트 소개 끝 -->
<!-- 수정 필요 -->
<div>
	<img src="${pageContext.request.contextPath}/images/logo_symbol_231208.png" width="100" height="100">
</div>
<input type="button" value="로그인하기"
	onclick="location.href='${pageContext.request.contextPath}/member/loginForm.do'">	
<input type="button" value="회원가입"
	onclick="location.href='${pageContext.request.contextPath}/member/registerUserForm.do'">
<div>
	<a href="${pageContext.request.contextPath}/member/checkIdForm.do">아이디/비밀번호 찾기</a>
</div>
<div>
	<a href="#">문의하기</a>
	<a href="#">이용약관</a>
</div>
<div>
	copyright(c) 2023. 올캠퍼스. All rights reserved
</div>
</body>
</html>