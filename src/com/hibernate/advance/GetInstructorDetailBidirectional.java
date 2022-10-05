package com.hibernate.advance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.advance.entity.Instructor;
import com.hibernate.advance.entity.InstructorDetail;

public class GetInstructorDetailBidirectional {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate_advance.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			// get the instructor detal Object
			int theId = 1;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class,
					theId);

			// print the instructor detail
			System.out.println("tempIstructorDetail: " + tempInstructorDetail);

			// print the associated instructor
			System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");

		}catch (Exception exc) {
			exc.printStackTrace();
		}
	
		finally {
			//handle connection leak issue
			session.close();
			factory.close();
		}
	}
}
