package cn.zcx.pa.PaperShow.service.paper;

import cn.zcx.pa.PaperShow.dao.PaperDao;
import cn.zcx.pa.PaperShow.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaperServiceImpl implements PaperService
{
  @Autowired
  private PaperDao paperDao;

  @Override
  public Paper getPaperById(String id)
  {
    return paperDao.selectPaperById(id);
  }

  @Override
  public List<Paper> queryPapersByMap(Map<String, Object> params)
  {
    return paperDao.selectPapersByMap(params);
  }

  @Override
  public List<Paper> queryPapersByKeyword(String keyword)
  {
    Map<String,Object> params=new HashMap<>();
    params.put("ekeyword",keyword);
    //根据keyword精确查询
    return paperDao.selectPapersByMap(params);
  }

  @Override
  public List<Paper> queryPapersByInput(String input)
  {
    Map<String,Object> params=new HashMap<>();
    params.put("vtitle",input);
    //根据title模糊搜索
    return paperDao.selectPapersByMap(params);
  }

  @Override
  public void deletePaperById(String id)
  {
    paperDao.deleteById(id);
  }

  @Override
  public void deletePapers(String[] ids)
  {
    for (String id:ids)
    {
      paperDao.deleteById(id);
    }
  }


}
