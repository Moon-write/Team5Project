package kr.co.iei.question.controller;

import java.io.File;
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
 * Servlet implementation class QuestionUpdateServlet
 */
@WebServlet(name = "QuestionUpdate", urlPatterns = { "/questionUpdate.do" })
public class QuestionUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionUpdateServlet() {
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
		//파일업로드 준비
		//2-1. 파일업로드 경로설정
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"upload/question";
		//2-2. 업로드 파일 최대크기 지정
		int maxSize = 10*1024*1024;
		//2-3. request -> MultipartRequest로 변환
		MultipartRequest mRequest = new MultipartRequest(request,saveDirectory,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		
		int questionNo = Integer.parseInt(mRequest.getParameter("questionNo"));
		String questionTitle = mRequest.getParameter("questionTitle");
		String questionContent = mRequest.getParameter("questionContent");
		//filename, filepath는 새첨부파일 있으면 파일이 없으면 null
		
		Question q = new Question();
		q.setQuestionNo(questionNo);
		q.setQuestionTitle(questionTitle);
		q.setQuestionContent(questionContent);
		//q.setFilename(filename);
		//q.setFilepath(filepath);
		//3.비즈니스 로직
		QuestionService service = new QuestionService();
		int result = service.questionUpdate(q);
		//4. 결과처리
		RequestDispatcher view
		= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "질문 글 완료");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "질문 글 수정 실패");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/questionView.do?questionNo="+q.getQuestionNo());
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
