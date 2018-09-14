package com.anl.user.cucumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;

@ContextConfiguration("/cucumber.xml")
public class BaseSteps {

    @Autowired
    protected DataSource dataSourceMain;

    JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSourceMain);
    }
}
