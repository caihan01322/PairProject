package com.topwordanalysis.crossDomain;

import javax.annotation.Priority;

import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
/**
 * 跨域请求过滤器
 *
 * @author 221801318_黄贸之
 * @Date 2021/3/24
 */
@Priority(Priorities.AUTHENTICATION-500)//优先级为第一级（<1000）
public class CrossDomainRequestFilter implements ContainerRequestFilter{

    /**
     * 添加允许的权限
     *
     * @param containerRequestContext 包含请求相关的内容
     */
    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        //允许跨域请求
        containerRequestContext.getHeaders().add("Access-Control-Expose-Headers","jwt, authorization");
        containerRequestContext.getHeaders().add("Access-Control-Allow-Origin",containerRequestContext.getHeaderString("origin"));
        containerRequestContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization,jwt");
        containerRequestContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        containerRequestContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD, PATCH");
        containerRequestContext.getHeaders().add("Access-Control-Max-Age","1209600");//14天
    }
}
