package ui.pnl;

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
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entity.Category;
import ui.component.BoxComponent;

public class Pnl_ManageRoom extends JPanel {

	private Font fontSan = new Font("Arial", Font.BOLD, 18);

	private JLabel lbl_id, lbl_position, lbl_status, lbl_category;
	private JTextField txt_id, txt_position;
	private JComboBox<Category> cbx_categories;
	private JComboBox<String> cbx_status;
	private JButton btn_add, btn_update, btn_save, btn_delete, btn_load;
	private JTable tbl_room;
	private DefaultTableModel tbl_model_room;
	private JScrollPane jsp_room;

	private JPanel pnl_header;

	public Pnl_ManageRoom() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(null, "Quản lý thông tin phòng:", TitledBorder.LEFT, TitledBorder.TOP,
				fontSan, Color.MAGENTA));
		init();
		gui();
	}

	private void init() {
		// Jpanel
		pnl_header = new JPanel(new BorderLayout());
		JLabel lbl = new JLabel();
		lbl.setIcon(new ImageIcon("imgs/rose.jpeg"));
		pnl_header.add(lbl);
		
		// JLabel
		lbl_id = new JLabel("Mã phòng:");
		lbl_position = new JLabel("Vị trí phòng:");
		lbl_status = new JLabel("Tình trạng:");
		lbl_category = new JLabel("Loại phòng:");

		lbl_id.setPreferredSize(lbl_category.getPreferredSize());
		lbl_status.setPreferredSize(lbl_position.getPreferredSize());

		// JTextField
		txt_id = new JTextField();
		txt_position = new JTextField();

		// JComboBox
		cbx_categories = new JComboBox<>();
		cbx_categories.addItem(new Category("aaa", "", 100, 0, "", "Single Room"));

		cbx_status = new JComboBox<>();
		cbx_status.addItem("Empty");
		cbx_status.addItem("Booked");
		cbx_status.addItem("Rented");

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

		// JTable
		String[] header = { "Mã phòng", "Loại phòng", "Vị trí phòng", "Tình trạng" };
		tbl_room = new JTable(tbl_model_room = new DefaultTableModel(header, 0));

		// JScrollPane
		jsp_room = new JScrollPane(tbl_room);
		jsp_room.setPreferredSize(new Dimension(tbl_room.getPreferredSize().width, 300));

	}

	private void gui() {		
		// Info
		Box b_id = BoxComponent.getHorizontalBox(lbl_id, txt_id, 10);
		Box b_category = BoxComponent.getHorizontalBox(lbl_category, cbx_categories, 10);
		Box b_position = BoxComponent.getHorizontalBox(lbl_position, txt_position, 10);
		Box b_status = BoxComponent.getHorizontalBox(lbl_status, cbx_status, 10);

		Box bv_left = BoxComponent.getVerticalBox(b_id, b_category, 10);
		Box bv_right = BoxComponent.getVerticalBox(b_position, b_status, 10);

		Box bh_info = BoxComponent.getHorizontalBox_NoPadding(bv_left, bv_right, 40);
		bh_info.setPreferredSize(new Dimension(bh_info.getPreferredSize().width, 82));

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
		Box bh = BoxComponent.getHorizontalBox(jsp_room, 10);
		Box bv = BoxComponent.getVerticalBox(bh, 10);

		JPanel pnl = new JPanel(new BorderLayout());
		pnl.setBorder(BorderFactory.createTitledBorder(null, "Danh sách phòng:", TitledBorder.LEFT, TitledBorder.TOP,
				fontSan, Color.MAGENTA));
		pnl.add(bv);

		// Full
		Box bv_full = BoxComponent.getVerticalBox(b_button, pnl, 10);
		Box bh_full = BoxComponent.getHorizontalBox(bv_full, 10);

		add(bh_full);
		add(BoxComponent.getVerticalBox(pnl_header, bh_info, 0), BorderLayout.NORTH);
	}
}
