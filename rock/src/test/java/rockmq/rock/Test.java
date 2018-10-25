package rockmq.rock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Test {
	public static void main(String[] args) {
		Long maxtime = (long) 0;
		Long[] arry = { (long) 1539570122, (long) 1539570048, (long) 1539510525, (long) 1539568688, (long) 1539569956,
				(long) 1539569951, (long) 1539569938, (long) 1539569927, (long) 1539569917, (long) 1539569873,
				(long) 1539569886, (long) 1539569732, (long) 1539569886, (long) 1539569869, (long) 1539569869,
				(long) 1539569840, (long) 1539569786, (long) 1539568546, (long) 1539569768, (long) 1539569715 };
		for (int i = 0; i < arry.length; i++) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String str = sdf.format(arry[i]* 1000);
			System.out.println(str);
			if (maxtime.longValue() == 0) {
				maxtime = arry[i].longValue();
			} else {
				maxtime = (maxtime.longValue() > arry[i].longValue()) ? maxtime.longValue() : arry[i].longValue();
			}
		}
		System.out.println(maxtime+"maxtime");
		Date date=new Date(maxtime.longValue() * 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String str = sdf.format(date);
		System.out.println(str);
		
		
		
	}
}
