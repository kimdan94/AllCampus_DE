<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의평 리스트</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jiwonstyle.css">
</head>
<body>
<div class="main_page">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="free_title">
		
		<form id="search_Form" action="list.do" method="get">
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
					<input type="submit" value="검색">
				</li>
			</ul>
		</form>
		<div class="list-space align-right">
			<input type="button" value="강의평 작성" onclick="location.href='evawriteForm.do'"
			<c:if test="${empty user_num}">disabled="disabled"</c:if>
			>
			<input type="button" value="목록" onclick="location.href='courseeva_list.do'">
		</div>
		<c:if test="${count == 0}">
		<div class="result-display">
			표시할 게시물이 업습니다. 
		</div>
		</c:if>
		<c:if test="${count > 0}">
		<table>
			<c:forEach var="courseeva" items="${list}">
			<tr>
				<td>
					${courseeva.courseVO.course_name}<br>
					${courseeva.courseVO.course_prof}<br>
					${courseeva.eva_star}<br>
					${courseeva.eva_semester}<br>
					${courseeva.eva_content}
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