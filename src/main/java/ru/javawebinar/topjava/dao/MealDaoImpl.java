package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoImpl implements MealDao {
    private final Map<Integer, Meal> mealMap = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    public MealDaoImpl() {
        MealsUtil.MEALS.forEach(this::saveMeal);
    }

    @Override
    public Collection<Meal> getAllMeals() {
        return mealMap.values();
    }

    @Override
    public Meal getMealById(int id) {
        return mealMap.get(id);
    }

    @Override
    public Meal saveMeal(Meal meal) {
        if (meal.getId() == null) {
            meal.setId(idCounter.incrementAndGet());
        }
        return mealMap.put(meal.getId(), meal);
    }

    @Override
    public boolean deleteMeal(int id) {
        return mealMap.remove(id) != null;
    }
}