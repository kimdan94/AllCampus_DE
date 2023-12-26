package kr.courseeva.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.courseeva.dao.CourseEvaDAO;
import kr.courseeva.vo.CourseEvaVO;
import kr.util.PageUtil;

public class EvaDetailAction implements Action{

	@Override 
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do"; 
		}
		
		//인증 회원과 관리자만 접근 가능
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth == 2) {
			request.setAttribute("notice_msg", "학교 인증을 마친 학생들만 이용할 수 있어요!");
			request.setAttribute("notice_url", request.getContextPath()+"/main/home.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		//강의평 글번호 반환
		String course_name = request.getParameter("course_name");
		String course_prof = request.getParameter("course_prof");
		
		
		CourseEvaDAO dao = CourseEvaDAO.getInstance();
		int count = dao.getEvaDetailCount(course_name, course_prof);
		List<CourseEvaVO> list = null;
		//페이지 처리
		PageUtil page = new PageUtil(Integer.parseInt(pageNum),count,20,10,"courseeva_detail.do");
		
		if(count > 0 ) {
			list = dao.getCourseEva(page.getStartRow(), page.getEndRow(),course_name, course_prof);
			
			double totalEvaStar = 0;
            for (CourseEvaVO evaDetail : list) {
            	totalEvaStar += evaDetail.getEva_star();
            }
            String totalgrade = calculateGrade(totalEvaStar / count);
            request.setAttribute("totalgrade", totalgrade);
            //request.setAttribute("totalEvaStar", totalEvaStar/count);
			
		}
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		request.setAttribute("course_name", course_name);
		request.setAttribute("course_prof", course_prof);
		
		return "/WEB-INF/views/courseeva/eva_detail.jsp";
	}
	
	private String calculateGrade(double totalEvaStarAverage) {
        if (totalEvaStarAverage >= 4.5) {
            return "A+";
        } else if (totalEvaStarAverage >= 4) {
            return "A";
        } else if (totalEvaStarAverage >= 3.5) {
            return "B+";
        } else if (totalEvaStarAverage >= 3) {
            return "B";
        } else if (totalEvaStarAverage >= 2.5) {
            return "C+";
        } else if (totalEvaStarAverage >= 2) {
            return "C";
        } else if (totalEvaStarAverage >= 1.5) {
            return "D+";
        } else if (totalEvaStarAverage >= 1) {
            return "D";
        } else {
            return "F";
        }
    }
}
