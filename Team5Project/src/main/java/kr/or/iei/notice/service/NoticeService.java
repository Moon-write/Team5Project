package kr.or.iei.notice.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.or.iei.notice.dao.NoticeDao;
import kr.or.iei.notice.vo.Notice;
import kr.or.iei.notice.vo.NoticeComment;
import kr.or.iei.notice.vo.NoticePageData;
import kr.or.iei.notice.vo.NoticeViewData;

public class NoticeService {

	public NoticePageData selectNoticeService(int reqPage,String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		NoticeDao dao = new NoticeDao();
		//페이징처리
		//1. 한페이지당 게시물 수 15개
		int numPerPage = 15;
		//게시물범위계산
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		
		ArrayList<Notice> list = dao.selectNoticeList(conn,start,end,memberId);
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

	public Notice selectOneNotice(int noticeNo,String memberId) {
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
		Notice n = dao.selectOneNotice(conn,noticeNo,memberId);
		JDBCTemplate.close(conn);
		return n;
	}

	public Notice getNotice(int noticeNo, String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		NoticeDao dao = new NoticeDao();
		Notice n = dao.selectOneNotice(conn, noticeNo, memberId);
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

	public int noticeUpdate(Notice n) {
		Connection conn = JDBCTemplate.getConnection();
		NoticeDao dao = new NoticeDao();
		int result = dao.noticeUpdate(conn,n);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertNoticeComment(NoticeComment nc) {
		Connection conn = JDBCTemplate.getConnection();
		NoticeDao dao = new NoticeDao();
		int result = dao.insertNoticeComment(conn,nc);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public NoticeViewData selectNoticeView(int noticeNo,String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		NoticeDao dao = new NoticeDao();
		//어떤 공지사항인지
		Notice n = dao.selectOneNotice(conn, noticeNo,memberId);
		//그 공지사앟에 있는 댓글
		ArrayList<NoticeComment> commentList = dao.selectNoticeComment(conn, noticeNo);
		//대댓글 조회
		ArrayList<NoticeComment> reCommentList = dao.selectNoticeReComment(conn, noticeNo);
		JDBCTemplate.close(conn);
		NoticeViewData nvd = new NoticeViewData(n, commentList, reCommentList);
		return nvd;
	}

	public int updateNoticeComment(NoticeComment nc) {
		Connection conn = JDBCTemplate.getConnection();
		NoticeDao dao = new NoticeDao();
		int result = dao.updateNoticeComment(conn,nc);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteNoticeComment(int ncNo) {
		Connection conn = JDBCTemplate.getConnection();
		NoticeDao dao = new NoticeDao();
		int result = dao.deleteNoticeComment(conn,ncNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int clicklike(int noticeNo, String memberId, int status) {
		Connection conn = JDBCTemplate.getConnection();
		NoticeDao dao = new NoticeDao();
		int result = 0;
		if(status == 1) {
			//좋아요를 취소하는 dao메소드 호출
			result = dao.cancellike(conn,noticeNo,memberId);
		}else if(status==2) {
			//좋아요를 누르는 dao 메소드 호출
			result = dao.pluslike(conn,noticeNo,memberId);
		}
		
		if(result>0) {
			JDBCTemplate.commit(conn);
			//현재 해당 공지사항의 좋아요 갯수 조회
			result = dao.countlike(conn,noticeNo);
		
		}else {
			JDBCTemplate.rollback(conn);
			result = -1;
		}		
		JDBCTemplate.close(conn);
		return result;
	}

	public NoticePageData searchNotice(int reqPage, String select, String value) {
		// TODO Auto-generated method stub
		return null;
	}



}
