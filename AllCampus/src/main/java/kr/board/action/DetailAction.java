package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class DetailAction implements Action{

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
		
		//글번호 반환
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		BoardDAO dao = BoardDAO.getInstance();
		//조회수 증가
		dao.updateReadcount(board_num);
		
		BoardVO board = dao.getBoard(board_num);
		
		//HTML를 허용하지 않음
		board.setBoard_title(StringUtil.useNoHtml(board.getBoard_title()));
		//HTML를 허용하지 않으면서 줄바꿈 처리
		board.setBoard_content(StringUtil.useBrNoHtml(board.getBoard_content())); 
		
		request.setAttribute("board", board);
		
		return "/WEB-INF/views/board/detail.jsp";
	}
}
