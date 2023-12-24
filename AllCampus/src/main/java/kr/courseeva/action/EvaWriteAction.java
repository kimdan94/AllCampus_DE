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
		
		//인증 회원과 관리자만 접근 가능
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth == 2) {
			request.setAttribute("notice_msg", "학교 인증을 마친 학생들만 이용할 수 있어요!");
			request.setAttribute("notice_url", request.getContextPath()+"/main/home.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
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
