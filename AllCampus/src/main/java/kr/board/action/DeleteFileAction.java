package kr.board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class DeleteFileAction implements Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//파일 삭제 ajax통신용이다
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인 된 경우
			//전송된 데이터 인코딩 처리
			request.setCharacterEncoding("utf-8");
			
			int board_num = Integer.parseInt(request.getParameter("board_num"));
			
			BoardDAO dao = BoardDAO.getInstance();
			BoardVO db_board = dao.getBoard(board_num);
			if(user_num!=db_board.getMem_num()) {
				mapAjax.put("result", "wrongAccess");
			}else {
				//로그인한 회원번호와 작성자 회원번호가 일치해서 파일 삭제 가능
				dao.deleteFile(board_num);   //파일 지워진거
				
				//파일 삭제    :  실제 파일도 삭제해야 한다.
				//이 작업을 안해도 DB에는 파일이 삭제되지만 쓰레기값을 없애기 위해서 한다. 
				FileUtil.removeFile(request, db_board.getBoard_filename());  
				
				mapAjax.put("result", "success");
			}
		}
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax); //ajaxData에 중괄호 형태의 문자가 담겨있음.result:access 이런식으로 
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
