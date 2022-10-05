package com.hibernate.advance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.advance.entity.Course;
import com.hibernate.advance.entity.Instructor;
import com.hibernate.advance.entity.InstructorDetail;

public class CreateInstructor {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate_advance.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();

		try {
			// create the objects
			Instructor tempInstructor = new Instructor("Andres", "Gomez", "andres@gmail.com");

			InstructorDetail tempInstructorDetail = new InstructorDetail("https://github.com/gitandrew?tab=repositories",
					"Andres code!!!");
			
//			Instructor tempInstructor = new Instructor("Jaime", "Muñoz", "jaime@gmail.com");
//
//			InstructorDetail tempInstructorDetail = new InstructorDetail("https://github.com/gitjamur?tab=repositories",
//					"Jaime code!!!");

			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);

			// start a transaction
			session.beginTransaction();
			
			//save the instructor
			//
			//Note: this will ALSO save the details object
			//because of CascadeType.ALL
			//
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");

		} finally {
			
			session.close();
			factory.close();
		}
	}
}
