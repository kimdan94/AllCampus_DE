package kr.warn.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.warn.dao.WarnDAO;

//중고거래 신고 삭제
public class SecondDeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		  
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth != 9) {//관리자로 로그인하지 않은 경우
			return "/WEB-INF/views/common/notice.jsp";
		}
		  
		//관리자로 로그인한 경우
		WarnDAO dao = WarnDAO.getInstance();
		dao.deleteSecond(Integer.parseInt(request.getParameter("secondhand_num")));
		
		return "/WEB-INF/views/warn/delete.jsp";
	}

}
