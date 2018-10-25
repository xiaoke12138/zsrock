package sendMail;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

/**
 * 读取配置文件工具类
 * 
 * @author ddd
 *
 * 		@LoadPolicy(LoadType.MERGE) @Sources({ "file:~/.myapp.config",
 *         "file:/etc/myapp.config", "classpath:foo/bar/baz.properties" })
 */
@Sources({ "classpath:config.properties" })
public interface ServerConfig extends Config {

	// dt
	@Key("mailaddress")
	@Separator(";")
	String[] mailaddress();

	@Key("mongohost")
	String mongo_host();

	@Key("mongo.MONGO_PORT")
	int MONGO_PORT();

	@Key("mongo.MONGO_USERNAME")
	String MONGO_USERNAME();

	@Key("mongo.MONGO_PASSWORD")
	String MONGO_PASSWORD();

	@Key("mongo.MONGO_DB_NAME")
	String MONGO_DB_NAME();

	@Key("mongo.MONGO_COLLECTION_NAME")
	String MONGO_COLLECTION_NAME();
	
	@Key("siteidarry")
	@Separator(";")
	int[] siteidarry();

}
