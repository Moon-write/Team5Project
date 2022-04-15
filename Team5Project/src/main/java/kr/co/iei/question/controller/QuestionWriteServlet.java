package kr.co.iei.question.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.iei.question.service.QuestionService;
import kr.co.iei.question.vo.Question;

/**
 * Servlet implementation class QuestionWriteServlet
 */
@WebServlet(name = "QuestionWrite", urlPatterns = { "/questionWrite.do" })
public class QuestionWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionWriteServlet() {
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
			//2-3. 값을 추출
			String questionTitle = request.getParameter("questionTitle");
			String questionWriter = request.getParameter("questionWriter");
			String questionContent = request.getParameter("questionContent");
			
			Question q = new Question();
			q.setQuestionTitle(questionTitle);
			q.setQuestionWriter(questionWriter);
			q.setQuestionContent(questionContent);
			//3. 비즈니스로직
			QuestionService service = new QuestionService();
			int result = service.insertQuestion(q);
			//4. 화면처리
			RequestDispatcher view
			= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			if(result>0) {
				request.setAttribute("title", "성공");
				request.setAttribute("msg", "질문이 등록되었습니다.");
				request.setAttribute("icon", "success");
			}else {
				request.setAttribute("title", "실패");
				request.setAttribute("msg", "질문 등록 중 문제가 발생했습니다.");
				request.setAttribute("icon", "error");
			}
			request.setAttribute("loc", "/questionList.do?reqPage=1");
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
