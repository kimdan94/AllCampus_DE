$(function() {
	var name = $('#courseDBtable tr');
	//Hover 효과
	for (let i = 0; i < name.length; i++) {
		//마우스 오버
		$(name[i]).on('mouseover', (e) => {
			$(name[i]).css({ "color": "red" });
			$.ajax({
				url: 'courseHover.do',
				type: 'post',
				data: { course_code: $(name[i]).prop("id") },
				dataType: 'json',
				success: function(param) {
					mouseoverTime(param);
				},
				error: function() {
					alert('네트워크 오류 발생');
				}
			});

		});
		//마우스 아웃
		$(name[i]).on('mouseout', (e) => {
			$(name[i]).css({ "color": "" });
			$('#timetable').css({ "color": "" });
			$.ajax({
				url: 'courseHover.do',
				type: 'post',
				data: { course_code: $(name[i]).prop("id") },
				dataType: 'json',
				success: function(param) {
					mouseoutTime(param);
				},
				error: function() {
					alert('네트워크 오류 발생');
				}
			});
		});
	}
	
	var standard_time = [];
		for (var i = 9; i < 18; i++) {
			for (var j = 0; j < 2; j++) {
				standard_time.push(i * 60 + j * 30);
			}
		}

	function mouseoverTime(param) {
		$(param.listHover).each(function(index, item) {
			//console.log("standard " + standard_time);
			var startList = item.course_start_time.split(':');
			var startTime = Number(startList[0]) * 60 + Number(startList[1]);
			var endList = item.course_end_time.split(':');
			var endTime = Number(endList[0]) * 60 + Number(endList[1]);

			//console.log("start_time " + item.course_start_time + " - " + startTime);
			//console.log("end_time " + item.course_end_time + " - " + endTime);

			for (var i = 0; i < standard_time.length; i++) {
				if (standard_time[i] >= startTime && standard_time[i] < endTime) {
					//console.log("해당되는 값" + standard_time[i]);
					//console.log(item.course_day + "_" + standard_time[i]);

					//$("#" + item.course_day + "_" + standard_time[i]).css({ "background-color": "blue" });
					$("#" + item.course_day + "_" + standard_time[i]).css({ "border": "1px solid red" }); 
				}
			}

			/*출력
			item.course_day
			item.course_start_time
			item.course_end_time
			*/
		});

	}

	function mouseoutTime(param) {
		/*var standard_time = [];
		for (var i = 9; i < 18; i++) {
			for (var j = 0; j < 2; j++) {
				standard_time.push(i * 60 + j * 30);
			}
		}*/
		$(param.listHover).each(function(index, item) {
			//console.log("standard " + standard_time);
			var startList = item.course_start_time.split(':');
			var startTime = Number(startList[0]) * 60 + Number(startList[1]);
			var endList = item.course_end_time.split(':');
			var endTime = Number(endList[0]) * 60 + Number(endList[1]);

			//console.log("start_time " + item.course_start_time + " - " + startTime);
			//console.log("end_time " + item.course_end_time + " - " + endTime);

			for (var i = 0; i < standard_time.length; i++) {
				if (standard_time[i] >= startTime && standard_time[i] < endTime) {
					//console.log("해당되는 값" + standard_time[i]);
					//console.log(item.course_day + "_" + standard_time[i]);

					//$("#" + item.course_day + "_" + standard_time[i]).css({ "background-color": "" });
					$("#" + item.course_day + "_" + standard_time[i]).css({ "border": "" });
				}
			}
		});

	}

});