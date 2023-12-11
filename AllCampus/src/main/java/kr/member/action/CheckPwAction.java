package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDAO;

public class CheckPwAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		
		MemberDAO dao = MemberDAO.getinstance();
		String user_passwd = dao.checkPw(email, id);
		if(user_passwd != null) {
			request.setAttribute("user_id", id);
			request.setAttribute("user_email", email);
			request.setAttribute("user_passwd", user_passwd);
		}
		
		request.setAttribute("user_id", id);
		request.setAttribute("user_email", email);
		
		return "/WEB-INF/views/member/checkPw.jsp";
	}

}
