package com.gzcstec.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * redis 处理开锁、解锁
 * Created by Administrator on 2017/11/11 0011.
 */
@Component
@Slf4j
public class RedisLockService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 加锁
     * @return
     */
    public boolean lock(String key , String value) {
        if(stringRedisTemplate.opsForValue().setIfAbsent(key , value)) {
            //加锁成功
            return true;
        }

        String currentTime = stringRedisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(currentTime) && Long.parseLong(currentTime) < System.currentTimeMillis()) {
            //查看是否先获取钥匙
            String oldTime = stringRedisTemplate.opsForValue().getAndSet(key , value);
            if(!StringUtils.isEmpty(oldTime) && oldTime.equals(currentTime)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 解锁
     */
    public void unlock(String key , String value) {
        //安全解锁
        try {
            String currentTime = stringRedisTemplate.opsForValue().get(key);
            if(!StringUtils.isEmpty(currentTime) && currentTime.equals(value)) {
                stringRedisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e) {
            log.error("【redis分布式锁】解锁失败，{}" , e);
        }
    }
}
