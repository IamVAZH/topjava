package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    private MealDao dao = new MealDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("come to list");
        String attribute = req.getParameter("action");
        if (attribute == null) {
            LOG.debug("show all meals");
            req.setAttribute("meals", MealsUtil.getUnfilteredListWithExcess(dao.getAllMeals()));
            req.getRequestDispatcher("meals.jsp").forward(req, resp);
        } else if (attribute.equals("delete")) {
            int mealId = Integer.parseInt(req.getParameter("id"));
            dao.deleteMeal(mealId);
            resp.sendRedirect("meals");
        } else if (attribute.equals("update")) {
            int mealId = Integer.parseInt(req.getParameter("id"));
            Meal meal = dao.getMealById(mealId);
            req.setAttribute("meal", meal);
            req.getRequestDispatcher("mealUpCr.jsp").forward(req, resp);
        } else if (attribute.equals("create")) {
            Meal meal = new Meal(LocalDateTime.now(), "", 200);
            dao.saveMeal(meal);
            req.setAttribute("meal", meal);
            req.getRequestDispatcher("mealUpCr.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = req.getParameter("id").isEmpty() ? null : Integer.valueOf(req.getParameter("id"));
        LocalDateTime ldt = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        Meal meal = new Meal(id, ldt, description, calories);

        dao.saveMeal(meal);
        resp.sendRedirect("meals");
    }
}