<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>친구시간표</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyun.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/friend.table.js"></script>
<script type="text/javascript">
window.onload=function(){
	let myForm = document.getElementById('myForm');
	//이벤트 연결
	myForm.onsubmit=function(){
		let add_friend = document.getElementById('add_friend');
		if(add_friend.value.trim()==''){
			alert('id를 입력하세요!');
			add_friend.value = '';
			add_friend.focus();
			return false;
		}
	};
};
</script>
<style type="text/css">
/* 공통 style */

/* 전체 레이아웃
------------------------------*/
body {
	margin:0 auto;
	background-color: #FFFFFF;
	
}
.page-main{
	width:1650px;
	margin:40px 0;
	min-height:850px;
	overflow:hidden;
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
.sc-btn{
	border:1px solid #e1e6ed;
	width:80px;
	height:25px;
	border-radius:5px;
	color:black;
	font-weight:bold;
}

/* 헤더
------------------------------*/
.header-style {
	height: 80px;
	width: 1670px;
	margin:15px 0 0 9px;
	padding:0;
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
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/><br><br>
	<div class="friend-search-area" style="margin-left:200px;">
	<!-- 친구 검색 - 추가 -->
	<form id="myForm" action="friendAdd.do" method="get">
	<h4>친구 id를 검색하세요</h4>
	<div class="search-container">
		<input type="search" size="30" name="add_friend" id="add_friend" class="search-box" placeholder="id를 검색하세요">
		<input type="submit" class="search-button" value="친구 추가">
		</div>
	</form>
	
	
		<div class="friend-list-area">
			
			<!-- 친구 리스트 검색 --><!-- FriendListAction -->
			<form action="friendList.do" method="get">
			<h4>친구 이름을 검색하세요&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/course/friendList.do"><img class="fit-picture" src="${pageContext.request.contextPath}/images/fresh.png" alt="새로고침" style="width:20px;"/></a></h4>
			<div class="search-container">
				<input type="search" size="30" name="search_friend" id="search_friend" class="search-box" placeholder="친구 이름을 검색하세요">
				<input type="submit" class="search-button" value="친구 검색">
				</div>
			</form><br>
		
		
			<!-- 친구 리스트 뽑기 --><!-- FriendListAction 전송된거 받아오기 -->
			<div class="friend-val-scroll">
				<c:forEach var="searchFriendList" items="${searchFriendList}">
				<input type="button" id="${searchFriendList.mem_num}" class="friend_val" value="${searchFriendList.mem_name}"><br>
				</c:forEach>
			</div>
		</div>
		
	</div><br><br>
	
	
	
	
	
	<!-- 시간표 table -->
	<table id="timetable" class="friend-timetable" border="1">
	   <thead>
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
			<!-- timeList : {9,10,11,12,13,14,15,16,17} CourseFormAction -->
			<tr>
				<td rowspan="2">
					<c:if test="${item<12}">오전 ${item}시</c:if>
					<c:if test="${item == 12}">오후 ${item}시</c:if>
					<c:if test="${item>12}">오후 ${item-12}시</c:if>
				</td>
				<td id="1__${item*60}"></td>
				<td id="2__${item*60}"></td>
				<td id="3__${item*60}"></td>
				<td id="4__${item*60}"></td>
				<td id="5__${item*60}"></td>
			</tr>
			<tr>
				<td id="1__${item*60+30}"></td>
				<td id="2__${item*60+30}"></td>
				<td id="3__${item*60+30}"></td>
				<td id="4__${item*60+30}"></td>
				<td id="5__${item*60+30}"></td>
			</tr>

		</c:forEach>
		</tbody>
	</table>
	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	
	
	

</body>
</html>