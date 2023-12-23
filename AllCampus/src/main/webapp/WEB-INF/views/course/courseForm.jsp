<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- viewport : 모바일 장치에서 웹 사이트가 원하는 사이즈로 보여지게 처리 -->
<meta name="viewport" content="width=device-width,initial-scale=1"><!-- 뷰포트 넣어주기 반응형 -->
<title>시간표</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/course.hover.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/timetable.add.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyun.css">
<style type="text/css">
/* 헤더 */
.header-style {
	height: 80px;
	width: 1670px;
	margin:15px 0 0 9px;
	padding:10px;
	vertical-align:middle;
	border:1px solid #dcdfe3;
	border-radius:10px;
	background-color:#f2f6fa;
}
.header-style ul{
	list-style:none;
}
.header-style li{
	display:inline-block;
	text-align:center;
	vertical-align:middle;
	margin-left:20px;
	margin-right:83px;
	margin-bottom:15px;
}
.home-logo{
	float:left;
	color:#6699cc;
	font-weight:bold;
	font-family:sans-serif;
	font-size:17px;
}
/* a 태그
------------------------------*/
a {
	text-decoration-line: none;
	color: black;
}
a:hover {
	color: gray;
}
a{
	cursor:pointer;
}
/*-----------------------*/
.friend-list-btn {
  display: inline-block;
  margin-top: 10px;
  padding: 10px 20px;
  font-size: 16px;
  font-weight: bold;
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  border: 2px solid #6699cc;
  color: #3498db;
  background-color: #fff;
  border-radius: 5px;
  transition: background-color 0.3s, color 0.3s;
  float: right;
}

/* Hover effect for the button */
.friend-list-btn:hover {
  background-color: #6699cc;
  color: #fff;
}
/*------------------------*/
</style>
<script type="text/javascript">
$(function(){
	/* $('.course_subject').on('click', (e) => {
		$("#btn-course-subject").val(e.target.value);
		var value = $('#btn-course-subject').val();
		$('#span-course-subject').text(value);
		//return false;
	}); */
	
	/* $('#course_submit_form').submit(funtion(){
		console.log('클릭');
		return false;
	}) */
	/* $("#courseDBtable tr").find("tr").css({"color": "red"});
	 

	 const timetableHover = $("#courseDBtable").find("tr");
	 const result = document.getElementById('result');
	
	 timetableHover.addEventListener('mouseover', (event) => {
		  result.innerHTML+= '<div>mouseover</div>';
	 }); */
	
	/*  $('#courseDBtable tr').on('mouseover', (e) => {
		 console.log('mouseover');
		 $("#courseDBtable tr").css({"color": "red"});
	 });
	  */
	  
	  /* var name = $('#courseDBtable tr');
	  console.log(name[1]);
	  
	  for(let i=0; i<name.length; i++){
	  $(name[i]).on('mouseover', (e) => {
			 console.log('mouseover');
			 $(name[i]).css({"color": "red"});
			 console.log($(name[i]).prop("id"));
			 $('#timetable').css({"color" : "yellow"});
			 
		 });
	  $(name[i]).on('mouseout', (e) => {
			 console.log('mouseoout');
			 $(name[i]).css({"color": ""});
			 $('#timetable').css({"color" : ""});
		 });
	   } */
	 
});
</script>
</head>
<body>

<div class="header-style">
	<ul>
		<li class="myPage home-logo" style="font-family:sans-serif;">
			<a href="${pageContext.request.contextPath}/mymember/myPage.do">
				<img src="${pageContext.request.contextPath}/images/user.png" width="60" height="60" style="margin:0px 10px 0px 50px;">
			</a>
			<span style="font-size:17pt;">[<span>${user_id}</span>]의 시간표</span>
		</li>
	</ul>
</div><br>

	<!-- 
	<form id="search_semester" action="course_list.do" method="get">
			<ul class="search">
				<li>
					<input type="hidden" name="keyfield_hidden" id="keyfield_hidden" value="${keyfield}">
					<select name="keyfield">
					<c:forEach var="semester_list" items="${semester_list}">
						<option value="${semester_list}" <c:if test="${param.keyfield eq semester_list}">selected</c:if>>
							<c:if test="${fn:substring(semester_list,4,5) eq 0}">${fn:substring(semester_list,0,4)} 1학기</c:if>
							<c:if test="${fn:substring(semester_list,4,5) eq 1}">${fn:substring(semester_list,0,4)} 여름 계절</c:if>
							<c:if test="${fn:substring(semester_list,4,5) eq 2}">${fn:substring(semester_list,0,4)} 2학기</c:if>
							<c:if test="${fn:substring(semester_list,4,5) eq 3}">${fn:substring(semester_list,0,4)} 겨울 계절</c:if>
						</option>
					</c:forEach>
					</select>
					

					
				</li>
				<li>
					<input type="submit" value="검색">
				</li>
			</ul>
		</form>
	
	<hr>
	
	 -->
	<div class="timetable-main">
	
	<a href="${pageContext.request.contextPath}/course/course_list.do"><img class="fit-picture" src="${pageContext.request.contextPath}/images/fresh.png" alt="새로고침" style="width:35px;"/></a>
	&nbsp;
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal1" style="background-color:#6699cc; border:none;">
	전공/영역:
	<c:forEach var="course_subject" items="${course_subject}">
	${course_subject}
	</c:forEach>
	</button><!-- 구분 : 팝업창 radio -->
	<!-- 전공/영역 --><!-- The Modal -------------------------------------->
	<div class="modal" id="myModal1">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">전공/영역</h4><!-- ~외 2개 -->
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<form id="course_submit_form" action="course_list.do" method="get"><!-- name이 전송됨 -->
					<%-- hidden 값 시작 --%>
					<c:forEach var="course_subject" items="${course_subject}">
					<input type="hidden" name="course_subject" value="${course_subject}">
					</c:forEach>
					<c:forEach var="course_category" items="${course_category}">
					<input type="hidden" name="course_category" value="${course_category}">
					</c:forEach>
					<c:forEach var="course_credit" items="${course_credit}">
					<input type="hidden" name="course_credit" value="${course_credit}">
					</c:forEach>
					<input type="hidden" name="keyword_hidden" id="keyword" value="${keyword}">
					<input type="hidden" name="keyfield_hidden" id="keyfield" value="${keyfield}">
					<%-- hidden 값 끝 --%>
					<!-- Modal body -->
						<div class="modal-body"><!-- 강의 구분(1:전필/2:전선/3:교필/4:교선/5:기타) -->
						<c:forEach var="course_list" items="${course_list}">
						<input type="checkbox" class="course_subject" name="course_subject" value="${course_list.course_subject}">${course_list.course_subject}<br>
						</c:forEach>
					</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<input type="submit" value="적용">
					</div>
				</form>
				
			</div>
		</div>
	</div>
	
	
	<input type="search" class="search" data-toggle="modal" data-target="#myModal2" placeholder="강의명/교수명 입력" value="${keyword}"><!-- value에 값 넣어주기 -->
	
	<!-- 강의명/교수명 검색 --><!-- The Modal ------------------------------------>
	<div class="modal" id="myModal2">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">강의명/교수명 검색</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<form action="course_list.do" method="get"><!-- name이 전송됨 --><!-- name=value임 -->
					<%-- hidden 값 시작 --%>
					<c:forEach var="course_subject" items="${course_subject}">
					<input type="hidden" name="course_subject" value="${course_subject}">
					</c:forEach>
					<c:forEach var="course_category" items="${course_category}">
					<input type="hidden" name="course_category" value="${course_category}">
					</c:forEach>
					<c:forEach var="course_credit" items="${course_credit}">
					<input type="hidden" name="course_credit" value="${course_credit}">
					</c:forEach>
					<input type="hidden" name="keyword_hidden" id="keyword" value="${keyword}">
					<input type="hidden" name="keyfield_hidden" id="keyfield" value="${keyfield}">
					<%-- hidden 값 끝 --%>
					<!-- Modal body -->
					<div class="modal-body"><!-- 강의명/교수명 검색창 -->
						<input type="text" name="keyword" id="keyword" value="${keyword}">
						<input type="submit" value="검색">
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal4" style="background-color:#6699cc; border:none;">
		구분:
		<c:forEach var="course_category" items="${course_category}">
		${course_category}
		</c:forEach>
	</button><!-- 구분 : 팝업창 radio -->
		
	<!-- 구분 -->	<!-- The Modal -->
	<div class="modal" id="myModal4">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">구분</h4><!-- ~외 2개 -->
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<form action="course_list.do" method="get"><!-- name이 전송됨 -->
					<%-- hidden 값 시작 --%>
					<c:forEach var="course_subject" items="${course_subject}">
					<input type="hidden" name="course_subject" value="${course_subject}">
					</c:forEach>
					<c:forEach var="course_category" items="${course_category}">
					<input type="hidden" name="course_category" value="${course_category}">
					</c:forEach>
					<c:forEach var="course_credit" items="${course_credit}">
					<input type="hidden" name="course_credit" value="${course_credit}">
					</c:forEach>
					<input type="hidden" name="keyword_hidden" id="keyword" value="${keyword}">
					<input type="hidden" name="keyfield_hidden" id="keyfield" value="${keyfield}">
					<%-- hidden 값 끝 --%>
					<!-- Modal body -->
						<div class="modal-body"><!-- 강의 구분(1:전필/2:전선/3:교필/4:교선/5:기타) -->
						<input type="checkbox" class="course_category" name="course_category" value="전필">전필<br>
						<input type="checkbox" class="course_category" name="course_category" value="전선">전선<br>
						<input type="checkbox" class="course_category" name="course_category" value="교필">교필<br>
						<input type="checkbox" class="course_category" name="course_category" value="교선">교선<br>
					</div>

					<!-- Modal footer -->
					<div class="modal-footer">	
						<input type="submit" value="적용">
					</div>
				</form>
				
			</div>
		</div>
	</div>
	
	
	<!-- 학점 -->	<!-- The Modal ------------------------------------->
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal5" style="background-color:#6699cc; border:none;">
		학점
		<c:forEach var="course_credit" items="${course_credit}">
		${course_credit}
		</c:forEach>
	</button><!-- 학점 : 팝업창 radio -->
	
	<div class="modal" id="myModal5">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">학점</h4><!-- ~외 2개 -->
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<form action="course_list.do" method="get"><!-- name이 전송됨 -->
					<%-- hidden 값 시작 --%>
					<c:forEach var="course_subject" items="${course_subject}">
					<input type="hidden" name="course_subject" value="${course_subject}">
					</c:forEach>
					<c:forEach var="course_category" items="${course_category}">
					<input type="hidden" name="course_category" value="${course_category}">
					</c:forEach>
					<c:forEach var="course_credit" items="${course_credit}">
					<input type="hidden" name="course_credit" value="${course_credit}">
					</c:forEach>
					<input type="hidden" name="keyword_hidden" id="keyword" value="${keyword}">
					<input type="hidden" name="keyfield_hidden" id="keyfield" value="${keyfield}">
					<%-- hidden 값 끝 --%>
					<!-- Modal body -->
					<div class="modal-body"><!-- course_credit -->
						<input type="checkbox" name="course_credit" value="1">1학점<br>
						<input type="checkbox" name="course_credit" value="2">2학점<br>
						<input type="checkbox" name="course_credit" value="3">3학점<br>
						<input type="checkbox" name="course_credit" value="4">4학점<br>
					</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<input type="submit" value="적용">
					</div>
				</form>

			</div>
		</div>
	</div>
	




<!-- ------------------------------------------------------------------------------------------------------------------ -->

	<!-- 강의 클릭 시 강의 삭제 모달 -->
	<button id="new_modal" type="button" class="btn btn-primary new-btn" data-toggle="modal" data-target="#deleteModal" style="display:none;"></button>
	<!-- The Modal -->
	<div class="modal" id="deleteModal">
		<div class="modal-dialog">
		<form action="course_list.do" method="get">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 id="delete_header" class="modal-title delete_header"></h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				
					<!-- Modal body -->
					<div class="modal-body delete_body">
						
					</div>
	
					<!-- Modal footer -->
					<div class="modal-footer delete-footer">
						<input type="submit" value="강의 삭제하기">
					</div>
				
			</div>
			</form>
		</div>
	</div>







<!-- ------------------------------------------------------------------------------------------------------------------ -->


	<!-- 모달 예시 -->
	<!-- The Modal -->
	<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Modal Heading</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					Modal body..
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>



<!-- ------------------------------------------------------------------------------------------------------------------ -->


	<input type="button" value="친구목록" class="friend-list-btn" onclick="location.href='friendList.do'"> <br><br><br>


<!-- ------------------------------------------------------------------------------------------------------------------ -->


	<!-- 시간표 table -->
	<!-- timeList : {9,10,11,12,13,14,15,16,17} CourseFormAction -->
	<table id="timetable" border="1">
	<caption>
		<button type="button" id="whole-init" class="btn btn-danger" style="background-color:#d3d3d3; border:none;">시간표 초기화</button>
	</caption>
		<thead><!-- 오류나면 thead tbody 지우기 -->
		<tr>
			<th></th>
			<th>월</th>
			<th>화</th>
			<th>수</th>
			<th>목</th>
			<th>금</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${timeList}" var="item">
			<tr>
				<td rowspan="2">
					<c:if test="${item<12}">오전 ${item}시</c:if>
					<c:if test="${item == 12}">오후 ${item}시</c:if>
					<c:if test="${item>12}">오후 ${item-12}시</c:if>
				</td>
				<td id="1_${item*60}"></td>
				<td id="2_${item*60}"></td>
				<td id="3_${item*60}"></td>
				<td id="4_${item*60}"></td>
				<td id="5_${item*60}"></td>
			</tr>
			<tr>
				<td id="1_${item*60+30}"></td>
				<td id="2_${item*60+30}"></td>
				<td id="3_${item*60+30}"></td>
				<td id="4_${item*60+30}"></td>
				<td id="5_${item*60+30}"></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	


<!-- 시간표 목록
	
		1. 모든 시간표 출력
		2. 필터링된 시간표 출력
	 -->
	 <div style="height: 500px; overflow: auto; width:45%;">
	 <table id="courseDBtable">
	 	<tr id="courseDBtable1">
	 		<th style="color:black;">구분</th>
	 		<th style="color:black;">학수번호</th>
	 		<th style="color:black;">교과목명</th>
	 		<th style="color:black;">교수명</th>
			<th style="color:black;">학점</th>
	 		<th style="color:black;">시간 및 강의실</th>
	 	</tr>
 		<c:forEach var="inner" items="${list2}"><!-- list, list2 : CoursFormAction -->
		 	<tr id="${inner.course_code}"><!-- class명도 넣어주기 -->
		 	<!-- <tr> -->
		 		<td>${inner.course_category}</td>
				<td>${inner.course_code}</td>
		 		<td>${inner.course_name}</td>
		 		<td>${inner.course_prof}</td>
		 		<td>${inner.course_credit}</td>
			 	<td>
			 	<c:forEach var="course" items="${list}">
			 	<%-- <td>${course.course_name} ${course.course_code} </td> --%>
				<c:if test="${course.course_code == inner.course_code}">
					<c:if test="${course.course_day == 1}">월 
					</c:if>
					<c:if test="${course.course_day == 2}">화 
					</c:if>
					<c:if test="${course.course_day == 3}">수 
					</c:if>
					<c:if test="${course.course_day == 4}">목 
					</c:if>
					<c:if test="${course.course_day == 5}">금 
					</c:if>
			 	${course.course_start_time} ${course.course_end_time}<br>
				</c:if>
		 		</c:forEach>
		 		</td>
		 	</tr>
	 	</c:forEach>
	 </table>
	 </div>
	
	
	
	
	<!-- ----------------------------------------------------------------------------------- -->
	
	
	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	
	
	
	</div>
	<!-- ---------------------------------------------------------------------------------------- -->

</body>
</html>