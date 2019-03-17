package ui.frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ui.component.BoxComponent;

public class Demo extends JFrame implements ActionListener {

	private JButton btn, btnShow;
	private JButton cbx1, cbx2, cbx3, cbx4, cbx5, cbx6, cbx7, cbx8, cbx9, cbx10, cbx11, cbx12, cbx13, cbx14, cbx15;
	private JPanel pnl;
	private Box b1;
	private Box b2;
	private Box b;
	
	public Demo() {
		setTitle("Login - ^^!");
		setSize(500, 400);
		setResizable(false);
		setLocationRelativeTo(null); // canh giua
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		init();
	}
	
	private void init() {
		
		pnl = new JPanel(new FlowLayout());
		pnl.setBorder(BorderFactory.createTitledBorder("List:"));
		
		cbx1 = new JButton("");
		cbx2 = new JButton("");
		cbx3 = new JButton("");
		cbx4 = new JButton("");
		cbx5 = new JButton("");
		cbx6 = new JButton("");
		cbx7 = new JButton("");
		cbx8 = new JButton("");
		cbx9 = new JButton("");
		cbx10 = new JButton("");
		cbx11 = new JButton("");
		cbx12 = new JButton("");
		cbx13 = new JButton("");
		cbx14 = new JButton("");
		cbx15 = new JButton("");
		getdata();
		
		btn = new JButton("OK");
		btn.addActionListener(this);
		btnShow = new JButton("Show");
		btnShow.addActionListener(this);
		
//		Box bvr1 = BoxComponent.getVerticalBox(cbx1, cbx4, cbx7,  10);
//		Box bvr2 = BoxComponent.getVerticalBox(cbx2, cbx5, cbx8,  10);
//		Box bvr3 = BoxComponent.getVerticalBox(cbx3, cbx6, cbx9,  10);
//		
//		Box bh = BoxComponent.getHorizontalBox(bvr1, bvr2, bvr3, 30);
//		pnl.add(bh);
		
		b1 = BoxComponent.getHorizontalBox(btn, btnShow, 10);
//		b2 = BoxComponent.getHorizontalBox(pnl, 10);
//		
//		b = BoxComponent.getVerticalBox(b1, b2, 10);
//		add(b);
		add(b1, BorderLayout.NORTH);
		add(pnl);
	}
	private void getdata() {
		for(int i = 1; i <= 15; i++) {
			returnButton(i).setText("R" + i);
			if(i <= 10) {
				pnl.add(returnButton(i));
			}
		}
	}
	
	private JButton returnButton(int index) {
		JButton cbx = new JButton();
		switch (index) {
			case 1:
				cbx = cbx1;
				break;
			case 2:
				cbx = cbx2;
				break;
			case 3:
				cbx = cbx3;
				break;
			case 4:
				cbx = cbx4;
				break;
			case 5:
				cbx = cbx5;
				break;
			case 6:
				cbx = cbx6;
				break;
			case 7:
				cbx = cbx7;
				break;
			case 8:
				cbx = cbx8;
				break;
			case 9:
				cbx = cbx9;
				break;
			case 10:
				cbx = cbx10;
				break;
			case 11:
				cbx = cbx11;
				break;
			case 12:
				cbx = cbx12;
				break;
			case 13:
				cbx = cbx13;
				break;
			case 14:
				cbx = cbx14;
				break;
			case 15:
				cbx = cbx15;
				break;
		}
		return cbx;
	}
	
	int index;
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btn)) {
			for(int i = 1; i < 13; i++) {
				if(i%2 != 0) {
					returnButton(i).setBackground(Color.LIGHT_GRAY);
					returnButton(i).setEnabled(true);
				}
				else 
				{
					index = i;
					returnButton(i).addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							
							JOptionPane.showMessageDialog(null, returnButton(index).getText().toString());
						}
					});
				}
			}
		}
		else if(o.equals(btnShow)) {
			for(int i = 1; i <= 13; i++) {
				returnButton(i).setText("Room " + i);
				pnl.add(returnButton(i));
			}
		}
		
	}

	public static void main(String[] args) {
		new Demo().setVisible(true);
	}

}
