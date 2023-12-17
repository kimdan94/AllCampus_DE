$(function() {
	var name = $('#courseDBtable tr');

	var standard_time = [];
	for (var i = 9; i < 18; i++) {
		for (var j = 0; j < 2; j++) {
			standard_time.push(i * 60 + j * 30);
		}
	}

	// 강의 click 시 클릭한 강의의 course_code를 addTime에 넘겨줌(오로지 넘겨주는 역할만 하는 ajax, action)
	for (let i = 0; i < name.length; i++) {
		// click 이벤트 실행
		$(name[i]).on('click', (e) => {
			console.log('click');
			
			$(name[i]).css({ "color": "green" });
			console.log($(name[i]).prop("id"));
			course_code = $(name[i]).prop("id");
			$.ajax({
				url: 'timetableAddList.do', // 전송 url (전송하고 데이터 받기 위한 action 페이지 dao XX)
				type: 'post',
				data: { course_code: $(name[i]).prop("id") },
				dataType: 'json',
				success: function(param) {
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
		$(param.listClick).each(function(index, item) {
			var startList = item.course_start_time.split(':');
			var startTime = Number(startList[0]) * 60 + Number(startList[1]);
			var endList = item.course_end_time.split(':');
			var endTime = Number(endList[0]) * 60 + Number(endList[1]);

			console.log("start_time " + item.course_start_time + " - " + startTime);
			console.log("end_time " + item.course_end_time + " - " + endTime);
			var timetable_table_id = [];
			for (var i = 0; i < standard_time.length; i++) {
				if (standard_time[i] >= startTime && standard_time[i] < endTime) {
					//console.log("id :  " + item.course_day + "_" + standard_time[i]);
					var timetable_tableID = item.course_day + "_" + standard_time[i];
					timetable_table_id.push(timetable_tableID);
					
					$("#" + item.course_day + "_" + standard_time[i]).css({ "color": "red" });
					//지울것
				}
			}
			$.ajax({
				url: 'timetableAdd.do',
				type: 'post',
				data: {course_code: course_code, timetable_table_id: JSON.stringify(timetable_table_id)},// 보내려는 데이터를 문자열로 변환해서 보내기
				dataType: 'json',
				success: function(param) {
					console.log('성공');
				},
				error: function() {
					alert('네트워크 오류 발생');
				}
			});

		
			//출력
			//item.course_day
			//item.course_start_time
			//item.course_end_time
			
		});

	}


});