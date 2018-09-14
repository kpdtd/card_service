package com.anl.user.cucumber.perset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class BasePersetter {

    @Autowired
    protected DataSource dataSourceMain;

    JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSourceMain);
    }

}
