package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;
import kr.util.StringUtil;

public class DetailAction implements Action {
    
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 글번호 반환
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));

		NoticeDAO dao = NoticeDAO.getinstance();
		NoticeVO notice = dao.getNotice(notice_num);
		request.setAttribute("notice", notice);

		return "/WEB-INF/views/notice/detail.jsp";
	}
}