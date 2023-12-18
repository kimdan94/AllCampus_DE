package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDAO;

public class CheckIdAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		String email = request.getParameter("email");
		
		MemberDAO dao = MemberDAO.getinstance();
		String user_id = dao.checkId(email);
		if(user_id != null) {
			request.setAttribute("check_email", email);
			request.setAttribute("check_id", user_id);
		}
		
		request.setAttribute("check_email", email);
		
		return "/WEB-INF/views/member/checkId.jsp";
	}

}
