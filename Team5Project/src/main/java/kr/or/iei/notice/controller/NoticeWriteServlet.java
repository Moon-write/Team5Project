package kr.or.iei.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.iei.notice.service.NoticeService;
import kr.or.iei.notice.vo.Notice;


/**
 * Servlet implementation class NoticeWriteServlet
 */
@WebServlet(name = "NoticeWrite", urlPatterns = { "/noticeWrite.do" })
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 값추출
		//2-1. 파일 업로드
		//1) 파일 업로드 경로 지정
		String root = getServletContext().getRealPath("/");//webapp폴더 절대경로를 servlet에서 가져오는 것
		String saveDirectory = root+"upload/notice";
		System.out.println("파일저장경로 : "+saveDirectory);
		//2) 파일 업로드 최대용량지정
		int maxSize = 10*1024*1024;	
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		//값을 추출(mRequest로 가져오기)
		String noticeTitle = mRequest.getParameter("noticeTitle");
		String noticeWriter = mRequest.getParameter("noticeWriter");
		String noticeContent = mRequest.getParameter("noticeContent");
		String filename = mRequest.getOriginalFileName("file"); //화면에서 업로드하는 실제 파일이름
		String filepath = mRequest.getFilesystemName("file");	//서버에 저장되는 파일이름
		String[] top = mRequest.getParameterValues("top");
		int topFixed = 0;
		if(top!=null){
		  topFixed=1;
		}
	
		//변수들 notice에 묶어두기()
		Notice n = new Notice();
		n.setNoticeTitle(noticeTitle);
		n.setNoticeWriter(noticeWriter);
		n.setNoticeContent(noticeContent);
		n.setFilename(filename);
		n.setFilepath(filepath);//데이터 처리완료 인서트 시작
		n.setTopFixed(topFixed);
		//3. 비즈니스로직
		NoticeService service = new NoticeService();
		int result = service.insertNotice(n);
		//4. 결과처리
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "공지사항이 등록되었습니다.");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "공지사항 등록 중 문제가 발생했습니다.");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/noticeList.do?reqPage=1");
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
