package com.eepractice.webcrawller.controller;

import com.eepractice.webcrawller.bean.User;
import com.eepractice.webcrawller.service.RedisService;
import com.eepractice.webcrawller.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    RedisService redisService;

    @Autowired
    CommonUtils commonUtils;

    /**
     * 返回用户的搜索记录
     * @param username
     * @return
     */

    @GetMapping(value = "/history/{username}",produces = "application/json")
    public ResponseEntity<?> getUserSearchHistory(@PathVariable("username") String username){
        List<String> searchHistory = redisService.getUserSearchHistory(username);
        return ResponseEntity.ok(searchHistory);
    }

    /**
     * 清空用户的搜索记录
     * @param token
     * @return
     */

    @GetMapping(value = "/clear",produces = "application/json")
    public ResponseEntity<?> clearUserHistory(@RequestHeader("Authorization")String token){
        User user = commonUtils.parseToken(token);
        redisService.clearUserHistory(user.getUsername());
        Map<String,Object> responseMap = new HashMap<>();
        return ResponseEntity.ok(responseMap);
    }
}
