package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.BookingDetail;

public class BookingDetailDAO extends DAOAbstract<BookingDetail> {

	@Override
	public List<BookingDetail> getAll() throws Exception {
		List<BookingDetail> list = new ArrayList<>();
		String sql = "select * from BookingDetails";
		try {
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				list.add(new BookingDetail(rs.getInt(1), rs.getDate(2), 
						rs.getDate(3), rs.getDouble(4), 
						rs.getFloat(5), rs.getInt(6), 
						rs.getInt(7), rs.getInt(8)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<BookingDetail> getByBookingID(int bookingID) throws Exception {
		List<BookingDetail> list = new ArrayList<>();
		String sql = "select * from BookingDetails where BookingID = " + bookingID;
		try {
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				list.add(new BookingDetail(rs.getInt(1), rs.getDate(2), 
						rs.getDate(3), rs.getDouble(4), 
						rs.getFloat(5), rs.getInt(6), 
						rs.getInt(7), rs.getInt(8)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public boolean insert(BookingDetail rd) throws Exception {
		java.util.Date utilCheckinDate = rd.getCheckinDate();
		java.sql.Date sqlCheckinDate = new java.sql.Date(utilCheckinDate.getTime());
		java.util.Date utilCheckoutDate = rd.getCheckoutDate();
		java.sql.Date sqlCheckoutDate = new java.sql.Date(utilCheckoutDate.getTime());
		String sql = "insert into BookingDetails(CheckinDate, CheckoutDate, "
				+ "Price, Discount, BookingID, RoomID, "
				+ "CategoryID) values (?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setDate(1, sqlCheckinDate);
		ps.setDate(2, sqlCheckoutDate);
		ps.setDouble(3, rd.getPrice());
		ps.setFloat(4, rd.getDiscount());
		ps.setInt(5, rd.getBookingID());
		ps.setInt(6, rd.getRoomID());
		ps.setInt(7, rd.getCategoryID());
		return ps.executeUpdate() > 0;
	}

}
