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
		
		System.out.println("----------------------------------------------");
		String[] course_subject = request.getParameterValues("course_subject");

		if(course_subject != null) {
			HashSet<String> hashSet_subject = new HashSet<>(Arrays.asList(course_subject));
			course_subject = hashSet_subject.toArray(new String[0]);
//			System.out.println("값 : " + Arrays.toString(course_subject));
		}
		
		if(course_subject != null) {
			for(int i=0; i<course_subject.length; i++) {
				System.out.println("course_subject["+ i+"]" + course_subject[i]);
			}
		}else {
			System.out.println("course_subject는 null");
		}

		String keyword = request.getParameter("keyword");
		
		String course_sort = request.getParameter("course_sort");
		
		String[] course_category = request.getParameterValues("course_category");
		if(course_category != null) {
			HashSet<String> hashSet_category = new HashSet<>(Arrays.asList(course_category));
			course_category = hashSet_category.toArray(new String[0]);
//			System.out.println("값 : " + Arrays.toString(course_category));
		}
		
		if(course_category != null) {
			for(int i=0; i<course_category.length; i++) {
				System.out.println("course_category["+ i+"]" + course_category[i]);
			}
		}else {
			System.out.println("course_category는 null");
		}
		
		String[] course_credit = request.getParameterValues("course_credit");
		if(course_credit != null) {
			HashSet<String> hashSet_credit = new HashSet<>(Arrays.asList(course_credit));
			course_credit = hashSet_credit.toArray(new String[0]);
//			System.out.println("값 : " + Arrays.toString(course_category));
		}
		
		if(course_credit != null) {
			for(int i=0; i<course_credit.length; i++) {
				System.out.println("course_credit["+ i+"]" + course_credit[i]);
			}
		}else {
			System.out.println("course_credit는 null");
		}
		
		
		CourseDAO dao = CourseDAO.getInstance();
		
		List<CourseVO> list = null;
		
		String[] subject = null;
		if(course_subject != null) {  
			subject = new String[course_subject.length];
			for(int i=0; i<course_subject.length; i++) {
				subject[i] = course_subject[i];
			}
		} else {
			subject = new String[1];
			subject[0] = "default_value";
		}
		if(course_sort == null) {
			course_sort = "default_value";
		}
		String[] category = null;
		if(course_category != null) {  
			category = new String[course_category.length];
			for(int i=0; i<course_category.length; i++) {
				category[i] = course_category[i];
			}
		}else {
			category = new String[1];
			category[0] = "default_value";
		}
		
		String[] credit = null;
		if(course_credit != null) { 
			credit = new String[course_credit.length];
			for(int i=0; i<course_credit.length; i++) {
				credit[i] = course_credit[i];
			}
		}else {
			credit = new String[1];
			credit[0] = "default_value";
		}

		System.out.println("----------------------------------------------");
		System.out.println();
		System.out.println();
		
//		list = dao.getListCourse(course_subject, keyword, course_sort, course_category, credit);
		System.out.println("값 : " + Arrays.toString(course_subject));
		System.out.println("값 : " + Arrays.toString(course_category));
		System.out.println("값 : " + Arrays.toString(course_credit));
		list = dao.getList(course_subject, keyword, course_sort, course_category, course_credit);
		

		request.setAttribute("list", list);
		request.setAttribute("course_subject", course_subject);
		request.setAttribute("course_category", course_category);
		request.setAttribute("course_credit", course_credit);
		
		
		// JSP 경로 반환
		return "/WEB-INF/views/course/courseForm.jsp";
	}

}
