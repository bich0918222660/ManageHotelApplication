package ui.pnl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.BookingDAO;
import dao.BookingDetailDAO;
import dao.CategoryDAO;
import dao.PaymentDAO;
import dao.PaymentDetailDAO;
import dao.ServiceDAO;
import entity.Account;
import entity.BookingDetail;
import entity.Category;
import entity.Payment;
import entity.PaymentDetail;
import entity.Service;
import ui.component.BoxComponent;

public class Pnl_ManagePayments extends JPanel {
	
	private Font fontSan = new Font("Arial", Font.BOLD, 14);
	
	private JTable tbl_booking, tbl_payment, tbl_detail;
	private DefaultTableModel tbl_model_booking, tbl_model_payment, tbl_model_detail;
	private JScrollPane jsp_booking, jsp_payment, jsp_detail;
	
	private JPanel pnl_booking, pnl_payment, pnl_detail;
	
	private PaymentDAO pdao = new PaymentDAO();
	private PaymentDetailDAO pddao = new PaymentDetailDAO();
	private BookingDAO bdao = new BookingDAO();
	private BookingDetailDAO bddao = new BookingDetailDAO();
	private ServiceDAO sdao = new ServiceDAO();
	private CategoryDAO cdao = new CategoryDAO();
	
	private Account account;
	
	public Pnl_ManagePayments(Account account) {
		setLayout(new BorderLayout());
		this.account = account;
		init();
		gui();
		getData();
		eventTable();
	}
	
	private void eventTable() {
		tbl_payment.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				tbl_model_booking.setRowCount(0);
				tbl_model_detail.setRowCount(0);
				int row = tbl_payment.getSelectedRow();
				
				int paymentID = Integer.parseInt(tbl_model_payment.getValueAt(row, 0).toString());
				int bookingID = Integer.parseInt(tbl_model_payment.getValueAt(row, 6).toString());
				
				try {
					for(PaymentDetail i : pddao.getAll()) {
						if(i.getPaymentID() == paymentID) {
							Object[] objRow = {
								i.getPaymentDetailID() + "", i.getPaymentID() + "",
								getServiceName(i.getServiceID()), i.getQuantity() + "",
								i.getPrice(), i.getSubtotal()
							};
							tbl_model_detail.addRow(objRow);
						}
					}
					for(BookingDetail i : bddao.getAll()) {
						if(i.getBookingID() == bookingID) {
							Object[] objRow = {
								i.getRoomID() + "", getCategoryName(i.getCategoryID()),
								new SimpleDateFormat("dd/MM/yyyy").format(i.getCheckinDate()),
								new SimpleDateFormat("dd/MM/yyyy").format(i.getCheckoutDate()),
								i.getPrice(), i.getDiscount()
							};
							tbl_model_booking.addRow(objRow);
						}
					}
				} catch (Exception err) {
					err.printStackTrace();
				}
			}
			
		});
	}
	
	private String getCategoryName(int id) {
		String name = "";
		try {
			for(Category c : cdao.getAll()) {
				if(c.getCategoryID() == id) {
					name = c.getCategoryName();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	private String getServiceName(int id) {
		String name = "";
		try {
			for(Service s : sdao.getAll()) {
				if(s.getServiceID() == id) {
					name = s.getServiceName();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	
	private void getData() {
		tbl_model_payment.setRowCount(0);
		try {
			for(Payment p : pdao.getAll()) {
				Object[] row = {
					p.getPaymentID() + "", p.getServiceQuantity() + "", 
					p.getServicePrice(), p.getRentalPrice(), 
					p.getSubTotal(), getDateFormat(p.getPaymentDate()), p.getBookingID() + ""
				};
				tbl_model_payment.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void init() {
		// Jpanel
		pnl_booking = new JPanel(new BorderLayout());
		pnl_booking.setBorder(BorderFactory.createTitledBorder(null, "Thông tin đơn đặt:", TitledBorder.LEFT, TitledBorder.TOP,
				fontSan, Color.MAGENTA));
		
		pnl_payment = new JPanel(new BorderLayout());
		pnl_payment.setBorder(BorderFactory.createTitledBorder(null, "Danh sách hóa đơn:", TitledBorder.LEFT, TitledBorder.TOP,
				fontSan, Color.MAGENTA));
		
		pnl_detail = new JPanel(new BorderLayout());
		pnl_detail.setBorder(BorderFactory.createTitledBorder(null, "Thông tin chi tiết hóa đơn:", TitledBorder.LEFT, TitledBorder.TOP,
				fontSan, Color.MAGENTA));
		
		// Jtable
		String[] headerBooking = {
			"Mã phòng", "Loại phòng", "Ngày nhận", 
			"Ngày trả", "Đơn giá", "Giảm giá"
		};
		tbl_booking = new JTable(tbl_model_booking = new DefaultTableModel(headerBooking, 0)) {
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
					return String.class;
				case 3:
					return String.class;
				case 4:
					return Double.class;
				default:
					return Float.class;
				}
			}
		};
		tbl_booking.setRowHeight(35);
		DefaultTableCellRenderer dtbl_cell_render1 = new DefaultTableCellRenderer();
		dtbl_cell_render1.setHorizontalAlignment(SwingConstants.CENTER);
		tbl_booking.setDefaultRenderer(String.class, dtbl_cell_render1);
		
		JTableHeader tableHeader = tbl_booking.getTableHeader();
		tableHeader.setBackground(Color.decode("#67e0fe"));
		tableHeader.setFont(new Font("Arial", Font.BOLD, 13));
		tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 35));

		String[] headerDetail = {
			"#", "Mã hóa đơn", "Dịch vụ", 
			"Số lượng", "Đơn giá", "Tổng tiền"
		};
		tbl_detail = new JTable(tbl_model_detail = new DefaultTableModel(headerDetail, 0)) {
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
					return String.class;
				case 3:
					return String.class;
				case 4:
					return Double.class;
				default:
					return Float.class;
				}
			}
		};
		tbl_detail.setRowHeight(35);
		DefaultTableCellRenderer dtbl_cell_render2 = new DefaultTableCellRenderer();
		dtbl_cell_render2.setHorizontalAlignment(SwingConstants.CENTER);
		tbl_detail.setDefaultRenderer(String.class, dtbl_cell_render2);
		
		JTableHeader tableHeaderDetail = tbl_detail.getTableHeader();
		tableHeaderDetail.setBackground(Color.decode("#67e0fe"));
		tableHeaderDetail.setFont(new Font("Arial", Font.BOLD, 13));
		tableHeaderDetail.setPreferredSize(new Dimension(tableHeaderDetail.getPreferredSize().width, 35));

		String[] headerPayment = {
			"#", "Số lượng dịch vụ", "Tổng tiền dịch vụ", 
			"Tiền thuê phòng", "Tổng tiền", "Ngày thanh toán", "Mã đơn đặt"
		};
		tbl_payment = new JTable(tbl_model_payment = new DefaultTableModel(headerPayment, 0)) {
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
					return Double.class;
				case 4:
					return Double.class;
				default:
					return String.class;
				}
			}
		};
		tbl_payment.setRowHeight(35);
		DefaultTableCellRenderer dtbl_cell_render3 = new DefaultTableCellRenderer();
		dtbl_cell_render3.setHorizontalAlignment(SwingConstants.CENTER);
		tbl_payment.setDefaultRenderer(String.class, dtbl_cell_render3);
		
		JTableHeader tableHeaderPayment = tbl_payment.getTableHeader();
		tableHeaderPayment.setBackground(Color.decode("#67e0fe"));
		tableHeaderPayment.setFont(new Font("Arial", Font.BOLD, 13));
		tableHeaderPayment.setPreferredSize(new Dimension(tableHeaderPayment.getPreferredSize().width, 35));

		// JScrollPane
		jsp_booking = new JScrollPane(tbl_booking);
		jsp_booking.setPreferredSize(new Dimension(jsp_booking.getPreferredSize().width, 300));

		jsp_detail = new JScrollPane(tbl_detail);
		jsp_detail.setPreferredSize(new Dimension(jsp_detail.getPreferredSize().width, 300));

		jsp_payment = new JScrollPane(tbl_payment);
		jsp_payment.setPreferredSize(new Dimension(jsp_payment.getPreferredSize().width, 300));
		
	}
	
	private void gui() {
		Box bh1 = BoxComponent.getHorizontalBox(jsp_booking, 10);
		Box bh2 = BoxComponent.getHorizontalBox(jsp_detail, 10);
		Box bh3 = BoxComponent.getHorizontalBox(jsp_payment, 10);

		Box bv1 = BoxComponent.getVerticalBox(bh1, 10);
		Box bv2 = BoxComponent.getVerticalBox(bh2, 10);
		Box bv3 = BoxComponent.getVerticalBox(bh3, 10);
		
		pnl_booking.add(bv1);
		pnl_detail.add(bv2);
		pnl_payment.add(bv3);
		
		Box bv = BoxComponent.getVerticalBox(pnl_payment, pnl_detail, pnl_booking, 20);
		Box bh = BoxComponent.getHorizontalBox(bv, 10);
		
		add(bh);
	}
	
	private String getDateFormat(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}

}
