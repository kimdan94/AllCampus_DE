package kr.board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardScrapVO;
import kr.controller.Action;

public class WriteScrapAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {//로그인이 되지 않은 경우
			mapAjax.put("result","logout");
		}else {//로그인 된 경우
		
			//전송된 데이터 인코딩 처리
			request.setCharacterEncoding("utf-8");
			//전송된 데이터 반환
			int board_num = Integer.parseInt(request.getParameter("board_num"));
			BoardScrapVO ScrapVO = new BoardScrapVO();
			ScrapVO.setBoard_num(board_num);
			ScrapVO.setMem_num(user_num);
			  							
			BoardDAO dao = BoardDAO.getInstance();
			//좋아요 등록 여부 체크
			BoardScrapVO db_scrap = dao.selectScrap(ScrapVO);
			
			//토글 형태를 만들어줌 !!!  이걸로 제어를 한다. 
			if(db_scrap!=null) {// 좋아요 등록 O (한 건의 레코드가 있음)
				//좋아요 삭제
				dao.deleteScrap(db_scrap);
				mapAjax.put("status", "noScrap"); //뚫려 있는 하트 이미지
			}else {//좋아요 등록 X
				//좋아요 등록
				dao.insertScrap(ScrapVO);
				mapAjax.put("status", "yesScrap"); //하트가 꽉 차 있는 이미지 
			}
			mapAjax.put("result", "success");
			mapAjax.put("count", dao.selectScrapCount(board_num)); //변경된 count를 다시
		}
		//JSON 문자열 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
