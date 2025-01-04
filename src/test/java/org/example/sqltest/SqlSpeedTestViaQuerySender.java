package org.example.sqltest;

import org.example.sqltest.speedtest.entitites.IdentityEntity;
import org.example.sqltest.speedtest.querysender.QuerySender;
import org.example.sqltest.speedtest.repository.IdentityEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@SpringBootTest
public class SqlSpeedTestViaQuerySender {

    @Autowired
    private QuerySender querySender;

    @Autowired
    private IdentityEntityRepository repository;

    private static final int  ROW_COUNT = 5000;

    @BeforeEach
    public void init() {
        List<IdentityEntity> entities = getListIdentityEntities();
        repository.saveAll(entities);
    }

    @Test
    @DisplayName("Последовательная выборка  записей через querySender (jdbc template)")
    public void insertionNonBatchViaQuerySenderOneConnection() {

        String request = "SELECT * FROM identity_entities  WHERE  id = :id";

        double start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            querySender.send(request, Map.of("id", i), (rs, rowNum) -> this.map(rs)); //1 подключение к БД
        }
        double finish = System.currentTimeMillis();
        System.out.printf("Время работы %.3f секунд. %d строк, 1 соединение с БД \n", (finish - start) /1000, ROW_COUNT);
    }

    @Test
    @DisplayName("Выборка записей через querySender (jdbc template) 1-м запросом")
    public void insertionBatchViaQuerySenderOneConnection() {

        String request = "SELECT * FROM identity_entities  WHERE  id IN (:ids)";
        List<Long> ids = getIds();

        double start = System.currentTimeMillis();
        querySender.send(request, Map.of("ids", ids), (rs, rowNum) -> this.map(rs));
        double finish = System.currentTimeMillis();

        System.out.printf("Время работы %.3f секунд. %d строк, 1 соединение с БД \n", (finish - start) /1000, ROW_COUNT);
    }


    @Test
    @DisplayName("Выборка  записей в цикле через репозиторий")
    //@Transactional с ней будет 1 соединение с БД
    public void insertionNonBatchViaRepoMultiConnection() {

        double start = System.currentTimeMillis();
        for (int i = 0; i < ROW_COUNT; i++) {
             repository.findById((long)i); //ROW_COUNT подключений к БД
        }
        double finish = System.currentTimeMillis();
        System.out.printf("Время работы %.3f секунд. %d строк, %d соединений с БД \n", (finish - start) /1000, ROW_COUNT, ROW_COUNT-1);
    }

    @Test
    @DisplayName("Выборка записей через репозиторий 1-м запросом")
    public void insertionBatchViaRepoMultiConnection() {

        List<Long> ids = getIds();
        double start = System.currentTimeMillis();
        repository.findAllById(ids); //ROW_COUNT подключений к БД
        double finish = System.currentTimeMillis();

        System.out.printf("Время работы %.3f секунд.  %d строк, 1 соединение с БД \n", (finish - start) /1000, ROW_COUNT);
    }

    private List<IdentityEntity> getListIdentityEntities() {
        List<IdentityEntity> entities = new ArrayList<>();
        for (int i = 0; i < ROW_COUNT; i++) {
            entities.add(new IdentityEntity(i));
        }
        return entities;
    }

    private IdentityEntity map(ResultSet resultSet) throws SQLException {
            return new IdentityEntity(
                    resultSet.getLong("id"),
                    resultSet.getInt("value")
            );
    }

    private List<Long> getIds() {
        List<Long> ids = new ArrayList<>();
        for (long i = 1; i <= ROW_COUNT; i++) {
            ids.add(i);
        }
        return ids;
    }
}
