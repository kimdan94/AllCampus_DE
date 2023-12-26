<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/yen.css">
<script type="text/javascript">
window.onload=function(){
	let myForm = document.getElementById('search_form');
	let rows = document.querySelectorAll("tr[data-href]");
	
	//한개의 게시글 클릭시 세부페이지로 이동 
	rows.forEach(function(row) {
        row.addEventListener("click", function() {
            window.location.href = this.dataset.href;
        });
    });
	
	//이벤트 연결
	myForm.onsubmit=function(){
		let keyword = document.getElementById('keyword');
		if(keyword.value.trim()==''){
			alert('검색어를 입력하세요');
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
		<h2 class="board-title">공지사항</h2>
		<hr width="10%" class="board-underline">
		<div class="free_title">
		<form id="search_Form" action="list.do" method="get" class="search-margin">
				<ul class="search">
				<li>
					<select name="keyfield">
						<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>제목</option>
						<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>내용</option>
					</select>
				</li>
				<li>
					<input type="search" size="16" name="keyword" id="keyword" value="${param.keyword}">
				</li>
				<li>
					<input type="submit" value="검색" class="search-btn">
				</li>
			</ul>
		</form>
		<%-- 관리자로 로그인된 계정만
					 글쓰기 가능 (일반회원은 접근 불가)--%>
		<c:if test="${user_auth == 9}">
		<div class="list-space align-right">
			<input type="button" value="글쓰기" class="notice-writebtn" onclick="location.href='writeForm.do'"
			<c:if test="${empty user_num}">disabled="disabled"</c:if>
			>
		</div>
		</c:if>
	</div>
	<c:if test="${count==0}">
	<div class="result-display">
		표시할 게시물이 없습니다.
	</div>
	</c:if>    
	<c:if test="${count > 0}">
	<table class="notice-table">
	<c:forEach var="notice" items="${list}">
		<tr>
			<td>
			<span class="title"><a href="detail.do?notice_num=${notice.notice_num}"> ${notice.notice_title}</a></span><br>
			<span class="reg_date"> ${notice.notice_reg_date} | 관리자</span>
			</td>
		</tr>
	</c:forEach>
	</table>
	<div class="align-center">${page}</div>
	</c:if>
</div>
</body>
</html>