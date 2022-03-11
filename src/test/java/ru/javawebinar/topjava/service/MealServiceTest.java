package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal mealActual = service.get(USER_MEAL_ID, USER_ID);
        assertMatch(mealActual, USER_MEAL_1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.get(USER_MEAL_ID + 1000, USER_ID));
    }

    @Test
    public void getDontHave() {
        assertThrows(NotFoundException.class, () -> service.get(USER_MEAL_ID,ADMIN_MEAL_ID));
    }

    @Test
    public void delete() {
        service.delete(USER_MEAL_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(USER_MEAL_ID, USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(USER_MEAL_ID + 1000,USER_ID));
    }

    @Test
    public void deleteDontHave() {
        assertThrows(NotFoundException.class, () -> service.delete(USER_MEAL_ID,ADMIN_MEAL_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> mealsA = service.getBetweenInclusive(
                LocalDate.of(2021, Month.JANUARY, 29),
                LocalDate.of(2021, Month.JANUARY, 31),
                USER_ID);
        assertMatch(mealsA, Arrays.asList(USER_MEAL_3,USER_MEAL_2, USER_MEAL_1));
    }

    @Test
    public void getAll() {
        List<Meal> mealsA = service.getAll(USER_ID);
        assertMatch(mealsA, USER_MEALS);
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(USER_MEAL_ID, USER_ID), getUpdated());
    }

    @Test
    public void updateNotFound() {
        assertThrows(NotFoundException.class, () -> service.update(getUpdated(),USER_MEAL_ID + 1000));
    }

    @Test
    public void updateDontHave() {
        assertThrows(NotFoundException.class, () -> service.update(getUpdated(),ADMIN_MEAL_ID));
    }

    @Test
    public void create() {
        Meal newMeal = getNew();
        Meal created = service.create(newMeal, USER_ID);
        Integer newId = created.getId();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, USER_ID), newMeal);
    }
}