<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jiwonstyle.css">
<script type="text/javascript">
window.onload=function(){
	let myForm = document.getElementById('search_Form');
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
		<h2 class="board-title">자유게시판</h2>
		<hr width="10%" class="board-underline">
	<div class="content-main">
		<form id="search_Form" action="list.do" method="get">
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
					<input type="submit" value="검색" class="cal-btn2">
				</li>
			</ul>
		</form>
		
		<div class="blist-space align-right">
			<input type="button" value="글 작성하기" class="board-btn" onclick="location.href='writeForm.do'"
			<c:if test="${empty user_num}">disabled="disabled"</c:if>
			>
		</div>
		
		<div class="board_listform">
			<c:if test="${count==0}"> 
			<div class="result-display">
				표시할 게시물이 없습니다.
			</div>
			</c:if>
			<c:if test="${count > 0}">
			<table class="board-table">
			<c:forEach var="board" items="${list}">
				<tr data-href="detail.do?board_num=${board.board_num}">
					<td>
					<span class="board1-title">${board.board_title}</span><br>
					<span class="board1-content">${board.board_content}</span><br>
			
					<span class="board-date">${board.board_reg_date}</span> 
					<span class="pipe">|</span>
					<!-- 익명 여부  1: 익명X   2: 익명 -->
					<span class="pipe">
					<c:if test="${board.board_anonymous ==1}">${board.mem_id}</c:if>
					<c:if test="${board.board_anonymous ==2}">익명</c:if>
					</span>
					</td>
				</tr>
			</c:forEach>
			</table>
			<div class="align-center">${page}</div>
			</c:if>
		</div>
	</div>
</div>
</body>
</html>