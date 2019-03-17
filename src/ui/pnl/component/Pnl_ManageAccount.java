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
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import ui.component.BoxComponent;

public class Pnl_ManageAccount extends JPanel {

	private Font fontSan = new Font("Arial", Font.BOLD, 18);

	private JLabel lbl_username, lbl_password, lbl_role;
	private JTextField txt_username, txt_password;
	private JComboBox<String> cbx_roles;
	private JButton btn_add, btn_update, btn_save, btn_load, btn_delete;
	private JTable tbl_account;
	private DefaultTableModel tbl_model_account;
	private JScrollPane jsp_account;

	public Pnl_ManageAccount() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(null, "Quản lý tài khoản:", TitledBorder.LEFT,
				TitledBorder.TOP, fontSan, Color.MAGENTA));
		init();
		gui();
	}

	private void init() {
		// JLabel
		lbl_username = new JLabel("Tài khoản:");
		lbl_password = new JLabel("Mật khẩu:");
		lbl_role = new JLabel("Vai trò:");

		lbl_password.setPreferredSize(lbl_username.getPreferredSize());
		lbl_role.setPreferredSize(lbl_username.getPreferredSize());

		// JTextField
		txt_password = new JTextField();
		txt_username = new JTextField();

		// JComboBox
		cbx_roles = new JComboBox<>();
		cbx_roles.setPreferredSize(new Dimension(200, cbx_roles.getPreferredSize().height));
		cbx_roles.addItem("User");
		cbx_roles.addItem("Admin");
		cbx_roles.addItem("Super Admin");

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
		String[] header = { "Tài khoản", "Mật khẩu", "Vai trò" };
		tbl_account = new JTable(tbl_model_account = new DefaultTableModel(header, 0));

		// JScrollPane
		jsp_account = new JScrollPane(tbl_account);
		jsp_account.setPreferredSize(new Dimension(tbl_account.getPreferredSize().width, 300));

	}

	private void gui() {
		// Info
		Box b_username = BoxComponent.getHorizontalBox(lbl_username, txt_username, 10);
		Box b_password = BoxComponent.getHorizontalBox(lbl_password, txt_password, 10);
		Box b_roles = BoxComponent.getHorizontalBox(lbl_role, cbx_roles, 10);

		Box b_info = BoxComponent.getHorizontalBox_NoPadding(b_username, b_password, b_roles, 30);

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
		Box bh = BoxComponent.getHorizontalBox(jsp_account, 10);
		Box bv = BoxComponent.getVerticalBox(bh, 10);

		JPanel pnl = new JPanel(new BorderLayout());
		pnl.setBorder(BorderFactory.createTitledBorder(null, "Danh sách tài khoản:", TitledBorder.LEFT,
				TitledBorder.TOP, fontSan, Color.MAGENTA));
		pnl.add(bv);

		// Full
		Box bv_full = Box.createVerticalBox();
		bv_full.add(Box.createVerticalStrut(10));
		bv_full.add(b_info);
		bv_full.add(Box.createVerticalStrut(20));
		bv_full.add(b_button);
		bv_full.add(Box.createVerticalStrut(20));
		bv_full.add(pnl);
		Box bh_full = BoxComponent.getHorizontalBox(bv_full, 10);

		add(bh_full);

	}

}
