package cn.wkj.utils;

import cn.wkj.pojo.Essay;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EssayListUtil {

    /*
      为了一次性把所有论文填充进List<Essay>，将以下两种方法整合在一起
    */

//    public static List<Essay> getEssayList_CVPR_ICCV(String fileName) {
//        File file = new File(fileName);
//        String[] paths = file.list();
//        List<Essay> essayList = new ArrayList<>();
//        for(int i = 0; i < paths.length; i++) {
//            String jsonpath = fileName + "\\" + paths[i];
//            Essay essay = JsonToBean_CVPR_ICCV.getEssay(jsonpath);
//            essayList.add(essay);
//        }
//        return essayList;
//    }
//
//    public static List<Essay> getEssayList_ECCV(String fileName) {
//        File file = new File(fileName);
//        String[] paths = file.list();
//        List<Essay> essayList = new ArrayList<>();
//        for(int i = 0; i < paths.length; i++) {
//            String jsonpath = fileName + "\\" + paths[i];
//            Essay essay = JsonToBean_ECCV.getEssay(jsonpath);
//            essayList.add(essay);
//        }
//        return essayList;
//    }

    public static List<Essay> getEssayList() {
        String[] fileName_CVPR_ICCV = {"G:\\论文数据\\CVPR（2000年至2020年，6916篇",
                "G:\\论文数据\\ICCV（2001年至2019年，3196篇）"};
        String[] fileName_ECCV = {"G:\\论文数据\\ECCV（2016至2020，3033份）",
                "G:\\论文数据\\ECCV补充"};
        List<Essay> essayList = new ArrayList<>();
        for(int i = 0; i < 2; i++) {
            String fileName = fileName_CVPR_ICCV[i];
            File file = new File(fileName);
            File[] files = file.listFiles();
            for(int j = 0; j < files.length; j++) {
                String jsonpath = files[j].getAbsolutePath();
                Essay essay = JsonToBean_CVPR_ICCV.getEssay(jsonpath);
                essayList.add(essay);
            }
        }
        for(int i = 0; i < 2; i++) {
            String fileName = fileName_ECCV[i];
            File file = new File(fileName);
            File[] files = file.listFiles();
            for(int j = 0; j < files.length; j++) {
                String jsonpath = files[j].getAbsolutePath();
                Essay essay = JsonToBean_ECCV.getEssay(jsonpath);
                essayList.add(essay);
            }
        }
        return essayList;
    }
}
