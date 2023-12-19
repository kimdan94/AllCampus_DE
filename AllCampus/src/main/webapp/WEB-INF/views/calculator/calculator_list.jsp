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
        body {
            font-family: Arial, sans-serif;
        }

        .modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
        }

        .modal-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
        .score_show {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-top: 20px;
    }

    .column {
        text-align: center;
        width: 30%;
        border: 1px solid #ddd;
        padding: 10px;
        border-radius: 5px;
    }
</style>
<script type="text/javascript">
$(document).ready(function () {
    var tbody = $('tbody');

    for (var i = 0; i < 7; i++) {
        tbody.append('<tr>' +
            '<td class="name"></td>' +
            '<td class="credit"></td>' +
            '<td class="grade"><select name="cal_grade">' +
            '<option value="4.5">A+</option>' +
            '<option value="4.0">A</option>' +
            '<option value="3.5">B+</option>' +
            '<option value="3.0">B</option>' +
            '<option value="2.5">C+</option>' +
            '<option value="2.0">C</option>' +
            '<option value="1.5">D+</option>' +
            '<option value="1.0">D</option>' +
            '<option value="0">F</option>' +
            '<option value="0">P</option>' +
            '<option value="0">NP</option>' +
            '</select></td>' +
            '<td class="major"><input type="checkbox" name="major_' + i + '"></td>' +
            '</tr>');
    }
	
    //select_semester에서 선택된 값을 form의 hidden값에 넣어준다
    $('select[name="select_semester"]').change(function () {
        $('#cal_semester').val($(this).val());	//선택된 값을 hidden값에 넣어준다.
    });
    
    
    $('#importForm').submit(function (event) {
        event.preventDefault();
        
        var selectedSemester = $('select[name="select_semester"]').val();
        var selectedTimetable = $('select[name="cal_timetable"]').val();
        
        $('#cal_semester').val(selectedSemester);
        
		
        var semesterParts = selectedTimetable.split('-');
        var timetableYear = semesterParts[0];
        var timetableSemester = semesterParts[1];

        $.ajax({
            url: 'select_timetable.do',
            type: 'post',
            data: {
                timetableYear: timetableYear,
                timetableSemester: timetableSemester
            },
            dataType: 'json',
            success: function (param) {
                if (param.result == 'success') {
                	//기존 존재하는 값을 비움
                	$('tbody').html('');
                	
                	//행에 강의명, 과목당 학점 가져와서 행에 넣어줌 
                    for (var i = 0; i < param.list.length && i < 7; i++) {
                        var course = param.list[i].timetableVO;
                        var newRow = '<tr>' +
                            '<td class="name">' + course.timetable_course_name +
                            '<input type="hidden" name="course_name_' + i + '" value="' + course.timetable_course_name + '"></td>' +
                            '<td class="credit">' + course.timetable_credit +
                            '<input type="hidden" name="timetable_credit_' + i + '" value="' + course.timetable_credit + '"></td>' +
                            '<td class="grade"><select name="cal_grade_' + i + '">' +
                            '<option value="4.5">A+</option>' +
                            '<option value="4.0">A</option>' +
                            '<option value="3.5">B+</option>' +
                            '<option value="3.0">B</option>' +
                            '<option value="2.5">C+</option>' +
                            '<option value="2.0">C</option>' +
                            '<option value="1.5">D+</option>' +
                            '<option value="1.0">D</option>' +
                            '<option value="0">F</option>' +
                            '<option value="0.1">P</option>' +
                            '<option value="0.2">NP</option>' +
                            '</select></td>' +
                            '<td class="major"><input type="checkbox" name="major_' + i + '" value="2"></td>' +
                            '<input type="hidden" name="cal_major_' + i + '" value="1">' +
                            '</tr>';

                   		 $('tbody').append(newRow);
                    }
                	
                    $('tbody').on('change', 'input[type="checkbox"]', function () {
                        var value = $(this).is(':checked') ? '2' : '1';

                        if ($(this).is(':checked')) {
                            var rowIndex = $(this).closest('tr').index();
                            $('input[name="cal_major_' + rowIndex + '"]').val(value);
                        }
                    });
                	
                    $('.modal').hide();
                } else {
                    alert('오류 발생');
                }
            },
            error: function () {
                alert('네트워크 오류 발생(학점계산기)');
            }
        });
    });

    var openModalBtn = $('#openModalBtn');
    var closeModalBtn = $('#closeModalBtn');
    var modal = $('#myModal');

    openModalBtn.click(function () {
        modal.show();
    });

    closeModalBtn.click(function () {
        modal.hide();
    });

    $(window).click(function (event) {
        if (event.target === modal[0]) {
            modal.hide();
        }
    });
    
    //계산하기 폼 제출 시 
    $('#cal_count').submit(function (event) {
    	
    	event.preventDefault();

        //배열 생성
        var dataRows = [];

        //7행에 대해 
        for (var i = 0; i < 7; i++) {
            var rowData = {
                course_name: $('input[name="course_name_' + i + '"]').val(),
                timetable_credit: $('input[name="timetable_credit_' + i + '"]').val(),
                cal_grade: $('select[name="cal_grade_' + i + '"]').val(),
                cal_major: $('input[name="cal_major_' + i + '"]').val()
            };
			//배열에 데이터 넣기
            dataRows.push(rowData);
        }
    	
        var formData = {
                cal_semester: $('#cal_semester').val()
            };
        
        for (var i = 0; i < dataRows.length; i++) {
            formData['course_name_' + i] = dataRows[i].course_name;
            formData['timetable_credit_' + i] = dataRows[i].timetable_credit;
            formData['cal_grade_' + i] = dataRows[i].cal_grade;
            formData['cal_major_' + i] = dataRows[i].cal_major;
        }
        
		$.ajax({
			url: 'calculate.do',
            type: 'post',
            data: formData,
       		dataType: 'json',
        	success: function (param) {
        		if(param.result == 'success'){
					alert("학점 계산 완료");
        		}else{
        			alert('오류');
        		}
			},
        	error: function () {
            	alert('네트워크 오류 발생(학점계산기1)');
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
					<h3>전체 평점</h3>
					<p class="value">11</p>
					<p class="total">11</p>
				</div>
				<div class="column major">
					<h3>전공 평점</h3>
					<p class="value">11</p>
					<p class="total">11</p>
				</div>
				<div class="column acquisition">
					<h3>취득 학점</h3>
					<p class="value">11</p>
					<p class="total" title="졸업 학점 설정">11</p>
				</div>
			</article>
		
			
		</div>
		<div>
			<select name="select_semester">
				<option>1학년 1학기</option>
				<option>1학년 여름</option>
				<option>1학년 2학기</option>
				<option>1학년 겨울</option>
				<option>2학년 1학기</option>
				<option>2학년 여름</option>
				<option>2학년 2학기</option>
				<option>2학년 겨울</option>
				<option>3학년 1학기</option>
				<option>3학년 여름</option>
				<option>3학년 2학기</option>
				<option>3학년 겨울</option>
				<option>4학년 1학기</option>
				<option>4학년 여름</option>
				<option>4학년 2학기</option>
				<option>4학년 겨울</option>
			</select>
		</div>
		<div>
		<div class="score_show">
			<div class="column avgscore">
				<h3>평점</h3>
				<p id="avgscore">11</p>
			</div>
			<div class="column majorscore">
				<h3>전공</h3>
				<p id="majorscore">11</p>
			</div>
			<div class="column acq">
				<h3>취득</h3>
				<p id="acq">11</p>
			</div>
		</div>
		<button id="openModalBtn">시간표 불러오기</button>
		
			<div id="myModal" class="modal">
				<div class="modal-content">
					<span class="close" id="closeModalBtn">&times;</span>
					<form id="importForm" class="modal-form">
           				 <h2>내 시간표 가져오기</h2>
           			 	<p>
              				<label>시간표 선택</label>
               				<select name="cal_timetable">
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
				</div>
			</div>
		<form id="cal_count">
		<table>
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
		<input type="hidden" id="cal_semester" name="cal_semester" value="">
		<input type="submit" value="계산하기">
		
		</form>
	</div> 
</div>
</div>
</div>
</body>
</html>