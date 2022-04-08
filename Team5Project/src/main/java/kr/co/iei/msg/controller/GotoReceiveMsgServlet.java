package kr.co.iei.msg.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.iei.msg.service.MessageService;
import kr.co.iei.msg.vo.Message;

/**
 * Servlet implementation class ReceiveMsgServlet
 */
@WebServlet(name = "GotoReceiveMsg", urlPatterns = { "/gotoReceiveMsg.do" })
public class GotoReceiveMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GotoReceiveMsgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전체 메세지 읽기
		
		// 1 인코딩
		request.setCharacterEncoding("utf-8");
		
		// 2 데이터불러오기
		String memberId = request.getParameter("memberId");
		String msgBoardTitle = request.getParameter("msgBoardTitle"); // 받은편지함인지 보낸편지함인지 구분할거임
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));				
		
		MessageService service = new MessageService();
		ArrayList<Message> list = service.readAllMsg(memberId, msgBoardTitle, pageNo);
		
		// 3 화면구현
		String loc ="";
		if(msgBoardTitle.equals("sendMsg")) {
			loc = "WEB-INF/views/msg/sendMsg.jsp";
		}else {
			loc = "WEB-INF/views/msg/receiveMsg.jsp";
		}
		RequestDispatcher view = request.getRequestDispatcher(loc);
		request.setAttribute("list", list);
		request.setAttribute("pageNo", pageNo);
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
