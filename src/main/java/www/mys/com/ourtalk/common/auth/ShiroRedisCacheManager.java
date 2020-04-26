package www.mys.com.ourtalk.common.auth;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class ShiroRedisCacheManager extends AbstractCacheManager {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    protected Cache<String, Object> createCache(String name) throws CacheException {
        return new ShiroRedisCache<String, Object>(redisTemplate, name);
    }
}
