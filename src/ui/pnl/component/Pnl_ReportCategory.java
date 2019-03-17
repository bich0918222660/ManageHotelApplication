package ui.pnl.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

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

import entity.Category;
import ui.component.BoxComponent;

public class Pnl_ReportCategory extends JPanel {

	private JLabel lbl_category, lbl_from, lbl_to, lbl_category_id, lbl_price, lbl_discount, lbl_type;
	private JTextField txt_category_id, txt_price, txt_discount;
	private JDateChooser dcs_from, dcs_to;
	private JComboBox<Category> cbx_categories;
	private JComboBox<String> cbx_types;
	private JButton btn_report;
	private JTable tbl_empty, tbl_rented, tbl_booked;
	private JScrollPane jsp_empty, jsp_rented, jsp_booked;
	private DefaultTableModel tbl_model_empty, tbl_model_rented, tbl_model_booked;
	private JPanel pnl_empty, pnl_rented, pnl_booked;
	
	private Font fontSan = new Font("Arial", Font.BOLD, 18);
	
	public Pnl_ReportCategory() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(null, "Báo cáo trạng thái loại phòng:", TitledBorder.LEFT, TitledBorder.TOP, fontSan, Color.MAGENTA));
		init();
		gui();
	}
	
	private void init() {
		// JLabel 
		lbl_category = new JLabel("Loáº¡i phÃ²ng:");
		lbl_from = new JLabel("Tá»« ngÃ y:");
		lbl_category_id = new JLabel("MÃ£ loáº¡i:");
		lbl_discount = new JLabel("Giáº£m:");
		lbl_to = new JLabel("Ä�áº¿n ngÃ y:");
		lbl_price = new JLabel("Ä�Æ¡n giÃ¡:");
		lbl_type = new JLabel("Loáº¡i:");
		
		lbl_from.setPreferredSize(lbl_category.getPreferredSize());
		lbl_category_id.setPreferredSize(lbl_category.getPreferredSize());
		lbl_discount.setPreferredSize(lbl_category.getPreferredSize());
		lbl_to.setPreferredSize(lbl_category.getPreferredSize());
		lbl_type.setPreferredSize(lbl_category.getPreferredSize());
		lbl_price.setPreferredSize(lbl_category.getPreferredSize());
		
		// JDateChooser
		dcs_from = new JDateChooser();
		dcs_from.setDateFormatString("dd/MM/yyyy");

		dcs_to = new JDateChooser();
		dcs_to.setDateFormatString("dd/MM/yyyy");
		
		// JTextField
		txt_category_id = new JTextField();
		txt_discount = new JTextField();
		txt_price = new JTextField();
		
		// JComboBox
		cbx_categories = new JComboBox<>();
		cbx_categories.addItem(new Category("abc", "abc", 22, 0, "aa", "aa"));
		
		cbx_types = new JComboBox<>();
		cbx_types.addItem("Single Room");
		cbx_types.addItem("Couple Room");
		cbx_types.addItem("Three Beds Room");
		cbx_types.addItem("Family Room");
		
		// JButton 
		btn_report = new JButton("Xem tÃ¬nh tráº¡ng");
		
		// JTable
		String[] header_empty = {
				"MÃ£ phÃ²ng", 
				"TÃ¬nh tráº¡ng"
		};
		tbl_empty = new JTable(tbl_model_empty = new DefaultTableModel(header_empty, 0));
		tbl_empty.setRowHeight(25);
		
		String[] header_booking = {
				"MÃ£ phÃ²ng", 
				"TÃ¬nh tráº¡ng",
				"NgÃ y nháº­n phÃ²ng", 
				"NgÃ y tráº£ phÃ²ng", 
				"KhÃ¡ch hÃ ng"
		};
		tbl_booked = new JTable(tbl_model_booked = new DefaultTableModel(header_booking, 0));
		tbl_booked.setRowHeight(25);
		
		String[] header_rented = {
				"MÃ£ phÃ²ng", 
				"TÃ¬nh tráº¡ng",
				"NgÃ y nháº­n phÃ²ng", 
				"NgÃ y tráº£ phÃ²ng", 
				"KhÃ¡ch hÃ ng"
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
	
	private void gui() {
		pnl_empty.add(jsp_empty);
		pnl_booked.add(jsp_booked);
		pnl_rented.add(jsp_rented);
		
		Box b_empty = BoxComponent.getHorizontalBox(pnl_empty, 10);
		pnl_empty.add(b_empty);
		
		Box b_booked = BoxComponent.getHorizontalBox(pnl_booked, 10);
		pnl_empty.add(b_booked);

		Box b_rented = BoxComponent.getHorizontalBox(pnl_rented, 10);
		pnl_empty.add(b_rented);
		
		// LEFT
		Box b_left = Box.createVerticalBox();
		b_left.setMaximumSize(new Dimension(500, 400));
		b_left.add(Box.createVerticalStrut(10));
		b_left.add(BoxComponent.getHorizontalBox(lbl_category, cbx_categories, 10));
		b_left.add(Box.createVerticalStrut(10));
		b_left.add(BoxComponent.getHorizontalBox(lbl_from, dcs_from, 10));
		b_left.add(Box.createVerticalStrut(10));
		b_left.add(BoxComponent.getHorizontalBox(lbl_to, dcs_to, 10));
		b_left.add(Box.createVerticalStrut(10));
		b_left.add(BoxComponent.getHorizontalBox(btn_report, 10));
		b_left.add(Box.createVerticalStrut(10));
		b_left.add(BoxComponent.getHorizontalBox(lbl_category_id, txt_category_id, 10));
		b_left.add(Box.createVerticalStrut(10));
		b_left.add(BoxComponent.getHorizontalBox(lbl_price, txt_price, 10));
		b_left.add(Box.createVerticalStrut(10));
		b_left.add(BoxComponent.getHorizontalBox(lbl_discount, txt_discount, 10));
		b_left.add(Box.createVerticalStrut(10));
		b_left.add(BoxComponent.getHorizontalBox(lbl_type, cbx_types, 10));
		b_left.add(Box.createVerticalStrut(10));
		
		// RIGHT
		Box b_right = BoxComponent.getVerticalBox(b_empty, b_booked, b_rented, 10);
		b_right.setMaximumSize(new Dimension(800, 450));
		
		Box b_full = BoxComponent.getHorizontalBox_NoPadding(b_left, b_right, 40);
		
		add(b_full);
	}
	
}
