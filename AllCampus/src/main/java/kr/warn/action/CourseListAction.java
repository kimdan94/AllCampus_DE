package kr.warn.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.courseeva.vo.CourseEvaVO;
import kr.controller.Action;
import kr.util.PageUtil;
import kr.warn.dao.WarnDAO;

//강의편 신고 목록
public class CourseListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum"); //처음에는 페이지가 null이니까 1로 간주하게 됨
		if(pageNum == null) pageNum = "1";
		
		WarnDAO dao = WarnDAO.getInstance();
		int count = dao.getCourseWarnCount();
		  
		//페이지 처리
		PageUtil page = new PageUtil(Integer.parseInt(pageNum),count,20,10,"list.do");
		List<CourseEvaVO> list = null;
		if(count > 0 ) {
		list = dao.getListCourseWarn(page.getStartRow(), page.getEndRow());
		}
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		//JSP 경로 반환
		return "/WEB-INF/views/warn/courselist.jsp";
	}

}
