package org.example.sqltest;

import org.example.sqltest.speedtest.entitites.IdentityEntity;
import org.example.sqltest.speedtest.entitites.SequenceEntity;
import org.example.sqltest.speedtest.repository.IdentityEntityRepository;
import org.example.sqltest.speedtest.repository.SequenceEntityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SqlSpeedTestUsingRepository {

    @Autowired
    private IdentityEntityRepository identityEntityRepo;

    @Autowired
    private SequenceEntityRepository sequenceEntityRepo;

    private static final int  ROW_COUNT = 5000;

    @Test
    @DisplayName("Вставка строк, не батчевая, через репозиторий, одно соединени с БД")
    public void insertionNonBatchViaRepoOneConnection() {

        List<IdentityEntity> entities = getListIdentityEntities();

        double start = System.currentTimeMillis();
        identityEntityRepo.saveAll(entities);
        double finish = System.currentTimeMillis();

        System.out.printf("Вставка %d строк, не батчевая, с установкой 1-го соединения с БД заняла %.3f секунд  \n",ROW_COUNT, (finish - start) / 1000);
    }


    @Test
    @DisplayName("Вставка строк, не батчевая, через репозиторий, множественное соединение с БД")
    public void insertionNonBatchViaRepoMultiConnection() {

        List<IdentityEntity> entities = getListIdentityEntities();

        double start = System.currentTimeMillis();
        entities.forEach(identityEntityRepo::save);
        double finish = System.currentTimeMillis();

        System.out.printf("Вставка %d строк в цикле, не батчевая,  с установкой %d соединений с БД заняла %.3f секунд  \n", ROW_COUNT, entities.size(), (finish - start) / 1000);
    }

    @Test
    @DisplayName("Вставка строк, не батчевая, через репозиторий, множественное соединение с БД")
    @Transactional
    public void insertionNonBatchViaRepoOneConnection_2() {

        List<IdentityEntity> entities = getListIdentityEntities();

        double start = System.currentTimeMillis();
        entities.forEach(identityEntityRepo::save);
        double finish = System.currentTimeMillis();

        System.out.printf("Вставка %d строк в цикле, не батчевая, с установкой 1-го соединения с БД через @Transactional  заняла %.3f секунд  \n", ROW_COUNT, (finish - start) / 1000);
    }

    @Test
    @DisplayName("Батчевая вставка строк через репозиторий")
    public void insertionBatchViaRepo() {
        //генерит (ROW_COUNT/allocation size) + 1 запросов для вычисленния айдишников
        //а также ROW_COUNT/batchSize запросов для вставки

        //для identity сущностей вычисление айдишников не происходит, поэтому там просто ROW_COUNT запросов на вставку
        List<SequenceEntity> entities = getListSequenceEntities();

        double start = System.currentTimeMillis();
        sequenceEntityRepo.saveAll(entities);
        double finish = System.currentTimeMillis();

        System.out.printf("Батчевая вставка %d строк через репозиторий заняла %.3f секунд  \n", ROW_COUNT, (finish - start) / 1000);
    }

    private List<IdentityEntity> getListIdentityEntities() {
        List<IdentityEntity> entities = new ArrayList<>();
        for (int i = 0; i < ROW_COUNT; i++) {
            entities.add(new IdentityEntity(i));
        }
        return entities;
    }

    private List<SequenceEntity> getListSequenceEntities() {
        List<SequenceEntity> entities = new ArrayList<>();
        for (int i = 0; i < ROW_COUNT; i++) {
            entities.add(new SequenceEntity(i));
        }
        return entities;
    }
}
