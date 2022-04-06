package kr.co.iei.free.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.co.iei.free.dao.freeDao;
import kr.co.iei.free.vo.Free;
import kr.co.iei.free.vo.Freeboard;

public class FreeService {

	public Freeboard selectFreeList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		freeDao dao = new freeDao();
		int numPerPage = 10;
		//게시물 rownum범위 계산
		int End = numPerPage * reqPage;
		int Start = End - numPerPage +1;
		ArrayList<Free> list = dao.selectFreeList(conn,Start,End);
		//페이징 처리
		int totalCount = dao.totalFreeBoardCount(conn);
		int totalPage = 0;
		if(totalCount%numPerPage==0) {
			totalPage=totalCount/numPerPage;
		}else {
			totalPage=totalCount/numPerPage+1;
		}
		
		JDBCTemplate.close(conn);
		return null;
	}
	
}
