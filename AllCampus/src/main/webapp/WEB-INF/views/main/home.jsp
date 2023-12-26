<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>올캠퍼스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jy.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<!-- 인증 회원 UI -->
<c:if test="${user_auth >= 3}">
<div class="page-main">
<h3 class="univName-style">[${univ_name}]</h3>
	<div class="myInfo-div">
		<table>
			<tr>
				<td>
					<c:if test="${empty user_photo}">
						<img src="${pageContext.request.contextPath}/images/face.png">
					</c:if>
					<c:if test="${!empty user_photo}">
						<img src="${pageContext.request.contextPath}/upload/${user_photo}">
					</c:if>
				</td>
			</tr>
			<tr>
				<c:if test="${user_nick.length() > 9}">
					<td class="univ-nick" style="font-size:13px;">
						<b>${user_nick}</b>
					</td>
				</c:if>
				<c:if test="${user_nick.length() <= 9}">
					<td class="univ-nick">
						<b>${user_nick}</b>
					</td>
				</c:if>
			</tr>
			<tr>
				<td class="univ-id">
					${user_id}
				</td>
			</tr>
		</table>
	</div>
	<div class="info-div">
		<a href="https://www.kimyoung.co.kr/offmegaky.asp" target="_blank"><img src="../images/kimyoung.jpg" width="200"></a>
	</div>
	<div class="info-div2">
		<a href="https://www.work.go.kr/experi/index.do" target="_blank"><img src="../images/work.jpg" width="200"></a>
	</div>
	<div class="info-div3">
		<a href="https://www.youtube.com/@NetflixKorea" target="_blank"><img src="../images/netflix.jpg" width="200"></a>
	</div>
	<div class="home-div">
		<h3>공지사항 <input type="button" value="더보기" class="home-btn"
			onclick="location.href='${pageContext.request.contextPath}/notice/list.do'"></h3>
		<div class="list-div">
		<table>
			<c:forEach var="notice" items="${noticeList}">
			<tr>
				<td class="list-margin2"><a href="${pageContext.request.contextPath}/notice/detail.do?notice_num=${notice.notice_num}">${notice.notice_title}</a></td>
				<td class="list-margin"><fmt:formatDate value="${notice.notice_reg_date}" pattern="MM/dd HH:mm"/></td>
			</tr>
			</c:forEach>
		</table>
		</div>
		<h3>FAQ <input type="button" value="더보기" class="home-btn"
			onclick="location.href='${pageContext.request.contextPath}/faq/faq.do'"></h3>
		<div class="list-div">
		<table class="faq-table">
			<tr>
				<th style="height:33px;">[자주 묻는 질문]</th>
			</tr>
			<c:forEach var="question" items="${questionList}">
			<tr>
				<td class="list-margin3"><a href="${pageContext.request.contextPath}/faq/detail.do?question_num=${question.question_num}">${question.question_title}</a></td>
			</tr>
			</c:forEach>
		</table>
		</div>
	</div>
	<div class="home-div">
		<h3>HOT 게시글 <input type="button" value="더보기" class="home-btn"
			onclick="location.href='${pageContext.request.contextPath}/board/list.do'"></h3>
		<div class="list-div">
		<table>
			<c:forEach var="hot" items="${hotList}">
			<tr>
				<td class="list-margin2"><a href="${pageContext.request.contextPath}/board/detail.do?board_num=${hot.board_num}">${hot.board_title}</a></td>
				<td class="list-margin"><fmt:formatDate value="${hot.board_reg_date}" pattern="MM/dd HH:mm"/></td>
			</tr>
			</c:forEach>
		</table>
		</div>
		<h3>자유 게시판 <input type="button" value="더보기" class="home-btn"
			onclick="location.href='${pageContext.request.contextPath}/board/list.do'"></h3>
		<div class="list-div">
		<table>
			<c:forEach var="board" items="${boardList}">
			<tr>
				<td class="list-margin2"><a href="${pageContext.request.contextPath}/board/detail.do?board_num=${board.board_num}">${board.board_title}</a></td>
				<td class="list-margin"><fmt:formatDate value="${board.board_reg_date}" pattern="MM/dd HH:mm"/></td>
			</tr>
			</c:forEach>	
		</table>
		</div>
	</div>
	<!-- 책방 -->
	<c:if test="${!empty scList}">
		<div class="home-div2">
			<table>
				<tr>
					<c:forEach var="sc" items="${scList}">
						<td>
							<a href="${pageContext.request.contextPath}/secondhand/secondhand_detail.do?secondhand_num=${sc.secondhand_num}">
								<img src="../upload/${sc.secondhand_filename}" width=150; height=200; class="scList-img">
							</a>
						</td>
					</c:forEach>
				</tr>
				<tr style="font-size:12px;font-weight:bold;">
					<c:forEach var="sc" items="${scList}">
						<td style="padding-left:10px;">
							<a href="${pageContext.request.contextPath}/secondhand/secondhand_detail.do?secondhand_num=${sc.secondhand_num}">
								${sc.secondhand_name}
							</a>
						</td>
					</c:forEach>	
				</tr>
				<tr style="font-size:11px;color:#6699cc;">
					<c:forEach var="sc" items="${scList}">
						<td style="padding-left:10px;"><fmt:formatNumber value="${sc.secondhand_price}"/>원</td>
					</c:forEach>
				</tr>
			</table>	
		</div>
	</c:if>
	<div class="home-end"></div>
</div>
</c:if>
<!-- 비인증 회원 UI -->
<c:if test="${user_auth < 3}">
<div class="page-main">
<h3 class="univName-style">[${univ_name}]</h3>
	<div class="myInfo-div">
		<table>
			<tr>
				<td>
					<c:if test="${empty user_photo}">
						<img src="${pageContext.request.contextPath}/images/face.png">
					</c:if>
					<c:if test="${!empty user_photo}">
						<img src="${pageContext.request.contextPath}/upload/${user_photo}">
					</c:if>
				</td>
			</tr>
			<tr>
				<c:if test="${user_nick.length() > 9}">
					<td class="univ-nick" style="font-size:13px;">
						<b>${user_nick}</b>
					</td>
				</c:if>
				<c:if test="${user_nick.length() <= 9}">
					<td class="univ-nick">
						<b>${user_nick}</b>
					</td>
				</c:if>
			</tr>
			<tr>
				<td class="univ-id">
					${user_id}
				</td>
			</tr>
		</table>
	</div>
	<div class="info-div">
		<a href="https://www.kimyoung.co.kr/offmegaky.asp" target="_blank"><img src="../images/kimyoung.jpg" width="200"></a>
	</div>
	<div class="info-div2">
		<a href="https://www.work.go.kr/experi/index.do" target="_blank"><img src="../images/work.jpg" width="200"></a>
	</div>
	<div class="info-div3">
		<a href="https://www.youtube.com/@NetflixKorea" target="_blank"><img src="../images/netflix.jpg" width="200"></a>
	</div>
	<div class="home-div">
		<h3>공지사항 <input type="button" value="더보기" class="home-btn"
			onclick="location.href='${pageContext.request.contextPath}/notice/list.do'"></h3>
		<div class="list-div">
		<table>
			<c:forEach var="notice" items="${noticeList}">
			<tr>
				<td class="list-margin2"><a href="${pageContext.request.contextPath}/notice/detail.do?notice_num=${notice.notice_num}">${notice.notice_title}</a></td>
				<td class="list-margin"><fmt:formatDate value="${notice.notice_reg_date}" pattern="MM/dd HH:mm"/></td>
			</tr>
			</c:forEach>
		</table>
		</div>
		<h3>FAQ <input type="button" value="더보기" class="home-btn"
			onclick="location.href='${pageContext.request.contextPath}/faq/faq.do'"></h3>
		<div class="list-div">	
		<table class="faq-table">
			<tr>
				<th style="height:33px;">[자주 묻는 질문]</th>
			</tr>
			<c:forEach var="question" items="${questionList}">
			<tr>
				<td class="list-margin3"><a href="${pageContext.request.contextPath}/faq/detail.do?question_num=${question.question_num}">${question.question_title}</a></td>
			</tr>
			</c:forEach>
		</table>
		</div>
	</div>
	<div class="home-div">	
		<h3>HOT 게시글</h3>
		<div class="list-div align-center" style="line-height:100px;">
			학교 인증을 마친 학생들만 이용할 수 있어요!<br>
			<input type="button" value="학교 인증" class="sc-btn"
				onclick="location.href='${pageContext.request.contextPath}/mymember/certifyForm.do'">
		</div>
		<h3>자유 게시판</h3>
		<div class="list-div align-center" style="line-height:100px;">
			학교 인증을 마친 학생들만 이용할 수 있어요!<br>
			<input type="button" value="학교 인증" class="sc-btn"
				onclick="location.href='${pageContext.request.contextPath}/mymember/certifyForm.do'">
		</div>
	</div>
	<!-- 책방 -->
	<c:if test="${!empty scList}">
		<div class="home-div2">
			<table>
				<tr>
					<c:forEach var="sc" items="${scList}">
						<td>
							<a href="${pageContext.request.contextPath}/secondhand/secondhand_detail.do?secondhand_num=${sc.secondhand_num}">
								<img src="../upload/${sc.secondhand_filename}" width=150; height=200; class="scList-img">
							</a>
						</td>
					</c:forEach>
				</tr>
				<tr style="font-size:12px;font-weight:bold;">
					<c:forEach var="sc" items="${scList}">
						<td style="padding-left:10px;">
							<a href="${pageContext.request.contextPath}/secondhand/secondhand_detail.do?secondhand_num=${sc.secondhand_num}">
								${sc.secondhand_name}
							</a>
						</td>
					</c:forEach>	
				</tr>
				<tr style="font-size:11px;color:#6699cc;">
					<c:forEach var="sc" items="${scList}">
						<td style="padding-left:10px;"><fmt:formatNumber value="${sc.secondhand_price}"/>원</td>
					</c:forEach>
				</tr>
			</table>	
		</div>
	</c:if>
	<div class="home-end"></div>
</div>
</c:if>
<div class="bottom-style page-sub">
	<a href="${pageContext.request.contextPath}/mymember/termForm.do">이용약관</a>
	<div class="copyright">
		copyright(c) 2023. 올캠퍼스. All rights reserved
	</div>
</div>
</body>
</html>