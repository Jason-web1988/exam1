package exam1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exam1.ds.JdbcUtil;
import exam1.dto.Member;

public class MemberDao {
	private static final MemberDao instance = new MemberDao();
	
	private MemberDao() {}

	public static MemberDao getInstance() {
		return instance;
	}



	public List<Member> selectMemberByAll(){
		String sql = "SELECT CUSTNO, CUSTNAME, PHONE, ADDRESS, JOINDATE, GRADE, CITY FROM MEMBER_TBL_02 ";
		try(Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				List<Member> list = new ArrayList<>();
				do {
					list.add(getMember(rs));
				}while(rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Member getMember(ResultSet rs) throws SQLException {
		// CUSTNO, CUSTNAME, PHONE, ADDRESS, JOINDATE, GRADE, CITY FROM MEMBER_TBL_02
		int custNo = rs.getInt("custno");
		String custName = rs.getString("custname");
		String phone = rs.getString("phone");
		String address = rs.getString("address");
		Date joinDate = rs.getDate("joindate");
		String grade = rs.getString("grade");
		String city = rs.getString("city");
		return new Member(custNo, custName, phone, address, joinDate, grade, city);
	}
	
	public void insertMember(Member member) {
		String sql = " insert into member_tbl_02 values(?,?, ?, ?, ?, ?, ?) ";
		try(Connection con =JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, member.getCustNo());
			pstmt.setString(2, member.getCustName());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getAddress());
			pstmt.setTimestamp(5, new Timestamp(member.getJoinDate().getTime()));
			pstmt.setString(6, member.getGrade());
			pstmt.setString(7, member.getCity());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateMember(Member member) {
		String sql = "update MEMBER_TBL_02 set custname=?, phone=?, address=?, joindate=?, grade=?, city=? where custno=?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, member.getCustName());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getAddress());
			pstmt.setTimestamp(4, new Timestamp(member.getJoinDate().getTime()));
			pstmt.setString(5, member.getGrade());
			pstmt.setString(6, member.getCity());
			pstmt.setInt(7, member.getCustNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Member selectMemberByNo(int custno) {
		String sql = "SELECT CUSTNO, CUSTNAME, PHONE, ADDRESS, JOINDATE, GRADE, CITY FROM MEMBER_TBL_02 where custno = ? ";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setInt(1, custno);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
						return getMember(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public int nextCustNo() {
		String sql = "SELECT MAX(CUSTNO)+1 FROM MEMBER_TBL_02 ";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
