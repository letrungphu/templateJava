package com.template.template.config;

import com.template.template.auth.mapper.AuthMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@Component
@MapperScan(
        basePackages = {"com.template.template.auth.mapper", "com.template.template.api.oracle.mapper"} , sqlSessionTemplateRef = "sqlSessionTemplate1"
)
public class ConnectOracleDB {
    @Bean(name = "dataSource1")
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSource dataSource1() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
//        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");
//        dataSource.setUsername("system");
//        dataSource.setPassword("trungphu109");
//        return dataSource;

        return DataSourceBuilder.create()
//                .driverClassName("oracle.jdbc.OracleDriver")
//                .url("jdbc:oracle:thin:@localhost:1521:ORCL")
//                .username("system")
//                .password("trungphu109")
//                .build();
                .driverClassName("oracle.jdbc.OracleDriver")
                .url("jdbc:oracle:thin:@10.101.1.200:1552:hsvDSH")
                .username("dsh_mgr")
                .password("hsv_mgr")
                .build();
    }

    @Bean(name = "sqlSessionFactory1")
    public SqlSessionFactory sqlSessionFactory1(@Qualifier("dataSource1") final DataSource dataSource1) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource1);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:sql/**/*.xml")); // Tải tất cả mapper XML
        return factoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplate1")
    public SqlSessionTemplate sqlSessionTemplate1(@Qualifier("sqlSessionFactory1") final SqlSessionFactory sqlSessionFactory1) {
        return new SqlSessionTemplate(sqlSessionFactory1);
    }
}


