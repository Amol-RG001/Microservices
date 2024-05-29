package com.amol.user.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DatabaseConnectionChecker {

    @Autowired
    private DataSource ds;

    @EventListener(ApplicationReadyEvent.class)
    public void checkDatabaseConnection(){
        try {
            Connection connection = ds.getConnection();
            System.out.println("Database connection is established.");
        } catch (SQLException e) {
            System.err.println("Failed to establish database connection: " + e.getMessage());
        }
    }

}
