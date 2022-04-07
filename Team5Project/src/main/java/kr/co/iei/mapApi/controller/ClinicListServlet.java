package kr.co.iei.mapApi.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.iei.mapApi.service.ApiExplorer;

/**
 * Servlet implementation class MapListServlet
 */
@WebServlet(name = "ClinicList", urlPatterns = { "/clinicList.do" })
public class ClinicListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClinicListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String pageNo = request.getParameter("pageNo");
		String numOfRows = request.getParameter("numOfRows");
		
		
		ApiExplorer api = new ApiExplorer();
		String result = api.main(pageNo, numOfRows);
		
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/xml");
		PrintWriter writer = response.getWriter();		
		writer.print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
