package com.ekta.myapp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.ekta.myapp.exception.ProjException;
import com.ekta.myapp.pojo.Person;
import com.ekta.myapp.pojo.User;

public class UserDAO extends DAO {
	
	public UserDAO() {
		
	}


	//Create user
	 public User create(String firstName, String lastName,String city,String username,String password,String roleType)
	            throws ProjException {
	      if(findUser(username))  {
	    	  System.out.println("User already exists"); //User exists in database
	    	  return null;
	    	  
	      }

	      else {

		 try {
	            begin();

	            System.out.println("inside DAO");
	            
	            User user = new User();
	            
	            user.setFirstName(firstName);
	            user.setLastName(lastName);
	            user.setCity(city);
	            user.setUsername(username);
	            user.setPassword(password);
	            user.setRoleType(roleType);
	            
	            getSession().save(user);
	            
	            commit();

	            return user;
	        }
	      
	      
	      catch (HibernateException e) {
	            rollback();
	            throw new ProjException("Error creating user: " + e.getMessage());
	        }
	      
	     
	    }

	 }

	 //Delete user
	 public void delete(User user)
	            throws ProjException {
	        try {
	            begin();
	            getSession().delete(user);
	            commit();
	        } catch (HibernateException e) {
	            rollback();
	            throw new ProjException("Cannot delete user " + user.getUsername(), e);
	        }
	}

	//Return true if user exists and false if doesn't exist
	public boolean findUser(String username){
	    	begin();
	    	Query q = getSession().createQuery("from Person where username=:username");
	    	q.setString("username", username);
	    	Person person = (Person) q.uniqueResult();

	    	if(person!=null){
	    		
	    		return true; //User exists
	    		
	    	}
	    	else{
	    		return false; //User doesn't exist
	    	}
	    	
	}

}
