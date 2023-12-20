<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매 글 수정하기 - 올캠퍼스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jy.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#bookImage').change(function(){
		let newImg = this.files[0];
		//파일을 선택하려다 취소한다면 안내 문구 유지
		if(!newImg){
			$('.file-detail').show();
			return;
		}
		
		//사이즈 체크
		if(newImg.size > 3*1024*1024){
			alert(Math.round(newImg.size/1024) + 'kbytes(3072kbytes까지만 업로드 가능)');
			$(this).val('');//선택한 파일의 경로 정보 삭제
			return;
		}
		
		//새로운 파일 선택 시 이전 파일 안내 문구 숨기기
		$('.file-detail').hide();
	});//end of change
	
	$('#update_form').submit(function(){
		let items = document.querySelectorAll('.input-check');
		for(let i=0;i<items.length;i++){
			if(items[i].value.trim()==''){
				let label = document.querySelector('label[for="' + items[i].id + '"]');
				alert(label.textContent + ' 항목이 기입되지 않았습니다.');
				items[i].value = '';
				items[i].focus();
				return false;
			}
		}//end of for
	});//end of submit
});	
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main-custom">
	<h2 class="board-title">[판매글 수정하기]</h2>
	<hr width="13%" class="board-underline">
	<form id="update_form" action="secondhand_update.do" method="post"
												enctype="multipart/form-data">
		<input type="hidden" name="secondhand_num" value="${sc.secondhand_num}">										
		<ul>
			<li>
				<label for="bookName">교재명</label>
				<input type="text" name="bookName" id="bookName" maxlength="40"
					placeholder="판매를 원하는 교재명을 입력해주세요." value="${sc.secondhand_name}"
					size="40px" class="input-check">
			</li>
			<li>
				<label for="bookWriter">저자명</label>
				<input type="text" name="bookWriter" id="bookWriter"
					maxlength="20" placeholder="대표 저자 한 명을 입력해주세요."
					size="35px" class="input-check" value="${sc.secondhand_writer}">
				<input type="checkbox" name="bookWriterPlus" value="외 지음"
					<c:if test="${sc.secondhand_writerPlus != null}">checked</c:if>>외 지음	
			</li>
			<li>
				<label for="bookCompany">출판사명</label>
				<input type="text" name="bookCompany" id="bookCompany"
					maxlength="10" placeholder="출판사명을 정확히 입력해주세요."
					size="35px" class="input-check" value="${sc.secondhand_company}">
			</li>
			<li>
				<label for="bookPrice">판매 가격</label>
				<input type="number" name="bookPrice" id="bookPrice"
					min="1" max="999999" class="input-check"
					value="${sc.secondhand_price}">
			</li>
			<li>
				<label for="bookImage">교재 이미지</label>
				<input type="file" name="bookImage" id="bookImage"
					accept="image/gif,image/png,image/jpeg">
				<div class="file-guide">
					*3,072KB(3MB) 이하의 jpg, gif, png 파일만 첨부 가능
				</div>
				<div class="file-detail">
					*이전 첨부 파일 : ${sc.secondhand_filename}
				</div>
			</li>
			<li>
				<label for="bookContent">설명</label><br>
				<textarea rows="15" cols="100" class="input-check" 
					name="bookContent" id="bookContent">${sc.secondhand_content}</textarea>
			</li>
			<li>
				<label for="bookUrl">오픈 카카오톡 URL</label>
				<input type="url" name="bookUrl" id="bookUrl"
					class="input-check" value="${sc.secondhand_openchat}">
			</li>
			<li>
				<label>교재 상태</label>
				<input type="radio" name="bookStatus" value="상" <c:if test="${sc.secondhand_status == '상'}">checked</c:if>>상
				<input type="radio" name="bookStatus" value="중" <c:if test="${sc.secondhand_status == '중'}">checked</c:if>>중
				<input type="radio" name="bookStatus" value="하" <c:if test="${sc.secondhand_status == '하'}">checked</c:if>>하
			</li>
			<li>
				<label>거래 방법</label>
				<input type="radio" name="bookWay" value="직거래" <c:if test="${sc.secondhand_way == '직거래'}">checked</c:if>>직거래
				<input type="radio" name="bookWay" value="배송" <c:if test="${sc.secondhand_way == '배송'}">checked</c:if>>배송
				<input type="radio" name="bookWay" value="둘 다 가능" <c:if test="${sc.secondhand_way == '둘 다 가능'}">checked</c:if>>둘 다 가능
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="수정하기" class="input-button2">
			<input type="button" value="목록으로" class="input-button1" 
				onclick="location.href='secondhand_list.do'">
		</div>
	</form>
</div>
</body>
</html>