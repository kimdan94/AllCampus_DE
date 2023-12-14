package kr.course.action;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.course.dao.CourseDAO;
import kr.course.vo.CourseVO;

public class CourseFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		// 전공/영역
		String[] course_subject = request.getParameterValues("course_subject");
		if(course_subject != null) {
			HashSet<String> hashSet_subject = new HashSet<>(Arrays.asList(course_subject));
			course_subject = hashSet_subject.toArray(new String[0]);
//			System.out.println("값 : " + Arrays.toString(course_subject));
		}

		// 검색어
		String keyword = request.getParameter("keyword");
		String keyword_hidden = request.getParameter("keyword_hidden");
		if(keyword == null) keyword = keyword_hidden;
		
		// 강의 구분 카테고리
		String[] course_category = request.getParameterValues("course_category");
		if(course_category != null) {
			HashSet<String> hashSet_category = new HashSet<>(Arrays.asList(course_category));
			course_category = hashSet_category.toArray(new String[0]);
		}
		
		// 강의 학점
		String[] course_credit = request.getParameterValues("course_credit");
		if(course_credit != null) {
			HashSet<String> hashSet_credit = new HashSet<>(Arrays.asList(course_credit));
			course_credit = hashSet_credit.toArray(new String[0]);
		}
		
		CourseDAO dao = CourseDAO.getInstance();
		
		List<CourseVO> list = null;
		List<CourseVO> list2 = null;
		List<CourseVO> course_list = null;
		
		list = dao.getListCourse(course_subject, keyword, course_category, course_credit);
		list2 = dao.getRemoveDuplicateCourseList(course_subject, keyword, course_category, course_credit);
		course_list = dao.getCourseList();
		
		request.setAttribute("list", list); // request.setAttribut("객체명", 객체);
		request.setAttribute("list2", list2);
		request.setAttribute("course_list", course_list);
		request.setAttribute("course_subject", course_subject);
		request.setAttribute("keyword", keyword);
		request.setAttribute("course_category", course_category);
		request.setAttribute("course_credit", course_credit);
		
		// JSP 경로 반환
		return "/WEB-INF/views/course/courseForm.jsp";
	}

}