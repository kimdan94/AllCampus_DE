<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>올캠퍼스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jy.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
</head>
<body>
<!-- 올캠퍼스 사이트 소개 시작 -->
<div class="container carousel">
	<div id="carouselExample" class="carousel slide" 
	                          data-bs-ride="carousel">
	     <div class="carousel-indicators">
	     	<button type="button" data-bs-target="#carouselExample"
	     	        data-bs-slide-to="0" class="active"></button>
	     	<button type="button" data-bs-target="#carouselExample"
	     	        data-bs-slide-to="1"></button>
	     	<button type="button" data-bs-target="#carouselExample"
	     	        data-bs-slide-to="2"></button>
	     </div>
	     <div class="carousel-inner">
	     	<!-- 0 이미지 시작 -->
	     	<div class="carousel-item active">
	     		<img src="${pageContext.request.contextPath}/images/Jellyfish.jpg">
	     	</div>
	     	<!-- 0 이미지 끝 -->
	     	<!-- 1 이미지 시작 -->
	     	<div class="carousel-item">
	     		<img src="${pageContext.request.contextPath}/images/Penguins.jpg">
	     	</div>
	     	<!-- 1 이미지 끝 -->
	     	<!-- 2 이미지 시작 -->
	     	<div class="carousel-item">
	     		<img src="${pageContext.request.contextPath}/images/Koala.jpg">
	     	</div>
	     	<!-- 2 이미지 끝 -->
	     </div><!-- end of .carousel-inner -->
	</div>     
</div>
<!-- 올캠퍼스 사이트 소개 끝 -->
<!-- 수정 필요 -->
<div>
	<img src="${pageContext.request.contextPath}/images/logo_symbol_231208.png" width="100">
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
<div class="copyright">
	copyright(c) 2023. 올캠퍼스. All rights reserved
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
</body>
</html>