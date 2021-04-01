package com.pairwork.pairwork.service;


import com.pairwork.pairwork.common.Lib;
import com.pairwork.pairwork.dao.PaperDao;
import com.pairwork.pairwork.entity.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collector;

@Service
public class PaperService {
    @Resource
    private PaperDao paperDao;

//    public Page<Paper> findPage(Integer pageNum, Integer pageSize, String toFind){
//        Sort sort = Sort.by(Sort.Direction.DESC,"paper_id");//倒序
//        PageRequest pageRequest = PageRequest.of(pageNum - 1,pageSize,sort);
//        return paperDao.findNameLike(toFind,pageRequest);
//    }

    public List<Paper> findPage(String toFind){
        return paperDao.findNameLike(toFind);
    }

    public Page<Paper> findCollection(Integer pageNum,Integer pageSize,Long user_id){//将用户收藏的所有论文以Page格式返回
        Sort sort = Sort.by(Sort.Direction.DESC,"paper_id");//倒序
        PageRequest pageRequest = PageRequest.of(pageNum - 1,pageSize,sort);
        return paperDao.findUserCollecion(user_id,pageRequest);
    }

//    public List<Paper> findCollecion(Long user_id){
//        return paperDao.findUserCollecion(user_id);
//    }

    public void delPaper(Long paper_id){
        paperDao.deleteById(paper_id);
    }


    public List<String> getKeywords(Long paper_id){//获得一篇文章的高词频词语
        Paper p = paperDao.findById(paper_id).get();
        Lib lib = new Lib(p.getSummary());
        List<String> word = Arrays.asList(lib.getWordFreK().clone());
        return word;
    }

    public List<Integer> getKeywordsV(Long paper_id){//获得一篇文章的高词频词语的频率
        Paper p = paperDao.findById(paper_id).get();
        Lib lib = new Lib(p.getSummary());
        List<Integer> fre = lib.getWordFreV() ;
        return fre;
    }

    public List<String> getKeywordsBySeg(){ //从所有文章keyword字段获得词频
        List<Paper> paperAll = paperDao.findAll();

        String sWord= "";

        for(Paper p : paperAll){//将所有的Paper中的关键词进行分析
            sWord = sWord + p.getKeyWords() + " ";
        }

        Lib lib = new Lib(sWord);
        List<String> resultSet = Arrays.asList(lib.getWordFreK().clone());

        /**
         *
         * @试了那么多的Map排序都不行!!!!气死了
         *
         * //        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
         * //        //将关键词按照频率排序
         * //        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
         * //            //降序排序
         * //            public int compare(Map.Entry<String, Integer> o1,
         * //                               Map.Entry<String, Integer> o2) {
         * //                return o2.getValue().compareTo(o1.getValue());
         * //            }
         * //
         * //        });
         *
         * //        map.entrySet().stream()
         * //                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
         * //                .forEachOrdered(x -> map.put(x.getKey(), x.getValue()));
         *
         * //        List<Map.Entry<String, Integer>> list2 = new ArrayList<>(map.entrySet());
         * //        Collections.sort(list2, (o1, o2) -> o1.getValue()-o2.getValue());
         * //        list2.forEach(entry -> {
         * //            resultSet.add(entry.getKey());
         * ////            System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue());
         * //        });
         *
         * //        for(Map.Entry<String,Integer> mapping:list){//将排序好的键值对的键存入结果set
         * //            resultSet.add(mapping.getKey());
         * //        }
         */

//        return  paperAll;
//        return sWord;
        return resultSet;
    }
}
