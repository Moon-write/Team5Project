package kr.co.iei.free.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.iei.free.service.FreeService;
import kr.co.iei.free.vo.FreeComment;

/**
 * Servlet implementation class FreeInsertCommentServlet
 */
@WebServlet(name = "FreeInsertComment", urlPatterns = { "/freeInsertComment.do" })
public class FreeInsertCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeInsertCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		int freeNo = Integer.parseInt(request.getParameter("freeNo"));
		String memberId = request.getParameter("memberId");
		String content = request.getParameter("content");
		int Recomment = Integer.parseInt(request.getParameter("recomment"));
		
		FreeComment fc = new FreeComment();
		fc.setFreeNo(freeNo);
		fc.setMemberId(memberId);
		fc.setRecomment(Recomment);			
		fc.setContent(content);
		FreeService service = new FreeService();
		
		int result = service.insertComment(fc);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "댓글 작성 성공");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "댓글 작성 성공");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/freeView.do?FreeNo="+freeNo);
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
