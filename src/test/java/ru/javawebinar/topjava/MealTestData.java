package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_MEAL_ID = START_SEQ + 3;
    public static final int ADMIN_MEAL_ID = START_SEQ + 9;

    //VALUES ('100000', '2021-01-29 11:00:00', 'Завтрак', '400'),
    //       ('100000', '2021-01-30 15:02:11', 'Обед', '1200'),
    //       ('100000', '2021-01-30 17:11:00', 'Ужин', '900'),
    //       ('100001', '2021-06-01 09:00:10', 'Завтрак', '450'),
    //       ('100001', '2021-09-11 10:00:00', 'Завтрак', '500'),
    //       ('100001', '2021-01-22 11:02:22', 'Завтрак', '80'),
    //       ('100000', '2021-03-02 12:23:40', 'Админ Завтрак', '400'),
    //       ('100000', '2021-03-02 18:00:00', 'Админ Обед', '2100'),
    //       ('100000', '2021-04-22 23:00:01', 'Админ Ужин', '1500');


    public static final Meal USER_MEAL_1 =
            new Meal(USER_MEAL_ID, LocalDateTime.of(2021, Month.JANUARY, 29, 11, 0, 0), "Завтрак", 400);
    public static final Meal USER_MEAL_2 =
            new Meal(USER_MEAL_ID + 1, LocalDateTime.of(2021, Month.JANUARY, 30, 15, 2, 11), "Обед", 1200);
    public static final Meal USER_MEAL_3 =
            new Meal(USER_MEAL_ID + 2, LocalDateTime.of(2021, Month.JANUARY, 30, 17, 11, 0), "Ужин", 900);
    public static final Meal USER_MEAL_4 =
            new Meal(USER_MEAL_ID + 3, LocalDateTime.of(2021, Month.JUNE, 1, 9, 0, 10), "Завтрак", 450);
    public static final Meal USER_MEAL_5 =
            new Meal(USER_MEAL_ID + 4, LocalDateTime.of(2021, Month.SEPTEMBER, 11, 10, 0, 0), "Завтрак", 500);
    public static final Meal USER_MEAL_6 =
            new Meal(USER_MEAL_ID + 5, LocalDateTime.of(2021, Month.JANUARY, 22, 11, 2, 22), "Завтрак", 80);
    public static final Meal ADMIN_MEAL_7 =
            new Meal(ADMIN_MEAL_ID, LocalDateTime.of(2021, Month.MARCH, 2, 12, 23, 40), "Админ завтрак", 400);
    public static final Meal ADMIN_MEAL_8 =
            new Meal(ADMIN_MEAL_ID + 1, LocalDateTime.of(2021, Month.MARCH, 2, 12, 0, 0), "Админ обед", 2100);
    public static final Meal ADMIN_MEAL_9 =
            new Meal(ADMIN_MEAL_ID + 2, LocalDateTime.of(2021, Month.APRIL, 2, 23, 0, 1), "Админ ужин", 1500);

    public static final List<Meal> USER_MEALS = Arrays.asList(USER_MEAL_1, USER_MEAL_2, USER_MEAL_3, USER_MEAL_4, USER_MEAL_5, USER_MEAL_6);

    public static final List<Meal> ADMIN_MEALS = Arrays.asList(ADMIN_MEAL_7, ADMIN_MEAL_8, ADMIN_MEAL_9);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2022, Month.MARCH, 11, 21, 0, 0), "Новый прием пищи", 200);
    }

    public static Meal getUpdated() {
        return new Meal(USER_MEAL_ID, LocalDateTime.of(2022, Month.MARCH, 11, 21, 0, 0), "Обновленный прием пищи", 500);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}