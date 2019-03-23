package ui.frm;

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

import dao.AccountDAO;
import entity.Account;
import ui.component.BoxComponent;

public class Frm_Login extends JFrame implements ActionListener {

	private BufferedImage image;
	private JLabel lblUsername, lblPassword, lblTitle;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnLogin;

	private Font fontSan = new Font("Arial", Font.BOLD, 14);
	
	public Frm_Login() {
		setTitle("Login - ^^!");
		setSize(800, 800);
		setResizable(false);
		setLocationRelativeTo(null); // canh giua
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		init();
	}
	
	private void init() {
		setLayout(new BorderLayout());
		JPanel pnl = new Pnl_Login();
		add(pnl);
		btnLogin.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnLogin)) {
			String username = txtUsername.getText().trim();
			String password = txtPassword.getText().trim();
			
			Account acc = existInList(username, password);
			if(acc != null) {
				Frm_ManageHotel_Admin frm = new Frm_ManageHotel_Admin(acc);
				frm.setVisible(true);
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Mật khẩu không hợp lệ!");
			}
		}
	}
	
	private Account existInList(String username, String password) {
		AccountDAO dao = new AccountDAO();
		try {
			for(Account acc : dao.getAll()) {
				if(acc.getUsername().equals(username) && acc.getPassword().equals(password)) {
					return acc;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public static void main(String[] args) {
		new Frm_Login().setVisible(true);
	}

	public class Pnl_Login extends JPanel {

		public Pnl_Login()
	    {
	        setOpaque(true);
	        setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
	        try
	        {
	            image = ImageIO.read(new File("imgs/bg.jpeg"));
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
			lblTitle = new JLabel(new ImageIcon("imgs/ic_user.png"));

			lblUsername = new JLabel("USERNAME :");
			lblUsername.setForeground(Color.white);
			lblUsername.setFont(fontSan);
			lblPassword = new JLabel("PASSWORD :");
			lblPassword.setForeground(Color.white);
			lblPassword.setFont(fontSan);

			lblUsername.setPreferredSize(lblPassword.getPreferredSize());

			// Jtextfield
			txtUsername = new JTextField(20);
			txtUsername.setFont(fontSan);
			txtPassword = new JPasswordField(20);
			txtPassword.setFont(fontSan);

			// Jbutton
			btnLogin = new JButton("LOGIN");
			btnLogin.setFont(fontSan);
			
		}

		public void createGUI() {
			setLayout(new GridBagLayout());
			JPanel loginPanel = new JPanel();
			loginPanel.setOpaque(false);
			loginPanel.setLayout(new BorderLayout());

			Box b0 = BoxComponent.getHorizontalBox(lblTitle, 20);
			Box b1 = BoxComponent.getHorizontalBox(lblUsername, txtUsername, 20);
			Box b2 = BoxComponent.getHorizontalBox(lblPassword, txtPassword, 20);
			Box b3 = BoxComponent.getHorizontalBox(btnLogin, 20);

			Box b = BoxComponent.getVerticalBox(b0, b1, b2, b3, 20);
			b.setBorder(BorderFactory.createLineBorder(Color.white));

			loginPanel.add(b);

			add(loginPanel);
		}

		@Override
		public Dimension getPreferredSize() {
			return (new Dimension(400, 400));
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, this);
		}
	}

}
