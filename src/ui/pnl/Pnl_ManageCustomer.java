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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import dao.CustomerDAO;
import entity.Account;
import entity.Customer;
import ui.component.BoxComponent;
import ui.service.ValidationService;

public class Pnl_ManageCustomer extends JPanel implements ActionListener {

	private Font fontSan = new Font("Arial", Font.BOLD, 14);

	private JLabel lbl_id, lbl_first_name, lbl_middle_name, lbl_last_name, lbl_address, lbl_gender, lbl_phone,
			lbl_date_of_birth, lbl_account, lbl_person_code, lbl_email;
	private JTextField txt_id, txt_first_name, txt_middle_name, txt_last_name, 
			txt_address, txt_phone, txt_account, txt_person_code, txt_email;
	private JDateChooser dcs_date_of_birth;
	private JRadioButton rb_male, rb_female;
	private ButtonGroup bg;
	private JButton btn_add, btn_update, btn_save, btn_load, btn_delete;
	private JTable tbl_customer;
	private DefaultTableModel tbl_model_customer;
	private JScrollPane jsp_customer;

	private JPanel pnl_header;

	private JPanel pnl_body;
	
	private Account account;
	
	private CustomerDAO cdao;

	public Pnl_ManageCustomer(Account account) {
		setLayout(new BorderLayout());
		this.account = account;
		this.cdao = new CustomerDAO();
		init();
		gui();
		setEditable(false);
		getData();
		eventTable();
	}

	private void eventTable() {
		tbl_customer.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tbl_customer.getSelectedRow();
				int customerID = Integer.parseInt(tbl_model_customer.getValueAt(row, 0).toString());
				Customer c = getCustomer(customerID);
				txt_id.setText(customerID + "");
				txt_first_name.setText(c.getFirstName());
				txt_last_name.setText(c.getLastName());
				txt_middle_name.setText(c.getMiddleName());
				if(c.getGender().equals("Nữ")) {
					rb_female.setSelected(true);
				}
				else {
					rb_male.setSelected(true);
				}
				dcs_date_of_birth.setDate(c.getDateOfBirth());
				txt_person_code.setText(c.getPersonCode());
				txt_phone.setText(c.getPhone());
				txt_address.setText(c.getAddress());
				txt_email.setText(c.getEmail());
				if(c.getAccountID() != null) {
					txt_account.setText(c.getAccountID() + "");
				}
				else {
					txt_account.setText("");
				}
			}
			
		});
	}

	private void getData() {
		tbl_model_customer.setRowCount(0);
		for(Customer c : cdao.getAll()) {
			String[] row = {
				c.getCustomerID() + "", getCustomerName(c),
				c.getGender(), getDateFormat(c.getDateOfBirth()), c.getPhone(), c.getPersonCode() 
			};
			tbl_model_customer.addRow(row);
		}
	}
	
	private String getCustomerName(Customer c) {
		String name = "";
		if(c.getMiddleName().equals("")) {
			name = c.getFirstName() + " " + c.getLastName();
		} else {
			name = c.getFirstName() + " " + c.getMiddleName() + " " + c.getLastName();
		}
		return name;
	}
	
	private String getDateFormat(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}
	
	private Customer getCustomer(int customerID) {
		Customer c = null;
		for(Customer i : cdao.getAll()) {
			if(i.getCustomerID() == customerID) {
				c = i;
			}
		}
		return c;
	}
	
	private void setEditable(boolean status) {
		txt_account.setEditable(status);
		txt_first_name.setEditable(status);
		txt_id.setEditable(status);
		txt_middle_name.setEditable(status);
		txt_last_name.setEditable(status);
		txt_address.setEditable(status);
		txt_phone.setEditable(status);
		txt_person_code.setEditable(status);
		txt_email.setEditable(status);
		rb_female.setEnabled(status);
		rb_male.setEnabled(status);
		btn_add.setEnabled(!status);
		btn_update.setEnabled(!status);
	}

	private void init() {
		// Jpanel
		pnl_header = new JPanel(new BorderLayout());
		JLabel lbl = new JLabel();
		lbl.setIcon(new ImageIcon(this.getClass().getResource("/rose.jpeg")));
		pnl_header.add(lbl);
		
		pnl_body = new JPanel(new BorderLayout());
		pnl_body.setBorder(BorderFactory.createTitledBorder(null, "Quản lý khách hàng:", TitledBorder.LEFT, TitledBorder.TOP,
				fontSan, Color.MAGENTA));

		// JLabel
		lbl_id = new JLabel("Mã khách hàng:");
		lbl_first_name = new JLabel("Họ:");
		lbl_middle_name = new JLabel("Tên lót:");
		lbl_last_name = new JLabel("Tên");
		lbl_address = new JLabel("Địa chỉ:");
		lbl_gender = new JLabel("Giới tính:");
		lbl_phone = new JLabel("Số điện thoại:");
		lbl_date_of_birth = new JLabel("Ngày sinh:");
		lbl_account = new JLabel("Tài khoản VIP:");
		lbl_person_code = new JLabel("Chứng minh nhân dân:");
		lbl_email = new JLabel("Email:");

		lbl_first_name.setPreferredSize(lbl_id.getPreferredSize());
		lbl_middle_name.setPreferredSize(lbl_id.getPreferredSize());
		lbl_last_name.setPreferredSize(lbl_id.getPreferredSize());
		lbl_address.setPreferredSize(lbl_id.getPreferredSize());
		lbl_email.setPreferredSize(lbl_id.getPreferredSize());

		lbl_phone.setPreferredSize(lbl_person_code.getPreferredSize());
		lbl_date_of_birth.setPreferredSize(lbl_person_code.getPreferredSize());
		lbl_account.setPreferredSize(lbl_person_code.getPreferredSize());
		lbl_gender.setPreferredSize(lbl_person_code.getPreferredSize());

		// JTextField
		txt_account = new JTextField();
		txt_first_name = new JTextField();
		txt_id = new JTextField();
		txt_middle_name = new JTextField();
		txt_last_name = new JTextField();
		txt_address = new JTextField();
		txt_phone = new JTextField();
		txt_person_code = new JTextField();
		txt_email = new JTextField();
		
		// JDateChooser
		dcs_date_of_birth = new JDateChooser();
		dcs_date_of_birth.setDateFormatString("dd/MM/yyyy");
		dcs_date_of_birth.setDate(new Date());

		// JRadioButton
		rb_male = new JRadioButton("Nam");
		rb_male.setSelected(true);
		rb_female = new JRadioButton("Nữ");
		
		bg = new ButtonGroup();
		bg.add(rb_female);
		bg.add(rb_male);

		// JButton
		btn_add = new JButton(new ImageIcon(this.getClass().getResource("/ic_add.png")));
		btn_add.setMargin(new Insets(0, 0, 0, 0));
		btn_add.setBorder(null);
		btn_add.addActionListener(this);
		btn_add.setBackground(Color.decode("#ebebeb"));

		btn_update = new JButton(new ImageIcon(this.getClass().getResource("/ic_edit.png")));
		btn_update.setMargin(new Insets(0, 0, 0, 0));
		btn_update.setBorder(null);
		btn_update.addActionListener(this);
		btn_update.setBackground(Color.decode("#ebebeb"));

		btn_delete = new JButton(new ImageIcon(this.getClass().getResource("/ic_delete.png")));
		btn_delete.setMargin(new Insets(0, 0, 0, 0));
		btn_delete.setBorder(null);
		btn_delete.addActionListener(this);
		btn_delete.setBackground(Color.decode("#ebebeb"));

		btn_load = new JButton(new ImageIcon(this.getClass().getResource("/ic_load.png")));
		btn_load.setMargin(new Insets(0, 0, 0, 0));
		btn_load.setBorder(null);
		btn_load.addActionListener(this);
		btn_load.setBackground(Color.decode("#ebebeb"));

		btn_save = new JButton(new ImageIcon(this.getClass().getResource("/ic_save.png")));
		btn_save.setMargin(new Insets(0, 0, 0, 0));
		btn_save.setBorder(null);
		btn_save.addActionListener(this);
		btn_save.setBackground(Color.decode("#ebebeb"));

		// JTable
		String[] header = { "Mã số", "Họ và tên", "Giới tính", "Ngày sinh", "Số điện thoại", "CMND" };
		tbl_customer = new JTable(tbl_model_customer = new DefaultTableModel(header, 0));
		tbl_customer.setRowHeight(35);
		
		JTableHeader tableHeader = tbl_customer.getTableHeader();
		tableHeader.setBackground(Color.decode("#67e0fe"));
		tableHeader.setFont(new Font("Arial", Font.BOLD, 13));
		tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 35));

		// JScrollPane
		jsp_customer = new JScrollPane(tbl_customer);
		jsp_customer.setPreferredSize(new Dimension(tbl_customer.getPreferredSize().width, 300));

	}

	private void gui() {
		// Info
		Box b_id = BoxComponent.getHorizontalBox(lbl_id, txt_id, 10);
		Box b_first = BoxComponent.getHorizontalBox(lbl_first_name, txt_first_name, 10);
		Box b_middle = BoxComponent.getHorizontalBox(lbl_middle_name, txt_middle_name, 10);
		Box b_last = BoxComponent.getHorizontalBox(lbl_last_name, txt_last_name, 10);
		Box b_address = BoxComponent.getHorizontalBox(lbl_address, txt_address, 10);
		Box b_gender = BoxComponent.getHorizontalBox(lbl_gender, rb_male, rb_female, 10);
		Box b_birthday = BoxComponent.getHorizontalBox(lbl_date_of_birth, dcs_date_of_birth, 10);
		Box b_phone = BoxComponent.getHorizontalBox(lbl_phone, txt_phone, 10);
		Box b_account = BoxComponent.getHorizontalBox(lbl_account, txt_account, 10);
		Box b_person_code = BoxComponent.getHorizontalBox(lbl_person_code, txt_person_code, 10);
		Box b_email = BoxComponent.getHorizontalBox(lbl_email, txt_email, 10);
		
		Box b_left = BoxComponent.getVerticalBox(b_id, b_first, b_middle, b_last, b_email, 10);
		Box b_right = BoxComponent.getVerticalBox(b_person_code, b_birthday, b_phone, b_account, b_gender, 10);

		Box bh_info = BoxComponent.getHorizontalBox_NoPadding(b_left, b_right, 40);
		Box bv_info = Box.createVerticalBox();
		bv_info.add(bh_info);
		bv_info.add(Box.createVerticalStrut(10));
		bv_info.add(b_address);
		bv_info.setMaximumSize(new Dimension(1200, 200));
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
		Box bh = BoxComponent.getHorizontalBox(jsp_customer, 10);
		Box bv = BoxComponent.getVerticalBox(bh, 10);

		JPanel pnl = new JPanel(new BorderLayout());
		pnl.setBorder(BorderFactory.createTitledBorder(null, "Danh sách khách hàng:", TitledBorder.LEFT,
				TitledBorder.TOP, fontSan, Color.MAGENTA));
		pnl.add(bv);

		// Full
		Box bv_full = Box.createVerticalBox();
		bv_full.add(Box.createVerticalStrut(10));
		bv_full.add(bv_info);
		bv_full.add(Box.createVerticalStrut(20));
		bv_full.add(b_button);
		bv_full.add(Box.createVerticalStrut(10));
		Box bh_full = BoxComponent.getHorizontalBox(bv_full, 10);

		pnl_body.add(BoxComponent.getVerticalBox(pnl_header, bh_full, 0));
		add(BoxComponent.getHorizontalBox(BoxComponent.getVerticalBox(pnl, 10), 10));
		add(BoxComponent.getHorizontalBox(pnl_body, 0), BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o.equals(btn_load)) {
			getData();
		}
		else if(o.equals(btn_add)) {
			setEditable(true);
			txt_id.setEditable(false);
			btn_update.setEnabled(true);
		}
		else if(o.equals(btn_update)) {
			setEditable(true);
			txt_id.setEditable(false);
			btn_add.setEnabled(true);
		}
		else if(o.equals(btn_save)) {
			try {
				String personCode = txt_person_code.getText().trim();
				String phone = txt_phone.getText().trim();
				String email = txt_email.getText().trim();
				String firstName = txt_first_name.getText().trim();
				String lastName = txt_last_name.getText().trim();
				String middleName = txt_middle_name.getText().trim();
				String gender = "";
				if(rb_female.isSelected())
					gender = "Nữ";
				else
					gender = "Nam";
				Date dateOfBirth = dcs_date_of_birth.getDate();
				String address = txt_address.getText().trim();
				String accountID = txt_account.getText().trim();
				if(!btn_add.isEnabled()) {
					if(!isExistInList(personCode)) {
						if(ValidationService.validatePersonCode(personCode) 
								&& ValidationService.validatePhone(phone) 
								&& ValidationService.validateEmail(email)) {
							Customer c = new Customer(firstName, middleName, lastName, gender, dateOfBirth, phone, address, email, accountID, personCode);
							try {
								cdao.insert(c);
								JOptionPane.showMessageDialog(null, "Thêm thông tin khách hàng thành công!");
								getData();
								setEditable(false);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Khách hàng này đã có trong danh sách!");
					}
				}
				else {
					int customerID = Integer.parseInt(txt_id.getText().trim());
					if(ValidationService.validatePersonCode(personCode) 
							&& ValidationService.validatePhone(phone) 
							&& ValidationService.validateEmail(email)) {
						Customer c = new Customer(customerID, firstName, 
								middleName, lastName, gender, 
								dateOfBirth, phone, address, email, 
								accountID, personCode);
						try {
							cdao.update(c);
							JOptionPane.showMessageDialog(null, "Cập nhật thông tin khách hàng thành công!");
							getData();
							setEditable(false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			} catch (NumberFormatException e2) {
			}
		}
		else if(o.equals(btn_delete)) {
			if(account.getRole().equals("Super Admin")) {
				int row = tbl_customer.getSelectedRow();
				if(row < 0) {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn thông tin khách hàng cần xóa!");
				}
				else {
					int id = Integer.parseInt(txt_id.getText());
					int answer = JOptionPane.showConfirmDialog(null,
							"Bạn có thực sự muốn xóa nhân viên - mã số: " + id + " không?", "Xóa thông tin khách hàng",
							JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {
						delete(id);
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Bạn chưa được cấp quyền!");
			}
		}
	}
	
	private boolean isExistInList(String personCode) {
		for(Customer c : cdao.getAll()) {
			if(c.getPersonCode().equals(personCode)) {
				return true;
			}
		}
		return false;
	}

	private void delete(int id) {
		try {
			cdao.delete(id);
			getData();
			JOptionPane.showMessageDialog(null, "Xóa thông tin khách hàng thành công!");
			removeText();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void removeText() {
		txt_first_name.setText("");
		txt_id.setText("");
		txt_middle_name.setText("");
		txt_last_name.setText("");
		txt_address.setText("");
		txt_phone.setText("");
		txt_account.setText("");
		txt_email.setText("");
		txt_person_code.setText("");
		dcs_date_of_birth.setDate(new Date());
		setEditable(false);
	}

}
