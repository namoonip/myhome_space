package todo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test1 {
	

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) +1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR);
		int min = cal.get(Calendar.MINUTE);
		
		Date now = new Date();
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH시 MM분");
		String date = smf.format(now);
		System.out.println(date);
	}
}
