package cn.capitek.web.controller;

import java.io.IOException;

import org.gearman.Gearman;
import org.gearman.GearmanServer;
import org.gearman.GearmanWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 创建一个job server 服务器，并且注册一个worker。
 *
 */
@Component
public class MysqlToRedisJobServer {

	private static final Logger log = LoggerFactory.getLogger(MysqlToRedisJobServer.class);

	@Autowired
	MysqlToRedisWorker mysqlToRedisWorker;

	public void startJobServer() throws Exception {
		/*
		 * Create a Gearman instance
		 */
		Gearman gearman = Gearman.createGearman();

		try {

			/*
			 * Start a new job server. The resulting server will be running in
			 * the local address space.
			 * 
			 * Parameter 1: The port number to listen on
			 * 
			 * throws IOException
			 */
			GearmanServer server = gearman.startGearmanServer(4730);

			if (server != null) {
				log.info("Start gearm jobServer with java !funcname:" + MysqlToRedisWorker.ECHO_FUNCTION_NAME + " port: 4730");
			}

			/*
			 * Create a gearman worker. The worker poll jobs from the server and
			 * executes the corresponding GearmanFunction
			 */
			GearmanWorker worker = gearman.createGearmanWorker();

			/*
			 * Tell the worker how to perform the echo function
			 */
			worker.addFunction(MysqlToRedisWorker.ECHO_FUNCTION_NAME, mysqlToRedisWorker);

			/*
			 * Tell the worker that it may communicate with the given job server
			 */
			worker.addServer(server);

		} catch (IOException ioe) {

			/*
			 * If an exception occurs, make sure the gearman service is shutdown
			 */
			gearman.shutdown();
			throw ioe;
		}
	}
}
