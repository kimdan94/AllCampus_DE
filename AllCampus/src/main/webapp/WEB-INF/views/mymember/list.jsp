<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 쓴 글 목록 - 올캠퍼스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dan.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/mymember/sidebar.jsp"/>
	<h2 class="header">내가 쓴 글</h2>
	<div class="content">
		
		<h3 class="write">[ 자유게시판 ]</h3>
		<c:if test="${count == 0}">
		<div class="result-display">
			표시할 게시물이 없습니다.
		</div>
		</c:if>
		<c:if test="${count > 0}">
		<table>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성일</th>
			</tr>
			<c:forEach var="board" items="${list}">
			<tr>
			<%--글 누르면 상세페이지 이동하는 <a> 추가해야함 --%>
				<td>${board.board_num}</td>
				<td><a href="${pageContext.request.contextPath}/board/detail.do?board_num=${board.board_num}">${board.board_title}</a></td>
				<td>${board.board_reg_date}</td>
			</tr>
			</c:forEach>
		</table><br><br>
		<div class="align-center">${page}</div>
		</c:if>
		
		<br>
		
		<h3 class="write">[ 책방 ]</h3>
		<c:if test="${count2 == 0}">
		<div class="result-display">
			표시할 게시물이 없습니다.
		</div>
		</c:if>
		<c:if test="${count2 > 0}">
		<table>
			<tr>
				<th>글번호</th>
				<th>교재명</th>
				<th>작성일</th>
			</tr>
			<c:forEach var="second" items="${list2}">
			<tr>
				<td>${second.secondhand_num}</td>
				<td><a href="${pageContext.request.contextPath}/secondhand/secondhand_detail.do?secondhand_num=${second.secondhand_num}">${second.secondhand_name}</a></td>
				<td>${second.secondhand_reg_date}</td>
			</tr>
			</c:forEach>
		</table><br><br>
		<div class="align-center">${page2}</div>
		</c:if>
		<br>
		
		<h3 class="write">[ 강의평 ]</h3>
		<c:if test="${count3 == 0}">
		<div class="result-display">
			표시할 게시물이 없습니다.
		</div>
		</c:if>
		<c:if test="${count3 > 0}">
		<table>
			<tr>
				<th>글번호</th>
				<th>강의명</th>
				<th>작성일</th>
			</tr>
			<c:forEach var="course" items="${list3}">
			<tr>
			 <td>${course.eva_num}</td> 
			 <td><a href="${pageContext.request.contextPath}/courseeva/courseeva_detail.do?course_name=${course.courseVO.course_name}&course_prof=${course.courseVO.course_prof}">${course.courseVO.course_name}</a></td>
			 <td>${course.eva_reg_date}</td> 
			</tr>
			</c:forEach>
		</table><br><br>
		<div class="align-center">${page3}</div>
		</c:if>
	</div>
	</div>
</body>
</html>