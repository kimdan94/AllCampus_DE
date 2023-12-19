package kr.notice.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;
import kr.util.PageUtil;

public class ListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		NoticeDAO dao = NoticeDAO.getinstance();
		int count = dao.getNoticeCount(keyfield, keyword);
		
		//페이지 처리
		PageUtil page = new PageUtil(keyfield, keyword, Integer.parseInt(pageNum),count,20,10,"list.do");
		List<NoticeVO> list = null;
		if(count > 0) {
			list = dao.getListNotice(page.getStartRow(),page.getEndRow(), keyfield, keyword);
		}
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		//JSP 경로 반환
		return "/WEB-INF/views/notice/list.jsp";
	}

}