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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entity.Account;
import ui.component.BoxComponent;

public class Pnl_ManageService extends JPanel {

	private Font fontSan = new Font("Arial", Font.BOLD, 14);
	
	private JLabel lbl_service_id, lbl_service_name,
			lbl_price, lbl_description;
	private JTextField txt_service_id, txt_service_name, txt_price;
	private JTextArea txt_description;
	private JButton btn_add, btn_update, btn_delete, btn_load;
	private JTable tbl_service;
	private DefaultTableModel tbl_model_service;
	private JScrollPane jsp_service;

	private JPanel pnl_header;
	
	private Account account;
	
	public Pnl_ManageService(Account account) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(null, "Quản lý thông tin dịch vụ:", TitledBorder.LEFT, TitledBorder.TOP, fontSan, Color.MAGENTA));
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
		lbl_description = new JLabel("Mô tả:");
		lbl_service_name = new JLabel("Tên dịch vụ:");
		lbl_service_id = new JLabel("Mã dịch vụ:");
		lbl_price = new JLabel("Đơn giá:");
		
		lbl_description.setPreferredSize(lbl_service_id.getPreferredSize());
		
		// JTextField
		txt_service_id = new JTextField();
		txt_service_name = new JTextField();
		txt_price = new JTextField();
		
		// JTextArea
		txt_description = new JTextArea();
		txt_description.setRows(3);
		
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
		
		// JTable
		String[] header = {
			"Mã dịch vụ", "Tên dịch vụ", "Đơn giá"
		};
		tbl_service = new JTable(tbl_model_service = new DefaultTableModel(header, 0));
		
		// JScrollPane
		jsp_service = new JScrollPane(tbl_service);
		jsp_service.setPreferredSize(new Dimension(tbl_service.getPreferredSize().width, 300));
	}
	
	private void gui() {
		// Info
		Box b_id = BoxComponent.getHorizontalBox(lbl_service_id, txt_service_id, 10);
		Box b_name = BoxComponent.getHorizontalBox(lbl_service_name, txt_service_name, 10);
		Box b_price = BoxComponent.getHorizontalBox(lbl_price, txt_price, 10);
		
		Box b1 = BoxComponent.getHorizontalBox_NoPadding(b_id, b_name, 30);
		Box b2 = BoxComponent.getHorizontalBox_NoPadding(b1, b_price, 30);
		
		Box b_desc = BoxComponent.getHorizontalBox(lbl_description, txt_description, 10);
		
		Box b_info = BoxComponent.getVerticalBox(b2, b_desc, 10);
		b_info.setPreferredSize(new Dimension(b_info.getPreferredSize().width, 120));
		
		// Button
		Box b_button = Box.createHorizontalBox();
		b_button.add(Box.createHorizontalStrut(970));
		b_button.add(btn_add);
		b_button.add(Box.createHorizontalStrut(15));
		b_button.add(btn_update);
		b_button.add(Box.createHorizontalStrut(15));
		b_button.add(btn_delete);
		b_button.add(Box.createHorizontalStrut(15));
		b_button.add(btn_load);
		b_button.add(Box.createHorizontalStrut(10));

		// table
		Box bh = BoxComponent.getHorizontalBox(jsp_service, 10);
		Box bv = BoxComponent.getVerticalBox(bh, 10);
		
		JPanel pnl = new JPanel(new BorderLayout());
		pnl.setBorder(BorderFactory.createTitledBorder(null, "Danh sách dịch vụ:", TitledBorder.LEFT, TitledBorder.TOP, fontSan, Color.MAGENTA));
		pnl.add(bv);
		
		// Full
		Box bv_full = BoxComponent.getVerticalBox(b_button, pnl, 10);
		Box bh_full = BoxComponent.getHorizontalBox(bv_full, 10);
		
		add(bh_full);
		add(BoxComponent.getVerticalBox(pnl_header, b_info, 0), BorderLayout.NORTH);
	}
}
