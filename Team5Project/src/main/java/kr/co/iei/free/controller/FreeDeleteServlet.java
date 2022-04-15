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
 * Servlet implementation class FreeDeleteServlet
 */
@WebServlet(name = "FreeDelete", urlPatterns = { "/freeDelete.do" })
public class FreeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		int FreeNo = Integer.parseInt(request.getParameter("freeno"));
				
		FreeService service = new FreeService();
		int result = service.FreeDelete(FreeNo);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "글 삭제 성공");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "글 삭제 실패");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/free.do?reqPage=1&numPage=10&Sort=3&keyword=");
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
