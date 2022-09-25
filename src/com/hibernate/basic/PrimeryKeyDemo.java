package com.hibernate.basic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.basic.entity.Student;

public class PrimeryKeyDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();

		try {
			// create 3 student objects
			System.out.println("Creating new studen object...");
			Student tempStudent1 = new Student("Gabriel", "Andres", "andres@luv2code.com");
			Student tempStudent2 = new Student("David", "Muñoz", "david@luv2code.com");
			Student tempStudent3 = new Student("Sandra", "Marquez", "sami@luv2code.com");

			// start a transaction
			session.beginTransaction();

			// save the student object
			System.out.println("Savinf the student ...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");

		} finally {
			factory.close();
		}

	}

}
