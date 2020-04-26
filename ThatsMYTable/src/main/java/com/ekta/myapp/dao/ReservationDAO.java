package com.ekta.myapp.dao;

import com.ekta.myapp.exception.ProjException;
import com.ekta.myapp.pojo.*;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ekta.myapp.dao.DAO.getSession;

public class ReservationDAO extends DAO {

    public ReservationDAO() {
    }

    //Create a new Reservation and calculate total price
    public Reservation create(Restaurant restaurant, RestaurantTable table, List<Food> foods, User user, Date startTime, Date endTime)
            throws ProjException {
        try {
            begin();

            System.out.println("inside ReservationDAO");

            Reservation reservation = new Reservation();

            reservation.setRestaurant(restaurant);
            reservation.setTable(table);
            reservation.setFoodList(foods);
            reservation.setUser(user);
            reservation.setStartTime(startTime);
            reservation.setEndTime(endTime);

            int totalPrice = 0;
            for(Food food: foods) {
                totalPrice += food.getFoodPrice();
            }
            reservation.setTotalPrice(totalPrice);

            getSession().save(reservation);
            commit();
            return reservation;

        } catch (HibernateException e) {
            rollback();
            throw new ProjException("Error creating reservation: " + e.getMessage());
        }
    }

    //Fetch all Reservations for a specified restaurant
    public List<Reservation> fetchRestaurantReservations(int restID)
    {
        try {
            begin();
            Query q = getSession().createQuery("from Reservation where restaurant.restID=:restID");

            q.setParameter("restID",restID);
            List<Reservation> reservations = (List<Reservation>)q.list();

            commit();
            return reservations;

        } catch (HibernateException e) {
            rollback();
            // throw new ProjException("Could not find match " + e.getMessage());
            return null;
        }
    }

    public Reservation fetchMyRestaurantReservation(int reserveID)
    {
        try {
            begin();
            Query q = getSession().createQuery("from Reservation where reserveID=:reserveID");

            q.setParameter("reserveID",reserveID);
            Reservation reservation =(Reservation) q.uniqueResult();

            commit();
            return reservation;

        } catch (HibernateException e) {
            rollback();
            // throw new ProjException("Could not find match " + e.getMessage());
            return null;

        }

    }


}
