<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의평 - 올캠퍼스</title>  
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jiwonstyle.css">
<script type="text/javascript">
window.onload = function(){
	let myForm = document.getElementById('search_Form');
	//이벤트 연결
	myForm.onsubmit = function(){
		let keyword = document.getElementById('keyword');
		if(keyword.value.trim()==''){
			alert('검색어를 입력하세요!');
			keyword.value = '';
			keyword.focus();
			return false;
		} 
	};
};
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
	<h2 class="board-title">강의평</h2>
	<hr width="10%" class="board-underline">
	<div class="free_title">
		<form id="search_Form" action="courseeva_list.do" method="get">
			<ul class="search">
				<li>
					<select name="keyfield">
						<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>과목명</option>
						<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>교수명</option>
					</select>
				</li> 
				<li>
					<input type="search" size="16" name="keyword" id="keyword" value="${param.keyword}">
				</li>
				<li>
					<input type="submit" value="검색" class="cal-btn2">
				</li>
			</ul>
		</form>
		<div class="list-space align-right">
			<input type="button" value="강의 평가하기" class="eva-writebtn1" onclick="location.href='evawriteForm.do'"
			<c:if test="${empty user_num}">disabled="disabled"</c:if>
			>
			<input type="button" value="목록" class="eva-writebtn2" onclick="location.href='courseeva_list.do'">
		</div>
		<c:if test="${count == 0}">
		<div class="result-display2">
			표시할 게시물이 없습니다. 
		</div>
		</c:if>
		<c:if test="${count > 0}">
		<table class="eva-table">
			<c:forEach var="courseeva" items="${list}">
			<tr onclick="location.href='courseeva_detail.do?course_name=${courseeva.courseVO.course_name}&course_prof=${courseeva.courseVO.course_prof}'">
				<td>
					<span class="course_name">${courseeva.courseVO.course_name}</span><br>
					<span class="course_prof">${courseeva.courseVO.course_prof}</span><br>
					<img src="${pageContext.request.contextPath}/images/star_icon2.png" width="18" height="18">
					<span class="eva_star">
						<c:choose>
								<c:when test="${courseeva.eva_star == 4.5}">A+</c:when>
								<c:when test="${courseeva.eva_star == 4}">A</c:when>
								<c:when test="${courseeva.eva_star == 3.5}">B+</c:when>
								<c:when test="${courseeva.eva_star == 3}">B</c:when>
								<c:when test="${courseeva.eva_star == 2.5}">C+</c:when>
								<c:when test="${courseeva.eva_star == 2}">C</c:when>
								<c:when test="${courseeva.eva_star == 1.5}">D+</c:when>
								<c:when test="${courseeva.eva_star == 1}">D</c:when>
								<c:when test="${courseeva.eva_star == 0}">F</c:when>
						</c:choose>
					</span><br>
					<span class="eva-semester">${courseeva.eva_semester} 수강자</span><br>
                    <span class="eva-content">${courseeva.eva_content}</span>
				</td>
			</tr>
			</c:forEach>
		</table>
		<div class="align-center">${page}</div>
		</c:if>
	</div>
</div>
</body>
</html>