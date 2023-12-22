$(function() {
	var name = $('#courseDBtable tr'); // 강의 (필터링) 목록
	
	var standard_time = []; // 시간 나눠놓은 기준
	for (var i = 9; i < 18; i++) {
		for (var j = 0; j < 2; j++) {
			standard_time.push(i * 60 + j * 30);
		}
	}
	var text = "...";//말 줄임표 -- > table 크기를 벗어나면 작동

	// 강의 click 시 클릭한 강의의 course_code를 addTime에 넘겨줌(오로지 넘겨주는 역할만 하는 ajax, action)
	for (let i = 0; i < name.length; i++) {
		// click 이벤트 실행
		$(name[i]).on('click', (e) => {
			$(name[i]).css({ "color": "green" });
			course_code = $(name[i]).prop("id");
			$.ajax({
				url: 'timetableAddList.do', // 전송 url (전송하고 데이터 받기 위한 action 페이지 dao XX)
				type: 'post',
				data: { course_code: $(name[i]).prop("id") },
				dataType: 'json',
				success: function(param) { // 클릭한 course_code에 대한 강의 정보
					addTime(param, course_code); // 받은 course_code를 넘겨줌 -> 다른 url에 넘길 것
				},
				error: function() {
					alert('네트워크 오류 발생');
				}
			});
		});
		
	}
	
	// 위에서 강의를 click해서 받아온 course_code를 이용해서 timetable 조작하기 -> timetableAdd에 전송하기
	function addTime(param, course_code) {
		console.log('listclick');
		console.log(param.listClick);
		$(param.listClick).each(function(index, item) {
			var startList = item.course_start_time.split(':');
			var startTime = Number(startList[0]) * 60 + Number(startList[1]);
			var endList = item.course_end_time.split(':');
			var endTime = Number(endList[0]) * 60 + Number(endList[1]);

			var timetable_table_id = [];
			for (var i = 0; i < standard_time.length; i++) {
				if (standard_time[i] >= startTime && standard_time[i] < endTime) {
					//console.log("id :  " + item.course_day + "_" + standard_time[i]);
					var timetable_tableID = item.course_day + "_" + standard_time[i];
					timetable_table_id.push(timetable_tableID);
					
					$("#" + item.course_day + "_" + standard_time[i]).css({ "color": "blue" }); // 강의 클릭시 시간표 색상 바뀜
					//지울것
				}
			}
			$.ajax({
				url: 'timetableAdd.do',
				type: 'post',
				data: {course_code: course_code, timetable_table_id: JSON.stringify(timetable_table_id)},// 보내려는 데이터를 문자열로 변환해서 보내기
				dataType: 'json',
				success: function(param) {
					console.log('성공'); // 통과
					year = param.year;
					semester = param.semester;
					getTimetablePrint(year, semester);
				},
				error: function() {
					alert('네트워크 오류 발생');
				}
			});
		});
	}
	
	
	
	
	//==================timetablePrint 시작======================================================================================================
	function getTimetablePrint(year, semester){
		$.ajax({
			url: 'timetablePrint.do', // 전송 url : TimetablePrintAction
			type: 'post',
			data: { year:year, semester:semester }, // year, semester 전송하기
			dataType: 'json',
			success: function(param) {
				//printMon(param);
				printMonTimetable(param);
				printTueTimetable(param);	
				printWedTimetable(param);
				printThurTimetable(param);
				printFriTimetable(param);
				
			},
			error: function() {
				alert('네트워크 오류 발생');
			}
		});
	}
	getTimetablePrint();
	
	
	//--------------시간표의 강의를 클릭했을 때 강의에 대한 정보&강의 삭제 버튼을 모달로 나타냄---------------------------------------------------------------------------
	$(document).on("click",'.class-info',function(e){ // table에서 클래스 name만 부여된 곳만 이벤트 처리
		$.ajax({
			url: 'timetableView.do',
			type: 'post',
			data: {unique: '#'+$(this).attr('id')}, // 테이블 아이디 전송
			dataType: 'json',
			success: function(param) {
				$('.delete_header').empty(); // 값이 누적되기 때문에 empty
				$('.delete_body').empty();
				
				const weekend = ['','월','화','수','목','금'];
				
				$('.new-btn').click();
				
				let delete_header = '<div>강의정보</div>';
				let delete_body = '<div>' + param.beforeDeleteView[0].course_name + '</div>';
				delete_body += '<div>  교수 : ' + param.beforeDeleteView[0].course_prof + '</div>';
				delete_body += '<div>  분류 : ' + param.beforeDeleteView[0].course_subject + '</div>';
				delete_body += '<div>  구분 : ' + param.beforeDeleteView[0].course_category + '</div>';
				delete_body += '<div>  학점 : ' + param.beforeDeleteView[0].course_credit + '</div>';
				delete_body += '<div>  강의실 : ' + param.beforeDeleteView[0].course_classroom + '</div>';
				for(var y=0; y<param.beforeDeleteView.length; y++){
					delete_body += '<div>' + weekend[param.beforeDeleteView[y].course_day]	+
					' ' + param.beforeDeleteView[y].course_start_time +
					' ' + param.beforeDeleteView[y].course_end_time + '</div>';
				}
				delete_body += '<input type="text" name="delete_course" id="delete_course" value="' + param.beforeDeleteView[0].course_code + '">';
				
				console.log(delete_body);
				$('.delete_header').append(delete_header);
				$('.delete_body').append(delete_body);

			},
			error: function() {
				alert('unique Arr 네트워크 오류 발생');
			}
		});
		
	});
	//-------------------------------------------------------------------------------------------------------------------------------------------
	
	
	// --------월요일------------------
	function printMonTimetable(param){
		for(var i=0; i<param.listMON.length; i++){
			var array = [];
			for(var j=0; j<param.monList.length; j++){
				if(param.listMON[i].course_code == param.monList[j].course_code){
					array.push(Number(param.monList[j].timetable_table_id.slice(2)));
				}
			}
			
			
			array.sort((a, b) => a - b); // js에서 정렬할 때는 문자열로 변경해서 정렬하기 때문에 이런 방식으로 정렬해야 한다
			var prev = array[0];
			var cnt = 1;
			var list = [];
			list.push([array[0],cnt]);
			for(var k=1; k<array.length; k++){
				if(array[k] == prev + 30){
					cnt += 1;
					prev = array[k];
				}else {
					prev = array[k];
					cnt = 1;
				}
				list.push([array[k],cnt]);
			}
			// array 다 만듦


			for(var j=0; j<param.monList.length; j++){
				if(param.listMON[i].course_code == param.monList[j].course_code){
					var stand1 = 0;
					var stand2 = 0;
					for(var s=0; s<list.length; s++){
						if(list[s][1] == 1 && stand1 == 0){ // 첫번째
							stand1 = list[s][0];
						} else if(list[s][1] == 1 && stand1 != 0){ // 두번째
							$('#1_'+stand1).attr('rowspan', stand2);
							$('#1_'+stand1).addClass('class-info');
							$('#1_' + stand1).html(param.monList[j].timetable_course_name); // text 크기를 넘어가면 조절
							$('#1_'+stand1).attr('style','background-color: '+param.monList[j].timetable_color); // db에서 색깔지정
							stand1 = list[s][0];
						} else if(list[s][1] != 1){
							stand2 = list[s][1];
							$('#1_'+list[s][0]).attr('style', "display:none;");
						}
					}
					$('#1_'+stand1).attr('rowspan', stand2);
					$('#1_'+stand1).addClass('class-info');
					$('#1_' + stand1).html(param.monList[j].timetable_course_name); // text 크기를 넘어가면 조절
					$('#1_'+stand1).attr('style','background-color: '+param.monList[j].timetable_color); // db에서 색깔지정
				}
			}
		}
	}
	
	// --------화요일---------
	function printTueTimetable(param){
		for(var i=0; i<param.listTUE.length; i++){
			var array = [];
			for(var j=0; j<param.tueList.length; j++){
				if(param.listTUE[i].course_code == param.tueList[j].course_code){
					array.push(Number(param.tueList[j].timetable_table_id.slice(2)));
				}
			}
			
			
			array.sort((a, b) => a - b); // js에서 정렬할 때는 문자열로 변경해서 정렬하기 때문에 이런 방식으로 정렬해야 한다
			var prev = array[0];
			var cnt = 1;
			var list = [];
			list.push([array[0],cnt]);
			for(var k=1; k<array.length; k++){
				if(array[k] == prev + 30){
					cnt += 1;
					prev = array[k];
				}else {
					prev = array[k];
					cnt = 1;
				}
				list.push([array[k],cnt]);
			}
			// array 다 만듦
			
			
			for(var j=0; j<param.tueList.length; j++){
				if(param.listTUE[i].course_code == param.tueList[j].course_code){
					var stand1 = 0;
					var stand2 = 0;
					for(var s=0; s<list.length; s++){
						if(list[s][1] == 1 && stand1 == 0){ // 첫번째
							stand1 = list[s][0];
						} else if(list[s][1] == 1 && stand1 != 0){ // 두번째
							$('#2_'+stand1).attr('rowspan', stand2);
							$('#2_'+stand1).addClass('class-info');
							$('#2_' + stand1).html(param.tueList[j].timetable_course_name); // text 크기를 넘어가면 조절
							$('#2_'+stand1).attr('style','background-color: '+param.tueList[j].timetable_color); // db에서 색깔지정
							stand1 = list[s][0];
						} else if(list[s][1] != 1){
							stand2 = list[s][1];
							$('#2_'+list[s][0]).attr('style', "display:none;");
						}
					}
					$('#2_'+stand1).attr('rowspan', stand2);
					$('#2_'+stand1).addClass('class-info');
					$('#2_' + stand1).html(param.tueList[j].timetable_course_name); // text 크기를 넘어가면 조절
					$('#2_'+stand1).attr('style','background-color: '+param.tueList[j].timetable_color); // db에서 색깔지정
				}
			}
		}
	}
	
	
	// --------수요일---------
	function printWedTimetable(param){
		for(var i=0; i<param.listWED.length; i++){
			var array = [];
			for(var j=0; j<param.wedList.length; j++){
				if(param.listWED[i].course_code == param.wedList[j].course_code){
					array.push(Number(param.wedList[j].timetable_table_id.slice(2)));
				}
			}
			
			
			array.sort((a, b) => a - b); // js에서 정렬할 때는 문자열로 변경해서 정렬하기 때문에 이런 방식으로 정렬해야 한다
			var prev = array[0];
			var cnt = 1;
			var list = [];
			list.push([array[0],cnt]);
			for(var k=1; k<array.length; k++){
				if(array[k] == prev + 30){
					cnt += 1;
					prev = array[k];
				}else {
					prev = array[k];
					cnt = 1;
				}
				list.push([array[k],cnt]);
			}
			// array 다 만듦


			for(var j=0; j<param.wedList.length; j++){
				if(param.listWED[i].course_code == param.wedList[j].course_code){
					var stand1 = 0;
					var stand2 = 0;
					for(var s=0; s<list.length; s++){
						if(list[s][1] == 1 && stand1 == 0){ // 첫번째
							stand1 = list[s][0];
						} else if(list[s][1] == 1 && stand1 != 0){ // 두번째
							$('#3_'+stand1).attr('rowspan', stand2);
							$('#3_'+stand1).addClass('class-info');
							$('#3_' + stand1).html(param.wedList[j].timetable_course_name); // text 크기를 넘어가면 조절
							$('#3_'+stand1).attr('style','background-color: '+param.wedList[j].timetable_color); // db에서 색깔지정
							stand1 = list[s][0];
						} else if(list[s][1] != 1){
							stand2 = list[s][1];
							$('#3_'+list[s][0]).attr('style', "display:none;");
						}
					}
					$('#3_'+stand1).attr('rowspan', stand2);
					$('#3_'+stand1).addClass('class-info');
					$('#3_' + stand1).html(param.wedList[j].timetable_course_name); // text 크기를 넘어가면 조절
					$('#3_'+stand1).attr('style','background-color: '+param.wedList[j].timetable_color); // db에서 색깔지정
				}
			}
		}
	}
	
	
	// --------목요일---------
	function printThurTimetable(param){
		for(var i=0; i<param.listTHUR.length; i++){
			var array = [];
			for(var j=0; j<param.thurList.length; j++){
				if(param.listTHUR[i].course_code == param.thurList[j].course_code){
					array.push(Number(param.thurList[j].timetable_table_id.slice(2)));
				}
			}
			
			
			array.sort((a, b) => a - b); // js에서 정렬할 때는 문자열로 변경해서 정렬하기 때문에 이런 방식으로 정렬해야 한다
			var prev = array[0];
			var cnt = 1;
			var list = [];
			list.push([array[0],cnt]);
			for(var k=1; k<array.length; k++){
				if(array[k] == prev + 30){
					cnt += 1;
					prev = array[k];
				}else {
					prev = array[k];
					cnt = 1;
				}
				list.push([array[k],cnt]);
			}
			// array 다 만듦
			
			
			for(var j=0; j<param.thurList.length; j++){
				if(param.listTHUR[i].course_code == param.thurList[j].course_code){
					var stand1 = 0;
					var stand2 = 0;
					for(var s=0; s<list.length; s++){
						if(list[s][1] == 1 && stand1 == 0){ // 첫번째
							stand1 = list[s][0];
						} else if(list[s][1] == 1 && stand1 != 0){ // 두번째
							$('#4_'+stand1).attr('rowspan', stand2);
							$('#4_'+stand1).addClass('class-info');
							$('#4_' + stand1).html(param.thurList[j].timetable_course_name); // text 크기를 넘어가면 조절
							$('#4_'+stand1).attr('style','background-color: '+param.thurList[j].timetable_color); // db에서 색깔지정
							stand1 = list[s][0];
						} else if(list[s][1] != 1){
							stand2 = list[s][1];
							$('#4_'+list[s][0]).attr('style', "display:none;");
						}
					}
					$('#4_'+stand1).attr('rowspan', stand2);
					$('#4_'+stand1).addClass('class-info');
					$('#4_' + stand1).html(param.thurList[j].timetable_course_name); // text 크기를 넘어가면 조절
					$('#4_'+stand1).attr('style','background-color: '+param.thurList[j].timetable_color); // db에서 색깔지정
				}
			}
		}
	}
	
	
	// --------금요일---------
	function printFriTimetable(param){
		for(var i=0; i<param.listFRI.length; i++){
			var array = [];
			for(var j=0; j<param.friList.length; j++){
				if(param.listFRI[i].course_code == param.friList[j].course_code){
					array.push(Number(param.friList[j].timetable_table_id.slice(2)));
				}
			}
			
			
			array.sort((a, b) => a - b); // js에서 정렬할 때는 문자열로 변경해서 정렬하기 때문에 이런 방식으로 정렬해야 한다
			var prev = array[0];
			var cnt = 1;
			var list = [];
			list.push([array[0],cnt]);
			for(var k=1; k<array.length; k++){
				if(array[k] == prev + 30){
					cnt += 1;
					prev = array[k];
				}else {
					prev = array[k];
					cnt = 1;
				}
				list.push([array[k],cnt]);
			}
			// array 다 만듦
			
			
			for(var j=0; j<param.friList.length; j++){
				if(param.listFRI[i].course_code == param.friList[j].course_code){
					var stand1 = 0;
					var stand2 = 0;
					for(var s=0; s<list.length; s++){
						if(list[s][1] == 1 && stand1 == 0){ // 첫번째
							stand1 = list[s][0];
						} else if(list[s][1] == 1 && stand1 != 0){ // 두번째
							$('#5_'+stand1).attr('rowspan', stand2);
							$('#5_'+stand1).addClass('class-info');
							$('#5_' + stand1).html(param.friList[j].timetable_course_name); // text 크기를 넘어가면 조절
							$('#5_'+stand1).attr('style','background-color: '+param.friList[j].timetable_color); // db에서 색깔지정
							stand1 = list[s][0];
						} else if(list[s][1] != 1){
							stand2 = list[s][1];
							$('#5_'+list[s][0]).attr('style', "display:none;");
						}
					}
					$('#5_'+stand1).attr('rowspan', stand2);
					$('#5_'+stand1).addClass('class-info');
					$('#5_' + stand1).html(param.friList[j].timetable_course_name); // text 크기를 넘어가면 조절
					$('#5_'+stand1).attr('style','background-color: '+param.friList[j].timetable_color); // db에서 색깔지정
				}
			}
		}
	}
	
	
	//=============================================================================================================================================
	
	function initTable(){
		let timeList = [9,10,11,12,13,14,15,16,17];
			$('#timetable > tbody').empty();
			for(let i=0;i<timeList.length;i++){
				let output = '<tr>';
				output += '<td rowspan="2">' + timeList[i] + '</td>';
				output += '<td id="1_'+ timeList[i]*60 + '"></td>';
				output += '<td id="2_'+ timeList[i]*60 + '"></td>';
				output += '<td id="3_'+ timeList[i]*60 + '"></td>';
				output += '<td id="4_'+ timeList[i]*60 + '"></td>';
				output += '<td id="5_'+ timeList[i]*60 + '"></td>';
				output += '</tr>';
				output += '<tr>';
				output += '<td id="1_'+ (timeList[i]*60+30) + '"></td>';
				output += '<td id="2_'+ (timeList[i]*60+30) + '"></td>';
				output += '<td id="3_'+ (timeList[i]*60+30) + '"></td>';
				output += '<td id="4_'+ (timeList[i]*60+30) + '"></td>';
				output += '<td id="5_'+ (timeList[i]*60+30) + '"></td>';
				output += '</tr>';
				
				$('#timetable > tbody').append(output)
			}
	}
	
	
	$('#whole-init').on('click', (e) =>{
		$.ajax({
			url: 'timetableWholeInit.do',
			type: 'post',
			data: {},
			dataType: 'json',
			success: function(param){
				initTable();
			},
			error: function(){
				alert('init 네트워크 오류');
			}
		})
		
	});
	
	
	$(name[i]).on('click', (e) => {
			$(name[i]).css({ "color": "green" });
			course_code = $(name[i]).prop("id");
			$.ajax({
				url: 'timetableAddList.do', // 전송 url (전송하고 데이터 받기 위한 action 페이지 dao XX)
				type: 'post',
				data: { course_code: $(name[i]).prop("id") },
				dataType: 'json',
				success: function(param) { // 클릭한 course_code에 대한 강의 정보
					addTime(param, course_code); // 받은 course_code를 넘겨줌 -> 다른 url에 넘길 것
				},
				error: function() {
					alert('네트워크 오류 발생');
				}
			});
		});
	
	
	
	
	
	//=============================================================================================================================================
	
	// 수정하기 전 코드 (강의명, 색상명이 작동을 하지 않고 나머지는 작동하는 코드, 이 코드를 이용해서 위에 실행되는 코드를 만들었음)
	// --------월요일--------------------
	function printMonTimetable2(param){
		for(var i=0; i<param.listMON.length; i++){
			var array = [];
			for(var j=0; j<param.monList.length; j++){
				if(param.listMON[i].course_code == param.monList[j].course_code){
					array.push(Number(param.monList[j].timetable_table_id.slice(2)));
				}
				
				if(j == param.monList.length - 1){
					array.sort((a, b) => a - b); // js에서 정렬할 때는 문자열로 변경해서 정렬하기 때문에 이런 방식으로 정렬해야 한다
					var prev = array[0];
					var cnt = 1;
					var list = [];
					list.push([array[0],cnt]);
					for(var k=1; k<array.length; k++){
						if(array[k] == prev + 30){
							cnt += 1;
							prev = array[k];
						}else {
							prev = array[k];
							cnt = 1;
						}
						list.push([array[k],cnt]);
					}
					
					var stand1 = 0;
					var stand2 = 0;
					for(var s=0; s<list.length; s++){
						if(list[s][1] == 1 && stand1 == 0){ // 첫번째
							stand1 = list[s][0];
						} else if(list[s][1] == 1 && stand1 != 0){ // 두번째
							$('#1_'+stand1).attr('rowspan', stand2);
							$('#1_'+stand1).addClass('class-info');
							$('#1_'+stand1).html(param.monList[j].timetable_course_name);
							$('#1_'+stand1).attr('style','background-color: ' + +param.monList[j].timetable_color);
							stand1 = list[s][0];
						} else if(list[s][1] != 1){
							stand2 = list[s][1];
							$('#1_'+list[s][0]).attr('style', "display:none;");
						}
					}
					$('#1_'+stand1).attr('rowspan', stand2);
					$('#1_'+stand1).addClass('class-info');
					$('#1_' + stand1).html(param.monList[j].timetable_course_name); // text 크기를 넘어가면 조절
					$('#1_'+stand1).attr('style','background-color: '+param.monList[j].timetable_color); // db에서 색깔지정
				}
			}
		}
	}
	
	
	
	// --------화요일--------------------
	function printTueTimetable2(param){
		
		for(var i=0; i<param.listTUE.length; i++){
			var array = [];
			for(var j=0; j<param.tueList.length; j++){
				if(param.listTUE[i].course_code == param.tueList[j].course_code){
					array.push(Number(param.tueList[j].timetable_table_id.slice(2)));
				}
				if(j == param.tueList.length - 1){
					console.log(param.listTUE[i]);
					array.sort((a, b) => a - b); // js에서 정렬할 때는 문자열로 변경해서 정렬하기 때문에 이런 방식으로 정렬해야 한다
					var prev = array[0];
					var cnt = 1;
					var list = [];
					list.push([array[0],cnt]);
					for(var k=1; k<array.length; k++){
						if(array[k] == prev + 30){
							cnt += 1;
							prev = array[k];
						}else {
							prev = array[k];
							cnt = 1;
						}
						list.push([array[k],cnt]);
					}
					
					var stand1 = 0;
					var stand2 = 0;
					for(var s=0; s<list.length; s++){
						if(list[s][1] == 1 && stand1 == 0){ // 첫번째
							stand1 = list[s][0];
						} else if(list[s][1] == 1 && stand1 != 0){ // 두번째
							$('#2_'+stand1).attr('rowspan', stand2);
							$('#2_'+stand1).addClass('class-info'); // 클래스 name 부여
							$('#2_'+stand1).html(param.tueList[j].timetable_course_name);
							$('#2_'+stand1).attr('style','background-color: '+param.tueList[j].timetable_color);
							stand1 = list[s][0];
						} else if(list[s][1] != 1){
							stand2 = list[s][1];
							$('#2_'+list[s][0]).attr('style', "display:none;");
						}
					}
					$('#2_'+stand1).attr('rowspan', stand2);
					$('#2_'+stand1).addClass('class-info');
					$('#2_'+stand1).html(param.tueList[j].timetable_course_name);
					$('#2_'+stand1).attr('style','background-color: '+param.tueList[j].timetable_color);
				}
			}
		}
	}
	
	// --------수요일--------------------
	function printWedTimetable2(param){
		for(var i=0; i<param.listWED.length; i++){
			var array = [];
			for(var j=0; j<param.wedList.length; j++){
				if(param.listWED[i].course_code == param.wedList[j].course_code){
					array.push(Number(param.wedList[j].timetable_table_id.slice(2)));
				}
				if(j == param.wedList.length - 1){
					array.sort((a, b) => a - b); // js에서 정렬할 때는 문자열로 변경해서 정렬하기 때문에 이런 방식으로 정렬해야 한다
					var prev = array[0];
					var cnt = 1;
					var list = [];
					list.push([array[0],cnt]);
					for(var k=1; k<array.length; k++){
						if(array[k] == prev + 30){
							cnt += 1;
							prev = array[k];
						}else {
							prev = array[k];
							cnt = 1;
						}
						list.push([array[k],cnt]);
					}
					
					var stand1 = 0;
					var stand2 = 0;
					for(var s=0; s<list.length; s++){
						if(list[s][1] == 1 && stand1 == 0){ // 첫번째
							stand1 = list[s][0];
						} else if(list[s][1] == 1 && stand1 != 0){ // 두번째
							$('#3_'+stand1).attr('rowspan', stand2);
							$('#3_'+stand1).addClass('class-info');
							$('#3_'+stand1).html(param.wedList[j].timetable_course_name);
							$('#3_'+stand1).attr('style','background-color: '+param.wedList[j].timetable_color);
							stand1 = list[s][0];
						} else if(list[s][1] != 1){
							stand2 = list[s][1];
							$('#3_'+list[s][0]).attr('style', "display:none;");
						}
					}
					$('#3_'+stand1).attr('rowspan', stand2);
					$('#3_'+stand1).addClass('class-info');
					$('#3_'+stand1).html(param.wedList[j].timetable_course_name);
					$('#3_'+stand1).attr('style','background-color: '+param.wedList[j].timetable_color);
				}
			}
		}
	}
	
	// --------목요일---------------------
	function printThurTimetable2(param){
		for(var i=0; i<param.listTHUR.length; i++){
			var array = [];
			for(var j=0; j<param.thurList.length; j++){
				if(param.listTHUR[i].course_code == param.thurList[j].course_code){
					array.push(Number(param.thurList[j].timetable_table_id.slice(2)));
				}
				if(j == param.thurList.length - 1){
					array.sort((a, b) => a - b); // js에서 정렬할 때는 문자열로 변경해서 정렬하기 때문에 이런 방식으로 정렬해야 한다
					var prev = array[0];
					var cnt = 1;
					var list = [];
					list.push([array[0],cnt]);
					for(var k=1; k<array.length; k++){
						if(array[k] == prev + 30){
							cnt += 1;
							prev = array[k];
						}else {
							prev = array[k];
							cnt = 1;
						}
						list.push([array[k],cnt]);
					}

					var stand1 = 0;
					var stand2 = 0;
					for(var s=0; s<list.length; s++){
						if(list[s][1] == 1 && stand1 == 0){ // 첫번째
							stand1 = list[s][0];
						} else if(list[s][1] == 1 && stand1 != 0){ // 두번째
							$('#4_'+stand1).attr('rowspan', stand2);
							$('#4_'+stand1).addClass('class-info');
							$('#4_'+stand1).html(param.thurList[j].timetable_course_name);
							$('#4_'+stand1).attr('style','background-color: '+param.thurList[j].timetable_color);
							stand1 = list[s][0];
						} else if(list[s][1] != 1){
							stand2 = list[s][1];
							$('#4_'+list[s][0]).attr('style', "display:none;");
						}
					}
					$('#4_'+stand1).attr('rowspan', stand2);
					$('#4_'+stand1).addClass('class-info');
					$('#4_'+stand1).html(param.thurList[j].timetable_course_name);
					$('#4_'+stand1).attr('style','background-color: '+param.thurList[j].timetable_color);
				}
			}
		}
	}
	
	// --------금요일--------------------
	function printFriTimetable2(param){
		for(var i=0; i<param.listFRI.length; i++){
			var array = [];
			for(var j=0; j<param.friList.length; j++){
				if(param.listFRI[i].course_code == param.friList[j].course_code){
					array.push(Number(param.friList[j].timetable_table_id.slice(2)));
				}
				if(j == param.friList.length - 1){
					array.sort((a, b) => a - b); // js에서 정렬할 때는 문자열로 변경해서 정렬하기 때문에 이런 방식으로 정렬해야 한다
					var prev = array[0];
					var cnt = 1;
					var list = [];
					list.push([array[0],cnt]);
					for(var k=1; k<array.length; k++){
						if(array[k] == prev + 30){
							cnt += 1;
							prev = array[k];
						}else {
							prev = array[k];
							cnt = 1;
						}
						list.push([array[k],cnt]);
					}
					
					var stand1 = 0;
					var stand2 = 0;
					for(var s=0; s<list.length; s++){
						if(list[s][1] == 1 && stand1 == 0){ // 첫번째
							stand1 = list[s][0];
						} else if(list[s][1] == 1 && stand1 != 0){ // 두번째
							$('#5_'+stand1).attr('rowspan', stand2);
							$('#5_'+stand1).addClass('class-info');
							$('#5_'+stand1).html(param.friList[j].timetable_course_name);
							$('#5_'+stand1).attr('style','background-color: '+param.friList[j].timetable_color);
							stand1 = list[s][0];
						} else if(list[s][1] != 1){
							stand2 = list[s][1];
							$('#5_'+list[s][0]).attr('style', "display:none;");
						}
					}
					$('#5_'+stand1).attr('rowspan', stand2);
					$('#5_'+stand1).addClass('class-info');
					$('#5_'+stand1).html(param.friList[j].timetable_course_name);
					$('#5_'+stand1).attr('style','background-color: '+param.friList[j].timetable_color);
				}
			}
		}
	}
	
	
	//================timetablePrint 끝==================
	
});