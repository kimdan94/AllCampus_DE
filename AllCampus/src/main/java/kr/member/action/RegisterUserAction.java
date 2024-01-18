package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class RegisterUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		//자바빈(VO) 생성
		MemberVO vo = new MemberVO();
		vo.setUniv_num(Integer.parseInt(request.getParameter("univ")));
		vo.setMem_major(request.getParameter("major"));
		vo.setMem_univNum(Integer.parseInt(request.getParameter("univNum")));
		vo.setMem_name(request.getParameter("name"));
		vo.setMem_id(request.getParameter("id"));
		vo.setMem_passwd(request.getParameter("passwd"));
		vo.setMem_email(request.getParameter("email"));
		vo.setMem_nick(request.getParameter("nick"));
		
		MemberDAO dao = MemberDAO.getinstance();
		dao.insertMember(vo);
		
		return "/WEB-INF/views/member/registerUser.jsp";
	}

}
 