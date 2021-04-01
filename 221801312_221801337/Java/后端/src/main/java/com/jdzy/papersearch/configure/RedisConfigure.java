package com.jdzy.papersearch.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class RedisConfigure  {
    @Autowired
    private RedisConnectionFactory connectionFactory;
    @Bean(name="cacheManger")
    public CacheManager initRedisCacheManager(){
        HashMap<String ,RedisCacheConfiguration> configMap = new HashMap<>();

        configMap.put("similarWord",getMyConfigure().entryTtl(Duration.ofMinutes(20)));
        configMap.put("searchPaper",getMyConfigure().entryTtl(Duration.ofMinutes(20)));
        configMap.put("top10",getMyConfigure().entryTtl(Duration.ofDays(1)));
        configMap.put("sunburst",getMyConfigure().entryTtl(Duration.ofDays(1)));

        RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(getMyConfigure())
                .withInitialCacheConfigurations(configMap)
                .build();
        return redisCacheManager;
    }
    private RedisCacheConfiguration getMyConfigure(){
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();

        config=config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));
        config=config.entryTtl(Duration.ofMinutes(5));
        return config;
    }
    @Bean("preSearchThreadPool")
    public ThreadPoolExecutor getPreSearchThreadPool(){
        ThreadFactory threadFactory = new CustomizableThreadFactory("springThread-pool");
        return new ThreadPoolExecutor(2,5,10,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(100),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
    }
}
