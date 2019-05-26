package ui.pnl.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import ui.component.BoxComponent;

public class Pnl_CreateCustomer extends JPanel {
	
	private Font fontSan = new Font("Arial", Font.BOLD, 18);
	
	private JLabel lbl_first_name, lbl_email,
		lbl_middle_name, lbl_last_name,
		lbl_gender, lbl_date_of_birth,
		lbl_phone, lbl_address, lbl_person_code;
	private JTextField txt_first_name, txt_middle_name,
		txt_last_name, txt_address,
		txt_phone, txt_email, txt_person_code;
	private JDateChooser dcs_date_of_birth;
	private ButtonGroup bg;
	private JRadioButton rb_male, rb_female;
	private JButton btn_add;
	
	public Pnl_CreateCustomer() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(null, "Thêm mới khách hàng:", TitledBorder.LEFT, TitledBorder.TOP,
				fontSan, Color.MAGENTA));
		init();
		gui();
	}
	
	private void init() {
		// JLabel
		lbl_first_name = new JLabel("Họ:");
		lbl_middle_name = new JLabel("Tên lót:");
		lbl_last_name = new JLabel("Tên:");
		lbl_gender = new JLabel("Giới tính:");
		lbl_date_of_birth = new JLabel("Ngày sinh:");
		lbl_phone = new JLabel("Số điện thoại");
		lbl_address = new JLabel("Địa chỉ:");
		lbl_email = new JLabel("Email:");
		lbl_person_code = new JLabel("Chứng minh nhân dân:");
		
		lbl_first_name.setPreferredSize(lbl_phone.getPreferredSize());
		lbl_date_of_birth.setPreferredSize(lbl_phone.getPreferredSize());
		lbl_address.setPreferredSize(lbl_phone.getPreferredSize());
		
		// JTextField
		txt_first_name = new JTextField();
		txt_middle_name = new JTextField();
		txt_last_name = new JTextField();
		txt_address = new JTextField();
		txt_phone = new JTextField();
		txt_email = new JTextField();
		txt_person_code = new JTextField();
		
		txt_first_name.setEnabled(false);
		txt_middle_name.setEnabled(false);
		txt_last_name.setEnabled(false);
		txt_address.setEnabled(false);
		txt_phone.setEnabled(false);
		txt_email.setEnabled(false);
		txt_person_code.setEnabled(false);
		
		// JDateChooser
		dcs_date_of_birth = new JDateChooser();
		dcs_date_of_birth.setDateFormatString("dd/MM/yyyy");
		dcs_date_of_birth.setDate(new Date());
		
		//JRadioButton
		rb_female = new JRadioButton("Nữ");
		rb_male = new JRadioButton("Nam");
		rb_male.setSelected(true);
		rb_male.setEnabled(false);

		bg = new ButtonGroup();
		bg.add(rb_female);
		bg.add(rb_male);
		
		// JButton
		btn_add = new JButton(new ImageIcon(this.getClass().getResource("/ic_add.png")));
		btn_add.setMargin(new Insets(0, 0, 0, 0));
		btn_add.setBorder(null);
		btn_add.setBackground(Color.decode("#ebebeb"));
	}
	
	private void gui() {
		Box b_firstName = BoxComponent.getHorizontalBox(lbl_first_name, txt_first_name, 10);
		Box b_lastName = BoxComponent.getHorizontalBox(lbl_last_name, txt_last_name, 10);
		Box b_middleName = BoxComponent.getHorizontalBox(lbl_middle_name, txt_middle_name, 10);
		Box b_gender = BoxComponent.getHorizontalBox(lbl_gender, rb_male, rb_female, 10);
		Box b_birthDate = BoxComponent.getHorizontalBox(lbl_date_of_birth, dcs_date_of_birth, 10);
		Box b_phone = BoxComponent.getHorizontalBox(lbl_phone, txt_phone, 10);
		Box b_email = BoxComponent.getHorizontalBox(lbl_email, txt_email, 10);
		Box b_address = BoxComponent.getHorizontalBox(lbl_address, txt_address, 10);
		Box b_person_code = BoxComponent.getHorizontalBox(lbl_person_code, txt_person_code, 10);
		
		Box b1 = BoxComponent.getHorizontalBox_NoPadding(b_firstName, b_middleName, b_lastName, 30);
		Box b2 = BoxComponent.getHorizontalBox_NoPadding(b_birthDate, b_gender, b_person_code, 30);
		Box b3 = BoxComponent.getHorizontalBox_NoPadding(b_phone, b_email, 30);
		Box b5 = Box.createHorizontalBox();
		b5.add(Box.createHorizontalStrut(1150));
		b5.add(btn_add);
		b5.add(Box.createHorizontalStrut(10));
		
		Box bv = Box.createVerticalBox();
		bv.add(Box.createVerticalStrut(10));
		bv.add(b1);
		bv.add(Box.createVerticalStrut(10));
		bv.add(b2);
		bv.add(Box.createVerticalStrut(10));
		bv.add(b3);
		bv.add(Box.createVerticalStrut(10));
		bv.add(b_address);
		bv.add(Box.createVerticalStrut(5));
		bv.add(b5);
		bv.add(Box.createVerticalStrut(5));
		
		this.add(bv);
	}

	public JTextField getTxt_first_name() {
		return txt_first_name;
	}

	public JTextField getTxt_middle_name() {
		return txt_middle_name;
	}

	public JTextField getTxt_last_name() {
		return txt_last_name;
	}

	public JTextField getTxt_address() {
		return txt_address;
	}

	public JTextField getTxt_phone() {
		return txt_phone;
	}

	public JTextField getTxt_email() {
		return txt_email;
	}
	
	public JTextField getTxt_person_code() {
		return txt_person_code;
	}

	public JDateChooser getDcs_date_of_birth() {
		return dcs_date_of_birth;
	}

	public JRadioButton getRb_male() {
		return rb_male;
	}

	public JRadioButton getRb_female() {
		return rb_female;
	}

	public JButton getBtn_add() {
		return btn_add;
	}

}
