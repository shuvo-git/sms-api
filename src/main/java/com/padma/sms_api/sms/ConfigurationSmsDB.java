package com.padma.sms_api.sms;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "smsEntityManagerFactory",
        transactionManagerRef = "smsTransactionManager",
        basePackages = {
                "com.padma.sms_api.sms"
        }
)
public class ConfigurationSmsDB {
    @Primary
    @Bean(name = "smsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource smsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "smsEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("smsDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.padma.sms_api.sms")
                .persistenceUnit("db1")
                .build();
    }

    @Primary
    @Bean(name = "smsTransactionManager")
    public PlatformTransactionManager smsTransactionManager(
            @Qualifier("smsEntityManagerFactory") EntityManagerFactory smsEntityManagerFactory
    ) {
        return new JpaTransactionManager(smsEntityManagerFactory);
    }
}
