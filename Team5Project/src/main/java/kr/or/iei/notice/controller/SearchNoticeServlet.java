package kr.or.iei.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.iei.member.vo.Member;
import kr.or.iei.notice.service.NoticeService;
import kr.or.iei.notice.vo.NoticePageData;

/**
 * Servlet implementation class SerchNoticeServlet
 */
@WebServlet(name = "SearchNotice", urlPatterns = { "/searchNotice.do" })
public class SearchNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchNoticeServlet() {
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
		String select = request.getParameter("select");//-> select 태그에서 선택한 option의 value값을 읽어옴 (notictTitle, noticeWriter)		
		String value = request.getParameter("value");
		System.out.println("select : "+select);
		System.out.println("value : "+value);
		//3.비즈니스로직
		NoticeService service = new NoticeService();
		NoticePageData npd = service.searchNotice(reqPage,select,value);
		//4.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/notice/noticeList.jsp");
		request.setAttribute("list", npd.getList());
		request.setAttribute("pageNav", npd.getPageNav());
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
