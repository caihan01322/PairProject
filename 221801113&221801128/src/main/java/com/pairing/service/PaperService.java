package com.pairing.service;

import com.pairing.bean.Paper;
import com.pairing.mapper.PaperMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaperService {

    @Autowired
    PaperMapper paperMapper;

    /**
     * 获取paper的list和总数
     * @param searchInfo
     * @param pageNum
     * @return
     */
    public Map<List<Paper>, Integer> getPaper(String searchInfo, int pageNum) {
        Map<List<Paper>, Integer> map= new HashMap<>();
        map.put(paperMapper.getPaper(searchInfo, Integer.valueOf(pageNum * 4)), paperMapper.getPaperCount(searchInfo));
        return map;
    }

    /**
     * 获取收藏夹的paper的list和总数
     * @param searchInfo
     * @param pageNum
     * @param userName
     * @return
     */
    public Map<List<Paper>, Integer> getCollectPaper(String searchInfo, int pageNum, String userName) {
        Map<List<Paper>, Integer> map= new HashMap<>();
        map.put(paperMapper.getCollectPaper(searchInfo, Integer.valueOf(pageNum * 4), userName)
                , paperMapper.getCollectPaperCount(searchInfo, userName));
        return map;
    }

    /**
     * 收藏
     * @param uid
     * @param did
     * @param keywords
     * @param abstrac
     * @param publicationTitle
     * @param persistentLink
     * @return
     */
    public String insertPaperToCollection(String uid, String did, String keywords, String abstrac
            , String publicationTitle, String persistentLink) {
        Integer integer = new Integer(0);
        try{
            integer = paperMapper.insertPaperToCollection(uid, did, keywords
                    , abstrac, publicationTitle, persistentLink);
        } catch (Exception e) {
            integer = new Integer(0);
        }
        if (integer == null) integer = new Integer(0);

        return (integer.intValue() == 0) ? "收藏失败！(可能原因：该论文已被收藏)" : "收藏成功！";
    }

    /**
     * 更新
     * @param uid
     * @param did
     * @param keywords
     * @param abstrac
     * @param publicationTitle
     * @param persistentLink
     * @return
     */
    public String updatePaperToCollection(String uid, String did, String keywords, String abstrac
            , String publicationTitle, String persistentLink) {
        Integer integer = new Integer(0);
        try{
            integer = paperMapper.updatePaperToCollection(uid, did, keywords
                    , abstrac, publicationTitle, persistentLink);
        } catch (Exception e) {
            integer = new Integer(0);
        }
        if (integer == null) integer = new Integer(0);

        return (integer.intValue() == 0) ? "修改失败！" : "修改成功！";
    }

    /**
     * 删除
     * @param uid
     * @param did
     * @return
     */
    public String deletePaperFromCollection(String uid, String did) {
        Integer integer = new Integer(0);
        try{
            integer = paperMapper.deletePaperFromCollection(uid, did);
        } catch (Exception e) {
            integer = new Integer(0);
        }
        if (integer == null) integer = new Integer(0);

        return (integer.intValue() == 0) ? "删除失败！" : "删除成功！";
    }
}
