<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- viewport : 모바일 장치에서 웹 사이트가 원하는 사이즈로 보여지게 처리 -->
<meta name="viewport" content="width=device-width,initial-scale=1"><!-- 뷰포트 넣어주기 반응형 -->
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/course.hover.js"></script>
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
	  
	  /* var name = $('#courseDBtable tr'); */
	  /* console.log(name[1]);
	   */
	  /*  for(let i=0; i<name.length; i++){
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

.banner:hover + .winner {
  background:#e54737; /*변경할 배경색*/
}
.courseDBtable>tr:hover {
	background-color: red;
}
/* #courseDBtable tr {
	background-color: blue;
} */
</style>
</head>
<body>
	<h2>시간표 출력</h2>
 	<!-- 필터링 --><!-- value 값 넣어주기 -->
	<div id='result'>
</div>
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

<!-- 시간표 목록
	
		1. 모든 시간표 출력
		2. 필터링된 시간표 출력
	 -->
	 
	 <table id="courseDBtable">
	 	<tr id="courseDBtable1">
	 		<th>구분</th>
	 		<th>학수번호</th>
	 		<th>교과목명</th>
	 		<th>교수명</th>
			<th>학점</th>
	 		<th>시간 및 강의실</th>
	 		<th>강의평</th>
	 	</tr>
 		<c:forEach var="inner" items="${list2}">
		 	<tr id="${inner.course_code}">
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
			<tr>
				<th rowspan="2">${item}</th>
				<th id="1_${item*60}">값</th>
				<th id="2_${item*60}"></th>
				<th id="3_${item*60}"></th>
				<th id="4_${item*60}"></th>
				<th id="5_${item*60}"></th>
			</tr>
			<tr>
				<th id="1_${item*60+30}">값</th>
				<th id="2_${item*60+30}"></th>
				<th id="3_${item*60+30}"></th>
				<th id="4_${item*60+30}"></th>
				<th id="5_${item*60+30}"></th>
			</tr>
			
		</c:forEach>
		
	</table>
	
	
	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	
	<!-- ---------------------------------------------------------------------------------------- -->

	<div class="banner">asdf
	</div>
	<div class="winner">qwer
	</div>



<!-- 아이콘 <a href="https://www.flaticon.com/kr/free-icons/-" title="다시 하다 아이콘">다시 하다 아이콘  제작자: Andrean Prabowo - Flaticon</a> -->

</body>
</html>