package com.eepractice.webcrawller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    private static final int MAX_HISTORY_NUM = 5;

    /**
     * 处理用户搜索记录的更新，一次最多显示5条记录
     */

    public void updateUserSearchHistory(String username, String keyword) {
        // 获得操作对象
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        // 用户搜索历史列表
        List<String> userHistory = listOperations.range(username, 0, -1);
        // 搜索记录提到最左
        if (userHistory.contains(keyword)) {
            // 从右往左删除第一个
            listOperations.remove(username, -1, keyword);
            listOperations.leftPush(username, keyword);
        }
        // 否则表示是新的记录
        else {
            // 若size<5，直接push
            if (userHistory.size() < MAX_HISTORY_NUM) listOperations.leftPush(username, keyword);
            else {
                listOperations.rightPop(username);
                listOperations.leftPush(username, keyword);
            }
        }
    }

    public List<String> getUserSearchHistory(String username){
        return redisTemplate.opsForList().range(username,0,-1);
    }

    public void clearUserHistory(String username){
        int size = redisTemplate.opsForList().range(username, 0, -1).size();
        for(int i = 0 ; i < size ; i ++){
            redisTemplate.opsForList().leftPop(username);
        }
    }
}
