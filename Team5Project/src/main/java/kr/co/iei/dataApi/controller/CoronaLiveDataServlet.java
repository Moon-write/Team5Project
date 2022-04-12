package kr.co.iei.dataApi.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.co.iei.dataApi.service.ApiExplorer;
import kr.co.iei.dataApi.vo.LiveData;

/**
 * Servlet implementation class CoronaLiveDataServlet
 */
@WebServlet(name = "CoronaLiveData", urlPatterns = { "/coronaLiveData.do" })
public class CoronaLiveDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CoronaLiveDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ajax로 데이터 주고받을거임
		request.setCharacterEncoding("utf-8");		
				
		ApiExplorer api = new ApiExplorer();
		ArrayList<LiveData> list = api.getData();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		Gson gson = new Gson();
		gson.toJson(list, out);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
