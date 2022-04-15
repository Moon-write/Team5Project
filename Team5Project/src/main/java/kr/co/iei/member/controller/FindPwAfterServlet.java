package kr.co.iei.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.iei.member.service.MemberService;
import kr.co.iei.member.vo.Member;

/**
 * Servlet implementation class FindPwAfter2Servlet
 */
@WebServlet(name = "FindPwAfter2", urlPatterns = { "/findPwAfter.do" })
public class FindPwAfterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPwAfterServlet() {
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
		String memberName = request.getParameter("memberName");
		String memberId = request.getParameter("memberId");
		String memberEmail = request.getParameter("email");
		Member member = new Member();
		member.setMemberName(memberName);
		member.setMemberId(memberId);
		member.setEmail(memberEmail);
		//3. 비즈니스로직
		MemberService service = new MemberService();
		Member m = service.findPw(member);//이름,아이디,이메일 들어있는 객체 전달
//		System.out.println("서비스에서 가져온 객체"+m);
		//4. 결과처리
		request.setAttribute("memberId", m.getMemberId());
		request.setAttribute("memberPw", m.getMemberPw());
		request.setAttribute("memberName", m.getMemberName());
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/findPwAfter.jsp");
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
