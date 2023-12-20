package kr.secondhand.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.secondhand.dao.SecondhandDAO;
import kr.secondhand.vo.SecondhandVO;
import kr.util.FileUtil;

public class DeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		//인증 회원과 관리자만 접근 가능
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth == 2) {
			request.setAttribute("notice_msg", "학교 인증을 마친 학생들만 이용할 수 있어요!");
			request.setAttribute("notice_url", request.getContextPath()+"/main/home.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		
		int secondhand_num = Integer.parseInt(request.getParameter("secondhand_num"));
		SecondhandDAO dao = SecondhandDAO.getInstance();
		SecondhandVO db_sc = dao.getsecondhand(secondhand_num);
		//동시 작업의 영향으로 신고 3건 누적, 미표시로 전환되었는지 체크
		if(db_sc.getSecondhand_show() == 1) {
			request.setAttribute("notice_msg", "해당 게시글은 미공개 처리되었습니다.");
			request.setAttribute("notice_url", request.getContextPath()+"/secondhand/secondhand_list.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
				
		if(user_num != db_sc.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호가 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//로그인한 회원번호와 작섲아 회원번호가 일치
		dao.deleteSecondhand(secondhand_num);
		//파일 삭제
		FileUtil.removeFile(request, db_sc.getSecondhand_filename());
		
		return "redirect:/secondhand/secondhand_list.do";
	}

}
