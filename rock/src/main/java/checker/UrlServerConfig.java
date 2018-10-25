package checker;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Key;
import org.aeonbits.owner.Config.Sources;

/**
 * 读取配置文件工具类
 * 
 * @author ddd
 *
 * 		@LoadPolicy(LoadType.MERGE) @Sources({ "file:~/.myapp.config",
 *         "file:/etc/myapp.config", "classpath:foo/bar/baz.properties" })
 */
@Sources({ "classpath:check.properties" })
public interface UrlServerConfig extends Config {

	// dt
	@Key("url")
	@Separator(",")
	String[] urlddress();
	
	// dt
	@Key("url2")
	@Separator(",")
	String[] urlddresshalf();
	
	
	@Key("target")
	String keyString();
}
