package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealDao {
    Collection<Meal> getAllMeals();

    Meal getMealById(int id);

    Meal saveMeal(Meal meal);

    boolean deleteMeal(int id);
}