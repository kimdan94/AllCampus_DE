package kr.timetable.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.timetable.dao.TimetableDAO;

public class TimetableWholeInit implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		
		// 해당 user_num으로 데이터를 지우기
		TimetableDAO daoTimetable = TimetableDAO.getInstance();
		daoTimetable.initTimetable(user_num);
		
		//JSON 문자열 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
