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
<script type="text/javascript">
$(function(){
	$('.eva_complaint_btn').click(function(){
		var index = $(this).attr('data-index');
		
		let choice = confirm('신고하시겠습니까?');
		if(choice){
			$.ajax({ 
				url:'evaComplaint.do',
				type:'post',
				data:{eva_num:$('#output_eva_complaint_' + index).attr('data-num')},
				dataType:'json', 
				success:function(param){
					if(param.status == 'success'){
						alert("신고 완료되었습니다.");
						location.href='courseeva_detail.do?course_name=${course_name}&course_prof=${course_prof}';
					}else if(param.status == 'duplicated'){
						alert('이미 신고 처리된 게시글입니다.');
						location.href='courseeva_detail.do?course_name=${course_name}&course_prof=${course_prof}';
					}else if(param.status == 'noLogin'){
						alert('로그인 후 이용 가능합니다.');
						location.href='${pageContext.request.contextPath}/member/loginForm.do';
					}else if(param.status == 'noCertify'){
						alert('학교 인증을 마친 학생들만 이용할 수 있어요!');
					}else{
						alert("신고 처리 오류 발생");
					}
				},
				error:function(){
					alert('이미 신고되어 숨겨진 게시물입니다.');
				}
			});
		}
	});
});
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="page-main">
		<h2 class="board-title">강의평 상세</h2>
		<hr width="10%" class="board-underline">
		<div class="content-main">
			<h2>${course_name}</h2>
			
			교수명 ${course_prof}<br>
			
			${totalgrade}<br><br>
			
			<div class="align-right">
				<input type="button" value="강의 평가하기" class="eva-writebtn1" onclick="location.href='evawriteForm.do'">
				<input type="button" value="목록" class="eva-writebtn2" onclick="location.href='courseeva_list.do'">	
				<!-- 등록순, 추천순 정렬 만들기 -->
				
				
				
			</div>
			<c:if test="${count == 0}">
				<div class="result-display2">
					표시할 게시물이 없습니다.
				</div>	
			</c:if>
			<c:if test="${count > 0}">
			
			<table>
				<c:forEach var="evadetail" items="${list}" varStatus="status">
					<tr>
						<td>
							<img src="${pageContext.request.contextPath}/images/star_icon2.png" width="18" height="18">
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
							<input type="button" value="추천" class="eva_fav_btn" id="output_eva_fav_${status.index}" data-num="${evadetail.eva_num}" data-index="${status.index}">
							<c:if test="${user_num != evadetail.mem_num}">
							<input type="button" value="신고" class="eva_complaint_btn" id="output_eva_complaint_${status.index}" data-num="${evadetail.eva_num}" data-index="${status.index}">
							</c:if>
							</div><br>
       						
       						${evadetail.eva_semester} 수강자
							<img src="${pageContext.request.contextPath}/images/redfav.png" width="18">
							<span style="color:red;" id="output_eva_count_${status.index}">${evadetail.eva_fav}</span>
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