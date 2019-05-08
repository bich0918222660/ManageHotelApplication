package ui.pnl.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import entity.Category;
import ui.component.BoxComponent;

public class Pnl_SearchRoom extends JPanel {

	private Font fontSan = new Font("Arial", Font.BOLD, 18);
	
	private JLabel lbl_category, lbl_checkin_date, lbl_checkout_date;
	
	private JDateChooser dcs_checkin, dcs_checkout;
	
	private JComboBox<Category> cbx_categories;
	
	private JButton btn_search, btn_delete, btn_add;
	
	private JPanel pnl_empty_rooms, pnl_selected_rooms;

	private JTable tbl_selected_rooms;
	private DefaultTableModel tbl_model_selected_rooms;
	private JScrollPane jsp_rooms;

	private JPanel pnl_rooms;
	
	public Pnl_SearchRoom() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(null, "Tìm kiếm phòng trống:", TitledBorder.LEFT, TitledBorder.TOP, fontSan, Color.MAGENTA));
		init();
		gui();
	}
	
	private void init() {
		// Jpanel
		pnl_empty_rooms = new JPanel(new BorderLayout());
		pnl_empty_rooms.setBorder(BorderFactory.createTitledBorder(null, "Danh sách phòng trống:", TitledBorder.LEFT, TitledBorder.TOP, fontSan, Color.MAGENTA));
		pnl_empty_rooms.setPreferredSize(new Dimension(500, 300));
		
		pnl_rooms = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnl_rooms.setPreferredSize(new Dimension(450, 300));
		
		pnl_selected_rooms = new JPanel(new BorderLayout());
		pnl_selected_rooms.setBorder(BorderFactory.createTitledBorder(null, "Danh sách phòng được chọn:", TitledBorder.LEFT, TitledBorder.TOP, fontSan, Color.MAGENTA));
		pnl_selected_rooms.setPreferredSize(new Dimension(700, 300));
		
		// JLabel
		lbl_category = new JLabel("Loại phòng:");
		lbl_checkout_date = new JLabel("Ngày trả phòng:");
		lbl_checkin_date = new JLabel("Ngày nhận phòng:");

		// JDateChooser
		dcs_checkin = new JDateChooser();
		dcs_checkin.setDateFormatString("dd/MM/yyyy");

		dcs_checkout = new JDateChooser();
		dcs_checkout.setDateFormatString("dd/MM/yyyy");
		
		// JComboBox 
		cbx_categories = new JComboBox<>();
		cbx_categories.setPreferredSize(new Dimension(270, cbx_categories.getPreferredSize().height));
		
		// JButton
		btn_search = new JButton("Tìm kiếm");
		
		btn_delete = new JButton(new ImageIcon("imgs/ic_delete.png"));
		btn_delete.setMargin(new Insets(0, 0, 0, 0));
		btn_delete.setBorder(null);

		btn_add = new JButton(new ImageIcon("imgs/ic_addcart.png"));
		btn_add.setMargin(new Insets(0, 0, 0, 0));
		btn_add.setBorder(null);
		
		// JList
		String[] header = {
				"Mã phòng", "Loại phòng",
				"Ngày nhận", "Ngày trả",
				"Đơn giá", "Giá giảm"
		};
		tbl_model_selected_rooms = new DefaultTableModel(header, 0);
		tbl_selected_rooms = new JTable(tbl_model_selected_rooms) {

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
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		tbl_selected_rooms.setDefaultRenderer(String.class, centerRenderer);
		tbl_selected_rooms.setRowHeight(30);
		jsp_rooms = new JScrollPane(tbl_selected_rooms);
	}

	private void gui() {
		// Input
		Box b_category = BoxComponent.getHorizontalBox(lbl_category, cbx_categories, 10);
		Box b_checkin = BoxComponent.getHorizontalBox(lbl_checkin_date, dcs_checkin, 10);
		Box b_checkout = BoxComponent.getHorizontalBox(lbl_checkout_date, dcs_checkout, 10);
		
		Box b_input = BoxComponent.getHorizontalBox(b_category, b_checkin, b_checkout, btn_search, 10);
		
		// List Room
			// LEFT
		pnl_empty_rooms.add(new JScrollPane(pnl_rooms));
		Box b_empty_rooms = BoxComponent.getHorizontalBox(pnl_empty_rooms, 10);
		
			// RIGHT
		Box b_button = Box.createHorizontalBox();
		b_button.add(Box.createHorizontalStrut(590));
		b_button.add(btn_delete);
		b_button.add(Box.createHorizontalStrut(10));
		b_button.add(btn_add);
		b_button.add(Box.createHorizontalStrut(10));
		Box bh_selected_rooms = BoxComponent.getHorizontalBox(jsp_rooms, 10);
		Box bv_selected_rooms = BoxComponent.getVerticalBox(bh_selected_rooms, b_button, 10);
		
		pnl_selected_rooms.add(bv_selected_rooms);
		
		Box b_rooms = BoxComponent.getHorizontalBox_NoPadding(BoxComponent.getHorizontalBox(pnl_selected_rooms, 10), b_empty_rooms, 5);
		
		// Full
		Box bv_full = BoxComponent.getVerticalBox(b_input, b_rooms, 10);
		
		add(bv_full);
	}
	
	// Getter
	public JDateChooser getDcs_checkin() {
		return dcs_checkin;
	}

	public JDateChooser getDcs_checkout() {
		return dcs_checkout;
	}

	public JComboBox<Category> getCbx_categories() {
		return cbx_categories;
	}

	public JButton getBtn_search() {
		return btn_search;
	}

	public JButton getBtn_delete() {
		return btn_delete;
	}

	public JButton getBtn_add() {
		return btn_add;
	}

	public JTable getTbl_selected_rooms() {
		return tbl_selected_rooms;
	}

	public DefaultTableModel getTbl_model_selected_rooms() {
		return tbl_model_selected_rooms;
	}

	public JPanel getPnl_empty_rooms() {
		return pnl_rooms;
	}

	public JPanel getPnl_selected_rooms() {
		return pnl_selected_rooms;
	}
	
}
