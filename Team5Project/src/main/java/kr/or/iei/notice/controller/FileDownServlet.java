package kr.or.iei.notice.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.notice.service.NoticeService;
import kr.or.iei.notice.vo.Notice;

/**
 * Servlet implementation class FileDownServlet
 */
@WebServlet(name = "FileDown", urlPatterns = { "/fileDown.do" })
public class FileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownServlet() {
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
		//3.비즈니스로직
		NoticeService service = new NoticeService();
		//selectNotice는 조회수도 올라가니까 새로 만들어서 사용하는 것
		Notice  n = service.getNotice(noticeNo);
		//4.결과처리(이걸 외울수 없어서 필요하면 보고치면 되지만 흐름은 알고 있어야 함)
		//파일과 현재 서블릿을 연결
		String root = getServletContext().getRealPath("/");		//webapp
		String downLoadFile = root+"upload/notice/"+n.getFilepath();
		//파일을 서블릿으로 읽어오기위한 스트림생성(주 스트림. 보조스트림)
		FileInputStream fis = new FileInputStream(downLoadFile);
		BufferedInputStream bis = new BufferedInputStream(fis);
		//읽어온 파일을 사용자에게 전달할 스트림 생성(cos로 처리하던걸 직접치면 이렇게 됌-같은이름으로 여러개 올리는게 불가능하기 때문에 이렇게 하는 수밖에 없음)
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		//파일명처리(사용자가 받아볼 파일명)
		String resFilename = new String(n.getFilename().getBytes("UTF-8"),"ISO-8859-1");
		//파일다운로드를 위한 HTTP Header 설정
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+resFilename);
		//파일전송
		while(true) {
			int read = bis.read();	//servlet이랑 file 연결되어있는 bis
			if(read != -1) {
				bos.write(read);	//servelt이랑 화면 연결 bos / -1이 파일 할 거 없다 라는 의미
			}else {
				break;
			}
		}
		bos.close();
		bis.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
