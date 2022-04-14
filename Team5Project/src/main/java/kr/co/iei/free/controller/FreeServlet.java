package kr.co.iei.free.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.iei.free.service.FreeService;
import kr.co.iei.free.vo.FreeboardTable;
import kr.co.iei.member.vo.Member;

/**
 * Servlet implementation class FreeServlet
 */
@WebServlet(name = "Free", urlPatterns = { "/free.do" })
public class FreeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.값추출
		int reqPage = Integer.parseInt(request.getParameter("reqPage"));
		int numPage = Integer.parseInt(request.getParameter("numPage"));
		String keyword = request.getParameter("keyword");
		int sort = Integer.parseInt(request.getParameter("Sort"));
		HttpSession logincheck = request.getSession(false);
		//3.비지니스로직
		FreeService service = new FreeService();
		ArrayList<FreeboardTable> freeboard = new ArrayList<FreeboardTable>();	
		
		if(sort==1) {
			freeboard = service.selectFreeList1(reqPage, numPage, keyword);
		}else if(sort==2) {
			freeboard = service.selectFreeList2(reqPage, numPage, keyword);
		}else {
			freeboard = service.selectFreeList3(reqPage, numPage, keyword);			
		}
		HashMap<Integer, Boolean> likecheck = new HashMap<Integer, Boolean>();
		String pageNavi = service.totalPage(reqPage, numPage);
		
		Member m = (Member)logincheck.getAttribute("m");
		if(m!=null) {
			int no = m.getMemberNo();
			likecheck = service.addLikecheck(no);			
		}
		//System.out.println(likecheck);
		//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/free/freeBoard.jsp");
		request.setAttribute("list", freeboard);
		request.setAttribute("pageNavi", pageNavi);
		request.setAttribute("likecheck", likecheck);
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
