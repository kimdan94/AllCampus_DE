$(function() {
	var name = $('#courseDBtable tr');
	//Hover 효과
	for (let i = 0; i < name.length; i++) {
		//마우스 오버
		$(name[i]).on('mouseover', (e) => {
			$(name[i]).css({ "color": "#808080" });
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
			var startList = item.course_start_time.split(':');
			var startTime = Number(startList[0]) * 60 + Number(startList[1]);
			var endList = item.course_end_time.split(':');
			var endTime = Number(endList[0]) * 60 + Number(endList[1]);


			for (var i = 0; i < standard_time.length; i++) {
				if (standard_time[i] >= startTime && standard_time[i] < endTime) {
					/*if(i == 0){
						$("#" + item.course_day + "_" + (standard_time[i])).css({ "border-top": "1px solid red" }); 						
					}
					if(i == (standard_time.length-1)){
						$("#" + item.course_day + "_" + (standard_time[i])).css({ "border-bottom": "1px solid red" }); 
					}*/
					$("#" + item.course_day + "_" + standard_time[i]).css({ "border": "3px solid #6699cc" }); 
					//$("#" + (item.course_day-1) + "_" + standard_time[i]).css({ "border-left": "1px solid yellow" }); 
				}
			}

		});
	}

	function mouseoutTime(param) {
		$(param.listHover).each(function(index, item) {
			//console.log("standard " + standard_time);
			var startList = item.course_start_time.split(':');
			var startTime = Number(startList[0]) * 60 + Number(startList[1]);
			var endList = item.course_end_time.split(':');
			var endTime = Number(endList[0]) * 60 + Number(endList[1]);

			for (var i = 0; i < standard_time.length; i++) {
				if (standard_time[i] >= startTime && standard_time[i] < endTime) {

					//$("#" + item.course_day + "_" + standard_time[i]).css({ "background-color": "" });
					$("#" + item.course_day + "_" + standard_time[i]).css({ "border": "" });
				}
			}
		});

	}

});