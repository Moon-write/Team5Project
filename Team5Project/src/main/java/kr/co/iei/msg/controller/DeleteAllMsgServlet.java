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
 * Servlet implementation class DeleteAllMsgServlet
 */
@WebServlet(name = "DeleteAllMsg", urlPatterns = { "/deleteAllMsg.do" })
public class DeleteAllMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAllMsgServlet() {
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
		String msgBoardTitle = request.getParameter("msgBoardTitle");
		
		MessageService service = new MessageService();
		int[] result = service.deleteAllMsg(list, memberId);
		
		String returnMent = "선택된 "+result[0]+"건의 메세지 중 "+result[1]+"건이 삭제되었습니다.";
		
		
		
		String loc = "/gotoReceiveMsg.do?msgBoardTitle="+msgBoardTitle+"&pageNo=1&memberId="+memberId;

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
