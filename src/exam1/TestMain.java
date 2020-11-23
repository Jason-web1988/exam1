package exam1;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import exam1.dao.MemberDao;
import exam1.dao.SaleDao;
import exam1.ds.JdbcUtil;
import exam1.dto.Member;
import exam1.dto.Sale;

public class TestMain {
	public static void main(String[] args) {
		Connection conn = JdbcUtil.getConnection();
		System.out.println(conn);
		
		List<Member> list = MemberDao.getInstance().selectMemberByAll();
//		System.out.println(list);
		for(Member m : list) {
			System.out.println(m);
		}
		
		Member member = MemberDao.getInstance().selectMemberByNo(100001);
		System.out.println("selectMemberByNo ==> " + member);
		
		List<Sale> saleList = SaleDao.getInstance().selectSaleByAll();
		for(Sale s : saleList) {
			System.out.println(s);
		}
		
		int nextCustNo = MemberDao.getInstance().nextCustNo();
		System.out.println("nextCustNo ==> " + nextCustNo);
		
		Member insertMember = new Member(nextCustNo, "이현석", "010-3356-0922", "대구광역시", new Date(), "V", "60");
		System.out.println("insertMember ==> " + insertMember);
		MemberDao.getInstance().insertMember(insertMember);
		
		Member upMember = MemberDao.getInstance().selectMemberByNo(nextCustNo);
		
		upMember.setCustName("최수정");
		upMember.setPhone("010-1234-5678");
		upMember.setAddress("부산광역시");
		upMember.setJoinDate(new Date());
		upMember.setGrade("A");
		upMember.setCity("50");
		
		System.out.println("upMember ==> " + upMember );
		MemberDao.getInstance().updateMember(upMember);
		
	}
}
