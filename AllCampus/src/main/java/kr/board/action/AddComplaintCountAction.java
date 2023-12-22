package kr.board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.board.vo.BoardWarnVO;
import kr.controller.Action;

public class AddComplaintCountAction implements Action{ 

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = new HashMap<String,String>();
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		BoardDAO dao = BoardDAO.getInstance();
		
		if(user_num == null) {//로그인이 되지 않은 경우
			mapAjax.put("status", "noLogin");
		}else {//로그인 된 경우
			if(user_auth == 2) {//학교 미인증 회원이라면
				mapAjax.put("status", "noCertify");
			}else {
				//신고 정보를 자바빈에 담음
				BoardWarnVO warnVO = new BoardWarnVO();
				warnVO.setBoard_num(board_num);
				warnVO.setMem_num(user_num);
				
				//신고 테이블에서 board_num,user_num이 같은 행 개수 반환
				int count = dao.selectWarnCount(board_num,user_num);
				if(count > 0) {//같은 사람이 해당 게시글을 한번 더 신고했을 경우
					mapAjax.put("status", "duplicated");
					
					//결과 JSON 문자열 생성
					ObjectMapper mapper = new ObjectMapper();
					String ajaxData = mapper.writeValueAsString(mapAjax);
					
					request.setAttribute("ajaxData", ajaxData);
					return "/WEB-INF/views/common/ajax_view.jsp";
				}
				//신고 개수 update (board_complaint 1증가)
				dao.addComplaintCount(board_num);
				//신고 정보 all_board_warn에 등록
				dao.insertWarn(warnVO);
			
				BoardVO vo = dao.getBoard(board_num);
				int board_complaint = vo.getBoard_complaint();
				//board_complaint 신고 3개이상이면 board_show 미표시(1)로 변경
				if(board_complaint >= 3) {
					dao.complaintUpdateShow(board_num);
				}
				mapAjax.put("status", "success");
			}
			
			//결과 JSON 문자열 생성
			ObjectMapper mapper = new ObjectMapper();
			String ajaxData = mapper.writeValueAsString(mapAjax);
			
			request.setAttribute("ajaxData", ajaxData);
		}
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}

