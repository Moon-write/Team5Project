package kr.co.iei.question.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.iei.question.service.QuestionService;
import kr.co.iei.question.vo.QuestionComment;

/**
 * Servlet implementation class QuestionCommentUpdate
 */
@WebServlet(name = "QuestionCommentUpdate", urlPatterns = { "/questionCommentUpdate.do" })
public class QuestionCommentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionCommentUpdateServlet() {
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
		String qcComment = request.getParameter("qcComment");
		QuestionComment qc = new QuestionComment();
		qc.setQcNo(qcNo);
		qc.setQcComment(qcComment);
		//3. 비즈니스로직
		QuestionService service = new QuestionService();
		int result = service.updateQuestionComment(qc);
		//4. 결과처리
		RequestDispatcher view
		= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "댓글 수정 완료!");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "댓글 수정 실패!");
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
