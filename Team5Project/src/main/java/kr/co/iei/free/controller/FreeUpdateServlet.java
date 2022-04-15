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

/**
 * Servlet implementation class FreeUpdateServlet
 */
@WebServlet(name = "FreeUpdate", urlPatterns = { "/freeUpdate.do" })
public class FreeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int FreeNo = Integer.parseInt(request.getParameter("freeno"));
		
		FreeService service = new FreeService();
		Free f = service.selectOneFree(FreeNo);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/free/freeUpdate.jsp");
		request.setAttribute("free", f);
		
		
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
