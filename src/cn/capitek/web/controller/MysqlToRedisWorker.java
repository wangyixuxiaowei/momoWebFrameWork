package cn.capitek.web.controller;

import java.util.concurrent.TimeUnit;
import org.gearman.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * mysql 同步到 redis 的工具类。
 * 连接远程gearman job server 实现原理 
 * mysql_udf >>>>> gearman job server>>>> gearm worker(本类) >>>>> redis
 *
 */
@Component
public class MysqlToRedisWorker implements GearmanFunction {
	private static final Logger log = LoggerFactory.getLogger(MysqlToRedisWorker.class);

	public static final String ECHO_FUNCTION_NAME = "MySQLToRedis";

	/**
	 * 连接其他jobserver用的。
	 */
	public void startWorker() {
		Gearman gearman = Gearman.createGearman(); // 创建gearman对象，无论是client，worker都是由这个对象产生的
		log.info("MysqlToRedisWorkder connection:192.168.181.130:4730,function:" + MysqlToRedisWorker.ECHO_FUNCTION_NAME);
		GearmanServer server = gearman.createGearmanServer("192.168.181.130", 4730); // 创建gearman server，主要是server地址和端口
		GearmanWorker worker = gearman.createGearmanWorker(); // 正题来了，创建work节点。
		worker.setReconnectPeriod(2, TimeUnit.SECONDS); // 设置超时重连时间
		worker.setMaximumConcurrency(5); // 最大并发数
		worker.addFunction(ECHO_FUNCTION_NAME, this); // 添加function方法
		worker.addServer(server); // 将work添加到server中
		log.info("MysqlToRedisWorkder is started!!!!");
	}

	@Override
	public byte[] work(String func, byte[] data, GearmanFunctionCallback callback) throws Exception {
		log.info("收到mysql的数据：：：" + new String(data));
		System.out.println("@@@@@ " + new String(data)); // byte[]
		// data是从client传递来的参数，我们将参数增加@@@@字符串后，原封不动返回
		return data;
	}
}