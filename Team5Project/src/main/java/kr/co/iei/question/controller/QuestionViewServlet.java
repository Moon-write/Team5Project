package kr.co.iei.question.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.iei.question.service.QuestionService;
import kr.co.iei.question.vo.QuestionViewData;


/**
 * Servlet implementation class QuestionViewServlet
 */
@WebServlet(name = "QuestionView", urlPatterns = { "/questionView.do" })
public class QuestionViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionViewServlet() {
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
		int questionNo = Integer.parseInt(request.getParameter("questionNo"));
		//3. 비즈니스로직
		QuestionService service = new QuestionService();
		QuestionViewData qvd = service.selectQuestionView(questionNo);
		//Notice n = service.selectOneNotice(noticeNo);
		//4. 결과처리
		RequestDispatcher view
		=request.getRequestDispatcher("WEB-INF/views/question/questionView.jsp");
		request.setAttribute("q", qvd.getQ());
		request.setAttribute("commentList", qvd.getCommentList());
		request.setAttribute("reCommentList", qvd.getRecommentList());
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
