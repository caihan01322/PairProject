package com.eepractice.webcrawller.controller;


import com.eepractice.webcrawller.bean.Item;
import com.eepractice.webcrawller.bean.Keyword;
import com.eepractice.webcrawller.bean.ResultItem;
import com.eepractice.webcrawller.bean.User;
import com.eepractice.webcrawller.context.UserContext;
import com.eepractice.webcrawller.repository.ResultItemRepository;
import com.eepractice.webcrawller.repository.UserRepository;
import com.eepractice.webcrawller.service.DataService;
import com.eepractice.webcrawller.service.RedisService;
import com.eepractice.webcrawller.utils.CommonUtils;
import com.eepractice.webcrawller.utils.DataUtils;
import lombok.Data;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/data")
public class DataFetchController {

    @Autowired
    ResultItemRepository dataRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    CommonUtils commonUtils;

    @Autowired
    DataService dataService;

    @Autowired
    RedisService redisService;


    /**
     *  搜索获得结果
     * @param queryTitles 论文题目列表
     * @param isUpload 是否是文件上传
     * @return
     */
    @PostMapping(value = "/search/{type}",produces = "application/json")
    public ResponseEntity<Map<String,Object>> fetchAllData(@RequestBody List<String> queryTitles,@PathVariable("type")Boolean isUpload){
        //  保存去重结束后的数据
        Set<Item> resultItemSet = new HashSet<>();
        for(String str : queryTitles){
            List<ResultItem> resultContent = dataRepository.findResultItemsByTitleLike("%"+str+"%");
            resultItemSet.addAll(CommonUtils.convertResultItemCollection(resultContent));
        }
        Map<String,Object> responseMap = new HashMap<>();
        Keyword[] countKeywords = CommonUtils.countKeywords(resultItemSet);
        responseMap.put("count",countKeywords);
        responseMap.put("data",resultItemSet);
        // 用户已登录且不是批量搜索
        if(UserContext.isHasCurrentUser() && !isUpload){
            String username = UserContext.getCurrentUser();
            // 更新用户的搜索记录
            redisService.updateUserSearchHistory(username, queryTitles.get(0));
        }
        return ResponseEntity.ok(responseMap);
    }


    /**
     *  获得用户的收藏论文项
     * @param userid 用户的id
     * @param token token
     * @return
     */
    @GetMapping(value = "/coll/{userid}",produces = "application/json")
    public ResponseEntity<Map<String,Object>> fetchCollectionsByUserId(@PathVariable Integer userid
            ,@RequestHeader("Authorization") String token){
        // token为验证信息，token有效时才执行操作
        User user = commonUtils.parseToken(token);
        if (user != null){
            Map<String,Object> responseMap = new HashMap<>();
            User foundUser = userRepository.findUserById(userid);
            List<ResultItem> userCollections = foundUser.getCollections();
            List<Item> resultItemCollection = CommonUtils.convertResultItemCollection(userCollections);
            responseMap.put("data",resultItemCollection);
            return ResponseEntity.ok(responseMap);
        }
        else{
            Map<String,Object> map = new HashMap<>();

            return new ResponseEntity<>(commonUtils.generateSimpleMap("msg","无效token"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 删除用户的某条收藏
     * @param id
     * @param token
     * @return
     */
    @DeleteMapping(value = "/del/{id}",produces = "application/json")
    public ResponseEntity<Map<String,Object>> delCollection(@PathVariable String id,@RequestHeader("Authorization") String token){
        // token为验证信息，token有效时才执行操作
        User user = commonUtils.parseToken(token);
        if (user != null){
            try{
                List<ResultItem> newList = new ArrayList<>();
                user.getCollections().forEach(item -> {
                    if(!item.getId().equals(id)){
                        newList.add(item);
                    }
                });
                user.setCollections(newList);
                userRepository.save(user);
                return ResponseEntity.ok(commonUtils.generateSimpleMap("code",200));
            }
            catch (Exception e){
                return ResponseEntity.ok(commonUtils.generateSimpleMap("code",201));
            }
        }else{
            return new ResponseEntity<>(commonUtils.generateSimpleMap("msg","无效token"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 添加用户收藏
     * @param id
     * @param token
     * @return
     */
    @GetMapping(value = "/add/{id}",produces = "application/json")
    public ResponseEntity<Map<String,Object>> addCollection(@PathVariable String id,@RequestHeader("Authorization") String token){
        User user = commonUtils.parseToken(token);
        if (user != null){
            try{
                // 查看用户是否已收藏过
                for (ResultItem item : user.getCollections()) {
                    // 用户已经收藏过该论文
                    if(item.getId().equals(id)){
                        return ResponseEntity.ok(commonUtils.generateSimpleMap("code",201));
                    }
                }
                // 用户未收藏过
                ResultItem targetItem = dataRepository.findResultItemById(id);
                user.getCollections().add(targetItem);
                userRepository.save(user);
                return ResponseEntity.ok(commonUtils.generateSimpleMap("code",200));
            }
            catch (Exception e){
                return ResponseEntity.ok(commonUtils.generateSimpleMap("code",203));
            }
        }else{
            return new ResponseEntity<>(commonUtils.generateSimpleMap("msg","无效token"), HttpStatus.NOT_FOUND);
        }
    }


    /**
     * 获取统计数据
     * @return
     */
    @GetMapping(value = "/statistics",produces = "application/json")
    public ResponseEntity<?> getStatisticsData(){
        Map<String,Object> responseMap = new HashMap<>();
        Map<String, Object> lineData = dataService.analyseDataAndGet();
        Pair<Integer, List<Keyword>> resultPair = dataService.getKeywordTop(10);
        responseMap.put("lineData",lineData);
        responseMap.put("count",resultPair.getFirst());
        responseMap.put("keywords",resultPair.getSecond());
        return ResponseEntity.ok(responseMap);
    }
}
