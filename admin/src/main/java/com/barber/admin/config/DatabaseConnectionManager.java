package com.barber.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DatabaseConnectionManager {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${spring.datasource.driver-class-name}")
    private String dbDriverClassName;

    @Value("${spring.datasource.default-db-name:default_db}")
    private String defaultDbName;

    public DataSource createDataSource(String dbName) {
        if (dbName == null || dbName.isEmpty()) {
            dbName = defaultDbName;  // Usa o nome do banco de dados default, se nenhum for passado
        }

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        String url = dbUrl.replace("{dbName}", dbName);  // Substitui o {dbName} na URL
        dataSource.setUrl(url);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        dataSource.setDriverClassName(dbDriverClassName);
        return dataSource;
    }
}
