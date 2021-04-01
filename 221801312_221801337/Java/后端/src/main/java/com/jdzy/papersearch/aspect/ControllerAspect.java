package com.jdzy.papersearch.aspect;

import com.jdzy.papersearch.tools.HttpTools;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
@Aspect
public class ControllerAspect {
    @Pointcut("execution( * com.jdzy.papersearch.controller.*.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public Map<String,Object> getData(ProceedingJoinPoint jp){
        Log log = LogFactory.getLog(ControllerAspect.class);

        Object[] args = jp.getArgs();
        HttpServletRequest httpReq = (HttpServletRequest) args[args.length-1];
        if(args[0]!=null&&args[0].getClass().getInterfaces()[0]==Map.class){
            Map<String,Object> data = (Map<String, Object>) args[0];
            data= (Map<String, Object>) data.get("data");
            args[0]=data;
        }

        Object resp = null;
        try {
            resp = jp.proceed(args);
        } catch (Throwable throwable) {
            log.error("Around失败");
            throwable.printStackTrace();
        }

        return HttpTools.buildSuccessResp(resp, httpReq.getRequestURI());
    }
}
