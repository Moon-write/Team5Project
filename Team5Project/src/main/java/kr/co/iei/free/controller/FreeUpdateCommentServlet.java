package kr.co.iei.free.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.iei.free.service.FreeService;

/**
 * Servlet implementation class FreeUpdateCommentServlet
 */
@WebServlet(name = "FreeUpdateComment", urlPatterns = { "/updateComment.do" })
public class FreeUpdateCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeUpdateCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		int commentNo = Integer.parseInt(request.getParameter("num"));
		String comment = request.getParameter("comment");
		
		FreeService service = new FreeService();
		int result = service.freeUpdateComment(commentNo, comment);
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if(result>0) {
			out.print("성공");
		}else {
			out.print("실패");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
