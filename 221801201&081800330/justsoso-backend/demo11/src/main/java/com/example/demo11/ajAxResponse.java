package com.example.demo11;

import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

@Data
public class ajAxResponse
{
    private boolean successeful;
    private int code;
    private String message;
    private Object data;
    private ajAxResponse(){}
    public static ajAxResponse successfullyUpdate(String msg,Object obj)
    {
        ajAxResponse ajAx = new ajAxResponse();
        ajAx.successeful = true;
        ajAx.code = 200;
        ajAx.message = msg;
        ajAx.data = obj;
        return ajAx;
    }
    public static ajAxResponse successfully(Object obj)
    {
        ajAxResponse ajAx = new ajAxResponse();
        ajAx.successeful = true;
        ajAx.code = 200;
        ajAx.message = "查询成功";
        ajAx.data = obj;
        return ajAx;
    }
    public static ajAxResponse fail401(String msg)
    {
        ajAxResponse ajAx = new ajAxResponse();
        ajAx.successeful = false;
        ajAx.code = 401;
        ajAx.message = msg;
        ajAx.data = -1;
        return ajAx;
    }
    public  static ajAxResponse fail(String msg)
    {
        ajAxResponse ajAx = new ajAxResponse();
        ajAx.successeful = false;
        ajAx.code = 400;
        ajAx.message = msg;
        ajAx.data = -1;
        return ajAx;
    }
}
