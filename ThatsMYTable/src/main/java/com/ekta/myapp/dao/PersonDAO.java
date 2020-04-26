package com.ekta.myapp.dao;





import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.ekta.myapp.exception.ProjException;
import com.ekta.myapp.pojo.Person;
import com.ekta.myapp.pojo.User;


//Database connection for person table. Extends superclass DAO
public class PersonDAO extends DAO{
	public PersonDAO() {
    }

    //Retrieving person object from database if the person exists (username and password are match)
    public Person get(String username,String password)
            throws ProjException {
        try {
            begin(); //Beginning transaction
            Query q = getSession().createQuery("from Person where username = :username AND password = :password ");
            q.setString("username", username);
            q.setString("password", password);

            Person person = (Person) q.uniqueResult(); //Retrieving person if exists and password is right
            commit();
            return person;

            //If person doesn't exist or password is not right
        } catch (HibernateException e) {
            rollback();
            throw new ProjException("Cannot Login. Please check if username and password are correctly entered." + e.getMessage());

        }
        
       
    }

   

}
