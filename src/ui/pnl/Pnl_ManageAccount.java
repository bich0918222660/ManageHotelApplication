package ui.pnl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.AccountDAO;
import entity.Account;
import ui.component.BoxComponent;

public class Pnl_ManageAccount extends JPanel implements ActionListener {

	private Font fontSan = new Font("Arial", Font.BOLD, 14);

	private JLabel lbl_username, lbl_password, lbl_role;
	private JTextField txt_username, txt_password;
	private JComboBox<String> cbx_roles;
	private JButton btn_add, btn_update, btn_save, btn_load, btn_delete;
	private JTable tbl_account;
	private DefaultTableModel tbl_model_account;
	private JScrollPane jsp_account;
	private JPanel pnl_header;
	
	private Account account;
	
	private AccountDAO adao = new AccountDAO();
	
	private List<Account> accounts;

	public Pnl_ManageAccount(Account account) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(null, "Quản lý tài khoản:", TitledBorder.LEFT,
				TitledBorder.TOP, fontSan, Color.MAGENTA));
		this.account = account;
		init();
		gui();
		getData();
		setEditable(false);
		eventTable();
	}
	
	private void eventTable() {
		tbl_account.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tbl_account.getSelectedRow();
				
				String username = tbl_model_account.getValueAt(row, 0).toString();
				String password = tbl_model_account.getValueAt(row, 1).toString();
				String role = tbl_model_account.getValueAt(row, 2).toString();
				
				txt_username.setText(username);
				txt_password.setText(password);
				cbx_roles.setSelectedItem(role);
			}
		});
	}

	private void getData() {
		tbl_model_account.setRowCount(0);
		try {
			accounts = adao.getAll();
			for(Account a : accounts) {
				String[] row = {
					a.getUsername(), a.getPassword(), a.getRole()
				};
				tbl_model_account.addRow(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void init() {
		// Jpanel
		pnl_header = new JPanel(new BorderLayout());
		JLabel lbl = new JLabel();
		lbl.setIcon(new ImageIcon("imgs/rose.jpeg"));
		pnl_header.add(lbl);
				
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
		btn_add.addActionListener(this);

		btn_update = new JButton(new ImageIcon("imgs/ic_edit.png"));
		btn_update.setMargin(new Insets(0, 0, 0, 0));
		btn_update.setBorder(null);
		btn_update.addActionListener(this);

		btn_delete = new JButton(new ImageIcon("imgs/ic_delete.png"));
		btn_delete.setMargin(new Insets(0, 0, 0, 0));
		btn_delete.setBorder(null);
		btn_delete.addActionListener(this);

		btn_load = new JButton(new ImageIcon("imgs/ic_load.png"));
		btn_load.setMargin(new Insets(0, 0, 0, 0));
		btn_load.setBorder(null);
		btn_load.addActionListener(this);

		btn_save = new JButton(new ImageIcon("imgs/ic_save.png"));
		btn_save.setMargin(new Insets(0, 0, 0, 0));
		btn_save.setBorder(null);
		btn_save.addActionListener(this);

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
		bv_full.add(BoxComponent.getHorizontalBox(b_button, 10));
		bv_full.add(Box.createVerticalStrut(20));
		bv_full.add(BoxComponent.getHorizontalBox(pnl, 10));
		bv_full.add(Box.createVerticalStrut(20));
		Box bh_full = BoxComponent.getHorizontalBox(bv_full, 10);

		add(bh_full);
		add(BoxComponent.getVerticalBox(pnl_header, BoxComponent.getVerticalBox(BoxComponent.getHorizontalBox(b_info, 10), 20), 0), BorderLayout.NORTH);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		String username = txt_username.getText();
		String password = txt_password.getText();
		String role = cbx_roles.getSelectedItem().toString();
		if(o.equals(btn_load)) {
			getData();
		}
		else if(o.equals(btn_delete)) {
			int answer = JOptionPane.showConfirmDialog(null,
					"Bạn có thực sự muốn xóa tài khoản ( " + username + " ) không?", "Xóa thông tin tài khoản",
					JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION) {
				delete(username);
			}
		}
		else if(o.equals(btn_add)) {
			setEditable(true);
			btn_update.setEnabled(true);
		}
		else if(o.equals(btn_update)) {
			setEditable(true);
			btn_add.setEnabled(true);
		}
		else if(o.equals(btn_save)) {
			if(btn_add.isEnabled()) {
				if(username.equals("") && password.equals("") && role.equals("")) {
					add(username, password, role);
				}
				else {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập đầy đủ thông tin!");
				}
			}
			else {
				if(username.equals("") && password.equals("") && role.equals("")) {
					update(username, password, role);
				}
				else {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập đầy đủ thông tin!");
				}
			}
		}
	}
	
	private void setEditable(Boolean status) {
		txt_username.setEditable(status);
		txt_password.setEditable(status);
		cbx_roles.setEditable(status);
		btn_add.setEnabled(!status);
		btn_update.setEnabled(!status);
	}
	
	private void add(String username, String password, String role) {
		try {
			adao.insert(new Account(username, password, role));
			JOptionPane.showMessageDialog(null, "Thêm mới tài khoản thành công!");
			getData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void update(String username, String password, String role) {
		try {
			adao.update(new Account(username, password, role));
			JOptionPane.showMessageDialog(null, "Cập nhật tài khoản thành công!");
			getData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void delete(String username) {
		try {
			adao.delete(username);
			JOptionPane.showMessageDialog(null, "Xóa thông tin tài khoản thành công!");
			getData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
