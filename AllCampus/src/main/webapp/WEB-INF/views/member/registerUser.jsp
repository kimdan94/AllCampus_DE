<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 완료</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jy.css">
</head>
<body>
<div class="page-main-custom">
<div>
	<div class="result-display">
		<div class="align-center">
		올캠퍼스 회원가입이 완료되었습니다.<p>
		<input type="button" value="로그인하기" class="input-button2" style="font-size:15px;"
			onclick="location.href='loginForm.do'">
		<input type="button" value="처음으로" class="input-button1" style="margin-left:5px;font-size:15px;"
			onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>		
	</div>
</div>	
</div>
</body>
</html>