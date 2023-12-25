<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고게시판 목록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/yen.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
	<div class="content-main">
		<div class="board_listform">
			<h2>자유게시판 신고</h2>	
			<hr width="25%" class="board-underline1">
			<br>
			<c:if test="${count==0}">
			<div class="result-display1">
				표시할 게시물이 없습니다.
			</div>
			</c:if>
			<c:if test="${count > 0}">
			<table class="warn-table">
			<c:forEach var="board" items="${list}" varStatus="status">
				<tr>
					<td>
					<span class="warn_title">${board.board_title}</span><br>
					<span class="warn_content">${board.board_content}</span>
					<div class="align-right">
						<span class="warn_">${board.board_reg_date} | ${board.mem_id}</span>
						<input type="button" value="등급변경" class="input-button1" id="auth_btn${status.count}">
						<script type="text/javascript">
						document.getElementById('auth_btn${status.count}').onclick=function(){
							let choice = confirm('일반회원으로 변경하시겠습니까?');
							if(choice) {
								location.replace('auth.do?mem_num=${board.mem_num}');
							}
						};
						</script>
						<input type="button" value="삭제" class="input-button1" id="delete_btn${status.count}">
						<script type="text/javascript">
						document.getElementById('delete_btn${status.count}').onclick=function(){
							let choice = confirm('신고된 글을 삭제하시겠습니까?')
							if(choice) {
								location.replace('delete.do?board_num=${board.board_num}')
							}
						}
						</script>
						<input type="button" value="복구" class="input-button1" id="show_btn${status.count}">
						<script type="text/javascript">
						document.getElementById('show_btn${status.count}').onclick=function(){
							let choice = confirm('게시글 표시로 변경하시겠습니까?')
							if(choice) {
								location.replace('boardshow.do?board_num=${board.board_num}')
							}
						}
						</script>
					</div>
					</td>
				</tr>
			</c:forEach>
			</table>
			<div style="margin-left:350px;">${page}</div>
			</c:if>
		</div>
		<div class="board_listform">
			<h2 class="header">강의평 신고</h2>	
			<hr width="25%" class="board-underline1">
			<br>
			<c:if test="${count2==0}">
			<div class="result-display1">
				표시할 게시물이 없습니다.
			</div>
			</c:if>
			<c:if test="${count2 > 0}">
			<table class="warn-table">
			<c:forEach var="course_eva" items="${list2}" varStatus="status">
				<tr>
					<td>
					<span class="warn_title">${course_eva.memberVO.mem_id}</span><br>
					<span class="warn_content">${course_eva.eva_content}</span>
					<div class="align-right">
						<span class="warn_">${course_eva.eva_reg_date} | ${course_eva.memberVO.mem_id}</span>
						<input type="button" value="등급변경" class="input-button1" id="auth_btn2${status.count}">
						<script type="text/javascript">
						document.getElementById('auth_btn2${status.count}').onclick=function(){
							let choice = confirm('일반회원으로 변경하시겠습니까?');
							if(choice) {
								location.replace('courseauth.do?mem_num=${course_eva.mem_num}');
							}
						};    
						</script>
						<input type="button" value="삭제" class="input-button1" id="delete_btn2${status.count}">
						<script type="text/javascript">
						document.getElementById('delete_btn2${status.count}').onclick=function(){
							let choice = confirm('신고된 글을 삭제하시겠습니까?')
							if(choice) {
								location.replace('coursedelete.do?eva_num=${course_eva.eva_num}')
							}
						}
						</script>
						<input type="button" value="복구" class="input-button1" id="show_btn2${status.count}">
						<script type="text/javascript">
						document.getElementById('show_btn2${status.count}').onclick=function(){
							let choice = confirm('게시글 표시로 변경하시겠습니까?')
							if(choice) {
								location.replace('courseshow.do?eva_num=${course_eva.eva_num}')
							}
						}
						</script>
					</div>
					</td>
				</tr>
			</c:forEach>
			</table>
			<div style="margin-left:350px;">${page2}</div>
			</c:if>
		</div>
		<div class="board_listform">
			<h2>중고거래 신고</h2>
			<hr width="25%" class="board-underline1">
			<br>	
			<c:if test="${count3==0}">
			<div class="result-display1">
				표시할 게시물이 없습니다.
			</div>
			</c:if>
			<c:if test="${count3 > 0}">
			<table class="warn-table">
			<c:forEach var="secondhand" items="${list3}" varStatus="status">
				<tr>
					<td>
					<span class="warn_title">${secondhand.mem_id}</span><br>
					<span class="warn_content">${secondhand.secondhand_content}</span>
					<div class="align-right">
						<span class="warn_">${secondhand.secondhand_reg_date} | ${secondhand.mem_id}</span>
						<input type="button" value="등급변경" class="input-button1" id="auth_btn3${status.count}">
						<script type="text/javascript">
						document.getElementById('auth_btn3${status.count}').onclick=function(){
							let choice = confirm('일반회원으로 변경하시겠습니까?');
							if(choice) {
								location.replace('secondauth.do?mem_num=${secondhand.mem_num}');
							}
						};
						</script>
						<input type="button" value="삭제" class="input-button1" id="delete_btn3${status.count}">
						<script type="text/javascript">
						document.getElementById('delete_btn3${status.count}').onclick=function(){
							let choice = confirm('신고된 글을 삭제하시겠습니까?')
							if(choice) {
								location.replace('seconddelete.do?secondhand_num=${secondhand.secondhand_num}')
							}
						}
						</script>
						<input type="button" value="복구" class="input-button1" id="show_btn3${status.count}">
						<script type="text/javascript">
						document.getElementById('show_btn3${status.count}').onclick=function(){
							let choice = confirm('게시글 표시로 변경하시겠습니까?')
							if(choice) {
								location.replace('secondshow.do?secondhand_num=${secondhand.secondhand_num}')
							}
						}
						</script>
					</div>
					</td>
				</tr>
			</c:forEach>
			</table>
			<div style="margin-left:350px;">${page3}</div>
			</c:if>
		</div>
	</div>
</div>
</body>
</html>