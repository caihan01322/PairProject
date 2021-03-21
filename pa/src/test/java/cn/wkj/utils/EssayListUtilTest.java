package cn.wkj.utils;

import cn.wkj.pojo.Essay;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EssayListUtilTest {
    @Test
    public void getEssayList_ECCV() {
        String fileName = "G:\\论文数据\\ECCV补充";
        File file = new File(fileName);
        File[] files = file.listFiles();
        List<Essay> essayList = new ArrayList<>();
        for(int i = 0; i < files.length; i++) {
            String jsonpath = files[i].getAbsolutePath();
            Essay essay = JsonToBean_ECCV.getEssay(jsonpath);
            essayList.add(essay);
        }
        for(int i = 0; i < 10; i++) {
            System.out.println(essayList.get(i));
        }
    }

    @Test
    public void getEssayList_CVPR() {
        String fileName = "G:\\论文数据\\CVPR（2000年至2020年，6916篇";
        File file = new File(fileName);
        File[] files = file.listFiles();
        List<Essay> essayList = new ArrayList<>();
        for(int i = 0; i < files.length; i++) {
            String jsonpath = files[i].getAbsolutePath();
            Essay essay = JsonToBean_CVPR_ICCV.getEssay(jsonpath);
            essayList.add(essay);
        }
        for(int i = 0; i < 10; i++) {
            System.out.println(essayList.get(i));
        }
    }

    @Test
    public void getEssayList() {
        EssayListUtil.getEssayList();
    }
}
