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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/courseeva.fav.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="page-main">
		<div class="content-main">
			<h2>${course_name}</h2>
			
			교수명 ${course_prof}<br>
			
			${totalgrade}<br><br>
			
			<div class="align-right">
				<input type="button" value="강의평 작성" class="sc-btn" onclick="location.href='secondhand_writeForm.do'">
				<input type="button" value="목록" class="sc-btn" onclick="location.href='secondhand_list.do'">	
				<!-- 등록순, 추천순 정렬 만들기 -->
				
				
				
			</div>
			<c:if test="${count == 0}">
				<div class="result-display2">
					표시할 게시물이 없습니다.
				</div>	
			</c:if>
			<c:if test="${count > 0}">
			<table>
				<c:forEach var="evadetail" items="${list}" varStatus="loop">
					<tr>
						<td>
       						<c:choose>
								<c:when test="${evadetail.eva_star == 4.5}">A+</c:when>
								<c:when test="${evadetail.eva_star == 4}">A</c:when>
								<c:when test="${evadetail.eva_star == 3.5}">B+</c:when>
								<c:when test="${evadetail.eva_star == 3}">B</c:when>
								<c:when test="${evadetail.eva_star == 2.5}">C+</c:when>
								<c:when test="${evadetail.eva_star == 2}">C</c:when>
								<c:when test="${evadetail.eva_star == 1.5}">D+</c:when>
								<c:when test="${evadetail.eva_star == 1}">D</c:when>
								<c:when test="${evadetail.eva_star == 0}">F</c:when>
							</c:choose>
							<div style="float:right">
							<input type="button" value="추천" class="eva_fav_btn" id="output_eva_fav_${loop.index}" data-num="${evadetail.eva_num}">
							<input type="button" value="신고" id="output_eva_complaint" data-num="${evadetail.eva_num}">
							</div><br>
       						${evadetail.eva_star}<br>
       						${evadetail.eva_semester} 수강자
							<img src="${pageContext.request.contextPath}/images/favj_image.png" width="20">
							<span id="output_eva_count_${loop.index}">${evadetail.eva_fav}</span>
							<br>
							${evadetail.eva_content}<br>
						</td>
					</tr>
				</c:forEach>
			</table>
			</c:if>
		</div>
	</div>
</body>
</html>