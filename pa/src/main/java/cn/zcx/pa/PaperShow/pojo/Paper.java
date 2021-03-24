package cn.zcx.pa.PaperShow.pojo;

import cn.zcx.pa.PaperShow.utils.MyUUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * 论文类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paper
{
  private String pid;                //论文id

  private String title;           //论文标题

  private String abst;            //论文摘要

  private String conference;      //会议名

  private int publicationYear;    //发布年份

  private String link;            //原文链接

  private Set<Keyword> keywords;  //关键词集合


  /**
   * 构造方法(用于生成新的keyword)
   * @param title
   * @param abst
   * @param conference
   * @param publicationYear
   * @param link
   *
   */
  public Paper(String title, String abst, String conference,
               int publicationYear, String link)
  {
    this.pid = MyUUID.createUserID();
    this.title = title;
    this.abst = abst;
    this.conference = conference;
    this.publicationYear = publicationYear;
    this.link = link;

  }

  @Override
  public String toString() {
    return "Paper{" +
        "pid=" + pid +
        ", title='" + title + '\'' +
        ", abst='" + abst + '\'' +
        ", conference='" + conference + '\'' +
        ", publicationYear=" + publicationYear +
        ", link='" + link + '\'' +
        ", keywords=" + keywords +
        '}';
  }
}
