package com.example.demo.config;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SqlScriptRunner implements CommandLineRunner {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void run(String... args) throws Exception {
        String sql = "CREATE TABLE\n" +
                "IF NOT EXISTS `user_entity` (\n" +
                "\t\t`id` INT UNSIGNED NOT NULL auto_increment,\n" +
                "\t\t`user_name` VARCHAR ( 100 ) NOT NULL,\n" +
                "\t\t`date_time` VARCHAR ( 50 ) NOT NULL,\n" +
                "\t\t`remark` VARCHAR ( 500 ) NOT NULL,\n" +
                "\t\t`classfy_id` VARCHAR ( 50 ) NOT NULL,\n" +
                "\tPRIMARY KEY ( `id` ) \n" +
                "\t)";
        jdbcTemplate.execute(
            sql);

    }
}
