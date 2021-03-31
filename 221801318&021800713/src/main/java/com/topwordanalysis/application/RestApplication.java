package com.topwordanalysis.application;

import com.topwordanalysis.crossDomain.CrossDomainRequestFilter;
import com.topwordanalysis.crossDomain.CrossDomainRespondFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * jersey框架注册类、自动扫描资源类，注册组件
 *
 * @author 221801318_黄贸之
 * @Date 2021/3/23
 */
@ApplicationPath("/1.0/*")//应用的虚拟目录
public class RestApplication extends ResourceConfig {
    /**
     * 构造方法
     */
    public RestApplication(){
        this.packages("cn.militaryTheoryOnlineExaminationSystem");//扫描路径
        this.register(MultiPartFeature.class);//注册文件上传相关类
        this.register(CrossDomainRequestFilter.class);//注册跨域请求过滤器
        this.register(CrossDomainRespondFilter.class);//注册跨域响应过滤器
    }
}
