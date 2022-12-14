package com.hibernate.advance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.advance.entity.Course;
import com.hibernate.advance.entity.Instructor;
import com.hibernate.advance.entity.InstructorDetail;
import com.hibernate.basic.entity.Student;

public class DeleteAdvance {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate_advance.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();
			
			// get instructor by primary key/ id
			int theId = 2;
			Instructor tempInstructor = session.get(Instructor.class, theId);

			System.out.println("Found instructor: " + tempInstructor);

			// delete the instructors
			if (tempInstructor != null) {
				System.out.println("Deleting: " + tempInstructor);
				
				// Note: will ALSO delete associated "details" object
				//because of CascadeType.ALL
				session.delete(tempInstructor);
			}

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");

		} finally {
			factory.close();
		}
	}
}
