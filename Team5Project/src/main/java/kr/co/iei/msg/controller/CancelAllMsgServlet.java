package kr.co.iei.msg.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.iei.msg.service.MessageService;

/**
 * Servlet implementation class CancelAllMsgServlet
 */
@WebServlet(name = "CancelAllMsg", urlPatterns = { "/cancelAllMsg.do" })
public class CancelAllMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelAllMsgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String list = request.getParameter("list");
		String memberId = request.getParameter("memberId");
		
		MessageService service = new MessageService();
		int[] result = service.cancelAllMsg(list);
		
		String returnMent ="";
		if(result[0]==0) {
			returnMent = "전송취소 가능한 쪽지가 없습니다!";
		}else {
			returnMent = "수신확인 된 "+result[1]+"건을 제외한 "+result[0]+"건이 전송 취소되었습니다.";
		}
		
		String loc = "/gotoReceiveMsg.do?msgBoardTitle=sendMsg&pageNo=1&memberId="+memberId;

		// 결과처리		
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
