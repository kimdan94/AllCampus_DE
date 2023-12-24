<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학점 계산기 - 올캠퍼스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jiwonstyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	let dataInSessionStorage;
	let semester_index;
	let list_length;
	var tbody = $('tbody');
	function selectTbody(){
		//tobody에 표 만들기
	    for (var i = 0; i < 7; i++) {
	        tbody.append('<tr>' +
	            '<td class="names"></td>' +
	            '<td class="credits"></td>' +
	            '<td class="grades"><select name="cal_grade">' +
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
	            '<td class="majors"><input type="checkbox" name="major_' + i + '"></td>' +
	            '</tr>');
	    }
	}
    selectTbody();
	
    //계산 후 다시 학기 선택 시 저장된 데이터 보여진다
    //select_semester에서 선택된 값을 form의 hidden값에 넣어준다
    $('select[name="select_semester"]').change(function () {
    	semester_index = $('select[name="select_semester"] option').index($('select[name="select_semester"] option:selected'));
    	//세션에서 데이터 가져오기
    	let data = $.parseJSON(sessionStorage.getItem(semester_index));
        //console.log(data);
        if(data){//undefiend면 if문 밖으로 감
        	$('tbody').html('');
        	for(var i=0;i<data.table.length;i++){
        		var grade = data.table[i].cal_grade;
        		var major = data.table[i].cal_major;
                var cal_Grade;
                var cal_Major;
                
                if (grade == 4.5) {
                	cal_Grade = 'A+';
                } else if (grade == 4.0) {
                	cal_Grade = 'A';
                } else if (grade == 3.5) {
                	cal_Grade = 'B+';
                } else if (grade == 3.0) {
                	cal_Grade = 'B';
                } else if (grade == 2.5) {
                	cal_Grade = 'C+';
                } else if (grade == 2.0) {
                	cal_Grade = 'C';
                } else if (grade == 1.5) {
                	cal_Grade = 'D+';
                } else if (grade == 1.0) {
                	cal_Grade = 'D';
                } else {
                	cal_Grade = 'F';
                }
        		
                //major 1:전공 X, 2:전공
        		if(major == 1){
        			cal_Major='';
        		}else if(major==2){
        			cal_Major='전공';
        		}else{
        			cal_Major='오류';
        		}
                //저장된 데이터 tbody에 출력
        		tbody.append('<tr>' +
                        '<td class="names">' + data.table[i].course_name + '</td>' +
                        '<td class="credits">' + data.table[i].timetable_credit + '</td>' +
                        '<td class="grades">' + cal_Grade + '</td>' +
                        '<td class="majors">' + cal_Major + '</td>' +
                        '</tr>');
        	}
        }else{
        	$('tbody').html('');
        	selectTbody();
        }
    	
        $('#cal_semester').val($(this).val());	//선택된 값을 hidden값에 넣어준다.
        $('.column.avgscore .value').text('0');
        $('.column.majorscore .value').text('0');
        $('.column.acq .value').text('0');
        
        if(data){
	        $('.column.avgscore .value').text(data.avgscore);
			$('.column.majorscore .value').text(data.majorscore);
		    $('.column.acq .value').text(data.acq);
        }
    }); 
    
    //모달 창 제출 시
    $('#importForm').submit(function (event) {
        event.preventDefault();
        
        var selectedSemester = $('select[name="select_semester"]').val();
        var selectedTimetable = $('select[name="cal_timetable"]').val();
        
        //select 선택한 인덱스
        semester_index = $('select[name="select_semester"] option').index($('select[name="select_semester"] option:selected'));
        
        $('#cal_semester').val(selectedSemester);
        
		
        var semesterParts = selectedTimetable.split('-');
        var timetableYear = semesterParts[0];
        var timetableSemester = semesterParts[1];
		
        //시간표에서 데이터 가져오기
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
                	list_length = param.list.length;
                	//기존 존재하는 값을 비움
                	$('tbody').html('');
                	
                	//행에 강의명, 과목당 학점 가져와서 행에 넣어줌 
                    for (var i = 0; i < param.list.length && i < 7; i++) {
                        var course = param.list[i].timetableVO;
                        var newRow = '<tr>' +
                            '<td class="names">' + course.timetable_course_name +
                            '<input type="hidden" name="course_name_' + i + '" value="' + course.timetable_course_name + '"></td>' +
                            '<td class="credits">' + course.timetable_credit +
                            '<input type="hidden" name="timetable_credit_' + i + '" value="' + course.timetable_credit + '"></td>' +
                            '<td class="grades"><select name="cal_grade_' + i + '">' +
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
                            '<td class="majors"><input type="checkbox" name="major_' + i + '" value="2"></td>' +
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
    	semester_index = $('select[name="select_semester"] option').index($('select[name="select_semester"] option:selected'));
    	if(semester_index == 0){
    		alert('학기를 선택하세요');
    		return;
    	}
    	
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
        dataInSessionStorage = '{"table":[';
       
        //7행에 대해 
        for (var i = 0; i < list_length; i++) {
            var rowData = {
                course_name: $('input[name="course_name_' + i + '"]').val(),
                timetable_credit: $('input[name="timetable_credit_' + i + '"]').val(),
                cal_grade: $('select[name="cal_grade_' + i + '"]').val(),
                cal_major: $('input[name="cal_major_' + i + '"]').val()
            };

            if(i>0)  dataInSessionStorage += ',';
            dataInSessionStorage += '{';
            dataInSessionStorage += '"course_name":"'+$('input[name="course_name_' + i + '"]').val()+'"';
            dataInSessionStorage += ',"timetable_credit":'+ $('input[name="timetable_credit_' + i + '"]').val();
            dataInSessionStorage += ',"cal_grade":'+ $('select[name="cal_grade_' + i + '"]').val();
            dataInSessionStorage += ',"cal_major":'+ $('input[name="cal_major_' + i + '"]').val();
            dataInSessionStorage += '}';
			//배열에 데이터 넣기
            dataRows.push(rowData);
        }
    	
        dataInSessionStorage += ']';

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
					$('.column.gpa .value').val('');
					$('.column.major .value').val('');
					$('.column.acquisition .value').val('');
					
					$('.column.gpa .value').text(param.totalscore.cal_total_avgscore);
			        $('.column.major .value').text(param.totalscore.cal_total_majorscore);
			        $('.column.acquisition .value').text(param.totalscore.cal_total_acq);
					
			        $('.column.avgscore .value').val('');
					$('.column.majorscore .value').val('');
					$('.column.acq .value').val('');
			        
					$('.column.avgscore .value').text(param.semesterscore.cal_avgscore);
           			$('.column.majorscore .value').text(param.semesterscore.cal_majorscore);
            		$('.column.acq .value').text(param.semesterscore.cal_acq);
        			
            		dataInSessionStorage += ',"avgscore":'+ $('.column.avgscore .value').text();
            		dataInSessionStorage += ',"majorscore":'+ $('.column.majorscore .value').text();
            		dataInSessionStorage += ',"acq":'+ $('.column.acq .value').text();
                    dataInSessionStorage += '}';
                    
                    //세션에 데이터 넣기
                    sessionStorage.setItem(semester_index,dataInSessionStorage);
                    
                    //console.log(dataInSessionStorage);
            		
        		}else{
        			alert('오류');
        		}
			},
        	error: function () {
            	alert('네트워크 오류 발생(학점계산기1)');
        	}
		});
		
    });
    //초기화
   $('#reset').click(function(){
	   //console.log('~~');
	   //console.log(semester_index);
	   //console.log(sessionStorage.getItem(semester_index));
	   if(sessionStorage.getItem(semester_index)){
		   sessionStorage.removeItem(semester_index);
		   let cal_semester = $('select[name="select_semester"] option').eq(semester_index).val();
		   //alert(cal_semester);
		   alert('초기화 완료');
		   
		   //ajax통신
		   $.ajax({
				url: 'caldelete.do',
           		type: 'post',
           	 	data: {cal_semester:cal_semester},
       			dataType: 'json',
        		success: function (param) {
        		   //ui 초기화
       			   tbody.empty();
       			   selectTbody();
       			   
       			   $('.column.avgscore .value').text('0');
       	      	   $('.column.majorscore .value').text('0');
       	      	   $('.column.acq .value').text('0');
       	      	   
       	      	   location.href='calculator_list.do';
				},
        		error: function () {
            		alert('네트워크 오류 발생(학점계산기 DB삭제)');
        		}
			});//end of ajax
	   }
   });
});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
	<div>
		<h2 class="board-title">학점 계산기</h2>
		<hr width="10%" class="board-underline">
	</div> 
	<div id="container" class="calculator">
		<div class="section">
			<div class="chart">
				<article class="overview">
					<div class="column gpa">
						<h3>전체 평점</h3>
						<p class="value">
							<c:choose>
								<c:when test="${totalscore.cal_total_avgscore!=null}" >
									${totalscore.cal_total_avgscore}
								</c:when>
								<c:otherwise>
                   	      	   0
                    	 	   </c:otherwise>
							</c:choose>
						</p>
						<p>/</p>
						<p class="total">4.5</p>
					</div>
					<div class="column major">
						<h3>전공 평점</h3>
						<p class="value">
							<c:choose>
							<c:when test="${totalscore.cal_total_avgscore!=null}" >
								${totalscore.cal_total_majorscore}
							</c:when>
							<c:otherwise>
                      	      0
                     	   </c:otherwise>
							</c:choose>
						</p>
						<p>/</p>
						<p class="total">4.5</p>
					</div>
					<div class="column acquisition">
						<h3>취득 학점</h3>
						<p class="value">
						<c:choose>
							<c:when test="${totalscore.cal_total_acq!=null}" >
								${totalscore.cal_total_acq}
							</c:when>
							<c:otherwise>
                     	       0
                    	    </c:otherwise>
						</c:choose>
						</p>
						<p>/</p>
						<p class="total" title="졸업 학점 설정">130</p>
					</div>
				</article>
			</div>
			<div class="select_ses">
				<select name="select_semester">
					<option disabled="disabled" selected>학기 선택</option>
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
						<p class="value" id="avgscore">0</p>
					</div>
					<div class="column majorscore">
						<h3>전공</h3>
						<p class="value" id="majorscore">0</p>
					</div>
					<div class="column acq">
						<h3>취득</h3>
						<p class="value" id="acq">0</p>
					</div>
				</div>
				<div class="button">
				<button id="openModalBtn" class="cal-btn4">시간표 불러오기</button>
				</div>
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
           					<input type="submit" value="가져오기" class="cal-btn4">
      					</form>
					</div>
				</div>
				<form id="cal_count">
					<table class="cal-table">
						<thead class="cal_thead">
							<tr>
        			 		   	<th class="name">과목명</th>
        			   		 	<th class="credit">학점</th>
        			   		 	<th class="grade">성적</th>
       		 		  		  	<th class="major">전공</th>
       				 	  	</tr>
						</thead>
						<tbody>
		
						</tbody>
					</table>
					<input type="hidden" id="cal_semester" name="cal_semester" value="">
					<input type="submit" value="계산하기" class="cal-btn4">
					<input type="button" class="cal-btn4" id="reset" value="초기화">
				</form>
			</div> 
		</div>
	</div>
</div>
</body>
</html>