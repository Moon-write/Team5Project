package kr.co.iei.free.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.co.iei.free.dao.freeDao;
import kr.co.iei.free.vo.Free;
import kr.co.iei.free.vo.Freeboard;
import kr.co.iei.free.vo.FreeboardTable;

public class FreeService {

	public Freeboard selectFreeList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		freeDao dao = new freeDao();
		int numPage = 20;
		//게시물 rownum범위 계산
		int End = numPage * reqPage;
		int Start = End - numPage +1;
		ArrayList<FreeboardTable> list = dao.selectFreeBoardTable(conn,Start,End);
		//게시물 닉네임 추출
		//ArrayList<String> writerList = dao.selectWriterList()
		//페이징 처리
		int totalCount = dao.totalFreeBoardCount(conn);
		int totalPage = 0;
		if(totalCount%numPage==0) {
			totalPage=totalCount/numPage;
		}else {
			totalPage=totalCount/numPage+1;
		}
		//Freeboard freeboard = new Freeboard(list,writerList,totalPage);
		JDBCTemplate.close(conn);
		return null;
	}
	
}
