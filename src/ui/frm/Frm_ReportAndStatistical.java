package ui.frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entity.Account;
import ui.component.BoxComponent;
import ui.pnl.Pnl_ReportCategory;
import ui.pnl.Pnl_Statistical;

public class Frm_ReportAndStatistical extends JFrame implements ActionListener {

	private Font fontSan = new Font("Arial", Font.BOLD, 14);

	private JLabel lbl_report, lbl_statistical, lbl_manage, lbl_printRevenueReport;
	private JButton btn_report, btn_statistical, btn_manage, btn_printRevenueReport;
	private JPanel pnl_menu, pnl_body;

	private Account account;

	public Frm_ReportAndStatistical(Account account) {
		setTitle("Report and Statistical Category - ^^!");
		setLayout(new BorderLayout());
		setSize(1200, 700);
		setResizable(false);
		setLocationRelativeTo(null); // canh giua
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.account = account;
		init();
		gui();
	}

	private void init() {
		// JPanel
		pnl_menu = new JPanel(new BorderLayout());
		pnl_menu.setBorder(BorderFactory.createTitledBorder(null, "Menu:", TitledBorder.LEFT, TitledBorder.TOP, fontSan,
				Color.BLACK));

		pnl_body = new JPanel(new BorderLayout());
		pnl_body.setBorder(
				BorderFactory.createTitledBorder(null, "", TitledBorder.LEFT, TitledBorder.TOP, fontSan, Color.BLACK));

		// JButton
		btn_report = new JButton(new ImageIcon("imgs/ic_report_category.png"));
		btn_report.setMargin(new Insets(0, 0, 0, 0));
		btn_report.setBorder(null);
		btn_report.addActionListener(this);

		btn_statistical = new JButton(new ImageIcon("imgs/ic_statistical.png"));
		btn_statistical.setMargin(new Insets(0, 0, 0, 0));
		btn_statistical.setBorder(null);
		btn_statistical.addActionListener(this);

		btn_manage = new JButton(new ImageIcon("imgs/ic_manage_hotel.png"));
		btn_manage.setMargin(new Insets(0, 0, 0, 0));
		btn_manage.setBorder(null);
		btn_manage.addActionListener(this);

		btn_printRevenueReport = new JButton(new ImageIcon("imgs/ic_export.png"));
		btn_printRevenueReport.setMargin(new Insets(0, 0, 0, 0));
		btn_printRevenueReport.setBorder(null);
		btn_printRevenueReport.addActionListener(this);

		// JLabel
		lbl_report = new JLabel("Báo cáo tình trạng phòng");
		lbl_statistical = new JLabel("Thống kê");
		lbl_manage = new JLabel("Quản lý khách sạn");
		lbl_printRevenueReport = new JLabel("In báo cáo doanh thu");
	}

	private void gui() {
		Box bv = Box.createVerticalBox();
		bv.add(Box.createVerticalStrut(20));
		bv.add(BoxComponent.getHorizontalBox(btn_report, 10));
		bv.add(Box.createVerticalStrut(6));
		bv.add(BoxComponent.getHorizontalBox(lbl_report, 10));
		bv.add(Box.createVerticalStrut(20));
		bv.add(BoxComponent.getHorizontalBox(btn_printRevenueReport, 10));
		bv.add(Box.createVerticalStrut(6));
		bv.add(BoxComponent.getHorizontalBox(lbl_printRevenueReport, 10));
		bv.add(Box.createVerticalStrut(20));
		bv.add(BoxComponent.getHorizontalBox(btn_statistical, 10));
		bv.add(Box.createVerticalStrut(6));
		bv.add(BoxComponent.getHorizontalBox(lbl_statistical, 10));
		bv.add(Box.createVerticalStrut(20));
		bv.add(BoxComponent.getHorizontalBox(btn_manage, 10));
		bv.add(Box.createVerticalStrut(6));
		bv.add(BoxComponent.getHorizontalBox(lbl_manage, 10));
		bv.add(Box.createVerticalStrut(600));

		pnl_menu.add(bv);
		this.add(pnl_menu, BorderLayout.WEST);
		Box b = BoxComponent.getHorizontalBox(BoxComponent.getVerticalBox(new Pnl_Statistical(), 5), 5);
		pnl_body.add(b);
		Box bx = Box.createVerticalBox();
		bx.add(Box.createVerticalStrut(8));
		bx.add(BoxComponent.getHorizontalBox(pnl_body, 2));
		bx.add(Box.createVerticalStrut(2));
		this.add(bx);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btn_report)) {
			pnl_body.removeAll();
			Box b = BoxComponent.getHorizontalBox(BoxComponent.getVerticalBox(new Pnl_ReportCategory(), 5), 5);
			pnl_body.add(b);
			revalidate();
		} else if (o.equals(btn_statistical)) {
			pnl_body.removeAll();
			Box b = BoxComponent.getHorizontalBox(BoxComponent.getVerticalBox(new Pnl_Statistical(), 5), 5);
			pnl_body.add(b);
			revalidate();
		} else if (o.equals(btn_manage)) {
			Frm_ManageHotel_Admin frm = new Frm_ManageHotel_Admin(account);
			frm.setVisible(true);
			this.dispose();
		} else if (o.equals(btn_printRevenueReport)) {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("Save Backup");
			chooser.setApproveButtonText("Save");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));

			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				// tạo một document
		        Document document = new Document();

		        try {
		        	// khởi tạo một PdfWriter truyền vào document và FileOutputStream
		            PdfWriter.getInstance(document, new FileOutputStream(new File(chooser.getSelectedFile(), "RevenueReport.pdf")));

		            document.open();
		            Image imgsup = Image.getInstance("imgs/ic_report.png");
		           // document.add(new Paragraph("Suppliers"));
		            document.add(imgsup);
		            document.add(new Paragraph("Supplier Details Report", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.BLUE)));
		            document.add(new Paragraph(new Date().toString()));
		            document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------"));

		            PdfPTable tablesup= new PdfPTable(2);

		            PdfPCell cell = new PdfPCell(new Paragraph("Title"));
		            cell.setColspan(4);
		            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		            cell.setBackgroundColor(BaseColor.PINK);
		            tablesup.addCell(cell);

		            tablesup.addCell("Supplier ID");
		            tablesup.addCell("Supplier ID2");
		            tablesup.addCell("Supplier ID3");
		            tablesup.addCell("Supplier ID4");
		            document.add(tablesup);

		            document.close();
		            JOptionPane.showMessageDialog(null, "Đã lưu thành công!");

		        } catch (DocumentException ex) {
		            ex.printStackTrace();
		        } catch (FileNotFoundException ex) {
		            ex.printStackTrace();
		        } catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new Frm_ReportAndStatistical(null).setVisible(true);
	}

}
