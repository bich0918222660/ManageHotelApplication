package ui.component;

import java.awt.Component;

import javax.swing.Box;

public class BoxComponent {
	
	private BoxComponent() {
	}
	
	public static Box getHorizontalBox(Component c, int marginSize) {
		Box b = Box.createHorizontalBox();
		b.add(Box.createHorizontalStrut(marginSize));
		b.add(c);
		b.add(Box.createHorizontalStrut(marginSize));
		return b;
	}
	
	public static Box getHorizontalBox(Component c1, Component c2, int marginSize) {
		Box b = Box.createHorizontalBox();
		b.add(Box.createHorizontalStrut(marginSize));
		b.add(c1);
		b.add(Box.createHorizontalStrut(marginSize));
		b.add(c2);
		b.add(Box.createHorizontalStrut(marginSize));
		return b;
	}
	
	public static Box getHorizontalBox(Component c1, Component c2, Component c3, Component c4, int marginSize) {
		Box b = Box.createHorizontalBox();
		b.add(Box.createHorizontalStrut(marginSize));
		b.add(c1);
		b.add(Box.createHorizontalStrut(marginSize));
		b.add(c2);
		b.add(Box.createHorizontalStrut(marginSize));
		b.add(c3);
		b.add(Box.createHorizontalStrut(marginSize));
		b.add(c4);
		b.add(Box.createHorizontalStrut(marginSize));
		return b;
	}
	
	public static Box getHorizontalBox_NoPadding(Component c1, Component c2, int marginSize) {
		Box b = Box.createHorizontalBox();
		b.add(c1);
		b.add(Box.createHorizontalStrut(marginSize));
		b.add(c2);
		return b;
	}
	
	public static Box getHorizontalBox_NoPadding(Component c1, Component c2, Component c3, int marginSize) {
		Box b = Box.createHorizontalBox();
		b.add(c1);
		b.add(Box.createHorizontalStrut(marginSize));
		b.add(c2);
		b.add(Box.createHorizontalStrut(marginSize));
		b.add(c3);
		return b;
	}
	
	public static Box getHorizontalBox_NoPadding(Component c1, Component c2, Component c3, Component c4, int marginSize) {
		Box b = Box.createHorizontalBox();
		b.add(c1);
		b.add(Box.createHorizontalStrut(marginSize));
		b.add(c2);
		b.add(Box.createHorizontalStrut(marginSize));
		b.add(c3);
		b.add(Box.createHorizontalStrut(marginSize));
		b.add(c4);
		return b;
	}
	
	public static Box getHorizontalBox(Component c1, Component c2, Component c3, int marginSize) {
		Box b = Box.createHorizontalBox();
		b.add(Box.createHorizontalStrut(marginSize));
		b.add(c1);
		b.add(Box.createHorizontalStrut(marginSize));
		b.add(c2);
		b.add(Box.createHorizontalStrut(marginSize));
		b.add(c3);
		b.add(Box.createHorizontalStrut(marginSize));
		return b;
	}
	
	public static Box getVerticalBox(Component c1, Component c2, int marginSize) {
		Box b = Box.createVerticalBox();
		b.add(Box.createVerticalStrut(marginSize));
		b.add(c1);
		b.add(Box.createVerticalStrut(marginSize));
		b.add(c2);
		b.add(Box.createVerticalStrut(marginSize));
		return b;
	}
	
	public static Box getVerticalBox(Component c1, Component c2, Component c3, int marginSize) {
		Box b = Box.createVerticalBox();
		b.add(Box.createVerticalStrut(marginSize));
		b.add(c1);
		b.add(Box.createVerticalStrut(marginSize));
		b.add(c2);
		b.add(Box.createVerticalStrut(marginSize));
		b.add(c3);
		b.add(Box.createVerticalStrut(marginSize));
		return b;
	}
	
	public static Box getVerticalBox(Component c1, Component c2, Component c3, Component c4, int marginSize) {
		Box b = Box.createVerticalBox();
		b.add(Box.createVerticalStrut(marginSize));
		b.add(c1);
		b.add(Box.createVerticalStrut(marginSize));
		b.add(c2);
		b.add(Box.createVerticalStrut(marginSize));
		b.add(c3);
		b.add(Box.createVerticalStrut(marginSize));
		b.add(c4);
		b.add(Box.createVerticalStrut(marginSize));
		return b;
	}
	
	public static Box getVerticalBox(Component c, int marginSize) {
		Box b = Box.createVerticalBox();
		b.add(Box.createVerticalStrut(marginSize));
		b.add(c);
		b.add(Box.createVerticalStrut(marginSize));
		return b;
	}

}
