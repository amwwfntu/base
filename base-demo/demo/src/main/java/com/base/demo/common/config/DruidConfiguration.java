package com.base.demo.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.base.demo.common.constant.PropertiesConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 数据库连接池配置
 */
@Component
@Configuration
public class DruidConfiguration {

    private Logger logger = LoggerFactory.getLogger(DruidConfiguration.class);

    @Autowired
    PropertiesConstant prop;

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", prop.getUsername());
        reg.addInitParameter("loginPassword", prop.getPassword());
        reg.addInitParameter("logSlowSql", prop.getLogSlowSql());
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

    @Bean
    public DataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(prop.getDbUrl());
        datasource.setUsername(prop.getUsername());
        datasource.setPassword(prop.getPassword());
        datasource.setDriverClassName(prop.getDriverClassName());
        datasource.setInitialSize(prop.getInitialSize());
        datasource.setMinIdle(prop.getMinIdle());
        datasource.setMaxActive(prop.getMaxActive());
        datasource.setMaxWait(prop.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(prop.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(prop.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(prop.getValidationQuery());
        datasource.setTestWhileIdle(prop.isTestWhileIdle());
        datasource.setTestOnBorrow(prop.isTestOnBorrow());
        datasource.setTestOnReturn(prop.isTestOnReturn());
        try {
            datasource.setFilters(prop.getFilters());
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        return datasource;
    }
}