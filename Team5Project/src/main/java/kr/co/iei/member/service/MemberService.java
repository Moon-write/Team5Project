package kr.co.iei.member.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.JDBCTemplate;
import kr.co.iei.member.dao.MemberDao;
import kr.co.iei.member.vo.Member;

public class MemberService {

	public Member selectOneMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		Member m = dao.selectOneMember(conn, member);
		JDBCTemplate.close(conn);
		return m;
	}

	public int insertMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		int result = dao.insertMember(conn,m); //insert이므로 결과는 result
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Member selectOneMember(String memberId) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		Member m = dao.selectOneMember(conn, memberId);
		return m;
	}

	public int updateMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		int result = dao.updateMember(conn, member);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteMember(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		int result = dao.deleteMember(conn, memberNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Member> selectAllmember() {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		ArrayList<Member> list = dao.selectAllMember(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public int changeLevel(int memberNo, int memberLevel) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		int result = dao.changeLevel(conn, memberNo, memberLevel);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public boolean checkedChangeLevel(String num, String level) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		//한번에 여러값을 처리하기 위해 StringTodenizer를 이용, 문자열을 / 기준으로 분리
		StringTokenizer sT1 = new StringTokenizer(num,"/");
		StringTokenizer sT2 = new StringTokenizer(level,"/");
		boolean result = true;
		//sT1에 토큰이 있으면(hasMoreTokens)
		while (sT1.hasMoreTokens()) {
			int memberNo = Integer.parseInt(sT1.nextToken());
			int memberLevel = Integer.parseInt(sT2.nextToken());
			int checkResult = dao.changeLevel(conn, memberNo, memberLevel);
			if(checkResult == 0) {
				result = false;
				break; //실패 시 뒤의 트랜잭션은 수행 X => break;
			}
		}
		if(result) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn); //토큰중 하나라도 실패 시 롤백
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public String findId(String email) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		String memberId = dao.findId(conn, email);
		if(memberId == null) {
			JDBCTemplate.rollback(conn);
		}else {
			JDBCTemplate.commit(conn);
		}
		JDBCTemplate.close(conn);
		return memberId;
	}

//	public String findPw(String memberName, String memberId, String memberEmail) {
//		Connection conn = JDBCTemplate.getConnection();
//		MemberDao dao = new MemberDao();
//		String memberPw = dao.findPw(conn, memberName, memberId, memberEmail);
//		if(memberPw == null) {
//			JDBCTemplate.rollback(conn);
//		}else {
//			JDBCTemplate.commit(conn);
//		}
//		JDBCTemplate.close(conn);
//		System.out.println(memberPw);
//		return memberPw;
//	}

	public Member findPw(Member member) {
		//System.out.println("서블릿에서보내고 서비스에서 받은 member "+member);
		Connection conn = JDBCTemplate.getConnection();
		MemberDao dao = new MemberDao();
		Member m = dao.findPw(conn, member);
//		System.out.println("DAO에서 뽑아오고 서비스에서 받은 m "+m);
		//System.out.println("dao로부터 서비스에서 받은 m : "+m);
		if(m == null) {
			JDBCTemplate.rollback(conn);
		}else {
			JDBCTemplate.commit(conn);
		}
		JDBCTemplate.close(conn);
		return m;
	}
}
