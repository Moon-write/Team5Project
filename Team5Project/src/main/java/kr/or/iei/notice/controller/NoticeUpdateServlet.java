package kr.or.iei.notice.controller;

import java.io.File;
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
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet(name = "NoticeUpdate", urlPatterns = { "/noticeUpdate.do" })
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateServlet() {
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
		//경로 설정
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root+"upload/notice";
		int maxSize = 10*1024*1024;
		//MultipartRequest로 변환
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		int noticeNo = Integer.parseInt(mRequest.getParameter("noticeNo"));
		String noticeTitle = mRequest.getParameter("noticeTitle");
		String noticeContent = mRequest.getParameter("noticeContent");
		String filename = mRequest.getOriginalFileName("file");
		String filepath = mRequest.getFilesystemName("file");
		//수정전 파일을 유지는 stay, 수정 전 삭제는 delete
		String status = mRequest.getParameter("status");
		//수정 전 파일이 잇다면!!!! 값이 있고 수정 전 파일이 없다면!! null
		String oldFilename = mRequest.getParameter("oldFilename");
		String oldFilepath = mRequest.getParameter("oldFilepath");
		//파일수정사항 case
		/*
		 * 1.기존첨부파일 있는경우
		 * 1-1. 기존 첨부파일 유지하는경우
		 * 1-2. 기존 첨부파일을 삭제하는경우
		 * 1-3. 기존첨부파일을 삭제하고, 새 첨부파일을 업로드하는경우
		 * 
		 * 2. 기존첨부파일이 없는경우
		 * 2-1. 수정시 첨부파일이 없는경우
		 * 2-2. 수정시 첨부파일이 있는경우
		 * */
		if(status.equals("delete")) {
			File delFile = new File(saveDirectory+"/"+oldFilepath);
			delFile.delete();
		}else if(oldFilename != null) {
			filename = oldFilename;
			filepath = oldFilepath;
		}
		Notice n = new Notice();
		n.setNoticeNo(noticeNo);
		n.setNoticeTitle(noticeTitle);
		n.setNoticeContent(noticeContent);
		n.setFilename(filename);
		n.setFilepath(filepath);
		//3.비즈니스로직
		NoticeService service = new NoticeService();
		int result = service.noticeUpdate(n);
		//4.결과처리->msg받기
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("title", "성공");
			request.setAttribute("msg", "공지사항 수정 완료");
			request.setAttribute("icon", "success");
		}else {
			request.setAttribute("title", "실패");
			request.setAttribute("msg", "공지사항 수정 실패");
			request.setAttribute("icon", "error");
		}
		request.setAttribute("loc", "/noticeView.do?noticeNo="+n.getNoticeNo());
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
