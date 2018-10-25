package checker;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FireFoxTest {
	private static Logger logger = LoggerFactory.getLogger(FireFoxTest.class);
	private static UrlServerConfig cfg = ConfigFactory.create(UrlServerConfig.class);
	private static final String[] urlddress = cfg.urlddress();
	public static String BrowserPath = "D:\\huohu\\firefox.exe";
	public static WebDriver driver_firefox = null;
	// private ExecutorService executor = Executors.newScheduledThreadPool(5);

	public static void init() {
		System.setProperty("webdriver.firefox.bin", BrowserPath);
		driver_firefox = new FirefoxDriver();
	}

	public static void main(String[] args) {
		init();
		// 打开火狐浏览器；
		logger.info("初始化完成");
		String pageSource=null;
		for (int i = 0; i < urlddress.length; i++) {
			try {
				logger.info("正在核查的网站" + urlddress[i]);
				driver_firefox.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				//driver_firefox.get(urlddress[i]);
				 driver_firefox.get("http://www.dd.com/");
				pageSource = driver_firefox.getPageSource();
				System.out.println(driver_firefox.getPageSource().isEmpty()+"++++++");
				if (pageSource.contains("蔬菜霉变")) {
					System.out.println(urlddress[i] + "30秒在稿");
				} else {
					System.out.println("title" + driver_firefox.getTitle() + urlddress[i]);// 不在稿的显示，页面
				}
				logger.info("核查完毕");
			} catch (Exception e) {
				pageSource = driver_firefox.getPageSource();
				if (pageSource.contains("蔬菜霉变")) {
					System.out.println(urlddress[i] + "超时在稿");
				} else {
					System.out.println("title" + driver_firefox.getTitle() + urlddress[i]);// 不在稿的显示，页面
				}
				logger.info("核查完毕");
			} finally {
			}
		}
	}
}
