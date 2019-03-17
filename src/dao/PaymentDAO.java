package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Booking;
import entity.Payment;

public class PaymentDAO extends DAOAbstract<Payment> {

	@Override
	public List<Payment> getAll() throws Exception {
		List<Payment> list = new ArrayList<>();
		String sql = "select * from Payments";
		try {
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				list.add(new Payment(rs.getInt(1), rs.getInt(2), 
						rs.getDouble(3), rs.getDouble(4), 
						rs.getDouble(5), rs.getInt(6)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public boolean insert(Payment t) throws Exception {
		String sql = "insert into Payments(ServiceQuantity, "
				+ "ServicePrice, RentalPrice, SubTotal, "
				+ "bookingID) values (?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, t.getServiceQuantity());
		ps.setDouble(2, t.getServicePrice());
		ps.setDouble(3, t.getRentalPrice());
		ps.setDouble(4, t.getSubTotal());
		ps.setInt(5, t.getBookingID());
		return ps.executeUpdate() > 0;
	}

}
