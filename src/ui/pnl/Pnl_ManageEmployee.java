package ui.pnl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entity.Account;
import ui.component.BoxComponent;

public class Pnl_ManageEmployee extends JPanel {

	private Font fontSan = new Font("Arial", Font.BOLD, 14);

	private JLabel lbl_id, lbl_first_name, lbl_middle_name, lbl_last_name, lbl_address, lbl_gender, lbl_phone,
			lbl_date_of_birth, lbl_account, lbl_person_code, lbl_position;
	private JTextField txt_id, txt_first_name, txt_middle_name, txt_last_name, txt_address, txt_phone,
			txt_date_of_birth, txt_account, txt_position;
	private JComboBox<String> cbx_position;
	private JRadioButton rb_male, rb_female;
	private ButtonGroup bg;
	private JButton btn_add, btn_update, btn_save, btn_load, btn_delete;
	private JTable tbl_employee;
	private DefaultTableModel tbl_model_employee;
	private JScrollPane jsp_employee;

	private JPanel pnl_header;
	
	private Account account;

	public Pnl_ManageEmployee(Account account) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(null, "Quản lý thông tin nhân viên:", TitledBorder.LEFT, TitledBorder.TOP,
				fontSan, Color.MAGENTA));
		this.account = account;
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
		lbl_id = new JLabel("Mã khách hàng:");
		lbl_first_name = new JLabel("Họ:");
		lbl_middle_name = new JLabel("Tên lót:");
		lbl_last_name = new JLabel("Tên:");
		lbl_address = new JLabel("Địa chỉ:");
		lbl_gender = new JLabel("Giới tính:");
		lbl_phone = new JLabel("Số điện thoại:");
		lbl_date_of_birth = new JLabel("Ngày sinh:");
		lbl_account = new JLabel("Tài khoản:");
		lbl_person_code = new JLabel("Chứng minh nhân dân:");
		lbl_position = new JLabel("Vị trí:");

		lbl_first_name.setPreferredSize(lbl_id.getPreferredSize());
		lbl_middle_name.setPreferredSize(lbl_id.getPreferredSize());
		lbl_last_name.setPreferredSize(lbl_id.getPreferredSize());
		lbl_address.setPreferredSize(lbl_id.getPreferredSize());

		lbl_date_of_birth.setPreferredSize(lbl_phone.getPreferredSize());
		lbl_account.setPreferredSize(lbl_phone.getPreferredSize());
		lbl_position.setPreferredSize(lbl_phone.getPreferredSize());

		// JTextField
		txt_account = new JTextField();
		txt_first_name = new JTextField();
		txt_id = new JTextField();
		txt_middle_name = new JTextField();
		txt_last_name = new JTextField();
		txt_address = new JTextField();
		txt_phone = new JTextField();
		txt_date_of_birth = new JTextField();
		txt_position = new JTextField();
		
		// JComboBox
		cbx_position = new JComboBox<>();
		cbx_position.addItem("Tiếp tân");
		cbx_position.addItem("Quản lý");
		cbx_position.addItem("Quản trị viên");
		cbx_position.setPreferredSize(new Dimension(250, cbx_position.getPreferredSize().height));

		// JRadioButton
		rb_male = new JRadioButton("Nam");
		rb_male.setSelected(true);
		rb_female = new JRadioButton("Nữ");
		
		bg = new ButtonGroup();
		bg.add(rb_female);
		bg.add(rb_male);

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
		String[] header = { "Mã số", "Họ và tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Tài khoản" };
		tbl_employee = new JTable(tbl_model_employee = new DefaultTableModel(header, 0));

		// JScrollPane
		jsp_employee = new JScrollPane(tbl_employee);
		jsp_employee.setPreferredSize(new Dimension(tbl_employee.getPreferredSize().width, 300));

	}

	private void gui() {
		// Info
		Box b_id = BoxComponent.getHorizontalBox(lbl_id, txt_id, 10);
		Box b_person_code = BoxComponent.getHorizontalBox(lbl_person_code, txt_position, 10);
		Box b_first = BoxComponent.getHorizontalBox(lbl_first_name, txt_first_name, 10);
		Box b_middle = BoxComponent.getHorizontalBox(lbl_middle_name, txt_middle_name, 10);
		Box b_last = BoxComponent.getHorizontalBox(lbl_last_name, txt_last_name, 10);
		Box b_address = BoxComponent.getHorizontalBox(lbl_address, txt_address, 10);
		Box b_gender = BoxComponent.getHorizontalBox(lbl_gender, rb_male, rb_female, 10);
		Box b_birthday = BoxComponent.getHorizontalBox(lbl_date_of_birth, txt_date_of_birth, 10);
		Box b_phone = BoxComponent.getHorizontalBox(lbl_phone, txt_phone, 10);
		Box b_account = BoxComponent.getHorizontalBox(lbl_account, txt_account, 10);
		Box b_postion = BoxComponent.getHorizontalBox(lbl_position, cbx_position, 10);

		Box b1 = BoxComponent.getHorizontalBox_NoPadding(b_id, b_person_code, b_gender, 30);
		Box b2 = BoxComponent.getHorizontalBox_NoPadding(b_first, b_birthday, 30);
		Box b3 = BoxComponent.getHorizontalBox_NoPadding(b_middle, b_phone, 30);
		Box b4 = BoxComponent.getHorizontalBox_NoPadding(b_last, b_account, 30);
		Box b5 = BoxComponent.getHorizontalBox_NoPadding(b_address, b_postion, 30);

		Box bv_info = Box.createVerticalBox();
		bv_info.add(Box.createVerticalStrut(10));
		bv_info.add(b1);
		bv_info.add(Box.createVerticalStrut(10));
		bv_info.add(b2);
		bv_info.add(Box.createVerticalStrut(10));
		bv_info.add(b3);
		bv_info.add(Box.createVerticalStrut(10));
		bv_info.add(b4);
		bv_info.add(Box.createVerticalStrut(10));
		bv_info.add(b5);
		bv_info.add(Box.createVerticalStrut(10));
		bv_info.setPreferredSize(new Dimension(bv_info.getPreferredSize().width, 200));
		
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
		Box bh = BoxComponent.getHorizontalBox(jsp_employee, 10);
		Box bv = BoxComponent.getVerticalBox(bh, 10);

		JPanel pnl = new JPanel(new BorderLayout());
		pnl.setBorder(BorderFactory.createTitledBorder(null, "Danh sách nhân viên:", TitledBorder.LEFT, TitledBorder.TOP,
				fontSan, Color.MAGENTA));
		pnl.add(bv);

		// Full
		Box bv_full = Box.createVerticalBox();
		bv_full.add(pnl_header);
		bv_full.add(Box.createVerticalStrut(10));
		bv_full.add(BoxComponent.getHorizontalBox(bv_info, 10));
		bv_full.add(Box.createVerticalStrut(20));
		bv_full.add(BoxComponent.getHorizontalBox(b_button, 10));

		add(BoxComponent.getHorizontalBox(pnl, 10));
		add(bv_full, BorderLayout.NORTH);
	}

}
