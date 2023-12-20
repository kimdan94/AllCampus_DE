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
<body style="overflow: hidden;">
<div class="row">
<!-- 올캠퍼스 사이트 소개 시작 -->
<div class="container col-md-9">
	<div id="carouselExample" class="carousel slide" 
	                          data-bs-ride="carousel">
	     <div class="carousel-inner">
	     	<!-- 0 이미지 시작 -->
	     	<div class="carousel-item active">
	     		<img src="${pageContext.request.contextPath}/images/carousel-1.png" 
	     			class="d-block w-100" data-bs-interval="2500">
	     	</div>
	     	<!-- 0 이미지 끝 -->
	     	<!-- 1 이미지 시작 -->
	     	<div class="carousel-item">
	     		<img src="${pageContext.request.contextPath}/images/carousel-2.png" 
	     			class="d-block w-100" data-bs-interval="2500">
	     	</div>
	     	<!-- 1 이미지 끝 -->
	     	<!-- 2 이미지 시작 -->
	     	<div class="carousel-item">
	     		<img src="${pageContext.request.contextPath}/images/carousel-3.png" 
	     			class="d-block w-100" data-bs-interval="2500">
	     	</div>
	     	<!-- 2 이미지 끝 -->
	     </div><!-- end of .carousel-inner -->
	</div>     
</div>
<!-- 올캠퍼스 사이트 소개 끝 -->
<div class="align-center col-md-3 main-style">
	<img src="${pageContext.request.contextPath}/images/logo_symbol_231208.png" width="100">
<p><p>
<input type="button" value="로그인하기" class="input-button1"
	onclick="location.href='${pageContext.request.contextPath}/member/loginForm.do'">
<br>		
<input type="button" value="회원가입" class="input-button2" style="margin-top:5px;"
	onclick="location.href='${pageContext.request.contextPath}/member/registerUserForm.do'">
<br>	
<a href="${pageContext.request.contextPath}/member/checkIdForm.do" class="a-style">아이디/비밀번호 찾기</a>
<div style="margin-bottom:3px;">
	<a href="${pageContext.request.contextPath}/faq/faq.do" class="bottom-style">문의하기</a>
	<a href="${pageContext.request.contextPath}/mymember/termForm.do" class="bottom-style">이용약관</a>
</div>
<div class="copyright">
	copyright(c) 2023. 올캠퍼스. All rights reserved
</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
</div>
</body>
</html>