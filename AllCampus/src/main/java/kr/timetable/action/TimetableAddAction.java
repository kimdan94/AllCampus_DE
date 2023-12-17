package kr.timetable.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.timetable.dao.TimetableDAO;

public class TimetableAddAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		// ajax에서 전송된 data 변환 
		String course_code = request.getParameter("course_code"); 
		String timetable_table_id = request.getParameter("timetable_table_id"); 
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		
		TimetableDAO dao = TimetableDAO.getInstance();
		
		//가진 정보 : user_num(=mem_num), course_code, listClick(시간표 정보)
//		System.out.println("user_num : " + user_num);
//		System.out.println("course_code : " + course_code);
		
		//timetable_table_id = timetable_table_id.substring(1, timetable_table_id.length()-1);
		timetable_table_id = timetable_table_id.replaceAll("[^0-9,_]", "");
//		System.out.println("timetable_table_id " + timetable_table_id);
		String[] timetable_tableId = timetable_table_id.split(",");
		for(int i=0; i<timetable_tableId.length; i++) {
//			System.out.println("[" + i + "] : " + timetable_tableId[i]);
//			System.out.println("user num : " + user_num + " course code : " + course_code + " timetable table id : " + timetable_tableId[i]);
			dao.insertTimetable(user_num, course_code, timetable_tableId[i]);
		}
		
		//JSON 문자열 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
