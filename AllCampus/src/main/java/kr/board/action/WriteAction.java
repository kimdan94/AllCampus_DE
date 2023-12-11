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
		/*
		 * HttpSession session = request.getSession(); Integer user_num =
		 * (Integer)session.getAttribute("user_num");
		 * 
		 * if(user_num==null) {//로그인이 되지 않은 경우 return "redirect:/member/loginForm.do"; }
		 */
		//로그인 된 경우
		MultipartRequest multi = FileUtil.createFile(request);
		BoardVO board = new BoardVO();
		board.setBoard_title(multi.getParameter("board_title"));
		board.setBoard_content(multi.getParameter("board_content"));
		board.setBoard_ip(request.getRemoteAddr());
		board.setBoard_filename(multi.getFilesystemName("board_filename"));
		board.setBoard_anonymous(Integer.parseInt(multi.getParameter("board_anonymous")));
		
		//다음주에 이 구문으로 바꿔주기 
		//board.setMem_num(user_num);
		board.setMem_num(400);
		
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.insertBoard(board);
		
		//JSP 경로 반환
		return "/WEB-INF/views/board/write.jsp";
	}
}














