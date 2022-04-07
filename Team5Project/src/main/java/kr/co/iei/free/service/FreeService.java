package kr.co.iei.free.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.co.iei.free.dao.freeDao;
import kr.co.iei.free.vo.FreeboardTable;

public class FreeService {

	public ArrayList<FreeboardTable> selectFreeList(int reqPage, int numPage) {
		Connection conn = JDBCTemplate.getConnection();
		freeDao dao = new freeDao();
		//게시물 rownum범위 계산
		int End = numPage * reqPage;
		int Start = End - numPage +1;
		ArrayList<FreeboardTable> list = dao.selectFreeBoardTable(conn,Start,End);
		JDBCTemplate.close(conn);
		return list;
	}
	
}
