package kr.co.iei.free.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.iei.free.service.FreeService;
import kr.co.iei.free.vo.Free;
import kr.co.iei.free.vo.FreeView;

/**
 * Servlet implementation class FreeBoardUpdateServlet
 */
@WebServlet(name = "FreeBoardUpdate", urlPatterns = { "/freeBoardUpdate.do" })
public class FreeBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		int No = Integer.parseInt(request.getParameter("No"));
		String Title = request.getParameter("Title");
		String Content = request.getParameter("Content");
		
		Free f = new Free();
		f.setFree_No(No);
		f.setFree_Content(Content);
		f.setFree_Title(Title);
		FreeService service = new FreeService();
		int result = service.freeBoardUpdate(f);
		FreeView FV = service.FreeView(No);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/free/freeView.jsp");
		request.setAttribute("FV", FV);
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
