package cn.zcx.pa.PaperShow.service.paper;

import cn.zcx.pa.PaperShow.pojo.Paper;

import java.util.List;

public interface PaperService
{
  /**
   * 通过id返回paper
   * @param id
   * @return
   */
  Paper getPaperById(String id);

  //模糊查询

  //精确查询

  //选择关键词展示

  /**
   * 通过用户搜索框中的输入查询paper
   * @param input
   * @return
   */
  List<Paper> queryPapersByInput(String input);

  /**
   * 通过id删除paper
   * @param id
   */
  void deletePaperById(String id);

  /**
   * 批量删除paper
   * @param ids
   */
  void deletePapers(String[] ids);

}
