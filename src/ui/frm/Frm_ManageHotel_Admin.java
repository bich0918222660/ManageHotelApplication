package ui.frm;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import ui.pnl.Pnl_HomePage;
import ui.pnl.Pnl_ManageCategoryAndRoom;
import ui.pnl.Pnl_ManageCustomer;
import ui.pnl.Pnl_ManageBooking;
import ui.pnl.Pnl_SearchAndBooking;

public class Frm_ManageHotel_Admin extends JFrame {

	private Font fontSan = new Font("Arial", Font.BOLD, 13);
	
	public Frm_ManageHotel_Admin() {
		setTitle("Manage Hotel Application - ^^!");
		setSize(1200, 1000);
		setResizable(false);
		setLocationRelativeTo(null); // canh giua
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
	}
	
	private void init() {
		// Icon Page
		ImageIcon tabIconHome = new ImageIcon("imgs/ic_home.png");
		ImageIcon tabIconBook = new ImageIcon("imgs/ic_book.png");
		ImageIcon tabIconManageList = new ImageIcon("imgs/ic_manage_list.png");
		ImageIcon tabIconManage = new ImageIcon("imgs/ic_manage.png");
		ImageIcon tabIconEmployee = new ImageIcon("imgs/ic_employee.png");
		
		// Tab Pane
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFont(fontSan);
        tabbedPane.addTab("Trang chủ", tabIconHome, new Pnl_HomePage(this), "click to show panel 0");
        tabbedPane.addTab("Tìm kiếm và đặt phòng", tabIconBook, new Pnl_SearchAndBooking(), "click to show panel 1");
        tabbedPane.addTab("Quản lý đơn đặt", tabIconManageList, new Pnl_ManageBooking(), "click to show panel 2");
        tabbedPane.addTab("Quản lý loại phòng", tabIconManage, new Pnl_ManageCategoryAndRoom(), "click to show panel 3");
        tabbedPane.addTab("Quản lý khách hàng", tabIconManage, new Pnl_ManageCustomer(), "click to show panel 4");
        
        add(tabbedPane);
	}
	
	public static void main(String[] args) {
		new Frm_ManageHotel_Admin().setVisible(true);
	}
	
}
