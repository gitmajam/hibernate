package com.hibernate.advance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.advance.entity.Course;
import com.hibernate.advance.entity.Instructor;
import com.hibernate.advance.entity.InstructorDetail;

public class DeleteCourse {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate_advance.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();
			
			// get a course
			int theId = 15;
			Course tempCourse = session.get(Course.class, theId);
			
			// delete course
			System.out.println("Deleting course: " + tempCourse);
			session.delete(tempCourse);	
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");

		} catch (Exception exc) {
			exc.printStackTrace();
		}

		finally {
			// handle connection leak issue
			session.close();
			factory.close();
		}
	}
}
