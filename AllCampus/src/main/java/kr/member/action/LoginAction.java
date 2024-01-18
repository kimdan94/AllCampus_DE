package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class LoginAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		//전송된 데이터 반환
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		MemberDAO dao = MemberDAO.getinstance();
		MemberVO vo = dao.checkMember(id);
		boolean check = false;
		
		if(vo!=null) {
			//비밀번호 일치 여부 체크
			check = vo.isCheckedPassword(passwd);
			//정지회원이어도 상태 표시를 위해
			request.setAttribute("mem_auth", vo.getMem_auth());
		}
		if(check) {//인증 성공
			HttpSession session = request.getSession();
			session.setAttribute("user_num", vo.getMem_num());
			session.setAttribute("user_id", vo.getMem_id());
			session.setAttribute("user_auth", vo.getMem_auth());
			session.setAttribute("user_photo", vo.getMem_photo());
			session.setAttribute("user_nick", vo.getMem_nick());
			session.setAttribute("user_email", vo.getMem_email());
			session.setAttribute("user_univ", vo.getUniv_num());
			
			return "redirect:/main/home.do";
		}
		
		//인증 실패
		return "/WEB-INF/views/member/login.jsp";
	}
}