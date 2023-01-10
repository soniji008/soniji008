package com.question.pro.problem.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration

public class AppConfig {
	
	@Bean
	public RestTemplate restTemplate() {
		
		return new RestTemplate();
	}

	@Bean
    public CacheManager cacheManager() {
		CaffeineCacheManager cacheManager = new CaffeineCacheManager("example");
		cacheManager.setCaffeine(caffeineCacheBuilder());
        return cacheManager;
    }

	private Caffeine<Object, Object> caffeineCacheBuilder() {
		
		return Caffeine.newBuilder()
				.initialCapacity(100)
				.maximumSize(500)
				.expireAfterAccess(15,TimeUnit.MINUTES)
				.recordStats();
	}
}
