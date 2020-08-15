package com.example.hikaricp.chapter00;

import com.zaxxer.hikari.HikariPoolMXBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
@RestController("chapter02")
@RequestMapping("/chapter02")
public class SampleController {
    @Qualifier("sampleDatasource")
    private DataSource dataSource;

    private HikariPoolMXBean poolMXBean;

    public SampleController(DataSource dataSource, HikariPoolMXBean poolMXBean) {
        this.dataSource = dataSource;
        this.poolMXBean = poolMXBean;
    }

    @PostMapping("/join")
    public void join(@RequestParam String name) throws SQLException {
        String query = "INSERT INTO user(name) VALUES(?)";

        Connection conn = dataSource.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, name);

        logHikariStatus();

        statement.execute();

        statement.close();
        conn.close();

        logHikariStatus();
    }

    private void logHikariStatus(){
        log.info("connections info total: {}, active: {}, idle: {}, await: {}", poolMXBean.getTotalConnections(),
                poolMXBean.getActiveConnections(), poolMXBean.getIdleConnections(), poolMXBean.getThreadsAwaitingConnection());
    }
}
