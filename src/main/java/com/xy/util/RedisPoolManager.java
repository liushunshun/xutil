package com.xy.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis连接池管理器
 */
public class RedisPoolManager {

    private static final Logger logger = LogManager.getLogger(RedisPoolManager.class);

    public  String REDIS_SERVER = "127.0.0.1";

    public  int REDIS_PORT = 6379;

    private JedisPool pool = null;

    private JedisPool getInstance() {

        if (pool == null) {

            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(1000);
            config.setMaxIdle(20);
            config.setMaxWaitMillis(10 * 1000l);
            config.setTestOnBorrow(true);
            config.setTestOnReturn(true);

            pool = new JedisPool(config, REDIS_SERVER, REDIS_PORT, 10);
        }

        return pool;

    }

    /**
     * 获取jedis
     *
     * @return
     */
    public Jedis getJedis() {

        Jedis jedis = null;

        try {
            jedis = getInstance().getResource();
            // jedis.auth(REDIS_AUTH);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return jedis;

    }

    /**
     * 返回jedis
     *
     * @param jedis
     */
    @SuppressWarnings("deprecation")
	public void returnJedis(Jedis jedis) {
        try {

            if (jedis != null) {
                getInstance().returnResource(jedis);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    /**
     * 返回关闭的redis
     *
     * @param jedis
     */
    @SuppressWarnings("deprecation")
	public void returnBrokenJedis(Jedis jedis) {
        try {

            if (jedis != null) {
                getInstance().returnBrokenResource(jedis);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    /**
     * 释放jedis
     *
     * @param jedis
     */
    @SuppressWarnings("deprecation")
	public void releaseJedis(Jedis jedis) {
        pool.returnResource(jedis);

    }

}
