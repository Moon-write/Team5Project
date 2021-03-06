package kr.co.iei.msg.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GotoWriteMsgServlet
 */
@WebServlet(name = "GotoWriteMsg", urlPatterns = { "/gotoWriteMsg.do" })
public class GotoWriteMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GotoWriteMsgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");		
		String memberId = request.getParameter("memberId");
		String msgReceiver = request.getParameter("msgReceiver");
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/msg/writeMsg.jsp");
		
		request.setAttribute("memberId", memberId);
		request.setAttribute("msgReceiver", msgReceiver);
		
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
