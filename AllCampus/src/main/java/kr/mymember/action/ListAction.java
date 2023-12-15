package kr.mymember.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.mymember.dao.MyMemberDAO;
import kr.util.PageUtil;

public class ListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		MyMemberDAO dao = MyMemberDAO.getInstance();
		
		//페이지 기능
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
				
		//내가 쓴 글
		int count = dao.getBoardCount(user_num);
		PageUtil page = new PageUtil(Integer.parseInt(pageNum),count,20,10,"list.do");
		List<BoardVO> list = null;
		if(count>0) {
			list = dao.getListBoard(page.getStartRow(), page.getEndRow(), user_num);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		//JSP 경로 반환
		return "/WEB-INF/views/mymember/list.jsp";
	}
}
