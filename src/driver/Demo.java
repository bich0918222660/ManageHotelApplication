package driver;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import dao.ReportDAO;
import entity.ReportCategory;
import entity.ReportRevenue;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import javax.swing.JFileChooser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class Demo {

	public static void main(String[] args) {
		// tạo một document
//        Document document = new Document();
//
//        try {
//        	// khởi tạo một PdfWriter truyền vào document và FileOutputStream
//            PdfWriter.getInstance(document, new FileOutputStream("F:\\demopdf.pdf"));
//
//            // mở file để thực hiện viết
//            document.open();
//            // thêm nội dung sử dụng add function
//            document.add(new Paragraph("A Hello World PDF document."));
//            // đóng file
//            document.close();
//
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
		
//		ReportDAO dao = new ReportDAO();
//		for(ReportRevenue i : dao.reportRevenueByMonth()) {
//			System.out.println(i);
//		}
//		System.out.println("---------------");
//		for(ReportRevenue i : dao.reportRevenueByYear()) {
//			System.out.println(i);
//		}
//		System.out.println("---------------");
//		for(ReportCategory i : dao.reportByCategory()) {
//			System.out.println(i);
//		}
		
//		Locale locale = new Locale("vi", "VN");
//	    Currency currency = Currency.getInstance("VND");
//
//	    DecimalFormatSymbols df = DecimalFormatSymbols.getInstance(locale);
//	    df.setCurrency(currency);
//	    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
//	    numberFormat.setCurrency(currency);
//	    double price = 2000;
//	    System.out.println(numberFormat.format(price));
		
		String content = "abc";

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("/home"));

		int retrieval = chooser.showSaveDialog(null);
		if (retrieval == JFileChooser.APPROVE_OPTION) {
		    try {
		       File dir = new File(chooser.getSelectedFile().toString());
		       dir.mkdir();
		       System.out.println(chooser.getSelectedFile().toString());
		       File file = new File(chooser.getSelectedFile().toString()+ "/temp.txt");

		       if (!file.exists()) {
		            file.createNewFile();
		       }

		       FileWriter fw = new FileWriter(file.getAbsoluteFile());
		       BufferedWriter bw = new BufferedWriter(fw);
		       bw.write(content);
		       bw.close();
		   }
		   catch (Exception ex) {
		       ex.printStackTrace();
		   }
		}
		
	}
	
}
