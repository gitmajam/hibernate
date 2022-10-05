package com.hibernate.advance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.advance.entity.Course;
import com.hibernate.advance.entity.Instructor;
import com.hibernate.advance.entity.InstructorDetail;

public class CreateCourse {

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
			
			// start a transaction
			session.beginTransaction();
			
			// get the instructor from db
			int theId = 4;
			Instructor tempInstructor = session.get(Instructor.class,theId);
			
			// create some courses
			Course tempCourse1 = new Course("Air TresCubano - The Ultimate Guide");
			Course tempCourse2 = new Course("The Micro Masterclass");
			
			// add courses to instructor
			tempInstructor.add(tempCourse1);
			tempInstructor.add(tempCourse2);
			
			// save the courses
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");

		} finally {
			
			session.close();
			factory.close();
		}
	}
}
