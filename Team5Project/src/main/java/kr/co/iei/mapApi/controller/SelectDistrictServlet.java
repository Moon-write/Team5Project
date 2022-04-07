package kr.co.iei.mapApi.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;


/**
 * Servlet implementation class SelectDistrictServlet
 */
@WebServlet(name = "SelectDistrict", urlPatterns = { "/selectDistrict.do" })
public class SelectDistrictServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectDistrictServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String inputVal = request.getParameter("district");
		
		String data = "";
		switch(inputVal) {
		case "서울": data="종로구,중구,용산구,성동구,광진구,동대문구,중랑구,성북구,강북구,도봉구,노원구,은평구,서대문구,마포구,양천구,강서구,구로구,금천구,영등포구,동작구,관악구,서초구,강남구,송파구,강동구";
			break;
		case "부산": data="중구,서구,동구,영도구,부산진구,동래구,남구,북구,강서구,해운대구,사하구,금정구,연제구,수영구,사상구,기장군";
			break;
		case "인천": data="중구,동구,남구,연수구,남동구,부평구,계양구,서구,강화군,옹진군";
			break;
		case "대구": data="중구,동구,서구,남구,북구,수성구,달서구,달성군";
			break;
		case "광주": data="동구,서구,남구,북구,광산구";
			break;
		case "대전": data="동구,중구,서구,유성구,대덕구";
			break;
		case "울산": data="중구,남구,동구,북구,울주군";
			break;
		case "세종": data="";
			break;
		case "경기": data="가평군,고양덕양구,고양일산동구,고양일산서구,과천시,광명시,광주시,구리시,군포시,김포시,남양주시,동두천시,부천시,성남중원구,성남수정구,성남분당구,수원장안구,수원팔달구,수원영통구,수원권선구,시흥시,안산단원구,안산상록구,안성시,안양만안구,안양동안구,양주시,양평군,여주시,연천군,오산시,용인처인구,용인기흥구,용인수지구,의왕시,의정부시,이천시,파주시,평택시,포천시,하남시,화성시";
			break;
		case "강원": data="원주시,춘천시,강릉시,동해시,속초시,삼척시,홍천군,태백시,철원군,횡성군,평창군,영월군,정선군,인제군,고성군,양양군,화천군,양구군";
			break;
		case "충북": data="청주상당구,청주흥덕구,청주청원구,청주서원구,충주시,제천시,보은군,옥천군,영동군,증평군,진천군,괴산군,음성군,단양군";
			break;
		case "충남": data="천안시,공주시,보령시,아산시,서산시,논산시,계룡시,당진시,금산군,부여군,서천군,청양군,홍성군,예산군,태안군";
			break;
		case "경북": data="포항시,경주시,김천시,안동시,구미시,영주시,영천시,상주시,문경시,경산시,군위군,의성군,청송군,영양군,영덕군,청도군,고령군,성주군,칠곡군,예천군,봉화군,울진군,울릉군";
			break;
		case "경남": data="창원시,김해시,진주시,양산시,거제시,통영시,사천시,밀양시,함안군,거창군,창녕군,고성군,하동군,합천군,남해군,함양군,산청군,의령군";
			break;
		case "전북": data="전주시,익산시,군산시,정읍시,완주군,김제시,남원시,고창군,부안군,임실군,순창군,진안군,장수군,무주군";
			break;
		case "전남": data="여수시,순천시,목포시,광양시,나주시,무안군,해남군,고흥군,화순군,영암군,영광군,완도군,담양군,장성군,보성군,신안군,장흥군,강진군,함평군,진도군,곡성군,구례군";
			break;
		case "제주": data="제주시,서귀포시";
			break;
		}
		String[] result = data.split(",");
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		new Gson().toJson(result, out);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
