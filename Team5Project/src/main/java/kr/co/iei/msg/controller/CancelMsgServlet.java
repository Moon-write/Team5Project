package kr.co.iei.msg.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.iei.msg.service.MessageService;

/**
 * Servlet implementation class CancelMsgServlet
 */
@WebServlet(name = "CancelMsg", urlPatterns = { "/cancelMsg.do" })
public class CancelMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelMsgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전송취소
		request.setCharacterEncoding("utf-8");
		
		// 정보 (쪽지번호)
		int msgNo = Integer.parseInt(request.getParameter("msgNo"));
		
		// 비즈니스로직
		MessageService service = new MessageService();
		int result = service.cancelMsg(msgNo);
		
		// 화면구현
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
