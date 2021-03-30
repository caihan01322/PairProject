package cn.zcx.pa.PaperImport.service;

import cn.zcx.pa.PaperShow.pojo.Paper;

public interface PaperImportService
{

  /**
   * 插入paper
   * @param paper
   */
  void insertPaper(Paper paper);


  /**
   * 导入论文
   * @param ICCV_CVPR
   * @param ECCV
   */
  void importPaper(String[] ICCV_CVPR,String[] ECCV);
}
