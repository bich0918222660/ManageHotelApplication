package ui.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;

public class ButtonComponent extends JButton {
	
	private String title;
	private String color;
	private String backgroundColor;
	private int width;
	private int height;
	
	public ButtonComponent(String title, String color, Font font, String backgroundColor, Insets margin) {
		this.setText(title);
		this.setFont(font);
		this.setForeground(Color.decode(color));
		this.setBackground(Color.decode(backgroundColor));
//		this.setPreferredSize(new Dimension(width, height));
//		this.setBounds(0, 0, width, height);
		this.setSize(width, height);
		this.setMargin(margin);
	}
	
}
