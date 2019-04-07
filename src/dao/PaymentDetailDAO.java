package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.PaymentDetail;

public class PaymentDetailDAO extends DAOAbstract<PaymentDetail> {

	@Override
	public List<PaymentDetail> getAll() throws Exception {
		List<PaymentDetail> list = new ArrayList<>();
		String sql = "select * from PaymentDetails";
		try {
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				list.add(new PaymentDetail(rs.getInt(1), rs.getInt(2), rs.getInt(3),
						rs.getInt(4), rs.getDouble(5), rs.getDouble(6)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public boolean insert(PaymentDetail t) throws Exception {
		String sql = "";
		PreparedStatement ps;
		if(t.getServiceID() == 0) {
			sql = "insert into PaymentDetails(PaymentID, Quantity, Price, SubTotal) values (?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, t.getPaymentID());
			ps.setInt(2, t.getQuantity());
			ps.setDouble(3, t.getPrice());
			ps.setDouble(4, t.getSubtotal());
			return ps.executeUpdate() > 0;
		}
		sql = "insert into PaymentDetails(serviceID, "
				+ "PaymentID, Quantity, Price, SubTotal) values (?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, t.getServiceID());
		ps.setInt(2, t.getPaymentID());
		ps.setInt(3, t.getQuantity());
		ps.setDouble(4, t.getPrice());
		ps.setDouble(5, t.getSubtotal());
		return ps.executeUpdate() > 0;
	}

}
