package exam1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exam1.ds.JdbcUtil;
import exam1.dto.Sale;

public class SaleDao {
	private static final SaleDao instance = new SaleDao();

	public SaleDao() {}

	public static SaleDao getInstance() {
		return instance;
	}
	
	public List<Sale> selectSaleByAll(){
		String sql = "select n.custno, custname, grade, total\r\n" + 
				" from MEMBER_TBL_02 m join (\r\n" + 
				" 						select custno, sum(price) as total \r\n" + 
				"							from money_tbl_02 \r\n" + 
				"							group by custno)\r\n" + 
				"    n on m.custno = n.custno\r\n" + 
				"	order by total desc ";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				List<Sale> list = new ArrayList<>();
				do {
					list.add(getSale(rs));
				}while(rs.next());
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Sale getSale(ResultSet rs) throws SQLException {
		int custNo = rs.getInt("custno");
		String custName = rs.getString("custname");
		String grade = rs.getString("grade");
		int total = rs.getInt("total");
		return new Sale(custNo, custName, grade, total);
	}
}
