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
<script type="text/javascript">
window.onload=function(){

	
};
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
</style>
</head>
<body>
	<h2>시간표 출력</h2>
 	<!-- 필터링 -->

	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal1">전공/영역:전체</button>
	<!-- 전공/영역 --><!-- The Modal -->
	<div class="modal" id="myModal1">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">전공/영역</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<form action="course_list.do" method="get"><!-- name이 전송됨 --><!-- name=value임 -->
					<!-- Modal body -->
					<div class="modal-body">
						<input type="submit" id="course_subject" name="course_subject" value="교양"><br>
						<input type="submit" id="course_subject" name="course_subject" value="소프트웨어학부"><br>
						<input type="submit" id="course_subject" name="course_subject" value="컴퓨터공학부"><br>
						<input type="submit" id="course_subject" name="course_subject" value="화학공학부"><br>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
	<input type="search" class="search" data-toggle="modal" data-target="#myModal2" placeholder="강의명/교수명 입력">
	<!-- 강의명/교수명 검색 --><!-- The Modal -->
	<div class="modal" id="myModal2">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">강의명/교수명 검색</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<form action="course_list.do" method="get"><!-- name이 전송됨 --><!-- name=value임 -->
					<!-- Modal body -->
					<div class="modal-body"><!-- 강의명/교수명 검색창 -->
						<input type="search" name="keyword" id="keyword" value="${param.keyword}">
						<input type="submit" value="검색">
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal3">정렬:기본</button><!-- 정렬 : 팝업창 check box -->
	<!-- 정렬 --><!-- The Modal -->
	<div class="modal" id="myModal3">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">정렬</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				
				<form action="course_list.do" method="get"><!-- name이 전송됨 -->
					<!-- Modal body -->
					<div class="modal-body">
						<div class="modal-body">
							<div><input type="radio" name="course_sort" value="sort_default" checked>기본</div>
							<div><input type="radio" name="course_sort" value="course_name">과목명</div>
							<div><input type="radio" name="course_sort" value="course_code">과목코드</div>
						</div>
					</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<input type="submit" value="적용">
					</div>
				</form>

			</div>
		</div>
	</div>

	
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal4">구분:전체</button><!-- 구분 : 팝업창 radio -->
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
					<!-- Modal body -->
					<div class="modal-body"><!-- 강의 구분(1:전필/2:전선/3:교필/4:교선/5:기타) -->
						<input type="checkbox" name="course_category" value="전필">전필<br>
						<input type="checkbox" name="course_category" value="전선">전선<br>
						<input type="checkbox" name="course_category" value="교필">교필<br>
						<input type="checkbox" name="course_category" value="교선">교선<br>
						<input type="checkbox" name="course_category" value="기타">기타<br>
					</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<input type="submit" value="적용">
					</div>
				</form>
				
			</div>
		</div>
	</div>
	
	
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal5">학점:전체</button><!-- 학점 : 팝업창 radio -->
	<!-- 학점 -->	<!-- The Modal -->
	<div class="modal" id="myModal5">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">학점</h4><!-- ~외 2개 -->
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<form action="course_list.do" method="get"><!-- name이 전송됨 -->
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
	 
	 <table>
	 	<tr>
	 		<th>구분</th>
	 		<th>학수번호</th>
	 		<th>교과목명</th>
	 		<th>교수명</th>
			<th>학점</th>
	 		<th>시간 및 강의실</th>
	 		<th>강의평</th>
	 	</tr>
	 	<c:forEach var="course" items="${list}">
		 	<tr>
		 		<th>${course.course_name}</th>
		 		<th>${course.course_prof}</th>
		 		<th>${course.course_subject}</th>
		 		<th>${course.course_start_time}</th>
		 		<th>${course.course_end_time}</th>
		 	</tr>
	 	</c:forEach>
	 </table>
	<br><br>
	<!-- ----------------------------------------------------------------------------------- -->
	
	<!-- 시간표 table -->
	<table border="1">
		<tr>
			<th></th>
			<th>월</th>
			<th>화</th>
			<th>수</th>
			<th>목</th>
			<th>금</th>
		</tr>
		<tr>
			<td rowspan="2">오전9시</td>
			<td>mon</td>
			<td>tue</td>
			<td>wed</td>
			<td>thur</td>
			<td>fri</td>
		</tr>
		<tr>
			<td>mon</td>
			<td>tue</td>
			<td>wed</td>
			<td>thur</td>
			<td>fri</td>
		</tr>
		<tr>
			<td rowspan="2">오전10시</td>
			<td>mon</td>
			<td>tue</td>
			<td>wed</td>
			<td>thur</td>
			<td>fri</td>
		</tr>
		<tr>
			<td>mon</td>
			<td>tue</td>
			<td>wed</td>
			<td>thur</td>
			<td>fri</td>
		</tr>
		<tr>
			<td rowspan="2">오전11시</td>
			<td>mon</td>
			<td>tue</td>
			<td>wed</td>
			<td>thur</td>
			<td>fri</td>
		</tr>
		<tr>
			<td>mon</td>
			<td>tue</td>
			<td>wed</td>
			<td>thur</td>
			<td>fri</td>
		</tr>
		<tr>
			<td rowspan="2">오후12시</td>
			<td>mon</td>
			<td>tue</td>
			<td>wed</td>
			<td>thur</td>
			<td>fri</td>
		</tr>
		<tr>
			<td>mon</td>
			<td>tue</td>
			<td>wed</td>
			<td>thur</td>
			<td>fri</td>
		</tr>
		<tr>
			<td rowspan="2">오후1시</td>
			<td>mon</td>
			<td>tue</td>
			<td>wed</td>
			<td>thur</td>
			<td>fri</td>
		</tr>
		<tr>
			<td>mon</td>
			<td>tue</td>
			<td>wed</td>
			<td>thur</td>
			<td>fri</td>
		</tr>
		<tr>
			<td rowspan="2">오후2시</td>
			<td>mon</td>
			<td>tue</td>
			<td>wed</td>
			<td>thur</td>
			<td>fri</td>
		</tr>
		<tr>
			<td>mon</td>
			<td>tue</td>
			<td>wed</td>
			<td>thur</td>
			<td>fri</td>
		</tr>
		<tr>
			<td rowspan="2">오후3시</td>
			<td>mon</td>
			<td>tue</td>
			<td>wed</td>
			<td>thur</td>
			<td>fri</td>
		</tr>
		<tr>
			<td>mon</td>
			<td>tue</td>
			<td>wed</td>
			<td>thur</td>
			<td>fri</td>
		</tr>
		<tr>
			<td rowspan="2">오후4시</td>
			<td>mon</td>
			<td>tue</td>
			<td>wed</td>
			<td>thur</td>
			<td>fri</td>
		</tr>
		<tr>
			<td>mon</td>
			<td>tue</td>
			<td>wed</td>
			<td>thur</td>
			<td>fri</td>
		</tr>
		<tr>
			<td rowspan="2">오후5시</td>
			<td>mon</td>
			<td>tue</td>
			<td>wed</td>
			<td>thur</td>
			<td>fri</td>
		</tr>
		<tr>
			<td>mon</td>
			<td>tue</td>
			<td>wed</td>
			<td>thur</td>
			<td>fri</td>
		</tr>
		<tr>
			<td rowspan="2">오후6시</td>
			<td>mon</td>
			<td>tue</td>
			<td>wed</td>
			<td>thur</td>
			<td>fri</td>
		</tr>
		<tr>
			<td>mon</td>
			<td>tue</td>
			<td>wed</td>
			<td>thur</td>
			<td>fri</td>
		</tr>
	</table>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<!-- ---------------------------------------------------------------------------------------- -->




<!-- 아이콘 <a href="https://www.flaticon.com/kr/free-icons/-" title="다시 하다 아이콘">다시 하다 아이콘  제작자: Andrean Prabowo - Flaticon</a> -->

</body>
</html>