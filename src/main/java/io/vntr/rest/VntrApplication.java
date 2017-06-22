package io.vntr.rest;

import io.vntr.rest.config.DbProperties;
import io.vntr.rest.filter.RequestorProcessingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.filter.OrderedRequestContextFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.apache.commons.dbcp2.BasicDataSource;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class})
@EnableSwagger2
@EnableJpaRepositories(basePackages = "io.vntr.rest")
@ComponentScan({"io.vntr.rest"})
@EnableConfigurationProperties({DbProperties.class})
@EnableTransactionManagement
@EnableJpaAuditing
public class VntrApplication extends SpringBootServletInitializer {

    @Autowired
    private DbProperties dbProperties;

    @Bean(destroyMethod = "close")
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DbProperties.H2_DRIVER);
        dataSource.setUrl(dbProperties.getUrl());
        dataSource.setUsername(dbProperties.getUsername());
        dataSource.setPassword(dbProperties.getPassword());
        dataSource.setDefaultAutoCommit(false);
        dataSource.setValidationQuery("SELECT 1 FROM dual");
        dataSource.setConnectionProperties("defaultNChar=true");
        return dataSource;
    }

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(VntrApplication.class).run(args);
        System.out.println("******************************************");
        System.out.println("***** Application startup successful *****");
        System.out.println("******************************************");
    }

    @Bean
    public FilterRegistrationBean requestContextListener() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new OrderedRequestContextFilter());
        registrationBean.setOrder(Integer.MIN_VALUE +1);
        return registrationBean;
    }

    @Bean
    @Autowired
    FilterRegistrationBean apiTokenProcessingFilterRegistration(RequestorProcessingFilter requestorProcessingFilter) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(requestorProcessingFilter);
        registrationBean.setOrder(Integer.MAX_VALUE - 2);
        return registrationBean;
    }

}
