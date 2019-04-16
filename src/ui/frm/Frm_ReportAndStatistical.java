package ui.frm;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import entity.Account;
import ui.component.BoxComponent;
import ui.pnl.Pnl_ReportCategory;
import ui.pnl.Pnl_Statistical;

public class Frm_ReportAndStatistical extends JFrame implements ActionListener  {
	
	private Font fontSan = new Font("Arial", Font.BOLD, 14);
	
	private JLabel lbl_report, lbl_statistical, lbl_manage;
	private JButton btn_report, btn_statistical, btn_manage;
	private JPanel pnl_menu, pnl_body;
	
	private Account account;
	
	public Frm_ReportAndStatistical(Account account) {
		setTitle("Report and Statistical Category - ^^!");
		setLayout(new BorderLayout());
		setSize(1100, 700);
		setResizable(false);
		setLocationRelativeTo(null); // canh giua
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.account = account;
		init();
		gui();
	}
	
	private void init() {
		// JPanel
		pnl_menu = new JPanel(new BorderLayout());
		pnl_menu.setBorder(BorderFactory.createTitledBorder(null, "Menu:", TitledBorder.LEFT, TitledBorder.TOP, fontSan, Color.BLACK));
			
		pnl_body = new JPanel(new BorderLayout());
		pnl_body.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.LEFT, TitledBorder.TOP, fontSan, Color.BLACK));
		
		// JButton
		btn_report = new JButton(new ImageIcon("imgs/ic_report_category.png"));
		btn_report.setMargin(new Insets(0, 0, 0, 0));
		btn_report.setBorder(null);
		btn_report.addActionListener(this);

		btn_statistical = new JButton(new ImageIcon("imgs/ic_statistical.png"));
		btn_statistical.setMargin(new Insets(0, 0, 0, 0));
		btn_statistical.setBorder(null);
		btn_statistical.addActionListener(this);
		
		btn_manage = new JButton(new ImageIcon("imgs/ic_manage_hotel.png"));
		btn_manage.setMargin(new Insets(0, 0, 0, 0));
		btn_manage.setBorder(null);
		btn_manage.addActionListener(this);
		
		// JLabel
		lbl_report = new JLabel("Báo cáo");
		lbl_statistical = new JLabel("Thống kê");
		lbl_manage = new JLabel("Quản lý khách sạn");
	}
	
	private void gui() {
		Box bv = Box.createVerticalBox();
		bv.add(Box.createVerticalStrut(20));
		bv.add(BoxComponent.getHorizontalBox(btn_report, 10));
		bv.add(Box.createVerticalStrut(6));
		bv.add(BoxComponent.getHorizontalBox(lbl_report, 10));
		bv.add(Box.createVerticalStrut(20));
		bv.add(BoxComponent.getHorizontalBox(btn_statistical, 10));
		bv.add(Box.createVerticalStrut(6));
		bv.add(BoxComponent.getHorizontalBox(lbl_statistical, 10));
		bv.add(Box.createVerticalStrut(20));
		bv.add(BoxComponent.getHorizontalBox(btn_manage, 10));
		bv.add(Box.createVerticalStrut(6));
		bv.add(BoxComponent.getHorizontalBox(lbl_manage, 10));
		bv.add(Box.createVerticalStrut(600));
		
		pnl_menu.add(bv);
		this.add(pnl_menu, BorderLayout.WEST);
		Box b = BoxComponent.getHorizontalBox(BoxComponent.getVerticalBox(new Pnl_Statistical(), 5), 5);
		pnl_body.add(b);
		Box bx = Box.createVerticalBox();
		bx.add(Box.createVerticalStrut(8));
		bx.add(BoxComponent.getHorizontalBox(pnl_body, 2));
		bx.add(Box.createVerticalStrut(2));
		this.add(bx);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btn_report)) {
			pnl_body.removeAll();
			Box b = BoxComponent.getHorizontalBox(BoxComponent.getVerticalBox(new Pnl_ReportCategory(), 5), 5);
			pnl_body.add(b);
			revalidate();
		}
		else if(o.equals(btn_statistical)) {
			pnl_body.removeAll();
			Box b = BoxComponent.getHorizontalBox(BoxComponent.getVerticalBox(new Pnl_Statistical(), 5), 5);
			pnl_body.add(b);
			revalidate();
		}
		else if(o.equals(btn_manage)) {
			Frm_ManageHotel_Admin frm = new Frm_ManageHotel_Admin(account);
			frm.setVisible(true);
			this.dispose();
		}
	}
	
	public static void main(String[] args) {
		new Frm_ReportAndStatistical(null).setVisible(true);
	}
	
}
