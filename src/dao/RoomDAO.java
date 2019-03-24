package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Room;
import factory.ConnectionFactory;

public class RoomDAO extends DAOAbstract<Room> {

	@Override
	public List<Room> getAll() {
		List<Room> list = new ArrayList<>();
		String sql = "select * from Rooms";
		try {
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				list.add(new Room(rs.getInt(1), rs.getString(2), 
						rs.getString(3), rs.getInt(4)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public boolean insert(Room t) throws Exception {
		String sql = "insert into Rooms(Position, CategoryID, Status) values(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, t.getPosition());
		ps.setInt(2, t.getCategoryID());
		ps.setString(3, t.getStatus());
		return ps.executeUpdate() > 0;
	}
	
	@Override
	public boolean update(Room t) throws Exception {
		String sql = "update Rooms set Status = ?, Position = ?, CategoryID = ? where RoomID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, t.getStatus());
		ps.setString(2, t.getPosition());
		ps.setInt(3, t.getCategoryID());
		ps.setInt(4, t.getRoomID());
		return ps.executeUpdate() > 0;
	}
	
	@Override
	public boolean delete(int id) throws Exception {
		String sql = "delete Rooms where RoomID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		return ps.executeUpdate() > 0;
	}
	
	public boolean updateStatus(int roomID, String status) throws Exception {
		String sql = "update Rooms set Status = ? where RoomID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, status);
		ps.setInt(2, roomID);
		return ps.executeUpdate() > 0;
	}
	
	/* RETURN EMPTY ROOMS 
	 * 
	 * check CheckoutDate must reduce to 1 day
	 * because customer return room on that day
	 * and another customer will booking on that day
	 * 
	select * from Rooms
	where CategoryID = 1 and RoomID not in (
		select r.RoomID
		from Rooms r join ReservationDetails rd
		on r.RoomID = rd.RoomID
		join Reservations rs
		on rs.ReservationID = rd.ReservationID
		where '2019-02-14' between rs.CheckinDate and DATEADD(dd,-1, rs.CheckoutDate)
	)
	 */
	
	public List<Room> getEmptyRooms(int categoryID, Date checkinDate) {
		java.util.Date utilCheckinDate = checkinDate;
		java.sql.Date sqlCheckinDate = new java.sql.Date(utilCheckinDate.getTime());
		List<Room> list = new ArrayList<>();
		String sql = "select * from Rooms " +
					"where CategoryID = " + categoryID + 
					" and RoomID not in (" +
						"select r.RoomID " +
						"from Rooms r join BookingDetails bd " +
						"on r.RoomID = bd.RoomID " +
						"where '" + sqlCheckinDate + 
						"' between bd.CheckinDate and DATEADD(dd,-1, bd.CheckoutDate))";
		try {
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				list.add(new Room(rs.getInt(1), rs.getString(2), 
						rs.getString(3), rs.getInt(4)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private String getDateFormat(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}
	
}
