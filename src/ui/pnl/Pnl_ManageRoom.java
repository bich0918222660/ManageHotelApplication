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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.CategoryDAO;
import dao.RoomDAO;
import entity.Account;
import entity.Category;
import entity.Room;
import ui.component.BoxComponent;
import ui.frm.Frm_Payment;

public class Pnl_ManageRoom extends JPanel implements ActionListener {

	private Font fontSan = new Font("Arial", Font.BOLD, 14);

	private JLabel lbl_id, lbl_position, lbl_status, lbl_category;
	private JTextField txt_id, txt_position;
	private JComboBox<Category> cbx_categories;
	private JComboBox<String> cbx_status;
	private JButton btn_add, btn_update, btn_save, btn_delete, btn_load, btn_return;
	private JTable tbl_room;
	private DefaultTableModel tbl_model_room;
	private JScrollPane jsp_room;

	private JPanel pnl_header;

	private RoomDAO rdao = new RoomDAO();
	private CategoryDAO cdao = new CategoryDAO();

	private List<Room> rooms = new ArrayList<>();
	private List<Category> categories = new ArrayList<>();

	private Account account;

	public Pnl_ManageRoom(Account account) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(null, "Quản lý thông tin phòng:", TitledBorder.LEFT,
				TitledBorder.TOP, fontSan, Color.MAGENTA));
		this.account = account;
		rooms = rdao.getAll();
		init();
		gui();
		setEditable(false);
		txt_id.setEditable(false);
		getData();
		eventTable();
	}

	private void eventTable() {
		tbl_room.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tbl_room.getSelectedRow();
				String roomID = tbl_model_room.getValueAt(row, 0).toString();
				String categoryName = tbl_model_room.getValueAt(row, 1).toString();
				String position = tbl_model_room.getValueAt(row, 2).toString();
				String status = tbl_model_room.getValueAt(row, 3).toString();

				txt_id.setText(roomID);
				txt_position.setText(position);
				for (int i = 0; i < cbx_categories.getItemCount(); i++) {
					Category c = cbx_categories.getItemAt(i);
					if (categoryName.equals(c.getCategoryName())) {
						cbx_categories.setSelectedIndex(i);
						break;
					}
				}
				cbx_status.setSelectedItem(status);
			}

		});
	}

	private void getData() {
		rooms = rdao.getAll();
		tbl_model_room.setRowCount(0);
		;
		for (Room r : rooms) {
			String[] row = { r.getRoomID() + "", getCategoryName(r.getCategoryID()), r.getPosition(), r.getStatus() };
			tbl_model_room.addRow(row);
		}
	}

	private String getCategoryName(int categoryID) {
		String name = "";
		for (Category c : categories) {
			if (c.getCategoryID() == categoryID)
				name = c.getCategoryName();
		}
		return name;
	}

	private void init() {
		// Jpanel
		pnl_header = new JPanel(new BorderLayout());
		JLabel lbl = new JLabel();
		lbl.setIcon(new ImageIcon("imgs/rose.jpeg"));
		pnl_header.add(lbl);

		// JLabel
		lbl_id = new JLabel("Mã phòng:");
		lbl_position = new JLabel("Vị trí phòng:");
		lbl_status = new JLabel("Tình trạng:");
		lbl_category = new JLabel("Loại phòng:");

		lbl_id.setPreferredSize(lbl_category.getPreferredSize());
		lbl_status.setPreferredSize(lbl_position.getPreferredSize());

		// JTextField
		txt_id = new JTextField();
		txt_position = new JTextField();

		// JComboBox
		cbx_categories = new JComboBox<>();
		for (Category c : cdao.getAll()) {
			cbx_categories.addItem(c);
		}

		cbx_status = new JComboBox<>();
		cbx_status.addItem("Trống");
		cbx_status.addItem("Đang được đặt");
		cbx_status.addItem("Đã nhận phòng");

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

		btn_return = new JButton(new ImageIcon(this.getClass().getResource("/ic_return_room.png")));
		btn_return.setMargin(new Insets(0, 0, 0, 0));
		btn_return.setBorder(null);
		btn_return.addActionListener(this);
		btn_return.setBackground(Color.decode("#ebebeb"));

		// JTable
		String[] header = { "Mã phòng", "Loại phòng", "Vị trí phòng", "Tình trạng" };
		tbl_room = new JTable(tbl_model_room = new DefaultTableModel(header, 0));
		tbl_room.setRowHeight(35);
		
		JTableHeader tableHeader = tbl_room.getTableHeader();
		tableHeader.setBackground(Color.decode("#67e0fe"));
		tableHeader.setFont(new Font("Arial", Font.BOLD, 13));
		tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 35));

		// JScrollPane
		jsp_room = new JScrollPane(tbl_room);
		jsp_room.setPreferredSize(new Dimension(tbl_room.getPreferredSize().width, 300));

	}

	private void gui() {
		// Info
		Box b_id = BoxComponent.getHorizontalBox(lbl_id, txt_id, 10);
		Box b_category = BoxComponent.getHorizontalBox(lbl_category, cbx_categories, 10);
		Box b_position = BoxComponent.getHorizontalBox(lbl_position, txt_position, 10);
		Box b_status = BoxComponent.getHorizontalBox(lbl_status, cbx_status, 10);

		Box bv_left = BoxComponent.getVerticalBox(b_id, b_category, 10);
		Box bv_right = BoxComponent.getVerticalBox(b_position, b_status, 10);

		Box bh_info = BoxComponent.getHorizontalBox_NoPadding(bv_left, bv_right, 40);
		bh_info.setPreferredSize(new Dimension(bh_info.getPreferredSize().width, 82));

		// Button
		Box b_button = Box.createHorizontalBox();
		b_button.add(Box.createHorizontalStrut(880));
		b_button.add(btn_add);
		b_button.add(Box.createHorizontalStrut(15));
		b_button.add(btn_update);
		b_button.add(Box.createHorizontalStrut(15));
		b_button.add(btn_save);
		b_button.add(Box.createHorizontalStrut(15));
		b_button.add(btn_delete);
		b_button.add(Box.createHorizontalStrut(15));
		b_button.add(btn_load);
		b_button.add(Box.createHorizontalStrut(15));
		b_button.add(btn_return);
		b_button.add(Box.createHorizontalStrut(10));

		// Table
		Box bh = BoxComponent.getHorizontalBox(jsp_room, 10);
		Box bv = BoxComponent.getVerticalBox(bh, 10);

		JPanel pnl = new JPanel(new BorderLayout());
		pnl.setBorder(BorderFactory.createTitledBorder(null, "Danh sách phòng:", TitledBorder.LEFT, TitledBorder.TOP,
				fontSan, Color.MAGENTA));
		pnl.add(bv);

		// Full
		Box bv_full = BoxComponent.getVerticalBox(b_button, pnl, 10);
		Box bh_full = BoxComponent.getHorizontalBox(bv_full, 10);

		add(bh_full);
		add(BoxComponent.getVerticalBox(pnl_header, bh_info, 0), BorderLayout.NORTH);
	}

	private void setEditable(boolean status) {
		txt_position.setEditable(status);
		cbx_categories.setEditable(status);
		cbx_status.setEditable(status);
		btn_add.setEnabled(!status);
		btn_update.setEnabled(!status);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btn_load)) {
			getData();
		} else if (o.equals(btn_return)) {
			int answer = JOptionPane.showConfirmDialog(null, "Bạn thực sự muốn ghi nhận việc trả phòng?", "Trả phòng",
					JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION) {
				try {
					rdao.updateStatus(Integer.parseInt(txt_id.getText().trim()), "Trống");
					JOptionPane.showMessageDialog(null, "Ghi nhận việc trả phòng thành công!");
					getData();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else if (o.equals(btn_add)) {
			setEditable(true);
			btn_update.setEnabled(true);
		} else if (o.equals(btn_update)) {
			setEditable(true);
			txt_id.setEditable(false);
			btn_add.setEnabled(true);
		} else if (o.equals(btn_delete)) {
			if (account.getRole().equals("Super Admin")) {
				int row = tbl_room.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn thông tin phòng cần xóa!");
				} else {
					int id = Integer.parseInt(txt_id.getText());
					int answer = JOptionPane.showConfirmDialog(null, "Bạn có thực sự muốn xóa phòng " + id + " không?",
							"Xóa thông tin phòng", JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {
						try {
							rdao.delete(id);
							getData();
							JOptionPane.showMessageDialog(null, "Xóa thông tin phòng thành công!");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Bạn chưa được cấp quyền!");
			}
		} else if (o.equals(btn_save)) {
			if (account.getRole().equals("Super Admin")) {
				Category c = (Category) cbx_categories.getSelectedItem();
				String position = txt_position.getText();
				if (!btn_add.isEnabled()) {
					Room room = new Room(position, "Trống", c.getCategoryID());
					try {
						rdao.insert(room);
						getData();
						JOptionPane.showMessageDialog(null, "Thêm thông tin phòng thành công!");
						setEditable(false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					int roomID = Integer.parseInt(txt_id.getText());
					String status = (String) cbx_status.getSelectedItem();
					Room room = new Room(roomID, position, status, c.getCategoryID());
					try {
						rdao.update(room);
						getData();
						JOptionPane.showMessageDialog(null, "Cập nhật thông tin phòng thành công!");
						setEditable(false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Bạn chưa được cấp quyền!");
			}
		}
	}
}
