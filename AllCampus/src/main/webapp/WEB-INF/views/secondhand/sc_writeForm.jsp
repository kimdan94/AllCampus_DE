<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매하기 - 올캠퍼스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jy.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#sell_form').submit(function(){
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
		
		if($('input[type=radio]:checked').length < 1){
			alert('판매하려는 교재의 제품 상태를 선택하세요!');
			return false;
		}
		
		if(!$('input:checked[name="bookWay"]').is(":checked")){
			alert('거래 방법을 선택하세요!');
			return false;
		}
				
	});//end of submit
});	
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
	<h2>판매하기</h2>
	<form id="sell_form" action="secondhand_write.do" method="post"
												enctype="multipart/form-data">
		<ul>
			<li>
				<label for="bookName">교재명</label>
				<input type="text" name="bookName" id="bookName" maxlength="40"
					placeholder="판매를 원하는 교재명을 입력해주세요."
					size="40px" class="input-check">
			</li>
			<li>
				<label for="bookWriter">저자명</label>
				<input type="text" name="bookWriter" id="bookWriter"
					maxlength="20" placeholder="대표 저자 한 명을 입력해주세요."
					size="35px" class="input-check">
				<input type="checkbox" name="bookWriterPlus" value="외 지음">외 지음	
			</li>
			<li>
				<label for="bookCompany">출판사명</label>
				<input type="text" name="bookCompany" id="bookCompany"
					maxlength="10" placeholder="출판사명을 정확히 입력해주세요."
					size="35px" class="input-check">
			</li>
			<li>
				<label for="bookPrice">판매 가격</label>
				<input type="number" name="bookPrice" id="bookPrice"
					min="1" max="999999" class="input-check">
			</li>
			<li>
				<label for="bookImage">교재 이미지 첨부</label>
				<input type="file" name="bookImage" id="bookImage"
					accept="image/gif,image/png,image/jpeg"
					class="input-check">
			</li>
			<li>
				<label for="bookContent">설명</label><br>
				<textarea rows="15" cols="100" class="input-check" 
					name="bookContent" id="bookContent"></textarea>
			</li>
			<li>
				<label for="bookUrl">오픈카톡 경로</label>
				<input type="url" name="bookUrl" id="bookUrl"
					class="input-check">
			</li>
			<li>
				<label>교재 상태</label>
				<input type="radio" name="bookStatus" value="상" id="bookStatus1">상
				<input type="radio" name="bookStatus" value="중" id="bookStatus2">중
				<input type="radio" name="bookStatus" value="하" id="bookStatus3">하
			</li>
			<li>
				<label>거래 방법</label>
				<input type="radio" name="bookWay" value="직거래">직거래
				<input type="radio" name="bookWay" value="배송">배송
				<input type="radio" name="bookWay" value="둘 다 가능">둘 다 가능
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="등록하기" class="input-button2" style="font-size:15px;">
			<input type="button" value="목록으로" class="input-button1" style="font-size:15px;"
				onclick="location.href='secondhand_list.do'">
		</div>
	</form>
</div>
</body>
</html>