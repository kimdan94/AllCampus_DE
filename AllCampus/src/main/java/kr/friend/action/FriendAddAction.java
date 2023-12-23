package kr.friend.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.friend.dao.FriendDAO;

public class FriendAddAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { // 로그인이 되지 않았을 경우
			return "redirect:/member/loginForm.do";
		}
		
		String add_friend = request.getParameter("add_friend");
		
		FriendDAO friendDao = FriendDAO.getInstance();
		friendDao.searchFriend(user_num, add_friend);
		
		request.setAttribute("notice_msg", add_friend + "가 추가되었습니다");
		request.setAttribute("notice_url", request.getContextPath()+"/course/friendList.do");
		
		return "/WEB-INF/views/common/alert_singleView.jsp";
	}

}
