package im.utils;

import java.util.Calendar;
import java.util.Random;

public class UserShopUtils {

	static String str =""; 
	
	public static String createNumberItemString() {
	    StringBuffer message = new StringBuffer() ; 
		Random random = new Random() ; 
		Calendar instance = Calendar.getInstance();
		int year = instance.get(Calendar.YEAR) ; 
		int month = instance.get(Calendar.MONTH) ; 
		int day = instance.get(Calendar.DAY_OF_MONTH) ; 
		int hour = instance.get(Calendar.HOUR_OF_DAY) ; 
		int minute = instance.get(Calendar.MINUTE) ; 
		int second = instance.get(Calendar.SECOND) ; 
		int millisecond = Calendar.MILLISECOND ; 
		message.append(year+"-") ; 
		message.append(month+"-") ; 
		message.append(day+"--"+hour+""+minute+""+second+""+millisecond) ; 
	 	message.append("-"+random.nextInt(1000, 10000)) ; 
	  return message.toString() ; 
	}
	
	public synchronized static String createNumberItem() {
		String result = createNumberItemString() ;   
		for(;result.equals(str);) {
			result = createNumberItemString() ;   
		}
		str = result ; 
	  return result ; 
	}

}
