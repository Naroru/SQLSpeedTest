package org.example.sqltest.speedtest.querysender;

import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;

public interface QuerySender {
  <D> List<D> send(String sqlText, Map<String, ?> params, RowMapper<D> mapper);
}
