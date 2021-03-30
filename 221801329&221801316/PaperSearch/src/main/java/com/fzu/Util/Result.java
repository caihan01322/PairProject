package com.fzu.Util;

public class  Result
{
    public static final int SUCCESS_CODE=200;

    public static final int ERROR_CODE=0;

    private int code;

    private String data;

    public Result(int code, String data)
    {
        if(code==SUCCESS_CODE){
            this.code =code;
            this.data = data;
        }
        else{
            this.code=ERROR_CODE;
            this.data="业务出错";
        }

    }

}


