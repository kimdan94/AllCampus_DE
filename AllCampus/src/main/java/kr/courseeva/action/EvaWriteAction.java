package kr.courseeva.action;


import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.courseeva.dao.CourseEvaDAO;
import kr.courseeva.vo.CourseEvaVO;

public class EvaWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		 
		request.setCharacterEncoding("utf-8");

		NumberFormat format = NumberFormat.getInstance();
		
		format.setGroupingUsed(false);
		
		 
		CourseEvaVO courseeva = new CourseEvaVO();
		courseeva.setCourse_num(Integer.parseInt(request.getParameter("course_num")));
		courseeva.setEva_star(Double.parseDouble(request.getParameter("eva_star")));
		courseeva.setEva_content(request.getParameter("eva_content"));
		courseeva.setEva_semester(request.getParameter("eva_semester"));
		courseeva.setMem_num(user_num);
		
		CourseEvaDAO dao = CourseEvaDAO.getInstance();
		dao.insertCourseEva(courseeva); 
		
		//JSP 경로 반환
		return "/WEB-INF/views/courseeva/evawrite.jsp";
	}
}
