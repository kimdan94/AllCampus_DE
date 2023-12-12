package kr.course.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.course.dao.CourseDAO;
import kr.course.vo.CourseVO;

public class CourseFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String course_subject = request.getParameter("course_subject");
		String keyword = request.getParameter("keyword");
		String course_sort = request.getParameter("course_sort");
		String course_category = request.getParameter("course_category");
		String course_credit = request.getParameter("course_credit");
		
		CourseDAO dao = CourseDAO.getInstance();
		
		List<CourseVO> list = null;
		
		list = dao.getListCourse(course_subject, keyword, course_sort, course_category, course_credit);
		
		request.setAttribute("list", list);
		
		// JSP 경로 반환
		return "/WEB-INF/views/course/courseForm.jsp";
	}

}
