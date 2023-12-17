package kr.secondhand.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.secondhand.dao.SecondhandDAO;
import kr.secondhand.vo.SecondhandVO;
import kr.util.StringUtil;

public class UpdateFormAction implements Action{

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
		
		//로그인한 회원번호와 작성자 회원번호 일치 여부 체크
		int secondhand_num = Integer.parseInt(request.getParameter("secondhand_num"));
		SecondhandDAO dao = SecondhandDAO.getInstance();
		SecondhandVO db_sc = dao.getsecondhand(secondhand_num);
		
		if(user_num != db_sc.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//<input> 태그들 큰 따옴표 처리
		db_sc.setSecondhand_name(StringUtil.parseQuot(db_sc.getSecondhand_name()));
		db_sc.setSecondhand_writer(StringUtil.parseQuot(db_sc.getSecondhand_writer()));
		db_sc.setSecondhand_content(StringUtil.parseQuot(db_sc.getSecondhand_content()));
		db_sc.setSecondhand_company(StringUtil.parseQuot(db_sc.getSecondhand_company()));
		db_sc.setSecondhand_openchat(StringUtil.parseQuot(db_sc.getSecondhand_openchat()));
		
		//'외 지음' 처리
		if(db_sc.getSecondhand_writer().contains("외 지음")) {
			db_sc.setSecondhand_writerPlus("외 지음");
			//외 지음 추출
			String etc = db_sc.getSecondhand_writer().substring(db_sc.getSecondhand_writer().length()-5,db_sc.getSecondhand_writer().length());
			db_sc.setSecondhand_writer(db_sc.getSecondhand_writer().replaceAll(etc, ""));
		}
		request.setAttribute("sc", db_sc);
		
		return "/WEB-INF/views/secondhand/sc_updateForm.jsp";
	}

}
