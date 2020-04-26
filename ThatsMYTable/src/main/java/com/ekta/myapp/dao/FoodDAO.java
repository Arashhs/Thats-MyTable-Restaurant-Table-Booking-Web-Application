package com.ekta.myapp.dao;

import com.ekta.myapp.exception.ProjException;
import com.ekta.myapp.pojo.Food;
import com.ekta.myapp.pojo.Restaurant;
import com.ekta.myapp.pojo.RestaurantTable;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;


public class FoodDAO extends DAO {

    public FoodDAO() {
    }

    //Create a new food record in database
    public Food create(String foodName, float foodPrice, int availability, Restaurant restaurant)
            throws ProjException {
        try {
            begin();

            System.out.println("inside FoodDAO");

            Food food = new Food();

            food.setFoodName(foodName);
            food.setFoodPrice(foodPrice);
            food.setAvailablity(availability);
            food.setRestaurant(restaurant);


            getSession().save(food);
            commit();
            return food;

        } catch (HibernateException e) {
            rollback();
            throw new ProjException("Error creating food: " + e.getMessage());
        }
    }

    //Fetch all foods available in a restaurant in a List
    public List<Food> fetchRestaurantMenu(int restID)
    {
        try {
            begin();
            Query q = getSession().createQuery("from Food where restaurant.restID=:restID");

            q.setParameter("restID",restID);
            List<Food> foodList = (List<Food>)q.list();

            commit();
            return foodList;

        } catch (HibernateException e) {
            rollback();
            // throw new ProjException("Could not find match " + e.getMessage());
            return null;

        }
    }

    public Food fetchMyRestaurantFood(String foodName)
    {
        try {
            begin();
            Query q = getSession().createQuery("from Food where foodName=:foodName");

            q.setParameter("foodName",foodName);
            Food food =(Food) q.uniqueResult();

            commit();
            return food;

        } catch (HibernateException e) {
            rollback();
            // throw new ProjException("Could not find match " + e.getMessage());
            return null;

        }

    }


}
