package cn.zcx.pa.PaperShow.utils;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class MyUUID
{
  public static String createUserID()
  {
    return UUID.randomUUID().toString();
  }
}
