package checker;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FireFoxplay {
	private static Logger logger = LoggerFactory.getLogger(FireFoxplay.class);
	private static UrlServerConfig cfg = ConfigFactory.create(UrlServerConfig.class);
	private static final String[] urlddress = cfg.urlddress();
	public static String BrowserPath = "D:\\huohu\\firefox.exe";
	public static WebDriver driver_firefox = null;
	public static WebDriver driver_firefox2 = null;
	private ExecutorService executor = Executors.newScheduledThreadPool(5);

	public static void main(String[] args) {
		// 打开火狐浏览器；
		logger.info("开启线程");
		new Thread(new Thread1()).start();
		new Thread(new Thread2()).start();
	}
}
