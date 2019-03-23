package ui.frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import entity.Account;
import ui.component.BoxComponent;
import ui.pnl.Pnl_ManageAccount;
import ui.pnl.Pnl_ManageBooking;
import ui.pnl.Pnl_ManageCategory;
import ui.pnl.Pnl_ManageCustomer;
import ui.pnl.Pnl_ManageEmployee;
import ui.pnl.Pnl_ManageRoom;
import ui.pnl.Pnl_ManageService;
import ui.pnl.Pnl_SearchAndBooking;

public class Frm_ManageHotel_Admin extends JFrame implements ActionListener {

	private Font fontSan = new Font("Arial", Font.BOLD, 14);
	
	private JButton btnBooking, btnManageBooking,
			btnManagePayment, btnManageCategory,
			btnManageRoom, btnManageService,
			btnManageCustomer, btnManageEmployee,
			btnLogout, btnManageAccount;
	
	private JLabel lblBooking, lblManageBooking,
			lblManagePayment, lblManageCategory,
			lblManageRoom, lblManageService, 
			lblManageCustomer, lblManageEmployee,
			lblLogout, lblManageAccount;
	
	private JPanel pnlMenu, pnlBody;
	
	private Account account;
	
	public Frm_ManageHotel_Admin(Account account) {
		setTitle("Manage Hotel Application - ^^!");
		setSize(1400, 1000);
		setResizable(false);
		setLocationRelativeTo(null); // canh giua
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.account = account;
		init();
		gui();
	}
	
	private void init() {
		// JPanel
		pnlMenu = new JPanel(new BorderLayout());
		pnlMenu.setBorder(BorderFactory.createTitledBorder(null, "Menu:", TitledBorder.LEFT, TitledBorder.TOP, fontSan, Color.BLACK));
		
		pnlBody = new JPanel(new BorderLayout());
		pnlBody.setBorder(BorderFactory.createTitledBorder(null, "Body:", TitledBorder.LEFT, TitledBorder.TOP, fontSan, Color.BLACK));
		
		// JButton
		btnBooking = new JButton(new ImageIcon("imgs/ic_booking.png"));
		btnBooking.setMargin(new Insets(0, 0, 0, 0));
		btnBooking.setBorder(null);
		btnBooking.addActionListener(this);
		
		btnManageBooking = new JButton(new ImageIcon("imgs/ic_manage_booking.png"));
		btnManageBooking.setMargin(new Insets(0, 0, 0, 0));
		btnManageBooking.setBorder(null);
		btnManageBooking.addActionListener(this);
		
		btnManagePayment = new JButton(new ImageIcon("imgs/ic_manage_payment.png"));
		btnManagePayment.setMargin(new Insets(0, 0, 0, 0));
		btnManagePayment.setBorder(null);
		btnManagePayment.addActionListener(this);
		
		btnManageCategory = new JButton(new ImageIcon("imgs/ic_manage_category.png"));
		btnManageCategory.setMargin(new Insets(0, 0, 0, 0));
		btnManageCategory.setBorder(null);
		btnManageCategory.addActionListener(this);
		
		btnManageRoom = new JButton(new ImageIcon("imgs/ic_manage_room.png"));
		btnManageRoom.setMargin(new Insets(0, 0, 0, 0));
		btnManageRoom.setBorder(null);
		btnManageRoom.addActionListener(this);
		
		btnManageService = new JButton(new ImageIcon("imgs/ic_manage_service.png"));
		btnManageService.setMargin(new Insets(0, 0, 0, 0));
		btnManageService.setBorder(null);
		btnManageService.addActionListener(this);

		btnManageCustomer = new JButton(new ImageIcon("imgs/ic_manage_customer.png"));
		btnManageCustomer.setMargin(new Insets(0, 0, 0, 0));
		btnManageCustomer.setBorder(null);
		btnManageCustomer.addActionListener(this);

		btnManageEmployee = new JButton(new ImageIcon("imgs/ic_manage_employee.png"));
		btnManageEmployee.setMargin(new Insets(0, 0, 0, 0));
		btnManageEmployee.setBorder(null);
		btnManageEmployee.addActionListener(this);
		
		btnManageAccount = new JButton(new ImageIcon("imgs/ic_manage_account.png"));
		btnManageAccount.setMargin(new Insets(0, 0, 0, 0));
		btnManageAccount.setBorder(null);
		btnManageAccount.addActionListener(this);

		btnLogout = new JButton(new ImageIcon("imgs/ic_logout.png"));
		btnLogout.setMargin(new Insets(0, 0, 0, 0));
		btnLogout.setBorder(null);
		btnLogout.addActionListener(this);
		
		// JLabel
		lblBooking = new JLabel("Đặt phòng");
		lblManageBooking = new JLabel("Quản lý đơn đặt");
		lblManagePayment = new JLabel("Quản lý đơn thanh toán");
		lblManageCategory = new JLabel("Quản lý loại phòng");
		lblManageRoom = new JLabel("Quản lý phòng");
		lblManageService = new JLabel("Quản lý dịch vụ");
		lblManageCustomer = new JLabel("Quản lý khách hàng");
		lblManageEmployee = new JLabel("Quản lý nhân viên");
		lblManageAccount = new JLabel("Quản lý tài khoản");
		lblLogout = new JLabel("Đăng xuất");
		
	}
	
	private void gui() {
		Box bv = Box.createVerticalBox();
		bv.add(Box.createVerticalStrut(2));
		bv.add(BoxComponent.getHorizontalBox(btnBooking, 10));
		bv.add(Box.createVerticalStrut(4));
		bv.add(BoxComponent.getHorizontalBox(lblBooking, 10));
		bv.add(Box.createVerticalStrut(10));
		bv.add(BoxComponent.getHorizontalBox(btnManageBooking, 10));
		bv.add(Box.createVerticalStrut(4));
		bv.add(BoxComponent.getHorizontalBox(lblManageBooking, 10));
		bv.add(Box.createVerticalStrut(10));
		bv.add(BoxComponent.getHorizontalBox(btnManagePayment, 10));
		bv.add(Box.createVerticalStrut(4));
		bv.add(BoxComponent.getHorizontalBox(lblManagePayment, 10));
		bv.add(Box.createVerticalStrut(10));
		bv.add(BoxComponent.getHorizontalBox(btnManageCategory, 10));
		bv.add(Box.createVerticalStrut(4));
		bv.add(BoxComponent.getHorizontalBox(lblManageCategory, 10));
		bv.add(Box.createVerticalStrut(10));
		bv.add(BoxComponent.getHorizontalBox(btnManageRoom, 10));
		bv.add(Box.createVerticalStrut(4));
		bv.add(BoxComponent.getHorizontalBox(lblManageRoom, 10));
		bv.add(Box.createVerticalStrut(10));
		bv.add(BoxComponent.getHorizontalBox(btnManageService, 10));
		bv.add(Box.createVerticalStrut(4));
		bv.add(BoxComponent.getHorizontalBox(lblManageService, 10));
		bv.add(Box.createVerticalStrut(10));
		bv.add(BoxComponent.getHorizontalBox(btnManageCustomer, 10));
		bv.add(Box.createVerticalStrut(4));
		bv.add(BoxComponent.getHorizontalBox(lblManageCustomer, 10));
		bv.add(Box.createVerticalStrut(10));
		bv.add(BoxComponent.getHorizontalBox(btnManageEmployee, 10));
		bv.add(Box.createVerticalStrut(4));
		bv.add(BoxComponent.getHorizontalBox(lblManageEmployee, 10));
		bv.add(Box.createVerticalStrut(10));
		bv.add(BoxComponent.getHorizontalBox(btnManageAccount, 10));
		bv.add(Box.createVerticalStrut(4));
		bv.add(BoxComponent.getHorizontalBox(lblManageAccount, 10));
		bv.add(Box.createVerticalStrut(10));
		bv.add(BoxComponent.getHorizontalBox(btnLogout, 10));
		bv.add(Box.createVerticalStrut(4));
		bv.add(BoxComponent.getHorizontalBox(lblLogout, 10));
		bv.add(Box.createVerticalStrut(2));
		
		pnlMenu.add(bv);
		this.add(pnlMenu, BorderLayout.WEST);
		Box b = BoxComponent.getHorizontalBox(BoxComponent.getVerticalBox(new Pnl_ManageBooking(this, account), 5), 5);
		pnlBody.add(b);
		this.add(pnlBody);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnBooking)) {
			pnlBody.removeAll();
			Box b = BoxComponent.getHorizontalBox(BoxComponent.getVerticalBox(new Pnl_SearchAndBooking(account), 5), 5);
			pnlBody.add(b);
			revalidate();
		}
		else if(o.equals(btnManageBooking)) {
			pnlBody.removeAll();
			Box b = BoxComponent.getHorizontalBox(BoxComponent.getVerticalBox(new Pnl_ManageBooking(this, account), 5), 5);
			pnlBody.add(b);
			revalidate();
		}
		else if(o.equals(btnManageCategory)) {
			pnlBody.removeAll();
			Box b = BoxComponent.getHorizontalBox(BoxComponent.getVerticalBox(new Pnl_ManageCategory(account), 20), 20);
			pnlBody.add(b);
			revalidate();
		}
		else if(o.equals(btnManageRoom)) {
			pnlBody.removeAll();
			Box b = BoxComponent.getHorizontalBox(BoxComponent.getVerticalBox(new Pnl_ManageRoom(account), 20), 20);
			pnlBody.add(b);
			revalidate();
		}
		else if(o.equals(btnManageCustomer)) {
			pnlBody.removeAll();
			Box b = BoxComponent.getHorizontalBox(BoxComponent.getVerticalBox(new Pnl_ManageCustomer(account), 20), 20);
			pnlBody.add(b);
			revalidate();
		}
		else if(o.equals(btnManageEmployee)) {
			if(account.getRole().equals("Super Admin")) {
				pnlBody.removeAll();
				Box b = BoxComponent.getHorizontalBox(BoxComponent.getVerticalBox(new Pnl_ManageEmployee(account), 20), 20);
				pnlBody.add(b);
				revalidate();
			}
			else {
				JOptionPane.showMessageDialog(null, "Bạn chưa được cấp quyền!");
			}
		}
		else if(o.equals(btnManageService)) {
			pnlBody.removeAll();
			Box b = BoxComponent.getHorizontalBox(BoxComponent.getVerticalBox(new Pnl_ManageService(account), 20), 20);
			pnlBody.add(b);
			revalidate();
		}
		else if(o.equals(btnLogout)) {
			Frm_Login frm = new Frm_Login();
			frm.setVisible(true);
			this.dispose();
		}
		else if(o.equals(btnManageService)) {
			
		}
		else if(o.equals(btnManageAccount)) {
			if(account.getRole().equals("Super Admin")) {
				pnlBody.removeAll();
				Box b = BoxComponent.getHorizontalBox(BoxComponent.getVerticalBox(new Pnl_ManageAccount(account), 20), 20);
				pnlBody.add(b);
				revalidate();
			}
			else {
				JOptionPane.showMessageDialog(null, "Bạn chưa được cấp quyền!");
			}
		}
	}

	
	public static void main(String[] args) {
		new Frm_ManageHotel_Admin(null).setVisible(true);
	}

}
