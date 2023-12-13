package kr.mymember.action; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.mymember.dao.MyMemberDAO;
import kr.mymember.vo.MyMemberVO;
import kr.util.FileUtil;

public class CertifyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		//로그인 된 경우
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//자바빈(VO)생성
		MyMemberVO mymember = new MyMemberVO();
		MultipartRequest multi = FileUtil.createFile(request);
		
		mymember.setMem_certifyfilename(multi.getFilesystemName("mem_certifyfilename"));
		mymember.setMem_num(user_num);
		
		MyMemberDAO dao = MyMemberDAO.getInstance();
		dao.insertFile(mymember);
		
		//JSP 경로 반환
		return "/WEB-INF/views/mymember/certifyAlert.jsp";
	}
}
