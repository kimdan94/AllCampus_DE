<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의평 상세 정보</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jiwonstyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="page-main">
		<div class="content-main">
			<h2>${course_name}</h2>
			
			과목명 ${course_name}<br>
			교수명 ${course_prof}<br>
			
			${totalgrade}<br><br>
			
			
			<c:if test="${count > 0}">
			<c:forEach var="evadetail" items="${list}">
				 <c:choose>
                        <c:when test="${evadetail.eva_star == 4.5}">A+</c:when>
                        <c:when test="${evadetail.eva_star == 4}">A</c:when>
                        <c:when test="${evadetail.eva_star == 3.5}">B+</c:when>
                        <c:when test="${evadetail.eva_star == 3}">B</c:when>
                        <c:when test="${evadetail.eva_star == 2.5}">C+</c:when>
                        <c:when test="${evadetail.eva_star == 2}">C</c:when>
                        <c:when test="${evadetail.eva_star == 1.5}">D+</c:when>
                        <c:when test="${evadetail.eva_star == 1}">D</c:when>
                        <c:when test="${evadetail.eva_star == 0}">D</c:when>
                    </c:choose><br>
				
				
				${evadetail.eva_star}<br>
				${evadetail.eva_semester}<br>
				${evadetail.eva_content}<br>
				
			</c:forEach>
			</c:if>
			
		
		
		
		</div>



</div>


</body>
</html>