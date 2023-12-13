<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>올캠퍼스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jy.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
<h3 class="univName-style">[${univ_name}]</h3>
	<div class="myInfo-div">
		<table>
			<tr>
				<td>
					<!-- 프로필 사진/기본 이미지로 처리 필요 -->
					<img src="${pageContext.request.contextPath}/images/face.png">
				</td>
			</tr>
			<tr>
				<td class="align-center">
					${user_nick}
				</td>
			</tr>
			<tr>
				<td class="align-center">
					${user_id}
				</td>
			</tr>
		</table>
	</div>
	<div class="home-div">
		<h3>공지사항 <input type="button" value="더보기"
			onclick="location.href='#'"></h3>
		<table>
			<tr>
			</tr>
		</table>
		<h3>FAQ <input type="button" value="더보기"
			onclick="location.href='#'"></h3>
		<table>
			<tr>
			</tr>
		</table>
	</div>
	<div class="home-div">	
		<h3>HOT 게시판 <input type="button" value="더보기"
			onclick="location.href='#'"></h3>
		<table>
			<tr>
			</tr>
		</table>
		<h3>자유 게시판 <input type="button" value="더보기"
			onclick="location.href='${pageContext.request.contextPath}/board/list.do'"></h3>
		<table>
			<tr>
			</tr>
		</table>
	</div>
	<div class="home-end"></div>
</div>

<div class="page-sub">
	<a href="#">이용약관</a>
	<div class="copyright">
		copyright(c) 2023. 올캠퍼스. All rights reserved
	</div>
</div>
</body>
</html>

 