package kr.co.iei.msg.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.iei.msg.service.MessageService;
import kr.co.iei.msg.vo.Message;

/**
 * Servlet implementation class NewMsgServlet
 */
@WebServlet(name = "NewMsg", urlPatterns = { "/newMsg.do" })
public class NewMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewMsgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 데이터추출
		String msgSender = request.getParameter("msgSender");
		String msgReceiver = request.getParameter("msgReceiver");
		String msgContent = request.getParameter("msgContent");

		Message msg = new Message(msgSender, msgReceiver, msgContent);
		
		// 비즈니스로직
		MessageService service = new MessageService();
		int result = service.newMsg(msg);
		
		String returnMent = "";
		String loc = "";
		// 결과처리
		if(result>0) {
			returnMent = "전송 성공!";
			loc= "/gotoSendMsg.do?memberId="+msgSender;
		} else {
			returnMent = "전송 실패!관리자에게 문의하세요";
			loc = "/gotoWriteMsg.do?memberId="+msgSender;
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<script>alert('"+returnMent+"'); location.href='"+loc+"';</script>");
		writer.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
