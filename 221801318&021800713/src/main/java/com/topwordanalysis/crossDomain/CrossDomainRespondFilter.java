package com.topwordanalysis.crossDomain;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

/**
 * 跨域请求过滤器
 *
 * @author 221801318_黄贸之
 * @Date 2021/3/24
 */
public class CrossDomainRespondFilter implements ContainerResponseFilter {
    /**
     * 添加允许的权限
     *
     * @param containerRequestContext 包含请求相关的内容（不能修改）
     * @param containerResponseContext 包含响应相关的内容
     */
    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) {

        //允许跨域请求
        if("OPTIONS".equalsIgnoreCase(containerRequestContext.getMethod()))
            containerResponseContext.setStatus(HttpServletResponse.SC_OK);
        containerResponseContext.getHeaders().add("Access-Control-Expose-Headers","jwt,authorization");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Origin",containerRequestContext.getHeaderString("origin"));
        containerResponseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization,jwt");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD, PATCH");
        containerResponseContext.getHeaders().add("Access-Control-Max-Age", "1209600");//14天
    }
}
