package com.example.hikaricp;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
@Service
public class SampleService {
    private HikariPoolMXBean poolProxy;

    private HikariDataSource dataSource;

    public SampleService(HikariDataSource dataSource, HikariPoolMXBean poolProxy) {
        this.poolProxy = poolProxy;
        this.dataSource = dataSource;
    }

    public boolean join(String name) throws SQLException {
        loggingStatus();

        insertUser(name);

        loggingStatus();

        return true;
    }

    private void insertUser(String name) throws SQLException {
        String query = "INSERT INTO user(name) VALUES(?)";

        Connection conn = dataSource.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, name);

        statement.execute();

        statement.close();
        conn.close();
    }

    private void loggingStatus(){
        log.info("total: {}, active: {}, awaiting: {}, idle: {}", poolProxy.getTotalConnections(),
                poolProxy.getActiveConnections(), poolProxy.getThreadsAwaitingConnection(), poolProxy.getIdleConnections());
    }
}
