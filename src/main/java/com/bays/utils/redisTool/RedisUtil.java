package com.bays.utils.redisTool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisUtil {
    private JedisPool jedisPool;
    private Jedis jedis;
    public RedisUtil(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public String set(String key,String value){
       try{
           if(jedisPool!=null){
               jedis = jedisPool.getResource();
               return jedis.set(key,value);
           }else {
               return null;
           }
       }finally {
           returnResource(jedis);
       }

    }

    private void returnResource(Jedis jedis){
        jedis.close();
    }
}
