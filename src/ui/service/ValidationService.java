package ui.service;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class ValidationService {
	
	private static final Pattern pattern_username = Pattern.compile("[A-Za-z0-9_\\.]{6,30}");
	private static final Pattern pattern_password = Pattern.compile("[A-Za-z0-9@!%&]{6,30}");
	private static final Pattern pattern_phone = Pattern.compile("\\d{10,11}");
	private static final Pattern pattern_email = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	private static final Pattern pattern_quantity = Pattern.compile("\\d{1,2}");
	private static final Pattern pattern_personCode = Pattern.compile("\\d{12}");
	
	private ValidationService() {};
	
	public static boolean validateUsername(String username) {
		if(pattern_username.matcher(username).matches()) {
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "Tên tài khoản không hợp lệ! Tài khoản phải gồm ký tự chữ hoa, chữ thường, chữ số và các kí tự đặc biệt như . và _", "Thông báo", 0);
			return false;
		}
	}
	
	public static boolean validatePassword(String password) {
		if(pattern_password.matcher(password).matches()) {
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "Mật khẩu không hợp lệ! Mật khẩu phải gồm ký tự chữ hoa, chữ thường, chữ số và các kí tự đặc biệt như !, @, & và %", "Thông báo", 0);
			return false;
		}
	}
	
	public static boolean validatePhone(String phone) {
		if(pattern_phone.matcher(phone).matches()) {
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ! Số điện thoại phải gồm 10 chữ số hoặc 11 chữ số!", "Thông báo", 0);
			return false;
		}
	}
	
	public static boolean validateEmail(String email) {
		if(pattern_email.matcher(email).matches()) {
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "Email không hợp lệ!", "Thông báo", 0);
			return false;
		}
	}
	
	public static boolean validateQuantity(String quantity) {
		if(pattern_quantity.matcher(quantity).matches()) {
			return true;
		}
		else if(quantity.trim().equals("0")) {
			JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0 và tối đa 2 chữ số!", "Thông báo", 0);
			return false;
		}
		else {
			JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0 và tối đa 2 chữ số!", "Thông báo", 0);
			return false;
		}
	}
	
	public static boolean validatePersonCode(String personCode) {
		if(pattern_personCode.matcher(personCode).matches()) {
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "Chứng minh nhân dân phải gồm 12 chữ số!", "Thông báo", 0);
			return false;
		}
	}
	

}
