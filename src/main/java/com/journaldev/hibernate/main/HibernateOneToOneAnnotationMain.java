package com.journaldev.hibernate.main;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.journaldev.hibernate.model.Customer1;
import com.journaldev.hibernate.model.Txn1;
import com.journaldev.hibernate.util.HibernateAnnotationUtil;

public class HibernateOneToOneAnnotationMain {

	public static void main(String[] args) {

		// Transaction Object Created
		Txn1 txn = new Txn1();
		txn.setDate(new Date());
		txn.setTotal(300);

		// Customer Object Created
		Customer1 cust = new Customer1();
		cust.setAddress("New Delhi, India");
		cust.setEmail("rohit@yahoo.com");
		cust.setName("Rohit Kumar");

		// Transaction Object Set To Customer Object
		cust.setTxn(txn);

		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			// Get Session
			sessionFactory = HibernateAnnotationUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			System.out.println("Session created using annotations configuration");
			// start transaction
			tx = session.beginTransaction();
			// Save the Model object
			session.save(cust);
			// Commit transaction
			tx.commit();
		} catch (Exception e) {
			System.out.println("Exception occured. " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (sessionFactory != null && !sessionFactory.isClosed()) {
				System.out.println("Closing SessionFactory");
				sessionFactory.close();
			}
		}
	}
}