package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Category;
import entity.ReportCategory;
import entity.ReportRevenue;
import entity.Room;
import entity.Service;
import factory.ConnectionFactory;

public class ReportDAO {
	
	private Connection conn;
	private CategoryDAO cdao = new CategoryDAO();
	private ServiceDAO sdao = new ServiceDAO();
	private List<ReportRevenue> reportRevenuesByMonth = new ArrayList<>();
	private List<ReportRevenue> reportRevenuesByYear = new ArrayList<>();
	private List<ReportCategory> reportCategories = new ArrayList<>();
	
	public ReportDAO() {
		try {
			conn = ConnectionFactory.getInstance().getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	select p.PaymentID, bd.CategoryID, b.QuantityCategory, 
	bd.RoomID, b.QuantityRoom, pd.serviceID, p.ServiceQuantity,
	p.ServicePrice, p.RentalPrice, p.PaymentDate, p.SubTotal 
		from Payments p join PaymentDetails pd 
		on p.PaymentID = pd.PaymentID 
		join Bookings b on b.BookingID = p.BookingID 
		join BookingDetails bd on bd.BookingID = b.BookingID 
		where MONTH(p.PaymentDate) = MONTH(getdate())
	 */
	
	public List<ReportRevenue> reportRevenueByMonth() {
		String sql = "select p.PaymentID, bd.CategoryID, b.QuantityCategory, "
				+ "bd.RoomID, b.QuantityRoom, pd.serviceID, p.ServiceQuantity, "
				+ "p.ServicePrice, p.RentalPrice, p.PaymentDate, p.SubTotal "
				+ "from Payments p join PaymentDetails pd "
				+ "on p.PaymentID = pd.PaymentID "
				+ "join Bookings b on b.BookingID = p.BookingID "
				+ "join BookingDetails bd on bd.BookingID = b.BookingID "
				+ "where MONTH(p.PaymentDate) = MONTH(getdate())";
		try {
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				if(reportRevenuesByMonth.size() <= 0) {
					reportRevenuesByMonth.add(new ReportRevenue(rs.getInt(1), 
							getCategoryName(rs.getInt(2)), rs.getInt(3), 
							rs.getInt(4) + "", rs.getInt(5), 
							getServiceName(rs.getInt(6)), rs.getInt(7), 
							rs.getDouble(8), rs.getDouble(9), 
							rs.getDate(10), rs.getDouble(11)));
				}
				else {
					if(!isExistInReportByMonth(rs.getInt(1), rs.getInt(2), rs.getInt(4), rs.getInt(6))) {
						reportRevenuesByMonth.add(new ReportRevenue(rs.getInt(1), 
								getCategoryName(rs.getInt(2)), rs.getInt(3), 
								rs.getInt(4) + "", rs.getInt(5), 
								getServiceName(rs.getInt(6)), rs.getInt(7), 
								rs.getDouble(8), rs.getDouble(9), 
								rs.getDate(10), rs.getDouble(11)));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reportRevenuesByMonth;
	}
	
	public List<ReportRevenue> reportRevenueByYear() {
		String sql = "select p.PaymentID, bd.CategoryID, b.QuantityCategory, "
				+ "bd.RoomID, b.QuantityRoom, pd.serviceID, p.ServiceQuantity, "
				+ "p.ServicePrice, p.RentalPrice, p.PaymentDate, p.SubTotal "
				+ "from Payments p join PaymentDetails pd "
				+ "on p.PaymentID = pd.PaymentID "
				+ "join Bookings b on b.BookingID = p.BookingID "
				+ "join BookingDetails bd on bd.BookingID = b.BookingID "
				+ "where YEAR(p.PaymentDate) = YEAR(getdate())";
		try {
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				if(reportRevenuesByYear.size() <= 0) {
					reportRevenuesByYear.add(new ReportRevenue(rs.getInt(1), 
							getCategoryName(rs.getInt(2)), rs.getInt(3), 
							rs.getInt(4) + "", rs.getInt(5), 
							getServiceName(rs.getInt(6)), rs.getInt(7), 
							rs.getDouble(8), rs.getDouble(9), 
							rs.getDate(10), rs.getDouble(11)));
				}
				else {
					if(!isExistInReportByYear(rs.getInt(1), rs.getInt(2), rs.getInt(4), rs.getInt(6))) {
						reportRevenuesByYear.add(new ReportRevenue(rs.getInt(1), 
								getCategoryName(rs.getInt(2)), rs.getInt(3), 
								rs.getInt(4) + "", rs.getInt(5), 
								getServiceName(rs.getInt(6)), rs.getInt(7), 
								rs.getDouble(8), rs.getDouble(9), 
								rs.getDate(10), rs.getDouble(11)));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reportRevenuesByYear;
	}
	
	private boolean isExistInReportByYear(int paymentID, int categoryID, int roomID, int serviceID) {
		for(ReportRevenue i : reportRevenuesByYear) {
			if(i.getPaymentID() == paymentID) {
				if(i.getCategoryName().indexOf(getCategoryName(categoryID)) < 0) {
					i.setCategoryName(i.getCategoryName() + " - " + getCategoryName(categoryID));
				}
				else if(i.getRoomID().indexOf(roomID + "") < 0) {
					i.setRoomID(i.getRoomID() + " - " + roomID);
				}
				else if(i.getServiceName().indexOf(getServiceName(serviceID)) < 0) {
					i.setServiceName(i.getServiceName() + " - " + getServiceName(serviceID));
				}
				return true;
			}
		}
		return false;
	}
	
	private boolean isExistInReportByMonth(int paymentID, int categoryID, int roomID, int serviceID) {
		for(ReportRevenue i : reportRevenuesByMonth) {
			if(i.getPaymentID() == paymentID) {
				if(i.getCategoryName().indexOf(getCategoryName(categoryID)) < 0) {
					i.setCategoryName(i.getCategoryName() + " - " + getCategoryName(categoryID));
				}
				else if(i.getRoomID().indexOf(roomID + "") < 0) {
					i.setRoomID(i.getRoomID() + " - " + roomID);
				}
				else if(i.getServiceName().indexOf(getServiceName(serviceID)) < 0) {
					i.setServiceName(i.getServiceName() + " - " + getServiceName(serviceID));
				}
				return true;
			}
		}
		return false;
	}
	
	/*
	select bd.CategoryID, bd.RoomID, p.PaymentID, bd.Price
	from Payments p join Bookings b
	on p.BookingID = b.BookingID
	join BookingDetails bd
	on bd.BookingID = b.BookingID
	 */
	
	public List<ReportCategory> reportRevenueByCategory() {
		String sql = "select bd.CategoryID, bd.RoomID, bd.Price "
				+ "from Payments p join Bookings b "
				+ "on p.BookingID = b.BookingID "
				+ "join BookingDetails bd "
				+ "on bd.BookingID = b.BookingID";
		try {
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				int num = 1;
				if(!isExistReportByCategory(rs.getInt(1), rs.getInt(2), rs.getDouble(3))) {
					reportCategories.add(new ReportCategory(
							getCategoryName(rs.getInt(1)), rs.getInt(2) + "", 
							rs.getDouble(3), 1, 1));
					num++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reportCategories;
	}
	
	private boolean isExistReportByCategory(int categoryID, int roomID, double price) {
		for(ReportCategory i : reportCategories) {
			if(i.getCategoryName().equals(getCategoryName(categoryID))) {
				i.setRoom(i.getRoom() + " - " + roomID);
				i.setPrice(i.getPrice() + price);
				i.setRoomQuantity(i.getRoomQuantity() + 1);
				i.setBookingQuantity(i.getBookingQuantity() + 1);
				return true;
			}
		}
		return false;
	}
	
	private String getCategoryName(int categoryID) {
		String name = "";
		for(Category c : cdao.getAll()) {
			if(c.getCategoryID() == categoryID) {
				name = c.getCategoryName();
			}
		}
		return name;
	}
	
	private String getServiceName(int serviceID) {
		String name = "";
		try {
			for(Service s : sdao.getAll()) {
				if(s.getServiceID() == serviceID) {
					name = s.getServiceName();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	
}
