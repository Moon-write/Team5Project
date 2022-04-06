package kr.or.iei.notice.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.or.iei.notice.dao.NoticeDao;
import kr.or.iei.notice.vo.Notice;

public class NoticeService {

	public ArrayList<Notice> selectNoticeService(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		NoticeDao dao = new NoticeDao();
		//페이징처리
		//1. 한페이지당 게시물 수 15개
		int numPerPage = 15;
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		
		ArrayList<Notice> list = dao.selectNoticeList(conn,start,end);
		JDBCTemplate.close(conn);
		return list;
	}

}
