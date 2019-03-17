package ui.pnl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ui.component.BoxComponent;
import ui.frm.Frm_Login;
import ui.frm.Frm_ManageHotel_SuperAdmin;

public class Pnl_HomePage extends JPanel {

	private BufferedImage image;
	private JLabel lblWelcome;
	private JButton btnLogout;

	private Font fontSan = new Font("Arial", Font.BOLD, 18);
	
	public Pnl_HomePage(JFrame obj) {
		setLayout(new BorderLayout());
		JPanel panel = new HomePage();
		add(panel);
		
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Frm_Login frm = new Frm_Login();
				frm.setVisible(true);
				obj.dispose();
			}
		});
	}
	
	public class HomePage extends JPanel {
		
		public HomePage()
	    {
	        setOpaque(true);
	        setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
	        try
	        {
	            image = ImageIO.read(new File("imgs/homepage.jpeg"));
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	        init();
	        createGUI();
	    }
	    
	    private void init() {
	    	// Jlabel
	    	lblWelcome = new JLabel("-------- WELCOME TO BN HOTEL --------");
	        lblWelcome.setFont(new Font("Arial", Font.BOLD, 36));
	        lblWelcome.setForeground(Color.white);
	    	
	        // Jbutton
	        btnLogout = new JButton("LOGOUT");
	        btnLogout.setFont(fontSan);
	        		
		}

	    public void createGUI()
	    {
	        setLayout(new GridBagLayout());
	        JPanel loginPanel = new JPanel();
	        loginPanel.setOpaque(false);
	        loginPanel.setLayout(new BorderLayout());

	        Box b0 = BoxComponent.getHorizontalBox(lblWelcome, 40);
	        Box b1 = BoxComponent.getHorizontalBox(btnLogout, 40);
	        
	        Box b = BoxComponent.getVerticalBox(b0, b1, 40);
	        b.setBorder(BorderFactory.createLineBorder(Color.white));
	        
	        loginPanel.add(b);

	        add(loginPanel);
	    }

	    @Override
	    public Dimension getPreferredSize()
	    {
	        return (new Dimension(400, 400));
	    }

	    public void paintComponent(Graphics g)
	    {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, this);
	    }
	}
	
}
