<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>친구시간표</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyun.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/friend.table.js"></script>
<script type="text/javascript">
window.onload=function(){
	let addForm = document.getElementById('add_form');
	addForm.onsubmit=function(){//이벤트 연결
		let add_friend = document.getElementById('add_friend');
		if(add_friend.value.trim()==''){
			alert('친구 ID를 입력하세요!');
			add_friend.value = '';
			add_friend.focus();
			return false;
		}
	};
	
	let searchForm = document.getElementById('search_form');
	searchForm.onsubmit=function(){//이벤트 연결
		let search_friend = document.getElementById('search_friend');
		if(search_friend.value.trim()==''){
			alert('이름을 입력하세요!');
			search_friend.value = '';
			search_friend.focus();
			return false;
		}
	};
};
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h2>Friend</h2>
	
	<!-- 친구 검색 - 추가 -->
	<form id="add_form" action="friendAdd.do" method="get">
	<div class="search-container">
		<input type="search" size="30" name="add_friend" id="add_friend" class="search-box" placeholder="id를 검색하세요">
		<input type="submit" class="search-button" value="친구 추가">
		</div>
	</form>
	
	
	<!-- 친구 리스트 검색 --><!-- FriendListAction -->
	<form id="search_form" action="friendList.do" method="get">
	<div class="search-container">
		<input type="search" size="30" name="search_friend" id="search_friend" class="search-box" placeholder="친구 이름을 검색하세요">
		<input type="submit" class="search-button" value="친구 검색">
		</div>
	</form>
	
	
	<!-- 친구 리스트 뽑기 --><!-- FriendListAction 전송된거 받아오기 -->
	<div class="friend-val-scroll">
		<c:forEach var="searchFriendList" items="${searchFriendList}">
		<input type="button" id="${searchFriendList.mem_num}" class="friend_val" value="${searchFriendList.mem_name}"><br>
		</c:forEach>
	</div>
	
	<!-- 시간표 table -->
	<table id="timetable" border="1">
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
				<td id="1__${item*60}">월</td>
				<td id="2__${item*60}">화</td>
				<td id="3__${item*60}">수</td>
				<td id="4__${item*60}">목</td>
				<td id="5__${item*60}">금</td>
			</tr>
			<tr>
				<td id="1__${item*60+30}">월</td>
				<td id="2__${item*60+30}">화</td>
				<td id="3__${item*60+30}">수</td>
				<td id="4__${item*60+30}">목</td>
				<td id="5__${item*60+30}">금</td>
			</tr>

		</c:forEach>
		</tbody>
	</table>
	
	
	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	
	
	
	

</body>
</html>