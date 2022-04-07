package kr.or.iei.notice.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.or.iei.notice.dao.NoticeDao;
import kr.or.iei.notice.vo.Notice;
import kr.or.iei.notice.vo.NoticePageData;

public class NoticeService {

	public NoticePageData selectNoticeService(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		NoticeDao dao = new NoticeDao();
		//페이징처리
		//1. 한페이지당 게시물 수 15개
		int numPerPage = 15;
		//게시물범위계산
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		
		ArrayList<Notice> list = dao.selectNoticeList(conn,start,end);
		//전체 페이지 계산 전 게시물 수 세기
		int totalCount = dao.totalNoticeCount(conn);
		//전체 페이지 수(나머지 있으면 하나 추가)
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage + 1;
		}
		int pageNavSize = 5;
		//nav시작번호 계산
		int pageNo = ((reqPage-1)/pageNavSize)*pageNavSize + 1;
		//nav제작
		String pageNav = "<ul class='pagination pagination-lg' style='justify-content: center;'>";
		//이전 버튼
		if(pageNo != 1) {
			pageNav += "<li>";
			//-1을 해줘야 ! 12345 느낌표 자리에 생기니까
			pageNav += "<a class='page-item' href='/noticeList.do?reqPage="+(pageNo-1)+"'>";
			pageNav += "<span class='page-link'>«</span>";
			pageNav += "</a></li>";
		}
		//페이지 숫자
		for(int i=0;i<pageNavSize;i++) {
			if(pageNo == reqPage) {
				pageNav += "<li class='badge rounded-pill bg-light'>";
				pageNav += "<a class='page-link' href='/noticeList.do?reqPage="+pageNo+"'>";
				pageNav += pageNo;
				pageNav += "</a></li>";
			}else {
				pageNav += "<li>";
				pageNav += "<a class='page-link' href='/noticeList.do?reqPage="+pageNo+"'>";
				pageNav += pageNo;
				pageNav += "</a></li>";	
			}
			pageNo++;
			//데이터의 양보다 페이지가 크면 없애기 위해서
			if(pageNo > totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo<=totalPage) {
			pageNav += "<li>";
			pageNav += "<a class='page-item' href='/noticeList.do?reqPage="+pageNo+"'>";
			pageNav += "<span class='page-link'>»</span>";
			pageNav += "</a></li>";
		}
		pageNav += "</ul>";
		NoticePageData npd = new NoticePageData(list, pageNav);
		JDBCTemplate.close(conn);
		return npd;
	}

	public int insertNotice(Notice n) {
		Connection conn = JDBCTemplate.getConnection();
		NoticeDao dao = new NoticeDao();
		int result = dao.insertNotice(conn,n);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Notice selectOneNotice(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		NoticeDao dao = new NoticeDao();
		int result = dao.updateReadCount(conn,noticeNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
			return null;
		}
		Notice n = dao.selectOneNotice(conn,noticeNo);
		JDBCTemplate.close(conn);
		return n;
	}

	public Notice getNotice(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		NoticeDao dao = new NoticeDao();
		Notice n = dao.selectOneNotice(conn, noticeNo);
		JDBCTemplate.close(conn);
		return n;
	}

	public int noticeDelete(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		NoticeDao dao = new NoticeDao();
		int result = dao.noticeDelete(conn,noticeNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

}
