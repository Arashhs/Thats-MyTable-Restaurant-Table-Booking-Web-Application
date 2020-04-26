package com.ekta.myapp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.ekta.myapp.exception.ProjException;
import com.ekta.myapp.pojo.Person;
import com.ekta.myapp.pojo.RestaurantAdmin;

//This is subclass which extends DAO
public class RestAdminDAO extends DAO {

	public RestAdminDAO(){
		
	}
	
	 public RestaurantAdmin create(String firstName, String lastName,String city,String username,String password,String roleType)
	            throws ProjException {
		 
		 if(findRestAdmin(username)){ //Admin already exists
			 
			 System.out.println("Username already exists");
			return null; 
		 }
		 else{

		 	//Admin doesn't exist, create a record in restAdmin table
			 
			 try {
	            begin();
	            System.out.println("inside DAO");
	            
	            
	            RestaurantAdmin restAd = new RestaurantAdmin();


	            restAd.setFirstName(firstName);
	            restAd.setLastName(lastName);
	            restAd.setCity(city);
	            restAd.setUsername(username);
	            restAd.setPassword(password);
	            restAd.setRoleType(roleType);
	            
	            getSession().save(restAd); //Add record to database
	            
	            commit();
	            return restAd;
	        } 
		 
		 
		 catch (HibernateException e) {
	            rollback();
	            throw new ProjException("Error while creating admin: " + e.getMessage());
	        }
	    }
	 }

	    public void delete(RestaurantAdmin restAd)
	            throws ProjException {
	        try {
	            begin();
	            getSession().delete(restAd);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new ProjException("Cannot delete admin " + restAd.getUsername(), e);
	        }
	    }

	    //Find restaurant admin in database and return true if exists. If admin doesn't exist return false
	    public boolean findRestAdmin(String username){
	    	begin();
	    	Query q = getSession().createQuery("from Person where username=:username");
	    	q.setString("username", username);
	    	Person person = (Person) q.uniqueResult();
	    	if(person!=null){
	    		return true;
	    	}
	    	
	    	else{
	    		return false;
	    	}
	    	
	    }
	
}
