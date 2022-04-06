package kr.co.iei.msg.controller;

import java.io.IOException;
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
		String deleteId = request.getParameter("deleteId"); // 누가 삭제하려고 하는지 정보 가져오기
		
		// 3. 비즈니스로직
		MessageService service = new MessageService();
		int result = service.deleteMsg(msgNo, deleteId);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
