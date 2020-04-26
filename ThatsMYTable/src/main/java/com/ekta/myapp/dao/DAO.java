package com.ekta.myapp.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;


//Using this superclass to connect and make changes to the database
public class DAO {
    private static final Logger log = Logger.getAnonymousLogger(); //Anonymous logger to log to the console
    
	private static final ThreadLocal sessionThread = new ThreadLocal(); //Using ThreadLocal to insure thread safety for variables

    //Session factory from hibernate config file
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    protected DAO() {
    }

    public static Session getSession()
    {
        //Session is used to connect to the database
        Session session = (Session) DAO.sessionThread.get();
        
        if (session == null)
        {
            //There is no session to database, so we open a new one
            session = sessionFactory.openSession();
            DAO.sessionThread.set(session);
        }
        return session;
    }

    protected void begin() {
        getSession().beginTransaction();
    } //Beginning transaction

    protected void commit() {
        getSession().getTransaction().commit();
    } //Committing transaction to database

    //Rollback transaction to the initial state
    protected void rollback() {
        try {
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot rollback", e);
        }
        try {
            getSession().close();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot close", e);
        }
        DAO.sessionThread.set(null);
    }

    //Closing session
    public static void close() {
        getSession().close();
        DAO.sessionThread.set(null);
    }
}
