<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jiwonstyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<style type="text/css">
/* Styles for the modal container */
.modal {
  display: none;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 20px;
  background-color: #fff;
  border: 1px solid #ccc;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}

/* Styles for the close button */
.modal .close {
  position: absolute;
  top: 10px;
  right: 10px;
  cursor: pointer;
}

/* Dim the background when the modal is open */
.overlay {
  display: none;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 1;
}
</style>
<script type="text/javascript">
  $(document).ready(function () {
	  var tbody = $('tbody');
	    for (var i = 0; i < 7; i++) {
	        tbody.append('<tr>' +
	            '<td class="name"></td>' +
	            '<td class="credit"></td>' +
	            '<td class="grade"></td>' +
	            '<td class="major"></td>' +
	            '</tr>');
	    }
		
		$('.import').click(function() {
			// Show the importForm modal
			$('#importForm').show();
		});

		
		$('.close, .overlay').click(function () {
        // Hide the modal
       		$('.modal').hide();
    	});
		
		
		$('#importForm').submit(function(event){
			
			event.preventDefault();
			
			var selectedSemester = $('select[name="semester"]').val();
			
			var semesterParts = selectedSemester.split('-');
	        var timetableYear = semesterParts[0];
	        var timetableSemester = semesterParts[1];
			
			
			$.ajax({
				url:'select_timetable.do',
				type:'post',
				data:{timetableYear: timetableYear,
	                timetableSemester: timetableSemester},
				dataType:'json',
				success:function(param){
					if(param.result=='success'){
						for (var i = 0; i < param.list.length && i < 7; i++) {
	                        var course = param.list[i].timetableVO;
	                        var currentRow = tbody.find('tr').eq(i);

	                        
	                        currentRow.find('.name').text(course.timetable_course_name);
	                        currentRow.find('.credit').text(course.timetable_credit);
	                        currentRow.find('.grade').text('');
	                        currentRow.find('.major').text('');
	                        
						<%--
						$('tbody').empty();
						for (var i = 0; i < param.list.length; i++) {
				              var course = param.list[i].timetableVO;
				              $('tbody').append('<tr>' +
	                                '<td class="name">' + course.timetable_course_name + '</td>' +
	                                '<td class="credit">' + course.timetable_credit + '</td>' +
	                                '<td class="grade"></td>' + 
	                                '<td class="major"></td>' + 
	                               '</tr>');
	            		}
						$('.modal').hide();
						
						--%>
						$('.modal').hide();
					}else{
						alert('오류 발생');
					}
					
				},
				error:function(){
					alert('네트워크 오류 발생(학점계산기)');
				}
			});
		});
	});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
<div id="container" class="calculator">
	<div class="title">
		<h1>학점계산기</h1>
	</div> 
	<div class="section">
		<div class="chart">
			<article class="overview">
				<div class="column gpa">
					<h4>전체 평점</h4>
					<p class="value">11</p>
					<p class="total">11</p>
				</div>
				<div class="column major">
					<h4>전공 평점</h4>
					<p class="value">11</p>
					<p class="total">11</p>
				</div>
				<div class="column acquisition">
					<h4>취득 학점</h4>
					<p class="value">11</p>
					<p class="total" title="졸업 학점 설정">11</p>
				</div>
			</article>
		
			
		</div>
		<div>
			<select>
				<option>1학년 1학기</option>
				<option>1학년 2학기</option>
				<option>2학년 1학기</option>
				<option>2학년 2학기</option>
				<option>3학년 1학기</option>
				<option>3학년 2학기</option>
				<option>4학년 1학기</option>
				<option>4학년 1학기</option>
			</select>
		</div>
		<table>
		<caption><input type="button" class="import" value="시간표 불러오기"></caption>
		<thead>
			<tr>
            	<th class="name">과목명</th>
           	 	<th class="credit">학점</th>
           	 	<th class="grade">성적</th>
            	<th class="major">전공</th>
          	</tr>
		
		</thead>
		<tbody>
		
		
		</tbody>
		<tfoot>
          <tr>
            <td colspan="4">
              <a class="new">더 입력하기</a>
              <a class="reset">초기화</a>
            </td>
          </tr>
        </tfoot>
		</table>
	</div> 
	
	<button id="openModalBtn">Open Modal</button>

    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close" id="closeModalBtn">&times;</span>
            <h2>Modal Content</h2>
            <p>This is a simple modal without Bootstrap.</p>
        </div>
    </div>
	<%-- 
 	<form id="importForm" class="modal">
      <a title="닫기" class="close"></a>
      <h3>내 시간표 가져오기</h3>
      <p>
        <label>시간표 선택</label>
        <select name="semester">
        	<option value="2024-0">2024년 1학기</option>
        	<option value="2023-3">2023년 겨울</option>
        	<option value="2023-2">2023년 2학기</option>
        	<option value="2023-1">2023년 여름</option>
        	<option value="2023-0">2023년 1학기</option>
        	<option value="2022-2">2022년 2학기</option>
        	<option value="2022-0">2022년 1학기</option>
        	<option value="2021-2">2021년 2학기</option>
        	<option value="2021-0">2021년 1학기</option>
        	<option value="2020-2">2020년 2학기</option>
        	<option value="2020-0">2020년 1학기</option>
        	<option value="2019-2">2019년 2학기</option>
        	<option value="2019-0">2019년 1학기</option>
        </select>
      </p>
      <input type="submit" value="가져오기" class="button">
    </form>
    <form id="requiredCreditForm" class="modal">
      <a title="닫기" class="close"></a>
      <h3>졸업 학점 설정</h3>
      <p>
        <label>졸업 학점</label>
        <input type="number" name="required_credit" maxlength="3" class="text">
      </p>
      <input type="submit" value="저장" class="button">
    </form>
    --%>
</div>
</div>
</body>
</html>