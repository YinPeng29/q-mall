package com.bays.utils.redisTool;

import java.util.List;

public interface RedisUtil<H,K,V> {
    //增
    void add(K key,String value);
    void addObj(H objectKey,K key,V object);

    //删
    void delete(K key);
    void delete(List<K> listKeys);
    void deleteObj(H objectKey,K key);

    //改
    void update(K key,String value);
    void update(H objectKey,K key,V object);

    //查
    String get(K key);
    V getObject(H objectKey,K key);
}
