package com.practice.pairproject.util;


import com.baomidou.mybatisplus.plugins.Page;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * limit: 查询限制
 * records: 用来存放查询出来的(分页)数据
 * total: 返回记录的总数
 * size: 每页显示条数，默认 10
 * pages: 总页数
 *
 * current: 当前页,默认1
 * pre: 上一页
 * next: 下一页
 * showPage: 显示在前端的页数list
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MyPage<T> extends Page<T> {
    /*
    static public void setMyPage(MyPage page){
        int pages = page.getPages();
        int current = page.getCurrent();
        if(current > 1){
            page.setPre(current -1);
        }
        if(current < pages){
            page.setNext(current + 1);
        }
        List<Integer> plist = IntStream.rangeClosed(1, pages).boxed().collect(Collectors.toList());
        page.setShowPage(plist);
    }
    */

    /**
     * 设置自定义MyPage的属性值
     */
    public void setMyPage( ){
        int pages = this.getPages();
        int current = this.getCurrent();
        if(current > 1){
            this.setPre(current -1);
        }
        if(current < pages){
            this.setNext(current + 1);
        }
        //List<Integer> plist = IntStream.rangeClosed(1, pages).boxed().collect(Collectors.toList());
        //this.setShowPage(plist);
    }

    private Integer pre;
    private Integer next;
    List<Integer> showPage;

    public MyPage(int current, int size) {
        super(current, size);

    }


    public Integer getPre() {
        return pre;
    }

    public void setPre(Integer pre) {
        this.pre = pre;
    }

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public List<Integer> getShowPage() {
        return showPage;
    }

    public void setShowPage(List<Integer> showPage) {
        this.showPage = showPage;
    }
}
