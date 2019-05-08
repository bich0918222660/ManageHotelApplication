package ui.pnl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.BookingDAO;
import dao.BookingDetailDAO;
import dao.CategoryDAO;
import dao.CustomerDAO;
import dao.RoomDAO;
import entity.Category;
import entity.Customer;
import entity.Room;
import ui.component.BoxComponent;

public class Pnl_ReportStatusCategory extends JPanel implements ActionListener {

	private JLabel lbl_category, lbl_checkin, lbl_checkout, lbl_category_id, lbl_price, lbl_discount, lbl_type;
	private JTextField txt_category_id, txt_price, txt_discount, txt_type;
	private JDateChooser dcs_date;
	private JComboBox<Category> cbx_categories;
	private JButton btn_report;
	private JTable tbl_empty, tbl_rented, tbl_booked;
	private JScrollPane jsp_empty, jsp_rented, jsp_booked;
	private DefaultTableModel tbl_model_empty, tbl_model_rented, tbl_model_booked;
	private JPanel pnl_empty, pnl_rented, pnl_booked;
	
	private Font fontSan = new Font("Arial", Font.BOLD, 18);
	
	private CategoryDAO cdao = new CategoryDAO();
	private RoomDAO rdao = new RoomDAO();
	private CustomerDAO cudao = new CustomerDAO();
	
	public Pnl_ReportStatusCategory() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(null, "Báo cáo trạng thái loại phòng:", TitledBorder.LEFT, TitledBorder.TOP, fontSan, Color.MAGENTA));
		init();
		gui();
		setEditable();
	}
	
	private void setEditable() {
		txt_category_id.setEditable(false);
		txt_discount.setEditable(false);
		txt_price.setEditable(false);
		txt_type.setEditable(false);
	}
	
	private void init() {
		// JLabel 
		lbl_category = new JLabel("Loại phòng:");
		lbl_checkin = new JLabel("Ngày nhận phòng:");
		lbl_category_id = new JLabel("Mã loại:");
		lbl_discount = new JLabel("Giảm giá:");
		lbl_checkout = new JLabel("Ngày trả phòng:");
		lbl_price = new JLabel("Đơn giá:");
		lbl_type = new JLabel("Thuộc:");
		
		lbl_type.setPreferredSize(lbl_discount.getPreferredSize());
		lbl_category_id.setPreferredSize(lbl_price.getPreferredSize());
		
		// JDateChooser
		dcs_date = new JDateChooser();
		dcs_date.setDateFormatString("dd/MM/yyyy");
		dcs_date.setDate(new Date());
		
		// JTextField
		txt_category_id = new JTextField();
		txt_discount = new JTextField();
		txt_price = new JTextField();
		txt_type = new JTextField();
		
		// JComboBox
		cbx_categories = new JComboBox<>();
		cbx_categories.setPreferredSize(new Dimension(350, cbx_categories.getPreferredSize().height));
		getListCategory();
		
		// JButton 
		btn_report = new JButton("Báo cáo tình trạng");
		btn_report.addActionListener(this);
		
		// JTable
		String[] header_empty = {
				"Mã phòng", 
				"Tình trạng"
		};
		tbl_empty = new JTable(tbl_model_empty = new DefaultTableModel(header_empty, 0));
		tbl_empty.setRowHeight(25);
		
		String[] header_booking = {
				"Mã phòng", 
				"Tình trạng",
				"Ngày nhận phòng", 
				"Ngày trả phòng", 
				"Khách hàng"
		};
		tbl_booked = new JTable(tbl_model_booked = new DefaultTableModel(header_booking, 0));
		tbl_booked.setRowHeight(25);
		
		String[] header_rented = {
				"Mã phòng", 
				"Tình trạng",
				"Ngày nhận phòng", 
				"Ngày trả phòng", 
				"Khách hàng"
		};
		tbl_rented = new JTable(tbl_model_rented = new DefaultTableModel(header_rented, 0));
		tbl_rented.setRowHeight(25);
		
		// JScrollPane
		jsp_empty = new JScrollPane(tbl_empty);
		jsp_empty.setPreferredSize(new Dimension(jsp_empty.getPreferredSize().width, 150));

		jsp_booked = new JScrollPane(tbl_booked);
		jsp_booked.setPreferredSize(new Dimension(jsp_booked.getPreferredSize().width, 150));

		jsp_rented = new JScrollPane(tbl_rented);
		jsp_rented.setPreferredSize(new Dimension(jsp_rented.getPreferredSize().width, 150));
		
		// JPanel
			// Empty
		pnl_empty = new JPanel(new BorderLayout());
		pnl_empty.setBorder(BorderFactory.createTitledBorder(null, "Còn trống <0 phòng>:", TitledBorder.LEFT, TitledBorder.TOP, fontSan, Color.MAGENTA));
		
			// Booked
		pnl_booked = new JPanel(new BorderLayout());
		pnl_booked.setBorder(BorderFactory.createTitledBorder(null, "Đang được đặt <0 phòng>: ", TitledBorder.LEFT, TitledBorder.TOP, fontSan, Color.MAGENTA));
		
			// Rented
		pnl_rented = new JPanel(new BorderLayout());
		pnl_rented.setBorder(BorderFactory.createTitledBorder(null, "Đang được thuê <0 phòng>", TitledBorder.LEFT, TitledBorder.TOP, fontSan, Color.MAGENTA));
		
	}
	
	private void getListCategory() {
		for(Category c : cdao.getAll()) {
			cbx_categories.addItem(c);
		}
	}

	private void gui() {
		pnl_empty.add(jsp_empty);
		pnl_booked.add(jsp_booked);
		pnl_rented.add(jsp_rented);

		pnl_empty.add(jsp_empty);
		Box b_empty = BoxComponent.getHorizontalBox(pnl_empty, 10);
		
		pnl_booked.add(jsp_booked);
		Box b_booked = BoxComponent.getHorizontalBox(pnl_booked, 10);

		pnl_rented.add(jsp_rented);
		Box b_rented = BoxComponent.getHorizontalBox(pnl_rented, 10);
		
		// top
		Box b_category = BoxComponent.getHorizontalBox(lbl_category, cbx_categories, 10);
		Box b_checkin = BoxComponent.getHorizontalBox(lbl_checkin, dcs_date, 10);
		Box b_category_id = BoxComponent.getHorizontalBox(lbl_category_id, txt_category_id, 10);
		Box b_price = BoxComponent.getHorizontalBox(lbl_price, txt_price, 10);
		Box b_discount = BoxComponent.getHorizontalBox(lbl_discount, txt_discount, 10);
		Box b_type = BoxComponent.getHorizontalBox(lbl_type, txt_type, 10);
		
		Box b1 = BoxComponent.getHorizontalBox_NoPadding(b_category, b_checkin, 20);
		Box b2 = Box.createHorizontalBox();
		b2.add(Box.createHorizontalStrut(780));
		b2.add(btn_report);
		b2.add(Box.createHorizontalStrut(10));
		Box b_left = BoxComponent.getVerticalBox(b_category_id, b_price, 10);
		Box b_right = BoxComponent.getVerticalBox(b_type, b_discount, 10);
		Box b3 = BoxComponent.getHorizontalBox_NoPadding(b_left, b_right, 20);
		Box b4 = BoxComponent.getHorizontalBox(b_empty, 0);
		Box b5 = BoxComponent.getHorizontalBox(b_booked, 0);
		Box b6 = BoxComponent.getHorizontalBox(b_rented, 0);
		
		Box b_full = Box.createVerticalBox();
		b_full.add(Box.createVerticalStrut(10));
		b_full.add(b1);
		b_full.add(Box.createVerticalStrut(10));
		b_full.add(b2);
		b_full.add(Box.createVerticalStrut(10));
		b_full.add(b3);
		b_full.add(Box.createVerticalStrut(10));
		b_full.add(b4);
		b_full.add(Box.createVerticalStrut(10));
		b_full.add(b5);
		b_full.add(Box.createVerticalStrut(10));
		b_full.add(b6);
		b_full.add(Box.createVerticalStrut(10));
		
		add(b_full);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btn_report)) {
			Category c = (Category) cbx_categories.getSelectedItem();
			Date date = dcs_date.getDate();
			getCategoryDetail(c.getCategoryID());
			getRoom(c.getCategoryID(), date);
		}
	}

	private void getRoom(int categoryID, Date date) {
		tbl_model_booked.setRowCount(0);
		tbl_model_rented.setRowCount(0);
		tbl_model_empty.setRowCount(0);
		int emptyRoomAmount = 0;
		int rentedRoomAmount = 0;
		int bookedRoomAmount = 0;
		try {
			bookedRoomAmount = rdao.getRoomByType(tbl_model_booked, "Đang được đặt", categoryID, date);
			rentedRoomAmount = rdao.getRoomByType(tbl_model_rented, "Đã nhận phòng", categoryID, date);
			for(Room r : rdao.getAll()) {
				if(!isExistRooms(r.getRoomID(), "Đang được đặt") && 
						!isExistRooms(r.getRoomID(), "Đã nhận phòng") &&
						r.getCategoryID() == categoryID) {
					emptyRoomAmount++;
					String[] row = {
							r.getRoomID() + "", "Trống"
					};
					tbl_model_empty.addRow(row);
				}
			}
			pnl_empty.setBorder(BorderFactory.createTitledBorder(null, "Còn trống <" + emptyRoomAmount + " phòng>:", TitledBorder.LEFT, TitledBorder.TOP, fontSan, Color.MAGENTA));
			pnl_rented.setBorder(BorderFactory.createTitledBorder(null, "Đang được thuê <" + rentedRoomAmount + " phòng>:", TitledBorder.LEFT, TitledBorder.TOP, fontSan, Color.MAGENTA));
			pnl_booked.setBorder(BorderFactory.createTitledBorder(null, "Đang được đặt <" + bookedRoomAmount + " phòng>:", TitledBorder.LEFT, TitledBorder.TOP, fontSan, Color.MAGENTA));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private boolean isExistRooms(int roomID, String type) {
		if(type.equals("Đang được đặt")) {
			for (int i = 0; i < tbl_model_booked.getRowCount(); i++) {
				int id = Integer.parseInt(tbl_model_booked.getValueAt(i, 0).toString());
				if (id == roomID)
					return true;
			}
		}
		else {
			for (int i = 0; i < tbl_model_rented.getRowCount(); i++) {
				int id = Integer.parseInt(tbl_model_rented.getValueAt(i, 0).toString());
				if (id == roomID)
					return true;
			}
		}
		return false;
	}
	
	private String getCustomerName(int customerID) {
		String name = "";
		for(Customer c : cudao.getAll()) {
			if(c.getCustomerID() == customerID) {
				if(c.getMiddleName().equals("")) {
					name = c.getFirstName() + " " + c.getLastName();
				}
				else {
					name = c.getFirstName() + " " + c.getMiddleName() + " " + c.getLastName();
				}
			}
		}
		return name;
	}
	
	private void getCategoryDetail(int categoryID) {
		for(Category c : cdao.getAll()) {
			if(c.getCategoryID() == categoryID) {
				txt_price.setText("$ " + c.getPrice());
				int discount = (int) c.getDiscount() * 100;
				txt_discount.setText(discount + "%");
				txt_type.setText(c.getType());
				txt_category_id.setText(c.getCategoryID() + "");
			}
		}
	}
	
}
