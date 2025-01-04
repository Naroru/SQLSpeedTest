package org.example.sqltest.speedtest.querysender;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QuerySenderImpl implements QuerySender {

  private final JdbcTemplateFactory jdbcTemplateFactory;
  private final DataSource defaultDataSource;

  @Override
  public <D> List<D> send(String sqlText, Map<String, ?> params, RowMapper<D> mapper) {
    return send(new QueryContext<>(sqlText, params, mapper, defaultDataSource));
  }

  private <D> List<D> send(QueryContext<D> queryContext) {
    final NamedParameterJdbcTemplate jdbcTemplate = jdbcTemplateFactory.create(queryContext.dataSource);

    return jdbcTemplate.query(queryContext.sqlText, queryContext.params, queryContext.mapper);

  }

  private record QueryContext<D>(String sqlText, Map<String, ?> params, RowMapper<D> mapper, DataSource dataSource) {
  }
}
