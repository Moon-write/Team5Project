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
 * Servlet implementation class InsertqCommentServlet
 */
@WebServlet(name = "InsertqComment", urlPatterns = { "/insertqComment.do" })
public class InsertqCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertqCommentServlet() {
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
				QuestionComment qc = new QuestionComment();
				qc.setQcWriter(request.getParameter("qcWriter"));
				qc.setQcRef(Integer.parseInt(request.getParameter("qcRef")));
				qc.setQcCommentRef(Integer.parseInt(request.getParameter("qcCommentRef")));
				qc.setQcComment(request.getParameter("qcComment"));
				//3. 비즈니스로직
				QuestionService service = new QuestionService();
				int result = service.insertQuestionComment(qc);
				//4. 결과처리
				//response.sendRedirect("/noticeView.do?noticeNo="+nc.getNoticeRef());
				RequestDispatcher view
				= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				if(result > 0) {
					request.setAttribute("title", "성공");
					request.setAttribute("msg", "댓글 등록 완료");
					request.setAttribute("icon","success");
				}else {
					request.setAttribute("title", "실패");
					request.setAttribute("msg", "댓글 등록 실패");
					request.setAttribute("icon", "error");
				}
				request.setAttribute("loc", "/questionView.do?questionNo="+qc.getQcRef());
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
