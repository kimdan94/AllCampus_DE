<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
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
<c:if test="${user_auth >= 3}">
<div class="page-main">
<h3 class="univName-style">[${univ_name}]</h3>
	<div class="myInfo-div">
		<table>
			<tr>
				<td>
					<c:if test="${empty user_photo}">
						<img src="${pageContext.request.contextPath}/images/face.png">
					</c:if>
					<c:if test="${!empty user_photo}">
						<img src="${pageContext.request.contextPath}/upload/${user_photo}">
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="align-center">
					<b>${user_nick}</b>
				</td>
			</tr>
			<tr>
				<td class="align-center" style="font-size:12px;">
					${user_id}
				</td>
			</tr>
		</table>
	</div>
	<div class="home-div">
		<h3>공지사항 <input type="button" value="더보기"
			onclick="location.href='#'"></h3>
		<div class="list-div">
		<table>
			<c:forEach var="notice" items="${noticeList}">
			<tr>
				<td><a href="${pageContext.request.contextPath}/notice/detail.do?notice_num=${notice.notice_num}">${notice.notice_title}</a></td>
				<td class="list-margin"><fmt:formatDate value="${notice.notice_reg_date}" pattern="MM/dd HH:mm"/></td>
			</tr>
			</c:forEach>
		</table>
		</div>
		<h3>FAQ <input type="button" value="더보기"
			onclick="location.href='#'"></h3>
		<div class="list-div">
		<table>
			<tr>
			</tr>
		</table>
		</div>
	</div>
	<div class="home-div">
		<h3>HOT 게시판 <input type="button" value="더보기"
			onclick="location.href='#'"></h3>
		<div class="list-div">
		<table>
			<tr>
			</tr>
		</table>
		</div>
		<h3>자유 게시판 <input type="button" value="더보기"
			onclick="location.href='${pageContext.request.contextPath}/board/list.do'"></h3>
		<div class="list-div">
		<table>
			<c:forEach var="board" items="${boardList}">
			<tr>
				<td><a href="${pageContext.request.contextPath}/board/detail.do?board_num=${board.board_num}">${board.board_title}</a></td>
				<td class="list-margin"><fmt:formatDate value="${board.board_reg_date}" pattern="MM/dd HH:mm"/></td>
			</tr>
			</c:forEach>	
		</table>
		</div>
	</div>
	<div class="home-end"></div>
</div>
</c:if>
<c:if test="${user_auth < 3}">
<div class="page-main">
<h3 class="univName-style">[${univ_name}]</h3>
	<div class="myInfo-div">
		<table>
			<tr>
				<td>
					<c:if test="${empty user_photo}">
						<img src="${pageContext.request.contextPath}/images/face.png">
					</c:if>
					<c:if test="${!empty user_photo}">
						<img src="${pageContext.request.contextPath}/upload/${user_photo}">
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="align-center">
					<b>${user_nick}</b>
				</td>
			</tr>
			<tr>
				<td class="align-center" style="font-size:12px;">
					${user_id}
				</td>
			</tr>
		</table>
	</div>
	<div class="home-div">
		<h3>공지사항 <input type="button" value="더보기"
			onclick="location.href='#'"></h3>
		<div class="list-div">
		<table>
			<tr>
			</tr>
		</table>
		</div>
		<h3>FAQ <input type="button" value="더보기"
			onclick="location.href='#'"></h3>
		<div class="list-div">	
		<table>
			<tr>
			</tr>
		</table>
		</div>
	</div>
	<div class="home-div">	
		<h3>HOT 게시판</h3>
		<div class="list-div align-center" style="line-height:100px;">
			학교 인증을 마친 학생들만 이용할 수 있어요!<br>
			<input type="button" value="학교 인증"
				onclick="location.href='${pageContext.request.contextPath}/mymember/certifyForm.do'">
		</div>
		<h3>자유 게시판</h3>
		<div class="list-div align-center" style="line-height:100px;">
			학교 인증을 마친 학생들만 이용할 수 있어요!<br>
			<input type="button" value="학교 인증"
				onclick="location.href='${pageContext.request.contextPath}/mymember/certifyForm.do'">
		</div>
	</div>
	<div class="home-end"></div>
</div>
</c:if>
<div class="page-sub">
	<a href="#">이용약관</a>
	<div class="copyright">
		copyright(c) 2023. 올캠퍼스. All rights reserved
	</div>
</div>
</body>
</html>
 