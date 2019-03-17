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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import ui.component.BoxComponent;
import ui.pnl.component.Pnl_ManageAccount;
import ui.pnl.component.Pnl_ManageEmployee;

public class Pnl_ManageEmployeeAndAccount extends JPanel {

	private Font fontSan = new Font("Arial", Font.BOLD, 18);
	private JPanel pnl_header;
	private Pnl_ManageEmployee pnl_manage_employee;
	private Pnl_ManageAccount pnl_manage_account;

	public Pnl_ManageEmployeeAndAccount() {
		setLayout(new BorderLayout());
		init();
		gui();
	}

	private void init() {
		// Jpanel
		pnl_header = new JPanel(new BorderLayout());
		JLabel lbl = new JLabel();
		lbl.setIcon(new ImageIcon("imgs/rose.jpeg"));
		pnl_header.add(lbl);
		
		pnl_manage_employee = new Pnl_ManageEmployee();
		pnl_manage_account = new Pnl_ManageAccount();
	}
	
	private void gui() {
		this.add(pnl_header, BorderLayout.NORTH);
		
		Box bv = BoxComponent.getVerticalBox(pnl_manage_account, pnl_manage_employee, 10);
		Box bh = BoxComponent.getHorizontalBox(bv, 10);
		add(bh);
	}

}
