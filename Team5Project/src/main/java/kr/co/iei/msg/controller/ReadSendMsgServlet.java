package kr.co.iei.msg.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.iei.msg.service.MessageService;
import kr.co.iei.msg.vo.Message;

/**
 * Servlet implementation class ReadMsgServlet
 */
@WebServlet(name = "ReadSendMsg", urlPatterns = { "/readSendMsg.do" })
public class ReadSendMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadSendMsgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 데이터
		int msgNo = Integer.parseInt(request.getParameter("msgNo"));
		
		// 비즈니스로직
		MessageService service = new MessageService();
		Message msg = service.selectMsg(msgNo);
		
		// 결과구현
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/msg/readSendMsg.jsp");
		request.setAttribute("msg", msg);
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
