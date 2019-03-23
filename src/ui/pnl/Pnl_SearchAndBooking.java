package ui.pnl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.CategoryDAO;
import dao.CustomerDAO;
import dao.BookingDAO;
import dao.BookingDetailDAO;
import dao.RoomDAO;
import entity.Category;
import entity.Customer;
import entity.Account;
import entity.Booking;
import entity.BookingDetail;
import entity.Room;
import ui.component.BoxComponent;
import ui.pnl.component.Pnl_Booking;
import ui.pnl.component.Pnl_CreateCustomer;
import ui.pnl.component.Pnl_SearchRoom;

public class Pnl_SearchAndBooking extends JPanel implements ActionListener {

	private Pnl_Booking pnl_booking;
	private Pnl_SearchRoom pnl_search;
	private Pnl_CreateCustomer pnl_CreateCustomer;

	// Search
	private JButton btn_search, btn_search_delete, btn_add;
	private JDateChooser dcs_checkin, dcs_checkout;
	private JComboBox<Category> cbx_categories;
	private JTable tbl_selected_rooms;
	private DefaultTableModel tbl_model_selected_rooms;
	private JPanel pnl_empty_rooms;

	// Booking
	private JTextField txt_keywords, txt_name, txt_subtotal;
	private JButton btn_booking_delete, btn_booking, btn_check;
	private JTable tbl_reservations;
	private DefaultTableModel tbl_model_reservations;
	
	// Create Customer
	private JTextField txt_first_name, txt_middle_name,
		txt_last_name, txt_address, txt_person_code,
		txt_customer_phone, txt_customer_email;
	private JDateChooser dcs_date_of_birth;
	private JRadioButton rb_male, rb_female;
	private JButton btn_customer_add;
	
	// DAO
	private CategoryDAO cdao;
	private RoomDAO rdao;
	private BookingDAO bdao;
	private BookingDetailDAO bddao;
	private CustomerDAO cudao;

	// LIST
	private List<Category> categories;
	private List<Room> rooms;
	private List<Room> emptyRooms;
	private List<BookingDetail> bookingDetails = new ArrayList<>();
	
	// ----
	private Customer customer = null;
	private Account account;

	public Pnl_SearchAndBooking(Account account) {
		setLayout(new BorderLayout());
		this.account = account;
		init();
		gui();
		getCategories();
//		getRooms();
	}

	private void init() {
		// DAO
		cdao = new CategoryDAO();
		rdao = new RoomDAO();
		bdao = new BookingDAO();
		bddao = new BookingDetailDAO();
		cudao = new CustomerDAO();
		
		// LIST
		categories = cdao.getAll();
		rooms = rdao.getAll();

		// Search
		pnl_search = new Pnl_SearchRoom();
			// Set
		dcs_checkin = pnl_search.getDcs_checkin();
		dcs_checkin.setDate(new Date());
		dcs_checkout = pnl_search.getDcs_checkout();
		dcs_checkout.setDate(new Date(dcs_checkin.getDate().getTime() + (1000 * 60 * 60 * 24)));

		cbx_categories = pnl_search.getCbx_categories();

		btn_add = pnl_search.getBtn_add();
		btn_add.addActionListener(this);
		btn_search_delete = pnl_search.getBtn_delete();
		btn_search_delete.addActionListener(this);
		btn_search = pnl_search.getBtn_search();
		btn_search.addActionListener(this);

		tbl_selected_rooms = pnl_search.getTbl_selected_rooms();
		tbl_model_selected_rooms = pnl_search.getTbl_model_selected_rooms();

		pnl_empty_rooms = pnl_search.getPnl_empty_rooms();

		// Booking
		pnl_booking = new Pnl_Booking();
			// Set
		txt_keywords = pnl_booking.getTxt_phone();
		txt_name = pnl_booking.getTxt_name();
		txt_name.setEditable(false);
		txt_subtotal = pnl_booking.getTxt_subtotal();
		txt_subtotal.setEditable(false);

		btn_booking_delete = pnl_booking.getBtn_delete();
		btn_booking_delete.addActionListener(this);
		btn_booking = pnl_booking.getBtn_booking();
		btn_booking.addActionListener(this);
		btn_check = pnl_booking.getBtn_check();
		btn_check.addActionListener(this);

		tbl_model_reservations = pnl_booking.getTbl_model_reservations();
		tbl_reservations = pnl_booking.getTbl_reservations();

		// Create Customer
		pnl_CreateCustomer = new Pnl_CreateCustomer();
		txt_first_name = pnl_CreateCustomer.getTxt_first_name();
		txt_middle_name = pnl_CreateCustomer.getTxt_middle_name();
		txt_last_name = pnl_CreateCustomer.getTxt_last_name();
		txt_address = pnl_CreateCustomer.getTxt_address();
		txt_customer_email = pnl_CreateCustomer.getTxt_email();
		txt_person_code = pnl_CreateCustomer.getTxt_person_code();
		txt_customer_phone = pnl_CreateCustomer.getTxt_phone();
		dcs_date_of_birth = pnl_CreateCustomer.getDcs_date_of_birth();
		rb_female = pnl_CreateCustomer.getRb_female();
		rb_male = pnl_CreateCustomer.getRb_male();
		btn_customer_add = pnl_CreateCustomer.getBtn_add();
		btn_customer_add.addActionListener(this);
		
	}

	private void gui() {
		Box bv = BoxComponent.getVerticalBox(pnl_search, pnl_booking, pnl_CreateCustomer, 10);
		Box bh = BoxComponent.getHorizontalBox(bv, 10);
		add(bh);
	}

	private void getCategories() {
		categories = cdao.getAll();
		for (Category c : categories) {
			cbx_categories.addItem(c);
		}
	}

	private void getRooms() {
		rooms = rdao.getAll();
		int count = 1;
		for (Room r : rooms) {
			System.out.println(count);
			pnl_empty_rooms.add(pnl_search.getButton(count, r.getRoomID() + ""));
			count++;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		Category c = (Category) cbx_categories.getSelectedItem();
		Date checkinDate = dcs_checkin.getDate();
		Date checkoutDate = dcs_checkout.getDate();
		if (o.equals(btn_search)) {
			searchRoom(checkinDate, checkoutDate, c);
		} 
		else if (o.equals(btn_search_delete)) {
			deleteItemInEmptyRooms();
		} else if (o.equals(btn_add)) {
			addItemToReservations();
		} else if (o.equals(btn_booking_delete)) {
			deleteItemInReversations();
		} 
		else if(o.equals(btn_booking)) {
			book();
		}
		else if(o.equals(btn_check)) {
			checkPhoneOfCustomer();
		}
		else if(o.equals(btn_customer_add)) {
			createCustomer();
		}
	}

	private Customer getCustomer(String keywords) {
		Customer cust = null;
		for(Customer c : cudao.getAll()) {
			if(keywords.equals(c.getPersonCode()) || keywords.equals(c.getPhone())) {
				cust = c;
			}
		}
		return cust;
	}
	
	private void book() {
		if(txt_name.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Làm ơn nhập số điện thoại để tìm ra khách hàng!");
		}
		else if(tbl_reservations.getRowCount() == 0)
			JOptionPane.showMessageDialog(null, "Danh sách hiện đang trống!");
		else {
				int quantityRoom = 0; 
				int quantityCategory = 0;
				for (int i = 0; i < tbl_model_reservations.getRowCount(); i++) {
					int q = Integer.parseInt(tbl_model_reservations.getValueAt(i, 1).toString());
					quantityCategory++;
					quantityRoom += q;
				}
				
				if(customer == null) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập khách hàng!");
				}
				else {
					int customerID = customer.getCustomerID();
					double subTotal = Double.parseDouble(txt_subtotal.getText());
					String personCode = customer.getPersonCode();
					Booking booking = new Booking(quantityRoom, quantityCategory, subTotal, personCode, customerID, "Booking");
					
					try {
						bdao.insert(booking);
						
						Booking b = bdao.getAll().get(bdao.getAll().size() - 1);
						int bookingID = b.getBookingID();
						
						for(BookingDetail bd : bookingDetails) {
							int roomID = bd.getRoomID();
							rdao.updateStatus(roomID, "Booked");
							
							bd.setBookingID(bookingID);
							bddao.insert(bd);
						}
						
						tbl_model_reservations.setRowCount(0);
						tbl_model_selected_rooms.setRowCount(0);
						JOptionPane.showMessageDialog(null, "Đặt phòng thành công!");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		}
	}

	private String getDateFormat(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}

	private int daysBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}
	
	private boolean isExistInSelectedRooms(int roomID) {
		for (int i = 0; i < tbl_selected_rooms.getRowCount(); i++) {
			int id = Integer.parseInt(tbl_model_selected_rooms.getValueAt(i, 0).toString());
			if (id == roomID)
				return true;
		}
		return false;
	}

	private int findRoom(int roomID) {
		int count = 1;
		for (Room r : rooms) {
			if (r.getRoomID() == roomID)
				break;
			count++;
		}
		return count;
	}

	private int getIndexExistInReservations(String category, String checkinDate, String checkoutDate) {
		int num = 0;
		for (int i = 0; i < tbl_reservations.getRowCount(); i++) {
			String name = tbl_model_reservations.getValueAt(i, 0).toString().trim();
			String checkin = tbl_model_reservations.getValueAt(i, 2).toString().trim();
			String checkout = tbl_model_reservations.getValueAt(i, 3).toString().trim();
			
			if (name.equals(category) && checkinDate.equals(checkin) && checkoutDate.equals(checkout)) {
				num = i;
				return num;
			}
		}
		return num;
	}
	
	private boolean isExistInReservations(String category, String checkinDate, String checkoutDate) {
		for (int i = 0; i < tbl_reservations.getRowCount(); i++) {
			String name = tbl_model_reservations.getValueAt(i, 0).toString().trim();
			String checkin = tbl_model_reservations.getValueAt(i, 2).toString().trim();
			String checkout = tbl_model_reservations.getValueAt(i, 3).toString().trim();
			
			if (name.equals(category) && checkinDate.equals(checkin) && checkoutDate.equals(checkout)) {
				return true;
			}
		}
		return false;
	}
	
	private int getCategoryID(String categoryName) {
		int categoryID = 0;
		for(Category category : categories) {
			if(category.getCategoryName().equals(categoryName)) {
				categoryID = category.getCategoryID();
			}
		}
		return categoryID;
	}
	
	private List<Integer> getListRoomID(String categoryName, String checkinDate, String checkoutDate) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < tbl_selected_rooms.getRowCount(); i++) {
			int roomID = Integer.parseInt(tbl_selected_rooms.getValueAt(i, 0).toString());
			String name = tbl_selected_rooms.getValueAt(i, 1).toString().trim();
			String checkin = tbl_selected_rooms.getValueAt(i, 2).toString().trim();
			String checkout = tbl_selected_rooms.getValueAt(i, 3).toString().trim();
			
			if (name.equals(categoryName) && checkinDate.equals(checkin) && checkoutDate.equals(checkout)) {
				list.add(roomID);
			}
		}
		return list;
	}
	
	private void searchRoom(Date checkinDate, Date checkoutDate, Category c) {
		pnl_empty_rooms.removeAll();
		this.revalidate();
		this.repaint();
		emptyRooms = rdao.getEmptyRooms(c.getCategoryID(), checkinDate);
		int daysBetween = daysBetween(checkinDate, checkoutDate);
		if (daysBetween > 0) {
			int count = 1;
			for (Room i : emptyRooms) {
				JButton btn = pnl_search.getButton(findRoom(i.getRoomID()), i.getRoomID() + "");
				btn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						int id = i.getRoomID();
						if (tbl_selected_rooms.getRowCount() != 0) {
							if (!isExistInSelectedRooms(id)) {
								Object[] row = { id + "", c.toString(), getDateFormat(dcs_checkin.getDate()),
										getDateFormat(dcs_checkout.getDate()), 
										c.getPrice(), c.getDiscount() };
								tbl_model_selected_rooms.addRow(row);
							} else
								JOptionPane.showMessageDialog(null, "Phòng " + id + " đã có trong danh sách!");
						} else {
							Object[] row = { id + "", c.toString(), getDateFormat(dcs_checkin.getDate()),
									getDateFormat(dcs_checkout.getDate()), c.getPrice(), c.getDiscount() };
							tbl_model_selected_rooms.addRow(row);
						}
					}
				});
				pnl_empty_rooms.add(btn);
				count++;
			}
			this.revalidate();
		} else
			JOptionPane.showMessageDialog(null, "Ngày trả phòng phải sau ngày nhận phòng ít nhất một ngày!");
	}
	
	private void deleteItemInEmptyRooms() {
		int[] selectedRow = tbl_selected_rooms.getSelectedRows();
		if (tbl_model_selected_rooms.getRowCount() != 0) {
			for (int i = tbl_selected_rooms.getRowCount(); i >= 0; i--) {
				for (int j : selectedRow) {
					if (i == j) {
						tbl_model_selected_rooms.removeRow(i);
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Danh sách hiện đang trống!");
		}
	}
	
	private void addItemToReservations() {
		bookingDetails.clear();
		if(tbl_selected_rooms.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "Danh sách hiện đang trống!");
		}
		else {
			tbl_model_reservations.setRowCount(0);
			String categoryName = "";
			String checkin = "", checkout = "";
			int roomID = 0;
			double price = 0, subtotal = 0;
			float discount = 0;
			for (int i = 0; i < tbl_selected_rooms.getRowCount(); i++) {
				roomID = Integer.parseInt(tbl_model_selected_rooms.getValueAt(i, 0).toString());
				categoryName = tbl_model_selected_rooms.getValueAt(i, 1).toString();
				checkin = tbl_model_selected_rooms.getValueAt(i, 2).toString();
				checkout = tbl_model_selected_rooms.getValueAt(i, 3).toString();
				price = Double.parseDouble(tbl_model_selected_rooms.getValueAt(i, 4).toString());
				discount = Float.parseFloat(tbl_model_selected_rooms.getValueAt(i, 5).toString());
				
				int categoryID = getCategoryID(categoryName);
				Date checkinDate = null, checkoutDate = null;
				try {
					checkinDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkin);
					checkoutDate =new SimpleDateFormat("dd/MM/yyyy").parse(checkout);  
				} catch (ParseException e) {
					e.printStackTrace();
				} 
				bookingDetails.add(new BookingDetail(checkinDate, checkoutDate, price, discount, 0, roomID, categoryID));
				
				int indexExist = getIndexExistInReservations(categoryName, checkin, checkout);
				if(tbl_reservations.getRowCount() == 0) {
					Object[] row = {
						categoryName, 1,
						checkin, checkout,
						price, discount
					};
					tbl_model_reservations.addRow(row);
				}
				else {
					if(isExistInReservations(categoryName, checkin, checkout)) {
						int count = Integer.parseInt(tbl_model_reservations.getValueAt(indexExist, 1).toString());
						double p = Double.parseDouble(tbl_model_reservations.getValueAt(indexExist, 4).toString());
						float d = Float.parseFloat(tbl_model_reservations.getValueAt(indexExist, 5).toString());
						
						count += 1;
						price += p;
						discount += d;
						tbl_model_reservations.setValueAt(count, indexExist, 1);
						tbl_model_reservations.setValueAt(price, indexExist, 4);
						tbl_model_reservations.setValueAt(discount, indexExist, 5);
					}
					else {
						Object[] row = {
							categoryName, 1,
							checkin, checkout,
							price, discount
						};
						tbl_model_reservations.addRow(row);
					}
				}
				
			}
			for(int i = 0; i < tbl_model_reservations.getRowCount(); i++) {
				price = Double.parseDouble(tbl_model_reservations.getValueAt(i, 4).toString());
				discount = Float.parseFloat(tbl_model_reservations.getValueAt(i, 5).toString());
				subtotal += price * (1 - discount);
				txt_subtotal.setText(subtotal + "");
			}
		}
	}
	
	private void deleteItemInReversations() {
		int[] selectedRow = tbl_reservations.getSelectedRows();
		if (tbl_model_reservations.getRowCount() != 0) {
			for (int i = tbl_model_reservations.getRowCount(); i >= 0; i--) {
				for (int j : selectedRow) {
					if (i == j) {
						tbl_model_reservations.removeRow(i);
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Danh sách hiện đang trống!");
		}
	}
	
	private void checkPhoneOfCustomer() {
		if(!txt_keywords.getText().trim().equals("")) {
			String keywords = txt_keywords.getText();
			customer = getCustomer(keywords);
			
			if(customer == null) {
				int answer = JOptionPane.showConfirmDialog(null,
						"Khách hàng này hiện không có trong danh sách, bạn có muốn tạo mới khách hàng không?", "Thêm mới khách hàng",
						JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					txt_first_name.setEnabled(true);
					txt_middle_name.setEnabled(true);
					txt_last_name.setEnabled(true);
					txt_first_name.setEnabled(true);
					rb_female.setEnabled(true);
					rb_male.setEnabled(true);
					dcs_date_of_birth.setEnabled(true);
					txt_customer_phone.setEnabled(true);
					txt_customer_email.setEnabled(true);
					txt_address.setEnabled(true);
					btn_customer_add.setEnabled(true);
					txt_person_code.setEnabled(true);
					dcs_date_of_birth.setEnabled(true);
				}
			}
			else {
				String customerName = customer.getFirstName() + " " + customer.getMiddleName() + " " + customer.getLastName();
				txt_name.setText(customerName);
			}
		}
		else 
			JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống!");
	}
	
	private void createCustomer() {
		String firstName = txt_first_name.getText().trim();
		String middleName = txt_middle_name.getText().trim();
		String lastName = txt_last_name.getText().trim();
		String email = txt_customer_email.getText().trim();
		String address = txt_address.getText().trim();
		String personCode = txt_person_code.getText().trim();
		String gender = "";
		if(rb_female.isSelected()) {
			gender = "Female";
		}
		else {
			gender = "Male";
		}
		Date dateOfBirth = dcs_date_of_birth.getDate();
		String phone = txt_customer_phone.getText().trim();
		
		Customer c = new Customer(firstName, middleName, lastName, gender, dateOfBirth, phone, address, email, null, personCode);
		try {
			cudao.insert(c);
			JOptionPane.showMessageDialog(null, "Thêm mới khách hàng thành công!");
			txt_name.setText(c.getFirstName() + " " + c.getMiddleName() + " " + c.getLastName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
