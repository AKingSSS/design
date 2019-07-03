package com.yk.design.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 *
 * @ClassName DataSourceConfig
 * @Description 扫描 Mapper 接口并容器管理
 * @Author yangkang
 * @Date 2019/7/3 15:55
 * @Version 1.0
 */
@Configuration
@MapperScan(basePackages = DataSourceConfig.DAO_PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {

    // 针对对应的dao包路径来关联数据源,这样在多数据源时有用
	public static final String DAO_PACKAGE = "com.yk.design.dao";
	public static final String SQL_MAP_LOCATION = "classpath*:/sqlmap/**/*.xml";

    @Value("${design.db.url}")
    private String url;

    @Value("${design.db.username}")
    private String user;

    @Value("${design.db.password}")
    private String password;

    @Value("${design.db.driver}")
    private String driverClass;

    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DataSourceConfig.SQL_MAP_LOCATION));
        return sessionFactory.getObject();
    }
}