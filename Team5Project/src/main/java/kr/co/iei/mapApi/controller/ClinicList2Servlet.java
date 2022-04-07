package kr.co.iei.mapApi.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.iei.mapApi.service.ApiExplorer;
import kr.co.iei.mapApi.vo.Clinic;

/**
 * Servlet implementation class ClinicList2Servlet
 */
@WebServlet(name = "ClinicList2", urlPatterns = { "/clinicList2.do" })
public class ClinicList2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClinicList2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String sidoNm = request.getParameter("sidoNm");
		String sgguNm = request.getParameter("sgguNm");
		String detailAddr = request.getParameter("detailAddr");
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		ApiExplorer api = new ApiExplorer();
		ArrayList<Clinic> list = api.getData(sidoNm, sgguNm, detailAddr, pageNo);
		
		// 결과 list를 보내줄거임

		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/map/searchClinic.jsp");
		request.setAttribute("sido", sidoNm);
		request.setAttribute("gugun", sgguNm);
		request.setAttribute("detailAddr", detailAddr);
		request.setAttribute("result", list);
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
