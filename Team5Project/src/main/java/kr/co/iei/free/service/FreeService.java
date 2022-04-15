package kr.co.iei.free.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import common.JDBCTemplate;
import kr.co.iei.free.dao.freeDao;
import kr.co.iei.free.vo.Free;
import kr.co.iei.free.vo.FreeComment;
import kr.co.iei.free.vo.FreeView;
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
		JDBCTemplate.close(conn);
		return pageNavi;
	}

	public int insertFree(Free f) {
		Connection conn = JDBCTemplate.getConnection();
		freeDao dao = new freeDao();
		int result = dao.insertFree(conn, f);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	public FreeView FreeView(int freeNo) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		freeDao dao = new freeDao();
		int result = dao.viewCountUpdate(conn,freeNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		FreeView view = dao.freeView(conn, freeNo);
		JDBCTemplate.close(conn);
		return view;
	}
	public Free selectOneFree(int freeNo) {
		Connection conn = JDBCTemplate.getConnection();
		freeDao dao = new freeDao();
		Free f = dao.selectOneFree(conn, freeNo);
		JDBCTemplate.close(conn);
		return f;
	}
	public int freeBoardUpdate(Free f) {
		Connection conn = JDBCTemplate.getConnection();
		freeDao dao = new freeDao();
		int result = dao.freeBoardUpdate(conn, f);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	public int insertComment(FreeComment fc) {
		Connection conn = JDBCTemplate.getConnection();
		freeDao dao = new freeDao();
		int result = dao.insertComment(conn, fc);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	public ArrayList<FreeComment> FreeCommentSearch(int freeNo) {
		Connection conn = JDBCTemplate.getConnection();
		freeDao dao = new freeDao();
		ArrayList<FreeComment> cmlist = dao.FreeCommentSearch(conn, freeNo);
		JDBCTemplate.close(conn);
		return cmlist;
	}
	public ArrayList<FreeComment> FreeRecommentSearch(int freeNo) {
		Connection conn = JDBCTemplate.getConnection();
		freeDao dao = new freeDao();
		ArrayList<FreeComment> recmlist = dao.FreeRecommentSearch(conn, freeNo);
		JDBCTemplate.close(conn);
		return recmlist;
	}
	public HashMap<Integer, Boolean> addLikecheck(int no) {
		Connection conn = JDBCTemplate.getConnection();
		freeDao dao = new freeDao();
		HashMap<Integer, Boolean> likecheck = dao.addLikecheck(conn, no);
		JDBCTemplate.close(conn);
		return likecheck;
	}
	public int InsertLike(int num1, int num2) {
		Connection conn = JDBCTemplate.getConnection();
		freeDao dao = new freeDao();
		int result = dao.InsertLike(conn,num1,num2);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	public int DeleteLike(int num1, int num2) {
		Connection conn = JDBCTemplate.getConnection();
		freeDao dao = new freeDao();
		int result = dao.DeleteLike(conn,num1,num2);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	public int FreeDelete(int freeNo) {
		Connection conn = JDBCTemplate.getConnection();
		freeDao dao = new freeDao();
		int result = dao.FreeDelete(conn,freeNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	public boolean likeCheck(int freeNo, int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		freeDao dao = new freeDao();
		boolean likecheck = dao.Likecheck(conn, freeNo, memberNo);
		JDBCTemplate.close(conn);
		return likecheck;
	}
	public int freeUpdateComment(int commentNo, String comment) {
		Connection conn = JDBCTemplate.getConnection();
		freeDao dao = new freeDao();
		
		int result = dao.freeUpdateComment(conn, commentNo, comment);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	
}
