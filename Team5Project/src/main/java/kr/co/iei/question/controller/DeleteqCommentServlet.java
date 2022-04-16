package kr.co.iei.question.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.iei.question.service.QuestionService;

/**
 * Servlet implementation class DeleteqCommentServlet
 */
@WebServlet(name = "DeleteqComment", urlPatterns = { "/deleteqComment.do" })
public class DeleteqCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteqCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 값추출
		int qcNo = Integer.parseInt(request.getParameter("qcNo"));
		int questionNo = Integer.parseInt(request.getParameter("questionNo"));
		//3. 비즈니스로직
		QuestionService service = new QuestionService();
		int result = service.deleteComment(qcNo);
		//4. 결과처리
		RequestDispatcher view
		= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "댓글 삭제가 완료되었습니다.");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "댓글 삭제가 실패했습니다.");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/questionView.do?questionNo="+questionNo);
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
