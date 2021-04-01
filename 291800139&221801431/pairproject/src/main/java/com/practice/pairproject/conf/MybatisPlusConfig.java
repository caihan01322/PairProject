package com.practice.pairproject.conf;

//import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求
        // 默认false 为false时 会返回空数据
        //page.setOverflow(true);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        //page.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        //page.setCountSqlParser(new JsqlParserCountOptimize());
    }
}
