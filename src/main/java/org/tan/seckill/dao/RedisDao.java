package org.tan.seckill.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tan.seckill.utils.RedisClient;

import java.util.List;

@Slf4j
@Component
public class RedisDao {

    @Autowired
    private RedisClient redisClient;

    public static final String KEY_PREFIX = "seckill:";
    public static final int TIMEOUT = 60 * 60;

    public <T> T getObject(long id, Class<T> targetClass) {
        String key = KEY_PREFIX + id;
        T object = null;
        try {
            object = redisClient.get(key, targetClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    public <T> void putObject(long id, T object) {
        String key = KEY_PREFIX + id;
        try {
            redisClient.setWithExpire(key, object, TIMEOUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> List<T> getObjectList(String mark, Class<T> targetClass) {
        String key = KEY_PREFIX + mark;
        List<T> objList = null;
        try {
            objList = redisClient.getList(key, targetClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objList;
    }

    public <T> void putObjectList(String mark, List<T> objList) {
        String key = KEY_PREFIX + mark;
        try {
            redisClient.setListWithExpire(key, objList, TIMEOUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
