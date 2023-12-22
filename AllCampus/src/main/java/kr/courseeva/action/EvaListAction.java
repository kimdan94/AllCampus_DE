package kr.courseeva.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.courseeva.dao.CourseEvaDAO;
import kr.courseeva.vo.CourseEvaVO;
import kr.util.PageUtil;

public class EvaListAction implements Action{

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
		
		String pageNum = request.getParameter("pageNum"); //처음에는 페이지가 null이니까 1로 간주하게 됨
		if(pageNum == null) pageNum = "1"; 
		
		String keyfield = request.getParameter("keyfield"); //안넘어오면 null
		String keyword = request.getParameter("keyword");
		
		CourseEvaDAO dao = CourseEvaDAO.getInstance();
		int count = dao.getCourseEvaListCount(keyfield, keyword);
		
		//페이지 처리
		PageUtil page = new PageUtil(keyfield, keyword, Integer.parseInt(pageNum),count,20,10,"courseeva_list.do");
		List<CourseEvaVO> list = null;
		if(count > 0 ) {
			list = dao.getCourseEvaList(page.getStartRow(), page.getEndRow(), keyfield, keyword);
		}
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		 
		//JSP 경로 반환
		return "/WEB-INF/views/courseeva/courseeva_list.jsp";  
	}
}
