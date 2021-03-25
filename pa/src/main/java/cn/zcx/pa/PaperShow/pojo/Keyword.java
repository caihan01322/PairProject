package cn.zcx.pa.PaperShow.pojo;

import cn.zcx.pa.PaperShow.utils.MyUUID;
import lombok.*;

import java.util.Objects;

/**
 * 关键词
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Keyword
{
  private String kid;            //关键词id

  private String pid;     //对应的论文id

  private String keyword;     //关键词

  private String conference;  //会议

  private int publicationYear;     //年份

  /**
   * 构造方法（用于生成新的关键词）
   * @param keyword
   * @param pid
   * @param conference
   */
  public Keyword(String pid,String keyword,String conference,int publicationYear)
  {
    this.kid= MyUUID.createUserID();
    this.pid=pid;
    this.keyword=keyword;
    this.conference=conference;
    this.publicationYear=publicationYear;
  }


  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Keyword keyword1 = (Keyword) o;
    return publicationYear == keyword1.publicationYear &&
        pid.equals(keyword1.pid) &&
        keyword.equals(keyword1.keyword) &&
        conference.equals(keyword1.conference);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pid, keyword, conference, publicationYear);
  }

  /**
   * toString
   * @return
   */
  @Override
  public String toString() {
    return "Keyword{" +
        "kid='" + kid + '\'' +
        ", pid='" + pid + '\'' +
        ", keyword='" + keyword + '\'' +
        ", conference='" + conference + '\'' +
        ", publicationYear=" + publicationYear +
        '}';
  }
}
