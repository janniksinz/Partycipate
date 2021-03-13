package com.partycipate.Partycipate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseController {

    @Autowired
    JdbcTemplate jdbcTemplate;

}
