package kr.main.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.main.dao.HomeDAO;
import kr.member.dao.MemberDAO;
import kr.notice.vo.NoticeVO;
import kr.question.vo.QuestionVO;
import kr.secondhand.vo.SecondhandVO;

public class HomeAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		//로그인이 된 경우 home.jsp에 출력할 학교명 구하기
		Integer user_univ = (Integer)session.getAttribute("user_univ");
		int univ_num = (int)user_univ;
		MemberDAO dao = MemberDAO.getinstance();
		String univ_name = dao.checkUniv(univ_num);
		
		request.setAttribute("univ_name", univ_name);
		

		HomeDAO homeDao = HomeDAO.getInstance();
		//공지사항 목록
		List<NoticeVO> noticeList = homeDao.getListNotice(1, 5);
		request.setAttribute("noticeList", noticeList);
		
		//FAQ 목록
		List<QuestionVO> questionList = homeDao.getListQuestion(1, 5);
		request.setAttribute("questionList", questionList);
		
		//HOT 게시판 목록                             
		List<BoardVO> hotList = homeDao.getListHot(1, 5, 100);
		request.setAttribute("hotList", hotList);
		
		//자유 게시판 목록
		List<BoardVO> boardList = homeDao.getListBoard(1, 5);
		request.setAttribute("boardList", boardList);
		
		//책방 목록
		List<SecondhandVO> scList = homeDao.getListSecondhand(1, 6);
		request.setAttribute("scList", scList);
		
		//JSP 경로 반환
		return "/WEB-INF/views/main/home.jsp";
	}

}
 