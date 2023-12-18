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

public class TimetableViewAction implements Action {

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
		
		//전송된 데이터 번환
		String unique = request.getParameter("unique"); 
		if(unique.length() > 0) {
			unique = unique.substring(1,unique.length());
			TimetableDAO daoTime = TimetableDAO.getInstance();
			
			CourseDAO dao = CourseDAO.getInstance();
			List<CourseVO> beforeDeleteView = daoTime.timetableView(unique);
			mapAjax.put("beforeDeleteView", beforeDeleteView); // ajax success로 보내는 데이터
			
		}
		//JSON 문자열 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
