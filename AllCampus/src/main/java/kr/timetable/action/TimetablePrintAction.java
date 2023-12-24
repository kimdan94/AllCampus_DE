package kr.timetable.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.timetable.dao.TimetableDAO;
import kr.timetable.vo.TimetableVO;

public class TimetablePrintAction implements Action {// -> js > timetable.print.js로 보내기

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		int year = 2023;
		int semester = 2;
		
		String tmp_year = request.getParameter("year");
		String tmp_semester = request.getParameter("semester");
		if(tmp_year != null) {
			year = Integer.parseInt(tmp_year);
		}
		if(tmp_semester != null) {
			semester = Integer.parseInt(tmp_semester);
		}
		
		//-----------------------------------------------
		String keyfield = request.getParameter("keyfield");
		if(keyfield != null) {
			year = Integer.parseInt(keyfield.substring(0,4));
			semester = Integer.parseInt(keyfield.substring(4));
		}
		//------------------------------------------------
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		
		TimetableDAO dao = TimetableDAO.getInstance();
		
		// 요일 필터링 - 요일/mem_num/year/semester
		List<TimetableVO> monList = dao.getListPrint(user_num,year,semester,1); // 월
		List<TimetableVO> tueList = dao.getListPrint(user_num,year,semester,2); // 화
		List<TimetableVO> wedList = dao.getListPrint(user_num,year,semester,3); // 수
		List<TimetableVO> thurList = dao.getListPrint(user_num,year,semester,4); // 목
		List<TimetableVO> friList = dao.getListPrint(user_num,year,semester,5); // 금
		mapAjax.put("monList", monList); 
		mapAjax.put("tueList", tueList); 
		mapAjax.put("wedList", wedList); 
		mapAjax.put("thurList", thurList); 
		mapAjax.put("friList", friList); 
		
		
		List<TimetableVO> listMON = dao.getListCourseCode(user_num, year, semester, 1);
		List<TimetableVO> listTUE = dao.getListCourseCode(user_num, year, semester, 2);
		List<TimetableVO> listWED = dao.getListCourseCode(user_num, year, semester, 3);
		List<TimetableVO> listTHUR = dao.getListCourseCode(user_num, year, semester, 4);
		List<TimetableVO> listFRI = dao.getListCourseCode(user_num, year, semester, 5);
		mapAjax.put("listMON", listMON);
		mapAjax.put("listTUE", listTUE);
		mapAjax.put("listWED", listWED);
		mapAjax.put("listTHUR", listTHUR);
		mapAjax.put("listFRI", listFRI);
		
		//JSON 문자열 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
