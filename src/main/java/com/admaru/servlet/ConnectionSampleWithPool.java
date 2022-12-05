package com.admaru.servlet;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisConnectionException;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class ConnectionSampleWithPool {
	private static final String REDIS_CON_URL = "redis://127.0.0.1:6379/0";

	public static GenericObjectPool<StatefulRedisConnection<String, String>> pool = null;

	public static void main(String[] args) throws InterruptedException 
	{
//		pool = nonClusterPoolUsage();
//
//		testSetValue("key-pool-test-1", "key-pool-test-1");
//		testSetValue("key-pool-test-2", "key-pool-test-2");
//		testSetValue("key-pool-test-3", "key-pool-test-3");
//
//		pool.close();

	}

	@SuppressWarnings("unused")
	public void setValue(String key, String value) {
	
		GenericObjectPool<StatefulRedisConnection<String, String>> pool = nonClusterPoolUsage();
	
		try (StatefulRedisConnection<String, String> connection = pool.borrowObject()) {
			connection.sync().set(key, value);
			value = connection.sync().get(key);
			System.out.println(value);
		} catch (RedisConnectionException e) {
			System.out.println(String.format("Failed to connect to Redis server: %s", e));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pool.close();
	}

	public void getValue(String key) {
	
		GenericObjectPool<StatefulRedisConnection<String, String>> pool = nonClusterPoolUsage();
	
		try (StatefulRedisConnection<String, String> connection = pool.borrowObject()) 
		{
			String value = connection.sync().get(key);
			System.out.println("getValue="+value);
		
		} catch (RedisConnectionException e) {
			System.out.println(String.format("Failed to connect to Redis server: %s", e));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pool.close();
	}


	public static GenericObjectPool<StatefulRedisConnection<String, String>> nonClusterPoolUsage() {
		RedisClient client = RedisClient.create(REDIS_CON_URL);
		client.setOptions(ClientOptions.builder().autoReconnect(true).build());

		return ConnectionPoolSupport.createGenericObjectPool(() -> client.connect(), createPoolConfig());
	}

	public static GenericObjectPool<StatefulRedisClusterConnection<String, String>> useClusterPoolUsage() {
		RedisClusterClient clusterClient = RedisClusterClient.create(REDIS_CON_URL);
		clusterClient.setOptions(ClusterClientOptions.builder().autoReconnect(true).build());

		return ConnectionPoolSupport.createGenericObjectPool(() -> clusterClient.connect(), createPoolConfig());
	}

	public static GenericObjectPoolConfig createPoolConfig() {
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxTotal(20);
		poolConfig.setMaxIdle(10);
		poolConfig.setBlockWhenExhausted(true);
		poolConfig.setMaxWaitMillis(1000);
		poolConfig.setMinIdle(5);
		return poolConfig;
	}
}