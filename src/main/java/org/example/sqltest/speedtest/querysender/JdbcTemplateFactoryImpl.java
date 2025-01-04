package org.example.sqltest.speedtest.querysender;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcTemplateFactoryImpl implements JdbcTemplateFactory {

  @Override
  public NamedParameterJdbcTemplate create(DataSource dataSource) {
    return new NamedParameterJdbcTemplate(dataSource);
  }
}
