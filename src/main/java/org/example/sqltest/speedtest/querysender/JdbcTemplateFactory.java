package org.example.sqltest.speedtest.querysender;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

public interface JdbcTemplateFactory {
  NamedParameterJdbcTemplate create(DataSource dataSource);
}
