package sendMail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class JavaMail {
	private static Logger logger = LoggerFactory.getLogger(JavaMail.class);
	private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
	
	private static final String MONGO_HOST = cfg.mongo_host();
	private static final Integer MONGO_PORT = cfg.MONGO_PORT();
	private static final String MONGO_USERNAME = cfg.MONGO_USERNAME();
	private static final String MONGO_PASSWORD = cfg.MONGO_PASSWORD();
	private static final String MONGO_DB_NAME = cfg.MONGO_DB_NAME();
	private static final String MONGO_COLLECTION_NAME = cfg.MONGO_COLLECTION_NAME();

	private static final String[] mailaddresses = cfg.mailaddress();
	private static final int[] siteidarray = cfg.siteidarry();

	private static DBCollection collection;

	public static void init() {
		logger.info("init..");
		MongoClient mongoClient = new MongoClient(MONGO_HOST, MONGO_PORT);
		/**
		 * 2.获取到指定db（若不存在，则mongo会创建该db）
		 */
		DB db = mongoClient.getDB(MONGO_DB_NAME);
		Set<String> collections = db.getCollectionNames();
		collection = db.getCollection(MONGO_COLLECTION_NAME);
	}

	public static void main(String[] args) throws Exception {
		logger.info("main方法..");
		Map<String, String> map = new HashMap<>();
		init();
		StringBuilder sb = new StringBuilder();
		//查询开始时间
	    logger.info("开始时间", System.currentTimeMillis()/1000);
		for (int i = 0; i < siteidarray.length; i++) {
			logger.info("开始核查");
			String getpttime = getpttime(siteidarray[i]);
			if (siteidarray[i] == 1) {
				logger.info("核查新浪..");
				map.put("新浪网最后一条数据时间是", getpttime);
			} else if (siteidarray[i] == 3) {
				logger.info("核查网易..");
				map.put("网易网最后一条数据时间是", getpttime);
			} else if (siteidarray[i] == 4) {
				logger.info("核查腾讯.");
				map.put("腾讯网最后一条数据时间是", getpttime);
			} else if (siteidarray[i] == 5) {
				logger.info("核查凤凰..");
				map.put("凤凰网最后一条数据时间是", getpttime);
			} else if (siteidarray[i] == 353) {
				logger.info("核查一点资讯..");
				map.put("一点资讯最后一条数据时间是", getpttime);
			} else if (siteidarray[i] == 354) {
				logger.info("核查今日头条..");
				map.put("今日头条最后一条数据时间是", getpttime);
			}
		}
		logger.info("结束时间", System.currentTimeMillis()/1000);
		System.out.println();
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			sb.append(entry.getKey());
			sb.append(entry.getValue());
			sb.append(";");
			sb.append(System.getProperty("line.separator"));
		}
		// 获取Mongo客户端
		//int count = collection.find(new BasicDBObject("site", 1)).count();// 获得目前时间 mongodb内该网站的总数量
		// 按照倒序查第一条数据

		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.163.com");// smtp服务器地址
		String[] mailaddress = mailaddresses;
		Session session = Session.getInstance(props);
		session.setDebug(true);
		Message msg = new MimeMessage(session);
		msg.setSubject("每天日常核查六家网站预警情况");
		msg.setText(sb.toString());
		msg.setFrom(new InternetAddress("srj19924741@163.com"));// 发件人邮箱(我的163邮箱)

		Transport transport = session.getTransport();
		transport.connect("srj19924741@163.com", "srj19924741");// 发件人邮箱,授权码(可以在邮箱设置中获取到授权码的信息)
		for (int i = 0; i < mailaddress.length; i++) {
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mailaddress[i])); // 收件人邮箱(我的QQ邮箱)
			msg.saveChanges();
			transport.sendMessage(msg, msg.getAllRecipients());
			logger.info("数组邮件发送成功...");
		}
		transport.close();
	}

	public static String getpttime(int siteid) {
		logger.info("核查siteid"+siteid);
		DBCursor sort = collection.find(new BasicDBObject("site", siteid)).sort(new BasicDBObject("it", -1)).limit(20);
		try {
			Long maxtime=(long) 0;
			while(sort.hasNext()) {
				JSONObject jo = JSONObject.parseObject(sort.next().toString());
				Long inter = jo.getLong("pt");
				logger.info("比较核查时间"+inter);
				if(maxtime.longValue()==0) {
					logger.info("maxtime第一次取"+maxtime);
					maxtime=inter.longValue();
				}else {
					maxtime=(maxtime.longValue()>inter.longValue())?maxtime.longValue():inter.longValue();
					logger.info("maxtime比较后为"+maxtime);
				}
			}
			logger.info("maxtime最终比较后为"+maxtime);
			Date date=new Date(maxtime.longValue() * 1000);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));//东八区时间
			String str = sdf.format(date);
			logger.info("返回的str最终时间为"+str);
			return str;
		} catch (Exception e) {
			logger.info("无数据，数据异常...siteid："+siteid);
			return "无数据，数据异常";
		}	
	}
}
