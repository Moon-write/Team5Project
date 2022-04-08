package kr.co.iei.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.iei.member.service.MemberService;
import kr.co.iei.member.vo.Member;

/**
 * Servlet implementation class AjaxCheckIdServlet
 */
@WebServlet(name = "AjaxCheckId", urlPatterns = { "/ajaxCheckId.do" })
public class AjaxCheckIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxCheckIdServlet() {
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
		String memberId = request.getParameter("memberId");
		//3. 비즈니스로직
		MemberService service = new MemberService();
		Member m = service.selectOneMember(memberId);
		//4. 결과처리
		PrintWriter out = response.getWriter();
		if(m != null) { //중복인 경우
			out.print(1);
		}else {			//중복이 아닌 경우
			out.print(0);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
