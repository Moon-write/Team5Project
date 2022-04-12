package kr.co.iei.free.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import kr.co.iei.free.dao.freeDao;
import kr.co.iei.free.vo.Free;
import kr.co.iei.free.vo.FreeboardTable;

public class FreeService {

	public ArrayList<FreeboardTable> selectFreeList3(int reqPage, int numPage, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		freeDao dao = new freeDao();
		//게시물 rownum범위 계산
		int End = numPage * reqPage;
		int Start = End - numPage +1;
		ArrayList<FreeboardTable> list = dao.selectFreeList3(conn,Start,End, keyword);
		JDBCTemplate.close(conn);
		return list;
	}
	public ArrayList<FreeboardTable> selectFreeList2(int reqPage, int numPage, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		freeDao dao = new freeDao();
		//게시물 rownum범위 계산
		int End = numPage * reqPage;
		int Start = End - numPage +1;
		ArrayList<FreeboardTable> list = dao.selectFreeList2(conn,Start,End, keyword);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<FreeboardTable> selectFreeList1(int reqPage, int numPage, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		freeDao dao = new freeDao();
		//게시물 rownum범위 계산
		int End = numPage * reqPage;
		int Start = End - numPage +1;
		ArrayList<FreeboardTable> list = dao.selectFreeList1(conn,Start,End, keyword);
		JDBCTemplate.close(conn);
		return list;
	}
	public String totalPage(int reqPage, int numPage) {
		Connection conn = JDBCTemplate.getConnection();
		freeDao dao = new freeDao();
		int totalCount = dao.totalFreeBoardCount(conn);
		int totalPage = 0;
		if(totalCount%numPage==0) {
			totalPage=totalCount/numPage;
		}else {
			totalPage=totalCount/numPage+1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize +1;
		String pageNavi="";
		if(reqPage!=1) {
			pageNavi += "<li class='page-item prev'><button class='page-link'>&laquo</button></li>";			
		}else {
			pageNavi += "<li class='page-item disabled'><button class='page-link'>&laquo</button></li>";			
		}
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo==reqPage) {
				pageNavi+="<li class='page-item active'><button class='page-link'>"+pageNo+"</button></li>";							
			}else {
				pageNavi+="<li class='page-item'><button class='page-link pagebtn'>"+pageNo+"</button></li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				 break;
			}
		}
		if(reqPage!=totalPage) {
			pageNavi += "<li class='page-item next'><button class='page-link'>&raquo</button></li>";			
			
		}else {
			pageNavi += "<li class='page-item disabled'><button class='page-link'>&raquo</button></li>";			
		}
		return pageNavi;
	}

	public int insertFree(Free f) {
		Connection conn = JDBCTemplate.getConnection();
		freeDao dao = new freeDao();
		int result = dao.insertFree(conn, f);
		try {
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}

	
}
