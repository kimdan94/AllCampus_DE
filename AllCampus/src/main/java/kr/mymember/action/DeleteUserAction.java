package kr.mymember.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.mymember.dao.MyMemberDAO;
import kr.mymember.vo.MyMemberVO;
import kr.util.FileUtil;

public class DeleteUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//전송된 데이터 반환
		String passwd = request.getParameter("passwd");
		//현재 로그인 한 아이디
		String user_id = (String)session.getAttribute("user_id");
		
		MyMemberDAO dao = MyMemberDAO.getInstance();
		MyMemberVO db_member = dao.checkId(user_id);
		boolean check = false;
		
		if(db_member!=null) {
			//비밀번호 일치여부 체크
			check = db_member.isCheckedPassword(passwd);
		}
		if(check) {//인증 성공
			//회원정보 삭제 및 회원탈퇴
			dao.deleteMember(user_num);
			//프로필 사진 삭제
			FileUtil.removeFile(request, db_member.getMem_photo());
			//로그아웃
			session.invalidate();
		}
		
		request.setAttribute("check", check);
		
		return "/WEB-INF/views/mymember/deleteUser.jsp";
	}
}
