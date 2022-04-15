package kr.co.iei.free.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.iei.free.service.FreeService;
import kr.co.iei.free.vo.FreeComment;
import kr.co.iei.free.vo.FreeView;
import kr.co.iei.member.vo.Member;

/**
 * Servlet implementation class FreeViewServlet
 */
@WebServlet(name = "freeView", urlPatterns = { "/freeView.do" })
public class FreeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		int FreeNo = Integer.parseInt(request.getParameter("FreeNo"));
		FreeService service = new FreeService();
		FreeView FV = service.FreeView(FreeNo);
		
		ArrayList<FreeComment> cmlist = service.FreeCommentSearch(FreeNo);
		ArrayList<FreeComment> recmlist = service.FreeRecommentSearch(FreeNo);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/free/freeView.jsp");
		request.setAttribute("FV", FV);
		request.setAttribute("cmlist", cmlist);
		request.setAttribute("recmlist", recmlist);
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
