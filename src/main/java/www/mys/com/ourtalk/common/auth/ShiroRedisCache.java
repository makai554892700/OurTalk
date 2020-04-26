package www.mys.com.ourtalk.common.auth;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import www.mys.com.ourtalk.common.base.StaticParam;
import www.mys.com.ourtalk.utils.LogUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class ShiroRedisCache<K, V> implements Cache<K, V> {
    private RedisTemplate<String, V> redisTemplate;
    private String prefix = StaticParam.SHIRO_PREFIX;

    public ShiroRedisCache(RedisTemplate<String, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public ShiroRedisCache(RedisTemplate<String, V> redisTemplate, String prefix) {
        this(redisTemplate);
        setPrefix(prefix);
    }

    @Override
    public V get(K key) throws CacheException {
//        LogUtils.log("get Key: {" + key + "}");
        if (key == null) {
            return null;
        }
        String realKey = getRealKey(key);
        return redisTemplate.opsForValue().get(realKey);
    }

    @Override
    public V put(K key, V value) throws CacheException {
//        LogUtils.log(" put Key: {" + key + "}, value: {" + value + "}");
        if (key == null || value == null) {
            return null;
        }
        String realKey = getRealKey(key);
        redisTemplate.opsForValue().set(realKey, value, StaticParam.SHIRO_SESSION_TIME_OUT, TimeUnit.SECONDS);
        return value;
    }

    @Override
    public V remove(K key) throws CacheException {
//        LogUtils.log("remove Key: {" + key + "}");
        if (key == null) {
            return null;
        }
        String realKey = getRealKey(key);
        ValueOperations<String, V> vo = redisTemplate.opsForValue();
        V value = vo.get(realKey);
        redisTemplate.delete(realKey);
        return value;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.getConnectionFactory().getConnection().flushDb();
    }

    @Override
    public int size() {
        Long len = redisTemplate.getConnectionFactory().getConnection().dbSize();
        return len.intValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<K> keys() {
        Set<String> set = redisTemplate.keys(getPrefix());
        Set<K> result = new HashSet<>();
        if (CollectionUtils.isEmpty(set)) {
            return Collections.emptySet();
        }
        for (String key : set) {
            result.add((K) key);
        }
        return result;
    }

    @Override
    public Collection<V> values() {
        Set<K> keys = keys();
        List<V> values = new ArrayList<>(keys.size());
        for (K k : keys) {
            values.add(redisTemplate.opsForValue().get(getRealKey(k)));
        }
        return values;
    }

    private String getRealKey(K key) {
        return this.prefix + key;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
