package kr.timetable.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.course.dao.CourseDAO;
import kr.course.vo.CourseVO;
import kr.timetable.dao.TimetableDAO;
import kr.timetable.vo.TimetableVO;

public class TimetableAddListAction implements Action { // data를 받아오기 위한 class

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 번환
		String course_code = request.getParameter("course_code"); // timetable.add.js에서 받은 데이터

		Map<String, Object> mapAjax = new HashMap<String, Object>();
		
		CourseDAO dao = CourseDAO.getInstance();
		List<CourseVO> listClick = dao.selectDay(course_code); // 강의 코드의 모든 강의 정보 저장
		
		mapAjax.put("listClick", listClick); // ajax success로 보내는 데이터
		
		TimetableDAO daoTime = TimetableDAO.getInstance();
		List<TimetableVO> slist = daoTime.getListTableID(user_num);
		mapAjax.put("slist", slist);

		//가진 정보 : user_num(=mem_num), course_code, listClick(시간표 정보)

		//JSON 문자열 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
