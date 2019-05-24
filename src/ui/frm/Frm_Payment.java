package ui.frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.BookingDAO;
import dao.BookingDetailDAO;
import dao.PaymentDAO;
import dao.PaymentDetailDAO;
import dao.RoomDAO;
import dao.ServiceDAO;
import entity.Account;
import entity.Booking;
import entity.BookingDetail;
import entity.Payment;
import entity.PaymentDetail;
import entity.Service;
import ui.component.BoxComponent;
import ui.service.ValidationService;

public class Frm_Payment extends JFrame implements ActionListener {

	private JLabel lbl_booking_id, lbl_service_name, lbl_service_quantity, lbl_rental_price, lbl_subtotal, lbl_title;
	private JTextField txt_booking_id, txt_service_quantiy, txt_rental_price, txt_subtotal;
	private JComboBox<Service> cbx_services;
	private JButton btn_pay, btn_delete, btn_add, btn_cancel;

	private JPanel pnl_services;

	private JTable tbl_services;
	private DefaultTableModel tbl_model_services;
	private JScrollPane jsp_services;

	private int bookingID;
	private double rentalPrice;
	private int quantityService = 0;
	private double subtotal, servicePrice = 0;

	private Font fontSan = new Font("Arial", Font.BOLD, 18);

	private ServiceDAO sdao = new ServiceDAO();
	private PaymentDAO pdao = new PaymentDAO();
	private PaymentDetailDAO pddao = new PaymentDetailDAO();
	private BookingDAO bdao = new BookingDAO();
	private BookingDetailDAO bddao = new BookingDetailDAO();
	private RoomDAO rdao = new RoomDAO();

	private Account account;

	public Frm_Payment(int bookingID, double rentalPrice, Account account) {
		setTitle("Thanh toán hóa đơn - ^^!");
		setSize(600, 600);
		setResizable(false);
		setLocationRelativeTo(null); // canh giua
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.bookingID = bookingID;
		this.rentalPrice = rentalPrice;
		this.account = account;
		init();
		gui();
		getData();
	}

	private void getData() {
		try {
			for (Service s : sdao.getAll()) {
				cbx_services.addItem(s);
			}

			Payment p = isPay();
			if (p != null) {
				txt_subtotal.setText(p.getSubTotal() + "");
				for (PaymentDetail pd : pddao.getAll()) {
					if (p.getPaymentID() == pd.getPaymentID()) {
						Service s = getService(pd.getServiceID());
						if (s != null) {
							Object[] row = { s.getServiceID(), s.getServiceName(), pd.getPrice(), pd.getQuantity(),
									pd.getSubtotal() };
							tbl_model_services.addRow(row);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Service getService(int serviceID) {
		Service service = null;
		try {
			for (Service s : sdao.getAll()) {
				if (s.getServiceID() == serviceID) {
					service = s;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return service;
	}

	private void init() {
		// Jpanel
		pnl_services = new JPanel(new BorderLayout());
		pnl_services.setBorder(BorderFactory.createTitledBorder(null, "Danh sách dịch vụ đã chọn:", TitledBorder.LEFT,
				TitledBorder.TOP, fontSan, Color.MAGENTA));
		pnl_services.setPreferredSize(new Dimension(500, 300));

		// JLabel
		lbl_title = new JLabel(new ImageIcon("imgs/ic_cash.png"));

		lbl_service_name = new JLabel("Dịch vụ:");
		lbl_booking_id = new JLabel("Mã đơn đặt:");
		lbl_service_quantity = new JLabel("Số lượng dịch vụ:");
		lbl_subtotal = new JLabel("Tổng tiền:");
		lbl_rental_price = new JLabel("Tiền phòng:");

		lbl_service_name.setPreferredSize(lbl_booking_id.getPreferredSize());
		lbl_rental_price.setPreferredSize(lbl_service_quantity.getPreferredSize());

		// JTextField
		txt_booking_id = new JTextField();
		txt_booking_id.setText(bookingID + "");
		txt_booking_id.setEditable(false);
		txt_booking_id.setPreferredSize(new Dimension(70, txt_booking_id.getPreferredSize().height));
		txt_service_quantiy = new JTextField();
		txt_service_quantiy.setText(quantityService + "");
		txt_rental_price = new JTextField();
		txt_rental_price.setEditable(false);
		txt_rental_price.setText(rentalPrice + "");
		txt_subtotal = new JTextField();
		subtotal = rentalPrice + servicePrice;
		txt_subtotal.setText(subtotal + "");
		txt_subtotal.setEditable(false);

		// ComboBox
		cbx_services = new JComboBox<>();
		cbx_services.setPreferredSize(new Dimension(160, cbx_services.getPreferredSize().height));

		// JButton
		btn_pay = new JButton(new ImageIcon(this.getClass().getResource("/ic_pay.png")));
		btn_pay.setMargin(new Insets(0, 0, 0, 0));
		btn_pay.setBorder(null);
		btn_pay.addActionListener(this);

		btn_cancel = new JButton(new ImageIcon(this.getClass().getResource("/ic_cancel.png")));
		btn_cancel.setMargin(new Insets(0, 0, 0, 0));
		btn_cancel.setBorder(null);
		btn_cancel.addActionListener(this);

		btn_delete = new JButton(new ImageIcon(this.getClass().getResource("/ic_delete.png")));
		btn_delete.setMargin(new Insets(0, 0, 0, 0));
		btn_delete.setBorder(null);
		btn_delete.addActionListener(this);

		btn_add = new JButton(new ImageIcon(this.getClass().getResource("/ic_add.png")));
		btn_add.setMargin(new Insets(0, 0, 0, 0));
		btn_add.setBorder(null);
		btn_add.addActionListener(this);

		// JTable
		String[] header = { "Mã dịch vụ", "Tên dịch vụ", "Đơn giá", "Số lượng", "Tổng tiền" };
		tbl_services = new JTable(tbl_model_services = new DefaultTableModel(header, 0)) {
			private static final long serialVersionUID = 1L;

			/*
			 * @Override public Class getColumnClass(int column) { return getValueAt(0,
			 * column).getClass(); }
			 */
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Double.class;
				case 3:
					return String.class;
				default:
					return Double.class;
				}
			}
		};
		tbl_services.setRowHeight(25);
		DefaultTableCellRenderer dtbl_cell_render = new DefaultTableCellRenderer();
		dtbl_cell_render.setHorizontalAlignment(SwingConstants.CENTER);
		tbl_services.setDefaultRenderer(String.class, dtbl_cell_render);

		jsp_services = new JScrollPane(tbl_services);
		jsp_services.setPreferredSize(new Dimension(jsp_services.getPreferredSize().width, 100));

	}

	private void gui() {
		Box b_title = BoxComponent.getHorizontalBox(lbl_title, 10);
		Box b_booking_id = BoxComponent.getHorizontalBox(lbl_booking_id, txt_booking_id, 10);
		Box b_service_name = BoxComponent.getHorizontalBox(lbl_service_name, cbx_services, 10);
		Box b_service_quantity = BoxComponent.getHorizontalBox(lbl_service_quantity, txt_service_quantiy, 10);
		Box b_rental_room = BoxComponent.getHorizontalBox(lbl_rental_price, txt_rental_price, 10);
		Box b_subtotal = BoxComponent.getHorizontalBox(lbl_subtotal, txt_subtotal, 10);
		Box b_pay = BoxComponent.getHorizontalBox(btn_pay, 10);

		Box b1 = BoxComponent.getHorizontalBox(b_booking_id, b_rental_room, 20);
		Box b2 = BoxComponent.getHorizontalBox(b_service_name, b_service_quantity, 20);
		Box b3 = Box.createHorizontalBox();
		b3.add(Box.createHorizontalStrut(480));
		b3.add(Box.createHorizontalStrut(10));
		b3.add(btn_add);
		b3.add(Box.createHorizontalStrut(10));
		b3.add(btn_delete);
		b3.add(Box.createHorizontalStrut(10));
		b3.add(Box.createHorizontalStrut(10));
		pnl_services.add(jsp_services);
		Box b4 = BoxComponent.getHorizontalBox(pnl_services, 10);
		Box b5 = BoxComponent.getHorizontalBox(b_subtotal, 20);
		Box b6 = Box.createHorizontalBox();
		b6.add(Box.createHorizontalStrut(470));
		b6.add(btn_pay);
		b6.add(Box.createHorizontalStrut(10));
		b6.add(btn_cancel);
		b6.add(Box.createHorizontalStrut(10));

		Box b = Box.createVerticalBox();
		b.add(Box.createVerticalStrut(30));
		b.add(b_title);
		b.add(Box.createVerticalStrut(40));
		b.add(b1);
		b.add(Box.createVerticalStrut(10));
		b.add(b2);
		b.add(Box.createVerticalStrut(10));
		b.add(b3);
		b.add(Box.createVerticalStrut(10));
		b.add(b4);
		b.add(Box.createVerticalStrut(10));
		b.add(b5);
		b.add(Box.createVerticalStrut(10));
		b.add(b6);
		b.add(Box.createVerticalStrut(30));

		this.add(b);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btn_add)) {
			if(txt_service_quantiy.getText().trim().equals("0")) {
				JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0");
			}
			else if(ValidationService.validateQuantity(txt_service_quantiy.getText())) {
				addService();
			}
		} else if (o.equals(btn_delete)) {
			int answer = JOptionPane.showConfirmDialog(null,
					"Bạn có thực sự muốn xóa dịch vụ?", "Xóa dịch vụ",
					JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION) {
				try {
					deleteService();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else if (o.equals(btn_cancel)) {
			Frm_ManageHotel_Admin frm = new Frm_ManageHotel_Admin(account);
			frm.setVisible(true);
			this.dispose();
		} else if (o.equals(btn_pay)) {
			int quantityService = 0, servicePrice = 0;
			int answer = JOptionPane.showConfirmDialog(null, "Khách hàng thực sự muốn thanh toán?",
					"Ghi nhận thanh toán", JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION) {
				pay();
				JOptionPane.showMessageDialog(null, "Thanh toán thành công!");
				Frm_ManageHotel_Admin frm = new Frm_ManageHotel_Admin(account);
				frm.setVisible(true);
				this.dispose();
			}
		}
	}

	/*
	 * Nếu đã thanh toán + đã sd dịch vụ => sử dụng thêm dịch vụ Kiểm tra
	 * PaymentDetails => nếu có mã dịch vụ đó rồi => update ==> Nếu chưa có dịch vụ
	 * đó => insert
	 */
	private void pay() {
		try {
			Payment payment = isPay();
			int paymentID = 0;
			if (isPay() != null) {
				payment.setServiceQuantity(quantityService);
				payment.setRentalPrice(rentalPrice);
				payment.setServicePrice(servicePrice);
				payment.setSubTotal(subtotal);
				paymentID = payment.getPaymentID();
				pdao.update(payment);
			} else {
				payment = new Payment(quantityService, servicePrice, rentalPrice, subtotal, bookingID);
				pdao.insert(payment);
				Payment p = pdao.getAll().get(pdao.getAll().size() - 1);
				paymentID = p.getPaymentID();
			}

			updatePaymentDetail(paymentID);

			bdao.updateStatus(bookingID, "Đã thanh toán");
			for (BookingDetail bd : bddao.getAll()) {
				if (bd.getBookingID() == bookingID) {
					rdao.updateStatus(bd.getRoomID(), "Trống");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updatePaymentDetail(int paymentID) {
		Payment p = isPay();
		int serviceID = 0, quantity = 0;
		double price = 0, sub = 0;
		if (tbl_model_services.getRowCount() != 0) {
			for (int i = 0; i < tbl_model_services.getRowCount(); i++) {
				serviceID = Integer.parseInt(tbl_model_services.getValueAt(i, 0).toString());
				quantity = Integer.parseInt(tbl_model_services.getValueAt(i, 3).toString());
				price = Double.parseDouble(tbl_model_services.getValueAt(i, 2).toString());
				sub = Double.parseDouble(tbl_model_services.getValueAt(i, 4).toString());

				try {
					if (!isPaymentDetail(p.getPaymentID(), serviceID, price, quantity, sub)) {
						PaymentDetail pd = new PaymentDetail(serviceID, paymentID, quantity, price, sub);
						pddao.insert(pd);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	private boolean isPaymentDetail(int paymentID, int serviceID, double price, int quantity, double sub) {
		try {
			for (PaymentDetail pd : pddao.getAll()) {
				if (pd.getPaymentID() == paymentID && pd.getServiceID() == serviceID) {
					pd.setPrice(price);
					pd.setQuantity(quantity);
					pd.setSubtotal(sub);
					pd.setServiceID(serviceID);
					pddao.update(pd);
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private Payment isPay() {
		Payment payment = null;
		try {
			for (Payment p : pdao.getAll()) {
				if (p.getBookingID() == bookingID) {
					payment = p;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return payment;
	}

	private void deleteService() {
		int[] selectedRow = tbl_services.getSelectedRows();
		if (tbl_model_services.getRowCount() != 0) {
			for (int i = tbl_services.getRowCount(); i >= 0; i--) {
				for (int j : selectedRow) {
					if (i == j) {
						double price = Double.parseDouble(tbl_model_services.getValueAt(i, 4).toString());
						int quantity = Integer.parseInt(tbl_model_services.getValueAt(i, 3).toString());
						servicePrice -= price;
						quantityService -= quantity;
						System.out.println(servicePrice + " " + quantityService);
						subtotal = rentalPrice + servicePrice;
						txt_subtotal.setText(subtotal + "");
						tbl_model_services.removeRow(i);
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Danh sách hiện đang trống!");
		}
	}

	private void addService() {
		Service s = (Service) cbx_services.getSelectedItem();
		int quantity = Integer.parseInt(txt_service_quantiy.getText().trim());
		int index = getIndexServiceInList(s);
		if (index >= 0) {
			int q = Integer.parseInt(tbl_model_services.getValueAt(index, 3).toString());
			double price = Double.parseDouble(tbl_model_services.getValueAt(index, 2).toString());
			tbl_model_services.setValueAt(q + quantity, index, 3);
			tbl_model_services.setValueAt((q + quantity) * price, index, 4);
		} else {
			Object[] row = { s.getServiceID(), s.getServiceName(), s.getPrice(), quantity, s.getPrice() * quantity };
			tbl_model_services.addRow(row);
		}

		servicePrice = 0;
		quantityService = 0;
		for (int i = 0; i < tbl_services.getRowCount(); i++) {
			servicePrice += Double.parseDouble(tbl_model_services.getValueAt(i, 4).toString());
			quantityService += Integer.parseInt(tbl_model_services.getValueAt(i, 3).toString());
		}
		subtotal = rentalPrice + servicePrice;
		txt_subtotal.setText(subtotal + "");
	}

	private int getIndexServiceInList(Service s) {
		int index = -1;
		for (int i = 0; i < tbl_services.getRowCount(); i++) {
			String id = tbl_model_services.getValueAt(i, 0).toString();
			if (id.equals(s.getServiceID() + "")) {
				index = i;
			}
		}
		return index;
	}

}
