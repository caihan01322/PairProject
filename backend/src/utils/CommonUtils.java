package com.eepractice.webcrawller.utils;

import com.eepractice.webcrawller.bean.Item;
import com.eepractice.webcrawller.bean.Keyword;
import com.eepractice.webcrawller.bean.ResultItem;
import com.eepractice.webcrawller.bean.User;
import com.eepractice.webcrawller.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.*;

@Component
public class CommonUtils {

    @Autowired
    UserRepository userRepository;

    static final String SECRET_KEY = "secret_key";

    public static List<Item> convertResultItemCollection(List<ResultItem> resultItemList){
        List<Item> itemList = new ArrayList<>();
        resultItemList.forEach(item -> itemList.add(DataUtils.ResultItem2Item(item)));
        return itemList;
    }


    public static Keyword[] countKeywords(Set<Item> originItemSet){
        List<Keyword> resultList = new LinkedList<>();
        Map<String, Integer> result = DataUtils.countKeyWord(originItemSet);
        result.forEach((k,v) -> {
            resultList.add(new Keyword(k,v));
        });
        int max = Math.min(resultList.size(), 10);
        Keyword[] karr = new Keyword[max];
        int i = 0;
        for (Keyword keyword : resultList) {
            karr[i] = keyword;
            i ++ ;
            if (i  == max ) break;
        }
        return karr;
    }

    public static String MD5Encode(String originPwd){
        return DigestUtils.md5DigestAsHex(originPwd.getBytes());
    }

    public static String generateToken(User user){
        JwtBuilder jwtBuilder =
                Jwts.builder()
                        .setSubject(user.getUsername())
                        .setIssuedAt(new Date())
                        .signWith(SignatureAlgorithm.HS256, SECRET_KEY);
        // 获取token字符串
        return jwtBuilder.compact();
    }

    public User parseToken(String token){
        try{
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            String un = claims.getSubject();
            return userRepository.findUserByUsername(un);
        }catch (Exception e){
            return null;
        }
    }

    public Map<String,Object> generateSimpleMap(String k, Object v){
        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put(k,v);
        return responseMap;
    }
}
