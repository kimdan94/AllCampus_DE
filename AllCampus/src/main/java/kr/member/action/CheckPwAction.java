package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDAO;

public class CheckPwAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		
		MemberDAO dao = MemberDAO.getinstance();
		String user_passwd = dao.checkPw(email, id);
		String secret_pw = "";
		if(user_passwd != null) {
			request.setAttribute("check_id", id);
			request.setAttribute("check_email", email);
			
			//길이에 따라 비밀번호 가공
			if(user_passwd.length() >= 13) {
				secret_pw += user_passwd.substring(0,8);
				secret_pw += "*".repeat(user_passwd.length() -8);
			}else if(user_passwd.length() >= 8 && user_passwd.length() < 13){
				secret_pw += user_passwd.substring(0,5);
				secret_pw += "*".repeat(user_passwd.length() -5);
			}
			request.setAttribute("secret_pw", secret_pw);
		}
		request.setAttribute("check_id", id);
		request.setAttribute("check_email", email);
		
		return "/WEB-INF/views/member/checkPw.jsp";
	}

}
