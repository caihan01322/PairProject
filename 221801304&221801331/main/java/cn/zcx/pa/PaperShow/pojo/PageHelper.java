package cn.zcx.pa.PaperShow.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * 分类工具类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageHelper
{
  private List<String> pidList;   //论文id列表

  private int totalNum;     //总条数

  private int paperNum;     //单页面页数

  private int currentPage; //当前页面

  private int totalPage;  //总页数

  /**
   * 构造方法
   * @param pidList
   * @param paperNum
   */
  public PageHelper(List<String> pidList, int paperNum)
  {
    this.pidList = pidList;
    this.paperNum = paperNum;
    this.totalNum=pidList.size();
    this.currentPage=0;
    this.totalPage=(totalNum%paperNum)==0?(totalNum/paperNum):(totalNum/paperNum+1);
  }

  /**
   * 根据索引获得列表
   * @param start
   * @param end
   * @return
   */
  public List<String> getListByIndex(int start,int end)
  {
    if(start>=totalNum || end<=0)
    {
      return new LinkedList<>();
    }
    if(end>=totalNum)
    {
      end=totalNum;
    }
    return pidList.subList(start,end);
  }

  /**
   * 获取指定页号的内容
   * @return
   */
  public List<String> getPageByNum(int pageNum)
  {
    currentPage=pageNum;
    return getListByIndex((pageNum-1)*paperNum,pageNum*paperNum);
  }


  /**
   * 获得上一页的内容
   * @return
   */
  public List<String> getPrePage()
  {
    return getListByIndex((currentPage-1)*paperNum,currentPage--*paperNum);
  }

  /**
   * 获得下一页的内容
   * @return
   */
  public List<String> getNextPage()
  {
    return getListByIndex((currentPage-1)*paperNum,currentPage++*paperNum);
  }
}
