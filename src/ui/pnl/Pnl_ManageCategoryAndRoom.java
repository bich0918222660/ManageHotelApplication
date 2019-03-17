package ui.pnl;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.component.BoxComponent;
import ui.pnl.component.Pnl_ManageCategory;
import ui.pnl.component.Pnl_ManageRoom;

public class Pnl_ManageCategoryAndRoom extends JPanel {

	private JPanel pnl_header;
	private Pnl_ManageCategory pnl_manage_category;
	private Pnl_ManageRoom pnl_manage_room;

	public Pnl_ManageCategoryAndRoom() {
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

			// Category
		pnl_manage_category = new Pnl_ManageCategory();

			// Room
		pnl_manage_room = new Pnl_ManageRoom();
		
	}

	private void gui() {
		this.add(pnl_header, BorderLayout.NORTH);
		
		Box bv = BoxComponent.getVerticalBox(pnl_manage_category, pnl_manage_room, 10);
		Box bh = BoxComponent.getHorizontalBox(bv, 10);
		add(bh);
	}

}
