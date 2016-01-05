package com.journaldev.hibernate.main;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.journaldev.hibernate.model.Customer;
import com.journaldev.hibernate.model.Txn;
import com.journaldev.hibernate.util.HibernateUtil;

public class HibernateOneToOneMain {

	public static void main(String[] args) {

		Txn txn = new Txn();
		txn.setDate(new Date());
		txn.setTotal(100);

		Customer cust = new Customer();
		cust.setAddress("Bangalore, India");
		cust.setEmail("pankaj@gmail.com");
		cust.setName("Pankaj Kumar");

		cust.setTxn(txn);

		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			// Get Session
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.getCurrentSession();
			System.out.println("Session created");
			// start transaction
			tx = session.beginTransaction();
			// Save the Model object
			session.save(cust);
			// Commit transaction
			tx.commit();
			System.out.println("Transaction ID=" + txn.getId());

		} catch (Exception e) {
			System.out.println("Exception occured. " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (!sessionFactory.isClosed()) {
				System.out.println("Closing SessionFactory");
				sessionFactory.close();
			}
		}
	}

}
