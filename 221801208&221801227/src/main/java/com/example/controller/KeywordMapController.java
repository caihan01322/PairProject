package com.example.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.dao.HotWordFrequencyDao;
import com.example.dao.KeywordMapDao;
import com.example.model.Conference;
import com.example.model.ConferenceKwd;
import com.example.model.Word;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/KeywordMapController")
public class KeywordMapController {

    @Autowired
    private KeywordMapDao paperDao;

    @Autowired
    private HotWordFrequencyDao hotWordFrequencyDao;

    /**
     * 返回词云
     * @return
     * @throws IOException
     */
    @RequestMapping("/getWordMap")
    public JSONObject getWordMap() throws IOException {
        Random random = new Random();
        JSONObject map = new JSONObject();
        JSONObject[] result = new JSONObject[3];
        result[0] = getCvprWords();
        result[1] = getEccvWords();
        result[2] = getIccvWords();
        JSONArray[] hotWords = new JSONArray[3];
        for(int i = 0;i < 3;i++){
            hotWords[i] = result[i].getJSONArray("hotWord");
        }
        for(int i = 0;i < 3;i++){
            JSONArray jsonArray = new JSONArray();
            for(int j = 0;j < hotWords[i].size();j++){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name",hotWords[i].get(j));
                jsonObject.put("value",random.nextInt(16)+12);
                jsonArray.add(jsonObject);
            }
            if(i == 0)
                map.put("cvpr",jsonArray);
            else if(i == 1)
                map.put("eccv",jsonArray);
            else if(i == 2)
                map.put("iccv",jsonArray);
        }
        return map;
    }

    /**
     * 返回关键词图谱中的关键词对应的论文列表
     * @param request
     * @return
     */
    @RequestMapping("/keywordToPaper")
    public JSONArray keywordToPaper(@RequestBody JSONObject request){
        JSONArray result = new JSONArray();
        String keyword = request.getString("keyword");

        List<Integer> idListCvpe = paperDao.getCvprId(keyword);
        for(int i = 0;i < idListCvpe.size();i++){
            List<Conference> conferenceList = paperDao.getCvpr(idListCvpe.get(i));
            Conference conference = conferenceList.get(0);
            result.add(bulidConferenceJson(conference,"cvpr"));
        }
        List<Integer> idListEccv = paperDao.getEccvId(keyword);
        for(int i = 0;i < idListEccv.size();i++){
            List<Conference> conferenceList = paperDao.getEccv(idListEccv.get(i));
            Conference conference = conferenceList.get(0);
            result.add(bulidConferenceJson(conference,"eccv"));
        }
        List<Integer> idListIccv = paperDao.getIccvId(keyword);
        for(int i = 0;i < idListIccv.size();i++){
            List<Conference> conferenceList = paperDao.getIccv(idListIccv.get(i));
            Conference conference = conferenceList.get(0);
            result.add(bulidConferenceJson(conference,"iccv"));
        }

        return result;
    }

    public JSONObject bulidConferenceJson(Conference conference,String type){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title",conference.getTitle());
        jsonObject.put("number",conference.getNumber());
        jsonObject.put("abstract",conference.getPaperabstract());
        jsonObject.put("link",conference.getLink());
        jsonObject.put("year",conference.getYear());
        jsonObject.put("type",type);
        return jsonObject;
    }

    public JSONObject getCvprWords() throws IOException{
        Map<String, Integer> map = new HashMap<>();
        Set<Word> set = new TreeSet<>();
        List<ConferenceKwd> conferenceKwdList = hotWordFrequencyDao.getCvprKwd();
        for (int j = 0; j < conferenceKwdList.size(); j++) {
            ConferenceKwd conferenceKwd = conferenceKwdList.get(j);
            wordToHashMap(conferenceKwd.getKeyword(), map);
        }
        return frequency(set,map);
    }

    public JSONObject getEccvWords() throws IOException{
        Map<String, Integer> map = new HashMap<>();
        Set<Word> set = new TreeSet<>();
        List<ConferenceKwd> conferenceKwdList = hotWordFrequencyDao.getEccvKwd();
        for (int j = 0; j < conferenceKwdList.size(); j++) {
            ConferenceKwd conferenceKwd = conferenceKwdList.get(j);
            wordToHashMap(conferenceKwd.getKeyword(), map);
        }
        return frequency(set,map);
    }

    public JSONObject getIccvWords() throws IOException{
        Map<String, Integer> map = new HashMap<>();
        Set<Word> set = new TreeSet<>();
        List<ConferenceKwd> conferenceKwdList = hotWordFrequencyDao.getIccvKwd();
        for (int j = 0; j < conferenceKwdList.size(); j++) {
            ConferenceKwd conferenceKwd = conferenceKwdList.get(j);
            wordToHashMap(conferenceKwd.getKeyword(), map);
        }
        return frequency(set,map);
    }

    /**
     * 将单词插入hashmap
     * @param curWord
     * @param map
     */
    public void wordToHashMap(String curWord,Map<String, Integer> map){
        curWord = curWord.toLowerCase();
        Integer times = map.get(curWord);
        //times==null说明这个单词已经插入hashmap
        if(times != null)
            map.put(curWord,times+1);
        else
            map.put(curWord,1);
    }

    /**
     * 统计频率最高的30个单词的出现次数
     * @param set
     * @param map
     * @return
     * @throws IOException
     */
    public JSONObject frequency(Set<Word> set,Map<String, Integer> map) throws IOException{
        JSONObject result = new JSONObject();
        JSONArray hotWord= new JSONArray();
        JSONArray frequency = new JSONArray();
        Iterator<Map.Entry<String, Integer>> iterator1 = map.entrySet().iterator();
        while (iterator1.hasNext()) {
            Map.Entry<String, Integer> entry = iterator1.next();
            Word w = new Word(entry.getKey(),entry.getValue());
            set.add(w);
        }
        Iterator<Word> iterator2 = set.iterator();
        int i = 0;
        while (iterator2.hasNext()) {
            Word w = iterator2.next();
            hotWord.add(w.word);
            frequency.add(w.frequency);
            i++;
            if(i >= 30)
                break;
        }
        result.put("hotWord",hotWord);
        result.put("frequency",frequency);
        return result;
    }
}

