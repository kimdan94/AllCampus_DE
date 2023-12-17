package kr.secondhand.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.secondhand.dao.SecondhandDAO;
import kr.secondhand.vo.SecondhandVO;
import kr.util.FileUtil;

public class UpdateAction implements Action{

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
		
		MultipartRequest multi = FileUtil.createFile(request);
		int secondhand_num = Integer.parseInt(multi.getParameter("secondhand_num"));
		String filename = multi.getFilesystemName("bookImage");
		
		SecondhandDAO dao = SecondhandDAO.getInstance();
		//수정 전 데이터 반환
		SecondhandVO db_sc = dao.getsecondhand(secondhand_num);
		if(user_num!=db_sc.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호 불일치
			FileUtil.removeFile(request, filename);
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//로그인한 회원번호와 작성자 회원번호가 일치
		SecondhandVO sc = new SecondhandVO();
		sc.setSecondhand_num(secondhand_num);
		sc.setSecondhand_name(multi.getParameter("bookName"));
		sc.setSecondhand_writer(multi.getParameter("bookWriter"));
		sc.setSecondhand_writerPlus(multi.getParameter("bookWriterPlus"));
		sc.setSecondhand_content(multi.getParameter("bookContent"));
		sc.setSecondhand_company(multi.getParameter("bookCompany"));
		sc.setSecondhand_price(Integer.parseInt(multi.getParameter("bookPrice")));
		sc.setSecondhand_way(multi.getParameter("bookWay"));
		sc.setSecondhand_status(multi.getParameter("bookStatus"));
		sc.setSecondhand_filename(filename);
		sc.setSecondhand_openchat(multi.getParameter("bookUrl"));
		sc.setSecondhand_ip(request.getRemoteAddr());
		sc.setMem_num(user_num);
		
		//글 수정
		dao.updateSecondhand(sc);
		
		if(filename!=null) {//새 파일로 교체할 때 원래 파일 제거
			FileUtil.removeFile(request, db_sc.getSecondhand_filename());
		}
		return "redirect:/secondhand/secondhand_detail.do?secondhand_num=" + secondhand_num;
	}

}
