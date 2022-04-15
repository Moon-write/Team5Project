package kr.co.iei.main.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.iei.main.service.SurveyService;
import kr.co.iei.main.vo.Survey;
import kr.co.iei.member.vo.Member;

/**
 * Servlet implementation class ServeyServlet
 */
@WebServlet(name = "Survey", urlPatterns = { "/survey.do" })
public class SurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SurveyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String[] symptoms = request.getParameterValues("symptom");
		String painDate = request.getParameter("painDate");
		String decideDate = request.getParameter("decideDate");
		int vaccinate = Integer.parseInt(request.getParameter("vaccinate"));
		String story = request.getParameter("story");
		String memberId = request.getParameter("surveyId");
		
		Survey sv = new Survey(memberId, painDate, decideDate, vaccinate, story);
		SurveyService service = new SurveyService();
		int result = service.newSurvey(sv, symptoms);
		
		if(result>0) {
			HttpSession session = request.getSession();
			Member m = (Member)session.getAttribute("m");
			m.setSurveyCheck(1);
		}
		response.sendRedirect("/");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
