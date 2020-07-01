package com.example.hikaricp.chapter01;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RestController
public class SampleController {
    private HikariDataSource dataSource;

    public SampleController(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostMapping("/")
    public void join(@RequestParam String name) throws SQLException {
        String query = "INSERT INTO user(name) VALUES(?)";

        Connection conn = dataSource.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, name);

        statement.execute();

        statement.close();
        conn.close();
    }
}
