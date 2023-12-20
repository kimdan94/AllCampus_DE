package kr.course.action;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.course.dao.CourseDAO;
import kr.course.vo.CourseVO;
import kr.timetable.dao.TimetableDAO;

public class CourseFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		//학기 선택
		String keyfield = request.getParameter("keyfield");
		String keyfield_hidden = request.getParameter("keyfield_hidden");
		Integer year = 2023;
		Integer semester = 1;
		if(keyfield != null) {
			year = Integer.parseInt(keyfield.substring(0,4));
			semester = Integer.parseInt(keyfield.substring(4));
		} else {
			if(keyfield_hidden != null & keyfield_hidden != "") {
				year =  Integer.parseInt(keyfield_hidden.substring(0,4)); 
				semester = Integer.parseInt(keyfield_hidden.substring(4)); 
			}
		}
			 
		// 강의 필터링
		// 전공/영역
		String[] course_subject = request.getParameterValues("course_subject");
		if(course_subject != null) {
			HashSet<String> hashSet_subject = new HashSet<>(Arrays.asList(course_subject));
			course_subject = hashSet_subject.toArray(new String[0]);
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
		
		// 강의 삭제 - all_timetable에서 강의 삭제하기
		String[] arr = request.getParameterValues("delete_course");
		if(arr != null) {
			TimetableDAO daoTime = TimetableDAO.getInstance();
			HashSet<String> hashSet = new HashSet<>(Arrays.asList(arr));        
			String[] delete_course = hashSet.toArray(new String[0]);
			
			daoTime.deleteCourse(delete_course);
		}
		 
		CourseDAO dao = CourseDAO.getInstance();
		
		List<CourseVO> list = null;
		List<CourseVO> list2 = null;
		List<CourseVO> course_list = null;
		List<String> semester_list = null;
		int[] timeList = {9,10,11,12,13,14,15,16,17};
		
		// CourseDAO // 강의 필터 결과 - 강의 목록
		list = dao.getListCourse(year, semester, course_subject, keyword, course_category, course_credit);
		// CourseDAO
		list2 = dao.getRemoveDuplicateCourseList(year, semester, course_subject, keyword, course_category, course_credit);
		course_list = dao.getCourseList();
		semester_list = dao.selectSemester();
		
		
		request.setAttribute("list", list); // request.setAttribut("객체명", 객체);
		request.setAttribute("list2", list2);
		request.setAttribute("course_list", course_list);
		request.setAttribute("semester_list", semester_list);
		request.setAttribute("timeList", timeList);
		request.setAttribute("course_subject", course_subject);
		request.setAttribute("keyfield", keyfield);
		request.setAttribute("keyword", keyword);
		request.setAttribute("course_category", course_category);
		request.setAttribute("course_credit", course_credit);
		
		// JSP 경로 반환
		return "/WEB-INF/views/course/courseForm.jsp";
	}

}