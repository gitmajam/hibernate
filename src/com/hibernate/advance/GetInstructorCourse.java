package com.hibernate.advance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.advance.entity.Course;
import com.hibernate.advance.entity.Instructor;
import com.hibernate.advance.entity.InstructorDetail;

public class GetInstructorCourse {

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
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Instructor: " + tempInstructor);
			
			// get course for the instructor
			System.out.println("Courses: " + tempInstructor.getCourses());

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
