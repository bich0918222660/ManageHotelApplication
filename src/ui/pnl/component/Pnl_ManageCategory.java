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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import ui.component.BoxComponent;

public class Pnl_ManageCategory extends JPanel {

	private Font fontSan = new Font("Arial", Font.BOLD, 18);

	private JLabel lbl_id, lbl_name, lbl_price, lbl_description, lbl_discount, lbl_image, lbl_type, lbl_image_url;
	private JTextField txt_id, txt_name, txt_price, txt_discount;
	private JTextArea txt_description;
	private JComboBox<String> cbx_types;
	private JButton btn_add, btn_update, btn_save, btn_delete, btn_load, btnUpload;
	private JTable tbl_category;
	private DefaultTableModel tbl_model_category;
	private JScrollPane jsp_category;

	public Pnl_ManageCategory() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(null, "Quản lý loại phòng:", TitledBorder.LEFT, TitledBorder.TOP,
				fontSan, Color.MAGENTA));
		init();
		gui();
	}

	private void init() {
		// JLabel
		lbl_id = new JLabel("Mã loại:");
		lbl_name = new JLabel("Tên loại:");
		lbl_price = new JLabel("Đơn giá:");
		lbl_description = new JLabel("Mô tả:");
		lbl_discount = new JLabel("Giảm giá:");
		lbl_image = new JLabel("Hình ảnh:");
		lbl_type = new JLabel("Thuộc loại:");
		lbl_image_url = new JLabel("Choose image file...");

		lbl_id.setPreferredSize(lbl_type.getPreferredSize());
		lbl_name.setPreferredSize(lbl_type.getPreferredSize());
		lbl_description.setPreferredSize(lbl_type.getPreferredSize());

		lbl_price.setPreferredSize(lbl_discount.getPreferredSize());

		// JTextField
		txt_id = new JTextField();
		txt_name = new JTextField();
		txt_price = new JTextField();
		txt_discount = new JTextField();

		// JTextArea
		txt_description = new JTextArea();
		txt_description.setRows(5);

		// JComboxBox
		cbx_types = new JComboBox<>();
		cbx_types.addItem("Single Room");
		cbx_types.addItem("Couple Room");
		cbx_types.addItem("Three Beds Room");

		// JButton
		btn_add = new JButton(new ImageIcon("imgs/ic_add.png"));
		btn_add.setMargin(new Insets(0, 0, 0, 0));
		btn_add.setBorder(null);

		btn_update = new JButton(new ImageIcon("imgs/ic_edit.png"));
		btn_update.setMargin(new Insets(0, 0, 0, 0));
		btn_update.setBorder(null);

		btn_delete = new JButton(new ImageIcon("imgs/ic_delete.png"));
		btn_delete.setMargin(new Insets(0, 0, 0, 0));
		btn_delete.setBorder(null);

		btn_load = new JButton(new ImageIcon("imgs/ic_load.png"));
		btn_load.setMargin(new Insets(0, 0, 0, 0));
		btn_load.setBorder(null);
		
		btn_save = new JButton(new ImageIcon("imgs/ic_save.png"));
		btn_save.setMargin(new Insets(0, 0, 0, 0));
		btn_save.setBorder(null);

		btnUpload = new JButton("Upload File");

		// JTable
		String[] header = { "Mã loại", "Tên loại", "Thuộc loại", "Đơn giá", "Giảm giá" };
		tbl_category = new JTable(tbl_model_category = new DefaultTableModel(header, 0));

		// JScrollPane
		jsp_category = new JScrollPane(tbl_category);
		jsp_category.setPreferredSize(new Dimension(tbl_category.getPreferredSize().width, 300));

	}

	private void gui() {
		// Info
		Box b_id = BoxComponent.getHorizontalBox(lbl_id, txt_id, 10);
		Box b_name = BoxComponent.getHorizontalBox(lbl_name, txt_name, 10);
		Box b_type = BoxComponent.getHorizontalBox(lbl_type, cbx_types, 10);
		Box b_desc = BoxComponent.getHorizontalBox(lbl_description, txt_description, 10);
		Box b_price = BoxComponent.getHorizontalBox(lbl_discount, txt_discount, 10);
		Box b_discount = BoxComponent.getHorizontalBox(lbl_price, txt_price, 10);
		Box b_image = BoxComponent.getHorizontalBox(lbl_image, btnUpload, lbl_image_url, 10);
		

		Box b_left = BoxComponent.getVerticalBox(b_id, b_name, b_type, 10);
		Box b_right = BoxComponent.getVerticalBox(b_price, b_discount, b_image, 10);

		Box bh_info = BoxComponent.getHorizontalBox_NoPadding(b_left, b_right, 50);

		// Button
		Box b_button = Box.createHorizontalBox();
		b_button.add(Box.createHorizontalStrut(900));
		b_button.add(btn_add);
		b_button.add(Box.createHorizontalStrut(15));
		b_button.add(btn_update);
		b_button.add(Box.createHorizontalStrut(15));
		b_button.add(btn_save);
		b_button.add(Box.createHorizontalStrut(15));
		b_button.add(btn_delete);
		b_button.add(Box.createHorizontalStrut(15));
		b_button.add(btn_load);
		b_button.add(Box.createHorizontalStrut(10));

		// Table
		Box bh = BoxComponent.getHorizontalBox(jsp_category, 10);
		Box bv = BoxComponent.getVerticalBox(bh, 10);

		JPanel pnl = new JPanel(new BorderLayout());
		pnl.setBorder(BorderFactory.createTitledBorder(null, "Danh sách loại phòng:", TitledBorder.LEFT, TitledBorder.TOP,
				fontSan, Color.MAGENTA));
		pnl.add(bv);

		// Full
		Box bv_full = BoxComponent.getVerticalBox(bh_info, b_button, pnl, 10);
		Box bh_full = BoxComponent.getHorizontalBox(bv_full, 10);

		add(bh_full);
	}

}
