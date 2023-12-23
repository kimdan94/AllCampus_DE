package kr.friend.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.friend.dao.FriendDAO;
import kr.member.vo.MemberVO;

public class FriendListAction implements Action { // 친구들 리스트 있는 곳

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { // 로그인이 되지 않았을 경우
			return "redirect:/member/loginForm.do";
		}
		
		
		int[] timeList = {9,10,11,12,13,14,15,16,17};
		
		String search_friend = request.getParameter("search_friend");
		//System.out.println(search_friend);
		
		FriendDAO friendDao = FriendDAO.getInstance();
		
		List<MemberVO> searchFriendList = friendDao.selectSearchFriend(user_num, search_friend); // 친구 리스트 뽑기
		
		
		
		
		request.setAttribute("searchFriendList", searchFriendList);
		request.setAttribute("timeList", timeList);
		
		return "/WEB-INF/views/friend/friend.jsp";
	}

}
