package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class UpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi = FileUtil.createFile(request);
		int board_num = Integer.parseInt(multi.getParameter("board_num"));
		String filename = multi.getFilesystemName("board_filename");//시스템에서 읽어오는 경우 getFilesystemName이라고 해야한다.
		
		BoardDAO dao = BoardDAO.getInstance();
		//수정전 데이터 반환
		BoardVO db_board = dao.getBoard(board_num);
		
		if(user_num!=db_board.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호가 불일치
			FileUtil.removeFile(request, filename);//filename은 null이 와도 상관없다
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		
		//로그인한 회원번호와 작성자 회원번호가 일치
		BoardVO board = new BoardVO();
		board.setBoard_num(board_num);
		board.setBoard_title(multi.getParameter("board_title"));
		board.setBoard_content(multi.getParameter("board_content"));
		board.setBoard_anonymous(Integer.parseInt(multi.getParameter("board_anonymous")));
		board.setBoard_ip(request.getRemoteAddr());
		board.setBoard_filename(filename);
		
		//글 수정
		dao.updateBoard(board);
		
		//파일이 업데이트 되었다면 예전 파일은 지워줘야 하기 때문에 조건 체크
		//쓰레기값 안쌓이게 하려고 하는 
		if(filename!=null) {//새 파일로 교체할 때 원래 파일 제거
			FileUtil.removeFile(request, db_board.getBoard_filename());
		}
		return "redirect:/board/detail.do?board_num=" + board_num;
	}

}
















