package kr.course.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.course.dao.CourseDAO;
import kr.course.vo.CourseVO;

public class CourseHoverAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 번환
		String course_code = request.getParameter("course_code");
		
		System.out.println("course_code " + course_code);
		
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		//mapAjax.put("result", course_code);
		
		CourseDAO dao = CourseDAO.getInstance();
		List<CourseVO> listHover = dao.selectDay(course_code);
		mapAjax.put("listHover", listHover);
		  
		//JSON 문자열 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
