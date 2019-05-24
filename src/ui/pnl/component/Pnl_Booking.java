package ui.pnl.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import ui.component.BoxComponent;

public class Pnl_Booking extends JPanel {

	private Font fontSan = new Font("Arial", Font.BOLD, 18);

	private JLabel lbl_phone, lbl_name, lbl_subtotal;
	private JTextField txt_phone, txt_name, txt_subtotal, txt_person_code;
	private JButton btn_delete, btn_booking, btn_check;

	private JTable tbl_reservations;
	private DefaultTableModel tbl_model_reservations;
	private DefaultTableCellRenderer dtbl_cell_render;
	private JScrollPane jsp_reservations;
	
	private JPanel pnl_reservations;

	public Pnl_Booking() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(null, "Đặt phòng:", TitledBorder.LEFT, TitledBorder.TOP, fontSan,
				Color.MAGENTA));
		init();
		gui();
	}

	private void init() {
		// JPanel
		pnl_reservations = new JPanel(new BorderLayout());
		pnl_reservations.setBorder(BorderFactory.createTitledBorder(null, "Thông tin đơn đặt:", TitledBorder.LEFT, TitledBorder.TOP, fontSan, Color.MAGENTA));
		pnl_reservations.setPreferredSize(new Dimension(pnl_reservations.getPreferredSize().width, 400));
		
		// JLabel
		lbl_phone = new JLabel("Số điện thoại/ CMND:");
		lbl_name = new JLabel("Khách hàng:");
		lbl_subtotal = new JLabel("Tổng tiền:");

		// JTextField
		txt_phone = new JTextField();
		txt_name = new JTextField();
		txt_subtotal = new JTextField();
		txt_person_code = new JTextField();

		// JButton
		btn_booking = new JButton(new ImageIcon(this.getClass().getResource("/ic_yes.png")));
		btn_booking.setMargin(new Insets(0, 0, 0, 0));
		btn_booking.setBorder(null);
		btn_booking.setBackground(Color.decode("#ebebeb"));
		
		btn_delete = new JButton(new ImageIcon(this.getClass().getResource("/ic_delete.png")));
		btn_delete.setMargin(new Insets(0, 0, 0, 0));
		btn_delete.setBorder(null);
		btn_delete.setBackground(Color.decode("#ebebeb"));
		
		btn_check = new JButton(new ImageIcon(this.getClass().getResource("/ic_check.png")));
		btn_check.setMargin(new Insets(0, 0, 0, 0));
		btn_check.setBorder(null);
		btn_check.setBackground(Color.decode("#ebebeb"));

		// JTable
		String[] header = { 
				"Loại phòng", "Số lượng", 
				"Ngày nhận phòng", "Ngày trả phòng", 
				"Đơn giá", "Giảm giá"
		};
		tbl_reservations = new JTable(tbl_model_reservations = new DefaultTableModel(header, 0)){

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
					return Integer.class;
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
		tbl_reservations.setRowHeight(35);
		dtbl_cell_render = new DefaultTableCellRenderer();
		dtbl_cell_render.setHorizontalAlignment(SwingConstants.CENTER);
		tbl_reservations.setDefaultRenderer(String.class, dtbl_cell_render);
		
		JTableHeader tableHeader = tbl_reservations.getTableHeader();
		tableHeader.setBackground(Color.decode("#67e0fe"));
		tableHeader.setFont(new Font("Arial", Font.BOLD, 13));
		tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 35));

		jsp_reservations = new JScrollPane(tbl_reservations);
		jsp_reservations.setPreferredSize(new Dimension(jsp_reservations.getPreferredSize().width, 400));
	}

	private void gui() {
		// Input
		Box b_id = BoxComponent.getHorizontalBox(lbl_phone, txt_phone, 10);
		b_id.setPreferredSize(new Dimension(30, b_id.getPreferredSize().height));
		Box b_name = BoxComponent.getHorizontalBox(lbl_name, txt_name, 10);
		b_name.setMinimumSize(new Dimension(200, b_name.getPreferredSize().height));
		Box b_subtotal = BoxComponent.getHorizontalBox(lbl_subtotal, txt_subtotal, 10);
		b_subtotal.setPreferredSize(new Dimension(5, b_subtotal.getPreferredSize().height));
		Box bh_input = Box.createHorizontalBox();
		bh_input.add(Box.createHorizontalStrut(10));
		bh_input.add(b_id);
		bh_input.add(Box.createHorizontalStrut(10));
		bh_input.add(btn_check);
		bh_input.add(Box.createHorizontalStrut(30));
		bh_input.add(b_name);
		bh_input.add(Box.createHorizontalStrut(30));
		bh_input.add(b_subtotal);
		bh_input.add(Box.createHorizontalStrut(10));
		
		// Reservations
		Box b_table = BoxComponent.getHorizontalBox(jsp_reservations, 10);
		Box b_button = Box.createHorizontalBox();
		b_button.add(Box.createHorizontalStrut(1050));
		b_button.add(btn_delete);
		b_button.add(Box.createHorizontalStrut(20));
		b_button.add(btn_booking);
		b_button.add(Box.createHorizontalStrut(10));
		Box bv_table_button = BoxComponent.getVerticalBox(b_table, b_button, 10);
		pnl_reservations.add(BoxComponent.getHorizontalBox(bv_table_button, 10));
		Box bh_reservations = BoxComponent.getHorizontalBox(pnl_reservations, 10);
		
		// Full
		Box bv_full = BoxComponent.getVerticalBox(bh_input, bh_reservations, 15);
		
		add(bv_full);
	}

	public JTextField getTxt_phone() {
		return txt_phone;
	}

	public JTextField getTxt_name() {
		return txt_name;
	}

	public JButton getBtn_delete() {
		return btn_delete;
	}

	public JButton getBtn_booking() {
		return btn_booking;
	}

	public JButton getBtn_check() {
		return btn_check;
	}

	public JTable getTbl_reservations() {
		return tbl_reservations;
	}

	public DefaultTableModel getTbl_model_reservations() {
		return tbl_model_reservations;
	}

	public JTextField getTxt_subtotal() {
		return txt_subtotal;
	}

	public JTextField getTxt_person_code() {
		return txt_person_code;
	}
	
}
