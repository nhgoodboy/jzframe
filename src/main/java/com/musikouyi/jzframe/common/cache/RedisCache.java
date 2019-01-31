package com.musikouyi.jzframe.common.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.util.Collection;
import java.util.Set;

/**
 * 权限数据的缓存
 * @param <K>
 * @param <V>
 */
@Slf4j
@Component
public class RedisCache<K, V> implements Cache<K, V> {

    @Autowired
    private RedisTemplate redisTemplate;

    private final String CACHE_PREFIX = "shiro-cache:";

    private byte[] getKey(K k) {
        if (k instanceof String) {
            return (CACHE_PREFIX + k).getBytes();
        }
        return SerializationUtils.serialize(k);
    }

    @Override
    public V get(K k) throws CacheException {
        log.info("从redis中获取授权数据");
        byte[] value = (byte[]) redisTemplate.opsForValue().get(getKey(k));
        if (value != null) {
            return (V) SerializationUtils.deserialize(value);
        }
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        log.info("更新redis中的授权数据");
        byte[] key = getKey(k);
        byte[] value = SerializationUtils.serialize(v);
        redisTemplate.opsForValue().set(key, value);
//        redisTemplate.expire(key, 600000, TimeUnit.MILLISECONDS);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        log.info("删除redis中的授权数据");
        byte[] key = getKey(k);
        byte[] value = (byte[]) redisTemplate.opsForValue().get(key);
        redisTemplate.delete(key);
        if (value != null) {
            return (V) SerializationUtils.deserialize(value);
        }
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
