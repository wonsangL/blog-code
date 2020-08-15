package com.example.hikaricp.chapter00;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.sql.DataSource;
import java.lang.management.ManagementFactory;

@Configuration
public class HikariConfig {
    @Bean
    public HikariPoolMXBean poolProxy() throws MalformedObjectNameException {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("com.zaxxer.hikari:type=Pool (hikari)");
        return JMX.newMBeanProxy(mBeanServer, objectName, HikariPoolMXBean.class);
    }

    @Bean(name = "sampleDatasource")
    public DataSource dataSource(){
        com.zaxxer.hikari.HikariConfig config = new com.zaxxer.hikari.HikariConfig();
        config.setPoolName("hikari");
        config.setRegisterMbeans(true);
        config.setPassword("mysqlpassword");
        config.setUsername("root");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/hikari?serverTimezone=UTC");
        return new HikariDataSource(config);
    }
}
