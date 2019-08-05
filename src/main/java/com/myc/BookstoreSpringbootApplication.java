package com.myc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class BookstoreSpringbootApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(BookstoreSpringbootApplication.class, args);
//        ConfigurableApplicationContext context = SpringApplication.run(BookstoreSpringbootApplication.class, args);
//        DataSource dataSource = context.getBean(DataSource.class);
//        Connection connection = dataSource.getConnection();
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("select * from book");
//        while (resultSet.next()) {
//            String name = resultSet.getString("name");
//            System.out.println(name);
//        }
//        connection.close();
//        statement.close();
//        resultSet.close();
    }
}
