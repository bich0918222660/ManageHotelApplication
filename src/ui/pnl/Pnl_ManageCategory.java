package ui.pnl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.CategoryDAO;
import entity.Account;
import entity.Category;
import ui.component.BoxComponent;
import ui.service.RandomString;

public class Pnl_ManageCategory extends JPanel implements ActionListener {

	private Font fontSan = new Font("Arial", Font.BOLD, 14);

	private JLabel lbl_id, lbl_name, lbl_price, lbl_description, lbl_discount, lbl_image, lbl_type, lbl_image_url;
	private JTextField txt_id, txt_name, txt_price, txt_discount;
	private JTextArea txt_description;
	private JComboBox<String> cbx_types;
	private JButton btn_add, btn_update, btn_save, btn_delete, btn_load, btnUpload;
	private JTable tbl_category;
	private DefaultTableModel tbl_model_category;
	private JScrollPane jsp_category;

	private JPanel pnl_header;

	private Account account;
	private CategoryDAO cdao = new CategoryDAO();

	private String imageUrl = "";
	private String imageName = "";

	public Pnl_ManageCategory(Account account) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(null, "Quản lý loại phòng:", TitledBorder.LEFT, TitledBorder.TOP,
				fontSan, Color.MAGENTA));
		this.account = account;
		init();
		gui();
		setEditable(false);
		getData();
		eventTable();
	}

	private void eventTable() {
		tbl_category.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tbl_category.getSelectedRow();
				String id = tbl_model_category.getValueAt(row, 0).toString();
				int categoryID = Integer.parseInt(id);

				for (Category c : cdao.getAll()) {
					if (c.getCategoryID() == categoryID) {
						txt_description.setText(c.getDescription());
						int discount = (int) c.getDiscount();
						txt_discount.setText(discount + "");
						txt_id.setText(categoryID + "");
						txt_name.setText(c.getCategoryName());
						int price = (int) c.getPrice();
						txt_price.setText(price + "");
						cbx_types.setSelectedItem(c.getType());
					}
				}

			}

		});
	}

	private void getData() {
		tbl_model_category.setRowCount(0);
		for (Category c : cdao.getAll()) {
			String[] row = { c.getCategoryID() + "", c.getCategoryName(), c.getType(), c.getPrice() + "",
					c.getDiscount() + "" };
			tbl_model_category.addRow(row);
		}
	}

	private void init() {
		// Jpanel
		pnl_header = new JPanel(new BorderLayout());
		JLabel lbl = new JLabel();
		lbl.setIcon(new ImageIcon(this.getClass().getResource("/rose.jpeg")));
		pnl_header.add(lbl);

		// JLabel
		lbl_id = new JLabel("Mã loại:");
		lbl_name = new JLabel("Tên loại:");
		lbl_price = new JLabel("Đơn giá:");
		lbl_description = new JLabel("Mô tả:");
		lbl_discount = new JLabel("Giảm giá:");
		lbl_image = new JLabel("Hình ảnh:");
		lbl_type = new JLabel("Thuộc loại:");
		lbl_image_url = new JLabel("Choose image file...");

		lbl_id.setPreferredSize(lbl_type.getPreferredSize());
		lbl_name.setPreferredSize(lbl_type.getPreferredSize());
		lbl_description.setPreferredSize(lbl_type.getPreferredSize());

		lbl_price.setPreferredSize(lbl_discount.getPreferredSize());

		// JTextField
		txt_id = new JTextField();
		txt_name = new JTextField();
		txt_price = new JTextField();
		txt_discount = new JTextField();

		// JTextArea
		txt_description = new JTextArea();
		txt_description.setRows(5);

		// JComboxBox
		cbx_types = new JComboBox<>();
		cbx_types.addItem("Giường đơn");
		cbx_types.addItem("Giường đôi");
		cbx_types.addItem("Ba giường");

		// JButton
		btn_add = new JButton(new ImageIcon(this.getClass().getResource("/ic_add.png")));
		btn_add.setMargin(new Insets(0, 0, 0, 0));
		btn_add.setBorder(null);
		btn_add.addActionListener(this);
		btn_add.setBackground(Color.decode("#ebebeb"));

		btn_update = new JButton(new ImageIcon(this.getClass().getResource("/ic_edit.png")));
		btn_update.setMargin(new Insets(0, 0, 0, 0));
		btn_update.setBorder(null);
		btn_update.addActionListener(this);
		btn_update.setBackground(Color.decode("#ebebeb"));

		btn_delete = new JButton(new ImageIcon(this.getClass().getResource("/ic_delete.png")));
		btn_delete.setMargin(new Insets(0, 0, 0, 0));
		btn_delete.setBorder(null);
		btn_delete.addActionListener(this);
		btn_delete.setBackground(Color.decode("#ebebeb"));

		btn_load = new JButton(new ImageIcon(this.getClass().getResource("/ic_load.png")));
		btn_load.setMargin(new Insets(0, 0, 0, 0));
		btn_load.setBorder(null);
		btn_load.addActionListener(this);
		btn_load.setBackground(Color.decode("#ebebeb"));

		btn_save = new JButton(new ImageIcon(this.getClass().getResource("/ic_save.png")));
		btn_save.setMargin(new Insets(0, 0, 0, 0));
		btn_save.setBorder(null);
		btn_save.addActionListener(this);
		btn_save.setBackground(Color.decode("#ebebeb"));

		btnUpload = new JButton("Upload File");
		btnUpload.addActionListener(this);

		// JTable
		String[] header = { "Mã loại", "Tên loại", "Thuộc loại", "Đơn giá", "Giảm giá" };
		tbl_category = new JTable(tbl_model_category = new DefaultTableModel(header, 0));
		tbl_category.setRowHeight(35);
		
		JTableHeader tableHeaderDetail = tbl_category.getTableHeader();
		tableHeaderDetail.setBackground(Color.decode("#67e0fe"));
		tableHeaderDetail.setFont(new Font("Arial", Font.BOLD, 13));
		tableHeaderDetail.setPreferredSize(new Dimension(tableHeaderDetail.getPreferredSize().width, 35));

		// JScrollPane
		jsp_category = new JScrollPane(tbl_category);
		jsp_category.setPreferredSize(new Dimension(tbl_category.getPreferredSize().width, 300));

	}

	private void gui() {
		// Info
		Box b_id = BoxComponent.getHorizontalBox(lbl_id, txt_id, 10);
		Box b_name = BoxComponent.getHorizontalBox(lbl_name, txt_name, 10);
		Box b_type = BoxComponent.getHorizontalBox(lbl_type, cbx_types, 10);
		Box b_desc = BoxComponent.getHorizontalBox(lbl_description, txt_description, 10);
		Box b_price = BoxComponent.getHorizontalBox(lbl_discount, txt_discount, 10);
		Box b_discount = BoxComponent.getHorizontalBox(lbl_price, txt_price, 10);
		Box b_image = BoxComponent.getHorizontalBox(lbl_image, btnUpload, lbl_image_url, 10);

		Box b_left = BoxComponent.getVerticalBox(b_id, b_name, b_type, 10);
		Box b_right = BoxComponent.getVerticalBox(b_price, b_discount, b_image, 10);

		Box bh_info = BoxComponent.getHorizontalBox_NoPadding(b_left, b_right, 50);
		bh_info.setPreferredSize(new Dimension(bh_info.getPreferredSize().width, 120));

		// Button
		Box b_button = Box.createHorizontalBox();
		b_button.add(Box.createHorizontalStrut(900));
		b_button.add(btn_add);
		b_button.add(Box.createHorizontalStrut(15));
		b_button.add(btn_update);
		b_button.add(Box.createHorizontalStrut(15));
		b_button.add(btn_save);
		b_button.add(Box.createHorizontalStrut(15));
		b_button.add(btn_delete);
		b_button.add(Box.createHorizontalStrut(15));
		b_button.add(btn_load);
		b_button.add(Box.createHorizontalStrut(10));

		// Table
		Box bh = BoxComponent.getHorizontalBox(jsp_category, 10);
		Box bv = BoxComponent.getVerticalBox(bh, 10);

		JPanel pnl = new JPanel(new BorderLayout());
		pnl.setBorder(BorderFactory.createTitledBorder(null, "Danh sách loại phòng:", TitledBorder.LEFT,
				TitledBorder.TOP, fontSan, Color.MAGENTA));
		pnl.add(bv);

		// Full
		Box bv_full = BoxComponent.getVerticalBox(b_button, pnl, 10);
		Box bh_full = BoxComponent.getHorizontalBox(bv_full, 10);

		add(bh_full);
		add(BoxComponent.getVerticalBox(pnl_header, bh_info, 0), BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnUpload)) {
			JFileChooser jfc = new JFileChooser();
			jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			jfc.setDialogTitle("Select an image");
			jfc.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG, JPG and GIF images", "png", "PNG", "gif",
					"jpg");
			jfc.addChoosableFileFilter(filter);

			int returnValue = jfc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				imageUrl = jfc.getSelectedFile().getPath();
				String[] arr = imageUrl.split("\\\\");
				lbl_image_url.setText(arr[arr.length - 1]);
				imageName = RandomString.randomAlphaNumeric(10);
			}
		} else if (o.equals(btn_load)) {
			getData();
		} else if (o.equals(btn_add)) {
			setEditable(true);
			txt_id.setEditable(false);
			btn_update.setEnabled(true);
		} else if (o.equals(btn_update)) {
			setEditable(true);
			txt_id.setEditable(false);
			btn_add.setEnabled(true);
		} else if (o.equals(btn_save)) {
			if (account.getRole().equals("Super Admin")) {
				try {
					int id = Integer.parseInt(txt_id.getText().trim());
					String categoryName = txt_name.getText().trim();
					String description = txt_description.getText().trim();
					double price = Double.parseDouble(txt_price.getText().trim());
					float discount = Float.parseFloat(txt_discount.getText().trim());
					
					String[] str = imageUrl.split("\\.");
					String typeFile = str[str.length - 1];
					String fileName = imageName + "." + typeFile;
					
					String type = cbx_types.getSelectedItem().toString();

					if (!btn_add.isEnabled()) {
						if (lbl_image_url.getText().equals("Choose image file...")) {
							JOptionPane.showMessageDialog(null, "Hình ảnh không được để trống", "Thông báo", 0);
						} else {
							Category c = new Category(categoryName, description, price, discount, fileName, type);

							try {
								saveImage(imageUrl, fileName, typeFile);
								cdao.insert(c);
								JOptionPane.showMessageDialog(null, "Thêm mới thành công!");
								resetText();
								getData();
								setEditable(false);
							} catch (Exception e2) {
								e2.printStackTrace();
							}
						}
					} else {
						Category c = new Category(id, categoryName, description, price, discount, fileName, type);

						try {
							if (lbl_image_url.getText().equals("Choose image file...")) {
								cdao.updateNoImage(c);
							} else {
								saveImage(imageUrl, fileName, typeFile);
								cdao.update(c);
							}
							JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
							lbl_image_url.setText("Choose image file...");
							getData();
							setEditable(false);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Đơn giá và giảm giá phải là ký tự số!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Bạn chưa được cấp quyền!");
			}
		} else if (o.equals(btn_delete)) {
			if (account.getRole().equals("Super Admin")) {
				int row = tbl_category.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn thông tin loại phòng cần xóa!");
				} else {
					int id = Integer.parseInt(txt_id.getText());
					int answer = JOptionPane.showConfirmDialog(null,
							"Bạn có thực sự muốn xóa loại phòng số (" + id + ") không?", "Xóa thông tin loại phòng",
							JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {
						try {
							cdao.delete(id);
							JOptionPane.showMessageDialog(null, "Xóa thông tin loại phòng thành công!");
							getData();
							resetText();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Bạn chưa được cấp quyền!");
			}
		}
	}

	private void resetText() {
		txt_description.setText("");
		txt_discount.setText("");
		txt_id.setText("");
		txt_name.setText("");
		txt_price.setText("");
		lbl_image_url.setText("Choose image file...");
	}

	private void setEditable(boolean status) {
		txt_description.setEditable(status);
		txt_discount.setEditable(status);
		txt_id.setEditable(status);
		txt_name.setEditable(status);
		txt_price.setEditable(status);
		cbx_types.setEditable(status);
		btn_add.setEnabled(!status);
		btn_update.setEnabled(!status);
	}

	private void saveImage(String path, String fileName, String typeName) {
		BufferedImage image = null;
		File f = null;
		try {
			// read
			f = new File(path);
			image = ImageIO.read(f);

			// write
			f = new File(System.getProperty("user.dir") + "\\ManageHotelApplication\\" + fileName);
			System.out.println(path);
			ImageIO.write(image, typeName, f);
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}

}
