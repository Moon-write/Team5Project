package kr.co.iei.member.service;

import java.sql.Connection;

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

}
