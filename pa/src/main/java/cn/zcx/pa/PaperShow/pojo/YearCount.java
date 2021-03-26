package cn.zcx.pa.PaperShow.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YearCount
{
  private String keyword;   //关键词

  private int year;       //年份

  private int count;      //频率
}
