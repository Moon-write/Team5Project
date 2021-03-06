package kr.co.iei.free.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.iei.free.service.FreeService;
import kr.co.iei.free.vo.Free;

/**
 * Servlet implementation class FreeInsertServlet
 */
@WebServlet(name = "FreeInsert", urlPatterns = { "/freeInsert.do" })
public class FreeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		String Title = request.getParameter("Title");
		String Id = request.getParameter("Id");
		String Content = request.getParameter("Content");
		Free f = new Free();
		f.setFree_Title(Title);
		f.setFree_Id(Id);
		f.setFree_Content(Content);
		//3.비지니스로직
		FreeService service = new FreeService();
		int result = service.insertFree(f);
		//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "글 등록 성공");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "글 등록 실패");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/free.do?reqPage=1&numPage=20&Sort=3&keyword=");
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
