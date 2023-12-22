package kr.warn.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.courseeva.vo.CourseEvaVO;
import kr.util.PageUtil;
import kr.warn.dao.WarnDAO;

//자유게시판 신고글 목록
public class ListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum"); //처음에는 페이지가 null이니까 1로 간주하게 됨
		if(pageNum == null) pageNum = "1";

		WarnDAO dao = WarnDAO.getInstance();
		int count = dao.getWarnCount();

		//페이지 처리
		PageUtil page = new PageUtil(Integer.parseInt(pageNum),count,20,10,"list.do");
		List<BoardVO> list = null;
		if(count > 0 ) {
			list = dao.getListBoard(page.getStartRow(), page.getEndRow());
		}
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
    
		//---------------------------------------------------

		String pageNum2 = request.getParameter("pageNum"); //처음에는 페이지가 null이니까 1로 간주하게 됨
		if(pageNum2 == null) pageNum2 = "1";

		int count2 = dao.getCourseWarnCount();

		//페이지 처리
		PageUtil page2 = new PageUtil(Integer.parseInt(pageNum2),count2,20,10,"list.do");
		List<CourseEvaVO> list2 = null;
		if(count2 > 0 ) {
			list2 = dao.getListCourseWarn(page2.getStartRow(), page2.getEndRow());
		}
		request.setAttribute("count2", count2);
		request.setAttribute("list2", list2);
		request.setAttribute("page2", page2.getPage());

		//JSP 경로 반환
		return "/WEB-INF/views/warn/list.jsp";
	}

}
