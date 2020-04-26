package com.ekta.myapp.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.ekta.myapp.exception.ProjException;
import com.ekta.myapp.pojo.Restaurant;
import com.ekta.myapp.pojo.RestaurantAdmin;

public class RestaurantDAO extends DAO {
	
	
	public RestaurantDAO(){
		
	}
	
	public Restaurant create(String restName,String restCity,int zipCode,RestaurantAdmin restAdmin)
            throws ProjException {
        try {


            begin();
            System.out.println("inside DAO");

            Restaurant rest = new Restaurant();;

            rest.setRestName(restName);
            rest.setRestCity(restCity);
            rest.setZipCode(zipCode);
            rest.setRestAdmin(restAdmin);
            restAdmin.setRestaurant(rest);
            
            getSession().merge(restAdmin);
            
            commit();
            return rest;

        } catch (HibernateException e) {
            rollback();
            throw new ProjException("Error creating restaurant: " + e.getMessage());
        }
    }
	

    //All restaurants in a city
    public List<Restaurant> get(String restCity)
            throws ProjException {
        try {
            begin();
            Query q = getSession().createQuery("from Restaurant where restCity = :restCity  ");
            q.setString("restCity", restCity);

            //Restaurant restaurant =(Restaurant) q.uniqueResult();

           List<Restaurant> result = q.list();

           commit();

           return  result;
           
        } catch (HibernateException e) {
            rollback();
            throw new ProjException("Cannot find restaurant in this city " + e.getMessage());

        }
    }

    //Return restaurant associated to an admin
    public Restaurant getMyRestaurant(RestaurantAdmin restAdmin)
            throws ProjException {
        try {
            begin();
            Query q = getSession().createQuery("from Restaurant where restAdminID=:restAdminID  ");

            q.setParameter("restAdminID", restAdmin.getPersonID());
            Restaurant restaurant =(Restaurant) q.uniqueResult();

            commit();
            return restaurant;
           
        } catch (HibernateException e) {
            rollback();
            throw new ProjException("Cannot find match " + e.getMessage());

        }

    }

    //Fetch restaurant by name
    public Restaurant fetchMyRestaurant(String restName)
    {
    	try {
            begin();
            Query q = getSession().createQuery("from Restaurant where restName=:restName");
            q.setParameter("restName",restName);

            Restaurant restaurant =(Restaurant) q.uniqueResult();

            commit();

            return restaurant;
           
        } catch (HibernateException e) {
            rollback();
           // throw new ProjException("Cannot find match " + e.getMessage());
            return null;
            
        }
    
    }

}
