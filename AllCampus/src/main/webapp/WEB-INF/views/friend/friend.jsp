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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/friend.table.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h2>Friend</h2>
	
	<!-- 친구 검색 - 추가 -->
	<form action="friendAdd.do" method="get">
		<input type="search" size="30" name="add_friend" id="add_friend" placeholder="id를 검색하세요">
		<input type="submit" value="친구 추가">
	</form>
	
	
	<!-- 친구 리스트 검색 --><!-- FriendListAction -->
	<form action="friendList.do" method="get">
		<input type="search" size="30" name="search_friend" id="search_friend" placeholder="친구 이름을 검색하세요">
		<input type="submit" value="친구 검색">
	</form>
	
	<!-- 친구 리스트 뽑기 --><!-- FriendListAction 전송된거 받아오기 -->
	<c:forEach var="searchFriendList" items="${searchFriendList}">
	<input type="button" id="${searchFriendList.mem_num}" class="friend_val" value="${searchFriendList.mem_name}"><br>
	<%-- <div>${searchFriendList.mem_num} ${searchFriendList.mem_name} ${searchFriendList.univ_num} ${searchFriendList.mem_major}</div> --%>
	</c:forEach>
	
	
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
				<td rowspan="2">${item}</td>
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