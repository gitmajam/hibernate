package com.hibernate.advance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.advance.entity.Course;
import com.hibernate.advance.entity.Instructor;
import com.hibernate.advance.entity.InstructorDetail;

public class EagerLazyDemo {

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

			// get the instructor detal Object
			int theId = 4;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Advance: Instructor: " + tempInstructor);

			// commit transaction
			session.getTransaction().commit();
			
			// close the session
			session.close();
			
			// get course for the instructor
			System.out.println("Advance: Courses: " + tempInstructor.getCourses());
			
			System.out.println("Advance: Done!");


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
