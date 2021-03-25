package com.practice.pairproject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.pairproject.util.StoragePaper;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Builder
@SpringBootTest
class PairprojectApplicationTests {

    @Test
    void contextLoads() {

    }


    /*@Test
    public boolean loadPaper2(){
        //实例一个ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        try {
            //new File("E:\\WorkSpace\\msg\\src\\main\\resources\\json\\hotelList\\hotellist_cityId1.json")
            *//*
             public <T> T readValue(File src, TypeReference valueTypeRef)
             调用这个，暂时猜测是，前者是具体文件，后者是需要加工成的参考类型，这里是通过jsonFactory处理为map文件
             *//*
            Map<String,Object> map = mapper.readValue(
                    new File("D:\\【Third _Grade_Next】\\软工实践\\结对\\第二次\\论文数据\\ECCV（2016至2020，3033份）\\2016_A Neural Approach to Blind Motion Deblurring.json"),//使用mapper.readValue，读取json文件
                    new TypeReference<Map<String, Object>>(){});
            //在反序列化时忽略在 json 中存在但 Java 对象不存在的属性
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            //取得需要的hotels，（这里hotelList就是整个json文件最外成的key，value就是hotels但也是map，强转为list就可进行遍历。）
            List<Hotel> hotels = (List<Hotel>) map.get("HotelList");
            Hotel hotel = new Hotel();//实例化实体对象
            int inser=0;//计算添加了多少条
            for (int i=0;i<hotels.size();i++){
                Map h = (Map) hotels.get(i);
                //封装实体
                hotel.setCityID(h.get("CityID")==null?null:(Integer) h.get("CityID"));//添加三元运算，主要是判断是否为空，为空就写null，不为空就转为需要的类型
                hotel.setNameChn(h.get("NameChn")==null?null:h.get("NameChn").toString());
                hotel.setNameEng(h.get("NameEng")==null?null:h.get("NameEng").toString());
                hotel.setAddress(h.get("Address")==null?null:h.get("Address").toString());
                hotel.setAddressEng(h.get("AddressEng")==null?null:h.get("AddressEng").toString());
                hotel.setStar(h.get("star")==null?null:(Integer) h.get("star"));
                hotel.setIntroduction(h.get("Introduction")==null?null:h.get("Introduction").toString());
                hotel.setLatitude(h.get("Latitude")==null?null:h.get("Latitude").toString());
                hotel.setLongitude(h.get("Latitude")==null?null:h.get("Latitude").toString());
                hotel.setCommentScore(h.get("CommentScore")==null?null:Double.parseDouble( h.get("CommentScore").toString()));//intger装doouble 要先转为string
                hotel.setRemarks(h.get("Remarks")==null?null:h.get("Remarks").toString());
                insert(hotel);//添加进数据库
                inser ++;
            }
            System.out.println("添加条数："+inser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }*/

}
