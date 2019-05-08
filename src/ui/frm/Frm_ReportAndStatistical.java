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
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

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

import org.jfree.ui.Align;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dao.ReportDAO;
import entity.Account;
import entity.ReportCategory;
import entity.ReportRevenue;
import ui.component.BoxComponent;
import ui.pnl.Pnl_ReportStatusCategory;
import ui.pnl.Pnl_Statistical;

public class Frm_ReportAndStatistical extends JFrame implements ActionListener {

	private Font fontSan = new Font("Arial", Font.BOLD, 14);

	private JLabel lbl_report, lbl_statistical, lbl_manage, lbl_printRevenueReport;
	private JButton btn_report, btn_statistical, btn_manage, btn_printRevenueReport;
	private JPanel pnl_menu, pnl_body;

	private Account account;
	
	private ReportDAO rdao = new ReportDAO();

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
			Box b = BoxComponent.getHorizontalBox(BoxComponent.getVerticalBox(new Pnl_ReportStatusCategory(), 5), 5);
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
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));

			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				// tạo một document
		        Document document = new Document(PageSize.A4.rotate(), 20f, 20f, 20f, 20f);

		        try {
		        	BaseFont baseFont = BaseFont.createFont("font/Lato-Regular.ttf", BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
					com.itextpdf.text.Font fontNormal = new com.itextpdf.text.Font(baseFont, 13);
		        	
		        	// khởi tạo một PdfWriter truyền vào document và FileOutputStream
		            PdfWriter.getInstance(document, new FileOutputStream(chooser.getSelectedFile() + ".pdf"));

		            document.open();
		            Image imgsup = Image.getInstance("imgs/hotel.png");
		            document.add(imgsup);
		            
		            Paragraph pDate = new Paragraph("Ngày báo cáo: " + getToDayFormat(new Date()) + "\n", fontNormal);
		            pDate.setAlignment(Element.ALIGN_CENTER);
		            
		            Paragraph pLine = new Paragraph("-------------------------- o0o --------------------------\n\n");
		            pLine.setAlignment(Element.ALIGN_CENTER);
		            
		            document.add(pDate);
		            document.add(pLine);
		            reportByMonth(document);
		            document.add(pLine);
		            reportByYear(document);
		            document.add(pLine);
		            reportByCategory(document);

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
	
	private void reportByCategory(Document document) {
		PdfPTable table = new PdfPTable(6);
		table.setTotalWidth(800);
		table.setLockedWidth(true);
		try {
			float[] columnWidths = new float[]{5f, 20f, 15f, 10f, 10f, 10f};
            table.setWidths(columnWidths);
			
			BaseFont baseFont = BaseFont.createFont("font/Lato-Regular.ttf", BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
			com.itextpdf.text.Font fontHeader = new com.itextpdf.text.Font(baseFont, 15, Font.BOLD);
			com.itextpdf.text.Font fontHeaderTable = new com.itextpdf.text.Font(baseFont, 13, Font.BOLD);
			com.itextpdf.text.Font fontBody = new com.itextpdf.text.Font(baseFont, 13);
			
	        PdfPCell cell = new PdfPCell(new Paragraph("Báo cáo theo loại", fontHeader));
	        cell.setColspan(6);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBackgroundColor(BaseColor.PINK);
            cell.setFixedHeight(20f);
	        table.addCell(cell);

	        table.addCell(new Paragraph("#", fontHeaderTable));
	        table.addCell(new Paragraph("Loại phòng", fontHeaderTable));
	        table.addCell(new Paragraph("Phòng", fontHeaderTable));
	        table.addCell(new Paragraph("Đơn giá", fontHeaderTable));
	        table.addCell(new Paragraph("Số lượng phòng", fontHeaderTable));
	        table.addCell(new Paragraph("Số lượng đơn", fontHeaderTable));
	        
	        int num = 1;
	        for(ReportCategory i : rdao.reportRevenueByCategory()) {
	        	table.addCell(num + "");
	        	table.addCell(new Paragraph(i.getCategoryName(), fontBody));
	        	table.addCell(i.getRoom());
	        	table.addCell(convertDoubleToCurrency(i.getPrice()));
	        	table.addCell(i.getRoomQuantity() + "");
	        	table.addCell(i.getBookingQuantity() + "");
	        	num++;
	        }
	        
	        document.add(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void reportByMonth(Document document) {
		PdfPTable table = new PdfPTable(11);
		table.setTotalWidth(800);
		table.setLockedWidth(true);
		try {
			float[] columnWidths = new float[]{5f, 20f, 5f, 20f, 5f, 20f, 5f, 12f, 12f, 12f, 12f};
            table.setWidths(columnWidths);
			
			BaseFont baseFont = BaseFont.createFont("font/Lato-Regular.ttf", BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
			com.itextpdf.text.Font fontHeader = new com.itextpdf.text.Font(baseFont, 14, Font.BOLD);
			com.itextpdf.text.Font fontHeaderTable = new com.itextpdf.text.Font(baseFont, 12, Font.BOLD);
			com.itextpdf.text.Font fontBody = new com.itextpdf.text.Font(baseFont, 12);
			
	        PdfPCell cell = new PdfPCell(new Paragraph("Báo cáo doanh thu theo tháng", fontHeader));
	        cell.setColspan(11);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBackgroundColor(BaseColor.PINK);
	        table.addCell(cell);

	        table.addCell(new Paragraph("#", fontHeaderTable));
	        table.addCell(new Paragraph("Loại phòng", fontHeaderTable));
	        table.addCell(new Paragraph("SL", fontHeaderTable));
	        table.addCell(new Paragraph("Phòng", fontHeaderTable));
	        table.addCell(new Paragraph("SL", fontHeaderTable));
	        table.addCell(new Paragraph("Dịch vụ", fontHeaderTable));
	        table.addCell(new Paragraph("SL", fontHeaderTable));
	        table.addCell(new Paragraph("Tổng tiền dịch vụ", fontHeaderTable));
	        table.addCell(new Paragraph("Tổng tiền thuê phòng", fontHeaderTable));
	        table.addCell(new Paragraph("Ngày thanh toán", fontHeaderTable));
	        table.addCell(new Paragraph("Tổng tiền", fontHeaderTable));
	        
	        int num = 1;
	        double sub = 0;
	        for(ReportRevenue i : rdao.reportRevenueByMonth()) {
	        	table.addCell(num + "");
	        	table.addCell(new Paragraph(i.getCategoryName(), fontBody));
	        	table.addCell(i.getCategoryQuantity() + "");
	        	table.addCell(i.getRoomID());
	        	table.addCell(i.getRoomQuantity() + "");
	        	table.addCell(new Paragraph(i.getServiceName(), fontBody));
	        	table.addCell(i.getServiceQuantity() + "");
	        	table.addCell(convertDoubleToCurrency(i.getServicePrice()));
	        	table.addCell(convertDoubleToCurrency(i.getRentalPrice()));
	        	table.addCell(getDateFormat(i.getPaymentDate()));
	        	table.addCell(convertDoubleToCurrency(i.getSubtotal()));
	        	num++;
	        	sub += i.getSubtotal();
	        }
	        PdfPCell cellSub1 = new PdfPCell(new Paragraph("Tổng cộng", fontHeaderTable));
	        cellSub1.setColspan(10);
	        cellSub1.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        cellSub1.setBackgroundColor(BaseColor.GREEN);
	        table.addCell(cellSub1);
	        PdfPCell cellSub2 = new PdfPCell(new Paragraph(convertDoubleToCurrency(sub), fontHeaderTable));
	        cellSub2.setBackgroundColor(BaseColor.GREEN);
	        table.addCell(cellSub2);
	        
	        document.add(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void reportByYear(Document document) {
		PdfPTable table = new PdfPTable(11);
		table.setTotalWidth(800);
		table.setLockedWidth(true);
		try {
			float[] columnWidths = new float[]{5f, 20f, 5f, 20f, 5f, 20f, 5f, 12f, 12f, 12f, 12f};
            table.setWidths(columnWidths);
			
			BaseFont baseFont = BaseFont.createFont("font/Lato-Regular.ttf", BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
			com.itextpdf.text.Font fontHeader = new com.itextpdf.text.Font(baseFont, 14, Font.BOLD);
			com.itextpdf.text.Font fontHeaderTable = new com.itextpdf.text.Font(baseFont, 12, Font.BOLD);
			com.itextpdf.text.Font fontBody = new com.itextpdf.text.Font(baseFont, 12);
			
	        PdfPCell cell = new PdfPCell(new Paragraph("Báo cáo doanh thu theo năm", fontHeader));
	        cell.setColspan(11);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBackgroundColor(BaseColor.PINK);
	        table.addCell(cell);

	        table.addCell(new Paragraph("#", fontHeaderTable));
	        table.addCell(new Paragraph("Loại phòng", fontHeaderTable));
	        table.addCell(new Paragraph("SL", fontHeaderTable));
	        table.addCell(new Paragraph("Phòng", fontHeaderTable));
	        table.addCell(new Paragraph("SL", fontHeaderTable));
	        table.addCell(new Paragraph("Dịch vụ", fontHeaderTable));
	        table.addCell(new Paragraph("SL", fontHeaderTable));
	        table.addCell(new Paragraph("Tổng tiền dịch vụ", fontHeaderTable));
	        table.addCell(new Paragraph("Tổng tiền thuê phòng", fontHeaderTable));
	        table.addCell(new Paragraph("Ngày thanh toán", fontHeaderTable));
	        table.addCell(new Paragraph("Tổng tiền", fontHeaderTable));
	        
	        int num = 1;
	        double sub = 0;
	        for(ReportRevenue i : rdao.reportRevenueByYear()) {
	        	table.addCell(num + "");
	        	table.addCell(new Paragraph(i.getCategoryName(), fontBody));
	        	table.addCell(i.getCategoryQuantity() + "");
	        	table.addCell(i.getRoomID());
	        	table.addCell(i.getRoomQuantity() + "");
	        	table.addCell(new Paragraph(i.getServiceName(), fontBody));
	        	table.addCell(i.getServiceQuantity() + "");
	        	table.addCell(convertDoubleToCurrency(i.getServicePrice()));
	        	table.addCell(convertDoubleToCurrency(i.getRentalPrice()));
	        	table.addCell(getDateFormat(i.getPaymentDate()));
	        	table.addCell(convertDoubleToCurrency(i.getSubtotal()));
	        	num++;
	        	sub += i.getSubtotal();
	        }
	        PdfPCell cellSub1 = new PdfPCell(new Paragraph("Tổng cộng", fontHeaderTable));
	        cellSub1.setColspan(10);
	        cellSub1.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        cellSub1.setBackgroundColor(BaseColor.GREEN);
	        table.addCell(cellSub1);
	        PdfPCell cellSub2 = new PdfPCell(new Paragraph(convertDoubleToCurrency(sub), fontHeaderTable));
	        cellSub2.setBackgroundColor(BaseColor.GREEN);
	        table.addCell(cellSub2);
	        
	        document.add(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getDateFormat(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}
	
	private String getToDayFormat(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return dateFormat.format(date);
	}

	public static void main(String[] args) {
		new Frm_ReportAndStatistical(null).setVisible(true);
	}
	
	private String convertDoubleToCurrency(double price) {
		Locale locale = new Locale("vi", "VN");
	    Currency currency = Currency.getInstance("VND");

	    DecimalFormatSymbols df = DecimalFormatSymbols.getInstance(locale);
	    df.setCurrency(currency);
	    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
	    numberFormat.setCurrency(currency);
        return numberFormat.format(price);
	}

}
