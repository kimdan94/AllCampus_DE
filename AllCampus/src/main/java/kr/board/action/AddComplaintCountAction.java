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

public class AddComplaintCountAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = new HashMap<String,String>();
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		if(user_num == null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {
			
			BoardDAO dao = BoardDAO.getInstance();
			//신고 개수 update
			dao.addComplaintCount(board_num);
			
			BoardVO vo = dao.getBoard(board_num);
			int board_complaint = vo.getBoard_complaint();
			System.out.println("board_complaint : " + board_complaint);
			//board_complaint 신고 3개이상이면 board_show 미표시(1)로 변경
			if(board_complaint >= 3) {
				dao.complaintUpdateShow(board_num);
			}
			
			mapAjax.put("result", "success");
		}
		//결과 JSON 문자열 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
