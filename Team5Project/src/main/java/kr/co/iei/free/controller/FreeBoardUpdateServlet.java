package kr.co.iei.free.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.iei.free.service.FreeService;
import kr.co.iei.free.vo.Free;
import kr.co.iei.free.vo.FreeView;
import kr.co.iei.member.vo.Member;

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
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");		
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "글 수정 성공");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "글 수정 실패");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/freeView.do?FreeNo="+No);
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
