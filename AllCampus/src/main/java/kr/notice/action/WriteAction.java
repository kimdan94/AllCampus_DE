package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.board.dao.BoardDAO;
import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;
import kr.util.FileUtil;

public class WriteAction implements Action{
 
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
	}
	//로그인 된 경우
	MultipartRequest multi = FileUtil.createFile(request);
	NoticeVO notice = new NoticeVO();
	notice.setNotice_num(Integer.parseInt(multi.getParameter("notice_num")));
	notice.setNotice_filename(multi.getParameter("notice_filename"));
	notice.setNotice_title(multi.getParameter("notice_title"));
	notice.setNotice_content(multi.getParameter("notice_content"));
	
	NoticeDAO dao = NoticeDAO.getinstance();
	dao.insertNotice(notice);
	//JSP 경로 반환
	return "/WEB-INF/views/notice/write.jsp";
		
  }
}
