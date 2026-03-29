package io.github.guilhermeewe.libraryapi.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfiguration {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String name;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    // @Bean
    public DataSource dataSource(){

        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setUrl(this.url);
        ds.setUsername(this.name);
        ds.setPassword(this.password);
        ds.setDriverClassName(driver);

        return ds;

    }

    @Bean
    public DataSource hikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(this.name);
        hikariConfig.setPassword(this.password);
        hikariConfig.setDriverClassName(driver);

        hikariConfig.setMaximumPoolSize(10); // Máximo de conexões liberadas
        hikariConfig.setMinimumIdle(1); // Tamanho inicial do Pool
        hikariConfig.setPoolName("libraryDbPool");
        hikariConfig.setMaxLifetime(60000);
        hikariConfig.setConnectionTimeout(10000); // se passar esse tempo ele larga a conexão
        hikariConfig.setConnectionTestQuery("select 3"); // testar se está conectando com o banco

        return new HikariDataSource(hikariConfig);
    }
}
