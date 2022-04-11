package kr.or.iei.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.notice.service.NoticeService;
import kr.or.iei.notice.vo.Notice;
import kr.or.iei.notice.vo.NoticeViewData;

/**
 * Servlet implementation class ClicklikeServlet
 */
@WebServlet(name = "Clicklike", urlPatterns = { "/clicklike.do" })
public class ClicklikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClicklikeServlet() {
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
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		String memberId = request.getParameter("memberId");
		//3.비즈니스로직
		NoticeService service = new NoticeService();
		Notice n =  service.clicklike(noticeNo,memberId);
		NoticeViewData nvd = service.selectNoticeView(noticeNo,memberId);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/notice/noticeView.jsp");
		request.setAttribute("n",nvd.getN());
		request.setAttribute("commentList", nvd.getCommentList());
		request.setAttribute("reCommentList", nvd.getReCommentList());
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
