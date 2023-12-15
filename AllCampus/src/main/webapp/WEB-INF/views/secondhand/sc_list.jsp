<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책방 - 올캠퍼스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jy.css">
<script type="text/javascript">
window.onload = function(){
	let myForm = document.getElementById('search_form');
	//이벤트 연결
	myForm.onsubmit = function(){
		let keyword = document.getElementById('keyword');
		if(keyword.value.trim()==''){
			alert('검색어를 입력하세요!');
			keyword.value = '';
			keyword.focus();
			return false;
		}
	};
};
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
	<h2>책방</h2>
	<form id="search_form" action="secondhand_list.do" method="get" class="sc-margin">
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>교재명</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>저자명</option>
				</select>
			</li>
			<li>
				<input type="search" size="20" name="keyword" id="keyword"
													value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="검색" class="sc-btn2">
			</li>
		</ul>
	</form>
	<div class="align-right">
		<input type="button" value="판매하기" class="sc-btn"
			onclick="location.href='secondhand_writeForm.do'">
		<input type="button" value="목록" class="sc-btn"
			onclick="location.href='secondhand_list.do'">	
	</div>
	<c:if test="${count == 0}">
		<div class="result-display2">
			표시할 게시물이 없습니다.
		</div>	
	</c:if>
	<c:if test="${count > 0}">
		<table class="sc-table">
		<c:forEach var="sc" items="${list}">
			<tr>
				<td rowspan="5">
					<img src="${pageContext.request.contextPath}/upload/${sc.secondhand_filename}" width="60">
				</td>
			</tr>
			<tr>
				<td>
					<a href="${pageContext.request.contextPath}/secondhand/secondhand_detail.do?secondhand_num=${sc.secondhand_num}"><b>${sc.secondhand_name}</b></a>
				</td>
			</tr>
			<tr style="font-size:12px;">
				<td>${sc.secondhand_writer}</td>
			</tr>
			<tr style="font-size:10px;color:gray;">
				<td>${sc.secondhand_company}</td>
			</tr>
			<tr style="color:#6699cc;font-weight:bold;font-size:13px;">
				<td><fmt:formatNumber value="${sc.secondhand_price}"/>원</td>
			</tr>
		</c:forEach>	
		</table>
		<div class="align-center" style="margin-left:420px;">${page}</div>
	</c:if>	
</div>
</body>
</html>