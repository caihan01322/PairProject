package com.example.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.dao.PaperOperationDao;
import com.example.model.Conference;
import com.example.model.ConferenceKwd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@RestController
@RequestMapping("/PaperOperationController")
public class PaperOperationController {

    @Autowired
    private PaperOperationDao paperOperationDao;

    String[] type = {"cvpr","eccv","iccv"};

    /**
     * 对论文题目的模糊查询
     * @param query
     */
    @RequestMapping(value = "/fuzzyQuery")
    public JSONObject fuzzyQuery(@RequestBody JSONObject query){
        String fuzzyTitle = query.getString("fuzzyTitle");
        JSONArray result = new JSONArray();
        JSONObject response = new JSONObject();
        int itemNum = 0;

        //对三个分别进行模糊查询
        for (int i = 0; i < 3; i++) {
            itemNum = queryPaper(result,i,fuzzyTitle,itemNum);
        }
        response.put("result",result);
        response.put("item_num",itemNum);

        return response;
    }

    public int queryPaper(JSONArray result,int index,String fuzzyTitle,int itemNum){
        List<Conference> conferenceList = null;
        if(index == 0){
            conferenceList = paperOperationDao.getCvpr(fuzzyTitle);
        }
        else if(index == 1){
            conferenceList = paperOperationDao.getEccv(fuzzyTitle);
        }
        else if(index == 2){
            conferenceList = paperOperationDao.getIccv(fuzzyTitle);
        }

        for (int j = 0; j < conferenceList.size(); j++) {
            Conference conference = conferenceList.get(j);
            JSONObject paperInfo = new JSONObject();
            paperInfo.put("title", conference.getTitle());
            paperInfo.put("number", conference.getNumber());
            paperInfo.put("abstract", conference.getPaperabstract());
            paperInfo.put("link", conference.getLink());
            paperInfo.put("year", conference.getYear());
            paperInfo.put("type",type[index]);

            List<ConferenceKwd> conferenceKwdList = null;

            if(index == 0){
                conferenceKwdList = paperOperationDao.getCvprKwd(conference.getNumber());
            }
            else if(index == 1){
                conferenceKwdList = paperOperationDao.getEccvKwd(conference.getNumber());
            }
            else if(index == 2){
                conferenceKwdList = paperOperationDao.getIccvKwd(conference.getNumber());
            }

            JSONArray keywordArray = new JSONArray();
            for (int k = 0; k < conferenceKwdList.size(); k++) {
                ConferenceKwd conferenceKwd = conferenceKwdList.get(k);
                keywordArray.add(conferenceKwd.getKeyword());
            }

            paperInfo.put("keyword", keywordArray);
            result.add(paperInfo);
            itemNum++;
        }
        return itemNum;
    }


    /**
     * 根据关键词查询论文
     * @param query
     * @return
     */
    @RequestMapping(value = "/keywordQuery")
    public JSONObject keywordQuery(@RequestBody JSONObject query){
        String kwd = query.getString("keyword");
        JSONArray result = new JSONArray();
        JSONObject response = new JSONObject();
        int itemNum = 0;
        for(int i = 0;i < 3;i++){
            //对关键词主表进行查询（分别为表cvprkwd、eccvkwd、iccvkwd）
            List<ConferenceKwd> conferenceKwdList = getConferenceKwdList(i,kwd);

            //遍历主表结果集
            for(int j = 0;j < conferenceKwdList.size();j++){
                ConferenceKwd conferenceKwd = conferenceKwdList.get(j);
                int number = conferenceKwd.getNumber();

                //对会议从表进行查询（分别包括表cvpr、eccv、iccv）
                List<Conference> conferenceList = null;
                if(i == 0){
                    conferenceList = paperOperationDao.getCvprBasedOnNumber(number);
                }
                else if(i == 1){
                    conferenceList = paperOperationDao.getEccvBasedOnNumber(number);
                }
                else if(i == 2){
                    conferenceList = paperOperationDao.getIccvBasedOnNumber(number);
                }

                for(int k = 0;k < conferenceList.size();k++){
                    Conference conference = conferenceList.get(k);
                    JSONObject jsonObject = getConferenceObject(conference);
                    List<ConferenceKwd> _conferenceKwdList1 = null;
                    if(i == 0){
                        _conferenceKwdList1 = paperOperationDao.getCvprKwd(conference.getNumber());
                    }
                    else if(i == 1){
                        _conferenceKwdList1 = paperOperationDao.getEccvKwd(conference.getNumber());
                    }
                    else if(i == 2){
                        _conferenceKwdList1 = paperOperationDao.getIccvKwd(conference.getNumber());
                    }

                    JSONArray jsonArray= new JSONArray();
                    for(int p = 0;p < _conferenceKwdList1.size();p++){
                        ConferenceKwd _conferenceKwd1 = _conferenceKwdList1.get(p);
                        String keyword = _conferenceKwd1.getKeyword();
                        jsonArray.add(keyword);
                    }
                    jsonObject.put("keyword",jsonArray);
                    jsonObject.put("type",type[i]);

                    result.add(jsonObject);
                    itemNum++;
                }
            }
        }

        response.put("item_num",itemNum);
        response.put("result",result);
        return response;
    }


    /**
     * 获取关键词model的列表
     * @param index
     * @param kwd
     * @return
     */
    public List<ConferenceKwd> getConferenceKwdList(int index, String kwd){
        List<ConferenceKwd> conferenceKwdList = null;
        if(index == 0){
            conferenceKwdList = paperOperationDao.getCvprKwdBasedOnKwd(kwd);
        }
        else if(index == 1){
            conferenceKwdList = paperOperationDao.getEccvKwdBasedOnKwd(kwd);
        }
        else if(index == 2){
            conferenceKwdList = paperOperationDao.getIccvKwdBasedOnKwd(kwd);
        }
        return conferenceKwdList;
    }

    /**
     * 获取论文的JsonObject
     * @param conference
     * @return
     */
    public JSONObject getConferenceObject(Conference conference){
        int number = conference.getNumber();
        String title = conference.getTitle();
        String abstractText = conference.getPaperabstract();
        String link = conference.getLink();
        String year = conference.getYear();

        //构建查询结果
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title",title);
        jsonObject.put("number",number);
        jsonObject.put("abstract",abstractText);
        jsonObject.put("link",link);
        jsonObject.put("year",year);

        return jsonObject;
    }
}
