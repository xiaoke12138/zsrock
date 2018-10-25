package checker;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Thread1 implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(FireFoxplay.class);
	private static UrlServerConfig cfg = ConfigFactory.create(UrlServerConfig.class);
	private static final String[] urlddress = cfg.urlddress();
	private static final String keyString=cfg.keyString();
	public static String BrowserPath = "D:\\huohu\\firefox.exe";
	public static WebDriver driver_firefox = null;
	void init(){
		System.setProperty("webdriver.firefox.bin", BrowserPath);
		driver_firefox = new FirefoxDriver();
	}
	
	@Override
	public void run() {
		init();
		// 打开火狐浏览器；
		logger.info("初始化完成"+keyString);
		String pageSource=null;
		for (int i = 0; i < urlddress.length; i++) {
			try {
				logger.info("正在核查的网站" + urlddress[i]);
				driver_firefox.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				driver_firefox.get(urlddress[i]);
				// driver_firefox.get("http://www.xinhuanet.com/");
				pageSource = driver_firefox.getPageSource();
				System.out.println(driver_firefox.getPageSource().isEmpty()+"++++++");
				if (pageSource.contains(keyString)) {
					System.out.println(urlddress[i] + "30秒在稿");
				} else {
					System.out.println("title" + driver_firefox.getTitle() + urlddress[i]);// 不在稿的显示，页面
				}
				logger.info("核查完毕");
			} catch (Exception e) {
				pageSource = driver_firefox.getPageSource();
				if (pageSource.contains(keyString)) {
					System.out.println(urlddress[i] + "超时在稿");
				} else {
					System.out.println("超时title" + driver_firefox.getTitle() + urlddress[i]);// 不在稿的显示，页面
				}
				logger.info("核查完毕");
			} finally {
			}
		}
	
	}

}
