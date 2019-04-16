package ui.component;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;

public class Clock {

	public void clock(JLabel lbl_time, JLabel lbl_date) {
		Thread clock = new Thread() {
			@Override
			public void run() {
				try {
					while (true) {
						Calendar cal = new GregorianCalendar();
						int second = cal.get(Calendar.SECOND);
						int minute = cal.get(Calendar.MINUTE);
						int hour = cal.get(Calendar.HOUR_OF_DAY);
						String thu;
						int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
						if (dayOfWeek == 1) {
							thu = "Chủ nhật";
						} else {
							thu = "Thứ " + Integer.toString(dayOfWeek);
						}
						int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
						int month = cal.get(Calendar.MONTH);
						int year = cal.get(Calendar.YEAR);

						lbl_time.setText(hour + ":" + minute + ":" + second);
						lbl_date.setText(thu + " ngày " + dayOfMonth + " tháng " + (month + 1) + " năm " + year);

						sleep(1000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		clock.start();
	}
	
}
