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
 * Servlet implementation class DeleteMsgServlet
 */
@WebServlet(name = "DeleteMsg", urlPatterns = { "/deleteMsg.do" })
public class DeleteMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMsgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("utf-8");
		
		// 2 데이터불러오기
		int msgNo = Integer.parseInt(request.getParameter("msgNo"));
		String memberId = request.getParameter("memberId"); // 누가 삭제하려고 하는지 정보 가져오기
		String msgBoardTitle = request.getParameter("msgBoardTitle");
		
		// 3. 비즈니스로직
		MessageService service = new MessageService();
		int result = service.deleteMsg(msgNo, memberId);
		
		
		// 화면구현
		String returnMent = "";
		if(result>0) {
			returnMent ="쪽지가 삭제되었습니다!";
		}else {
			returnMent ="쪽지 삭제에 실패했습니다. 관리자에게 문의하세요!";
		}		
		
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
