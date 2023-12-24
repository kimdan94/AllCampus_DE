package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class WriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(); 
		Integer user_num =(Integer)session.getAttribute("user_num");
		
		if(user_num==null) {//로그인이 되지 않은 경우 
			return "redirect:/member/loginForm.do"; 
		}
		
		//인증 회원과 관리자만 접근 가능
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth == 2) {
			request.setAttribute("notice_msg", "학교 인증을 마친 학생들만 이용할 수 있어요!");
			request.setAttribute("notice_url", request.getContextPath()+"/main/home.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		
		//로그인 된 경우
		MultipartRequest multi = FileUtil.createFile(request);
		BoardVO board = new BoardVO();
		board.setBoard_title(multi.getParameter("board_title"));
		board.setBoard_content(multi.getParameter("board_content"));
		board.setBoard_ip(request.getRemoteAddr());
		board.setBoard_filename(multi.getFilesystemName("board_filename"));
		board.setBoard_anonymous(Integer.parseInt(multi.getParameter("board_anonymous")));
		
		board.setMem_num(user_num);
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.insertBoard(board);
		
		//JSP 경로 반환
		return "/WEB-INF/views/board/write.jsp";
	}
}


