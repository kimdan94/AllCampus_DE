package kr.courseeva.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.course.vo.CourseVO;
import kr.courseeva.dao.CourseEvaDAO;

public class CourseYearSemesterAction implements Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		HttpSession session = request.getSession(); 
		Integer user_num =(Integer)session.getAttribute("user_num"); 
		if(user_num == null) {//로그인이 되지 않은경우 
			return "redirect:/member/loginForm.do"; 
		}
		//인증 회원과 관리자만 접근 가능
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth == 2) {
			request.setAttribute("notice_msg", "학교 인증을 마친 학생들만 이용할 수 있어요!");
			request.setAttribute("notice_url", request.getContextPath()+"/main/home.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		
		String course_name = request.getParameter("course_name");
		String course_prof = request.getParameter("course_prof");
		
		CourseEvaDAO dao = CourseEvaDAO.getInstance();
		List<CourseVO> list1 = null;
		list1 = dao.getCourseYearSemester(course_name,course_prof);
		
		mapAjax.put("list1",list1);
		
		//JSON 문자열 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		//로그인이 된 경우
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}