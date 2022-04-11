package kr.co.iei.member.controller;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.iei.member.service.MemberService;
import kr.co.iei.member.vo.Member;

/**
 * Servlet implementation class UpdateMemberServlet
 */
@WebServlet(name = "UpdateMember", urlPatterns = { "/updateMember.do" })
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 값추출
		Member member = new Member();
		member.setMemberId(request.getParameter("memberId"));
		member.setMemberPw(request.getParameter("memberPw"));
		member.setMemberNickname(request.getParameter("memberNickname"));
		member.setPhone(request.getParameter("phone"));
		member.setAddress(request.getParameter("address"));
		member.setEmail(request.getParameter("email"));
		System.out.println(member);
		//3. 비즈니스로직
		MemberService service = new MemberService();
		int result = service.updateMember(member);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {//정보변경 성공
			HttpSession session = request.getSession();		//회원정보 성공 시 세션 재생성
			Member m = (Member)session.getAttribute("m");	//새로만든 세션의 객체정보 저장
			m.setMemberPw(member.getMemberPw());
			m.setMemberNickname(member.getMemberNickname());
			m.setPhone(member.getPhone());
			m.setAddress(member.getAddress());
			m.setEmail(member.getEmail());
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "정보 변경이 완료되었습니다.");
			request.setAttribute("icon", "success");
		}else {//정보변경 실패
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "정보 변경이 실패하였습니다.");
			request.setAttribute("icon", "error");
		}
		//jsp가 아닌 컨트롤러 주소인 이유 : WEB-INF 이하의 폴더는 직접 접근이 안되고 서블릿을 통해야 한다
		request.setAttribute("loc", "/mypage1.do");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
