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
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/timetable.print.js"></script> --%>
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
<style type="text/css">
body {
  position: relative;
}
  table {
    width: 60%;
    border: 1px solid #444444;
  }
  th, td {
    border: 1px solid #444444;
  }
  .banner{
  width:100%; /*가로사이즈*/
  height:100px; /*세로사이즈*/
  background: blue; /*배경색*/
  margin:0 auto; /*중앙정렬*/
  }

/*--------------------------------------------------*/
/* 공통 style */

/* 전체 레이아웃
------------------------------*/
body {
	margin:0 auto;
	background-color: #FFFFFF;
}
.page-main{
	width:100%;
	margin:40px 0;
	min-height:850px;
}

/* 공통 정렬
------------------------------*/
.align-center{
	text-align:center;
}
.align-right{
	text-align:right;
}

/* 헤더
------------------------------*/
.header-style {
	height: 80px;
	width: 99%;
	margin:15px 0 0 7px;
	padding:2px;
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
.clear{
	clear:both;
}
.myPage{
	float:right;
}
/* 공통 등록, 수정 폼
------------------------------*/
form{
	width:1000px;
	margin:0 auto;
	padding:10px;
}
.form-clear {
	clear:both;
}
.form-float {
	width:130px;
	float:left;
}
ul{
	list-style:none;
}
input{
	margin-top:4px;
}


/* 버튼
------------------------------*/
.all-btn {
	background-color: #6699cc;
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

/* font 크기/색상
------------------------------*/
.font-style {
	color:#000;
	font-size:10pt;
}
h2 {
	margin: 10px;
}
/*--------------------------------------------------*/

/* 공통
------------------------------*/
.page-main-custom{
	width:100%;
	margin:40px 0;
	min-height:800px;
}
a{
	cursor:pointer;	
}
input[type="button"]{
	cursor:pointer;
}
input[type="submit"]{
	cursor:pointer;
}
input:focus{
	outline:none;
}
.copyright{
	font-size:10px;
	color:gray;
}
.result-display{
	width:600px;
	height:400px;
	margin:200px 530px 0 530px;
	border:1px solid #000;
	display:flex;
	align-items:center;
	justify-content:center;
}
.result-display2{
	width:600px;
	height:400px;
	margin:50px 560px;
	border:1px solid #000;
	display:flex;
	align-items:center;
	justify-content:center;
}
.page-sub{
	text-align:center;
	font-size:12px;
}
.page-sub2{
	text-align:center;
	font-size:12px;
}
.member-style{
	margin-left:300px;
	margin-top:200px;
}
.input-button1{
	border:1px solid #e1e6ed;
	width:200px;
	height:45px;
	border-radius:10px;
	font-weight:bold;
	color:gray;
	font-size:15px;
}
.input-button2{
	border:none;
	width:200px;
	height:45px;
	border-radius:10px;
	background-color:#6699cc;
	font-weight:bold;
	color:white;
	font-size:15px;
}
.sc-btn{
	border:1px solid #e1e6ed;
	width:80px;
	height:25px;
	border-radius:5px;
	color:black;
	font-weight:bold;
}
.sc-btn2{
	border:none;
	width:90px;
	height:25px;
	border-radius:10px;
	background-color:#6699cc;
	font-weight:bold;
	color:white;
}
.home-btn{
	border:none;
	width:80px;
	height:25px;
	border-radius:6px;
	background-color:#6699cc;
	font-weight:bold;
	color:white;
}
.a-style{
	text-decoration:none;
	color:#575959;
	font-size:11px;
}
.bottom-style{
	text-decoration:none;
	color:gray;
	font-size:10px;
	margin-bottom:5px;
}
.find-link{
	font-size:12px;
}
.find-link-pick{
	font-size:12px;
	text-decoration:overline;
	text-decoration-color:#6699cc;
}
textarea{
	resize:none;
}
.form-notice{
	font-size:10pt;
	color:gray;
}
.mem-logo{
	margin:140px 0 -65px -60px;	
}
.bottom{
	margin-bottom:3px;
	margin-left:-60px;
}
.important{
	color:#6699cc;
	font-weight:bold;
	font-size:20px;
	font-family:sans-serif;
}
.finds{
	margin:77px 58px -100px 0;
}
/* 메인 : main.jsp
------------------------------*/
.main-style{
	padding-top:300px;
}
/* 홈 : home.jsp
------------------------------ */
.home-div{
	width:30%;
	float:left;
	padding:5px;
	margin:70px -390px 0 400px;
}
.home-div2{
	width:975px;
	padding:5px;
	float:left;
	border-radius:10px;
	border:1px solid #edeff0;
	padding-left:33px;
	padding-right:25px;
	margin-left:477px;
	margin-top:15px;
}
.home-end{
	clear:both;
}
.myInfo-div{
	width:9%;
	height:200px;
	float:left;
	margin:70px -300px 0 220px;
	border-radius:10px;
	background:#f5f7f7;
}
.myInfo-div img{
	width:70px;
	border-radius:50%;
	object-fit:cover;
	object-position:top;
	margin:25px 35px 10px 35px;
}
.univName-style{
	margin:0 0 -50px 230px;
}
.list-div{
	height:200px;
	border:1px solid #edeff0;
	border-radius:15px;
}
.list-div table{
	padding:35px 0 0 80px;
}
.list-margin{
	padding-left:30px;
	font-size:12px;
}
.info-div{
	float:left;
	padding:5px;
	margin:285px -350px 0 117px;
}
.info-div2{
	float:left;
	padding:5px;
	margin:420px -350px 0 117px;
}
.info-div3{
	float:left;
	padding:5px;
	margin:556px -350px 0 117px;
}

/*--------------------------------------------------*/
</style>
</head>
<body>

<!-- header 시작 -->
<div class="header-style">
	<ul>
	<c:if test="${!empty user_num}">
		<li class="home-logo">
			<a href="${pageContext.request.contextPath}/main/home.do">
			<img src="${pageContext.request.contextPath}/images/logo_symbol_231208.png" width="100" style="margin-top:-10px;">	
			</a>
			All CAMPUS
		</li>
		<li class="clear">
			<h2>
			<a href="${pageContext.request.contextPath}/course/course_list.do">시간표</a>
			</h2>
		</li>
		<li class="clear">
			<h2>
			<a href="${pageContext.request.contextPath}/courseeva/courseeva_list.do">강의평</a>
			</h2> 
		</li>
		<li class="clear">
			<h2>
			<a href="${pageContext.request.contextPath}/calculator/calculator_list.do">학점 계산기</a>
			</h2>
		</li>
		<li class="clear">
			<h2>
			<a href="${pageContext.request.contextPath}/secondhand/secondhand_list.do">책방</a>
			</h2>
		</li>
		<c:if test="${!empty user_num && user_auth == 9}">
		<li class="clear">
			<h2>
			<a href="${pageContext.request.contextPath}/admin/adminMenu.do">관리</a>
			</h2>
		</li>
		</c:if>	
		<li class="myPage">
			<a href="${pageContext.request.contextPath}/mymember/myPage.do">
				<img src="${pageContext.request.contextPath}/images/user.png" width="40" height="40" style="margin-right:10px;margin-top:5px;">
			</a>
			[<span>${user_id}</span>]
			<input type="button" value="로그아웃" class="sc-btn"
				onclick="location.href='${pageContext.request.contextPath}/member/logout.do'">
		</li>
	</c:if>		
	</ul>
</div>
<!-- header 끝 -->

	<h2>시간표 출력</h2>${keyfield}

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
	
	
	<a href="${pageContext.request.contextPath}/course/course_list.do"><img class="fit-picture" src="${pageContext.request.contextPath}/images/fresh.png" alt="새로고침" style="width:35px;"/></a>

	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal1">
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
						<!-- <input type="checkbox" id="course_subject1" class="course_subject" name="course_subject" value="교양">교양<br>
						<input type="checkbox" id="course_subject2" class="course_subject" name="course_subject" value="소프트웨어학부">소프트웨어학부<br>
						<input type="checkbox" id="course_subject3" class="course_subject" name="course_subject" value="컴퓨터공학부">컴퓨터공학부<br>
						<input type="checkbox" id="course_subject4" class="course_subject" name="course_subject" value="화학공학부">화학공학부<br> -->
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
	
	
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal4">
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
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal5">
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


	<input type="button" value="친구목록" onclick="location.href='friendList.do'">


<!-- ------------------------------------------------------------------------------------------------------------------ -->

<!-- 시간표 목록
	
		1. 모든 시간표 출력
		2. 필터링된 시간표 출력
	 -->
	 
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
			 	${course.course_day} ${course.course_start_time} ${course.course_end_time}<br>
				</c:if>
		 		</c:forEach>
		 		</td>
		 	</tr>
	 	</c:forEach>
	 </table>
	<br><br>
	
	
	
	
	
	<!-- ----------------------------------------------------------------------------------- -->
	
	
	<!-- 시간표 table -->
	<table id="timetable" border="1">
		<tr>
			<th></th>
			<th>월</th>
			<th>화</th>
			<th>수</th>
			<th>목</th>
			<th>금</th>
		</tr>
		<c:forEach items="${timeList}" var="item">
			<!-- timeList : {9,10,11,12,13,14,15,16,17} CourseFormAction -->
			<tr>
				<td rowspan="2">${item}</td>
				<td id="1_${item*60}">월</td>
				<td id="2_${item*60}">화</td>
				<td id="3_${item*60}">수</td>
				<td id="4_${item*60}">목</td>
				<td id="5_${item*60}">금</td>
			</tr>
			<tr>
				<td id="1_${item*60+30}">월</td>
				<td id="2_${item*60+30}">화</td>
				<td id="3_${item*60+30}">수</td>
				<td id="4_${item*60+30}">목</td>
				<td id="5_${item*60+30}">금</td>
			</tr>
			
		</c:forEach>
	</table>
	
	
	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	
	<!-- ---------------------------------------------------------------------------------------- -->

<%-- 
	<table id="timetable" border="1">
		<tr>
			<th></th>
			<th>월</th>
			<th>화</th>
			<th>수</th>
			<th>목</th>
			<th>금</th>
		</tr>
			<tr>
				<td rowspan="2">${item}</td>
				<td>값</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td style="display:none"></td>
				<td>값</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
	</table> 

--%>



<!-- 아이콘 <a href="https://www.flaticon.com/kr/free-icons/-" title="다시 하다 아이콘">다시 하다 아이콘  제작자: Andrean Prabowo - Flaticon</a> -->

</body>
</html>