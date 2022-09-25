package com.hibernate.basic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.basic.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();

		try {
			
			int studentId = 7;
			
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
			System.out.println("\nGetting student with id : " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			//delete the student passing a student object 
			System.out.println("Deleting student : " + myStudent);
			session.delete(myStudent);
			
			//delete student id = 1 with query
//			System.out.println("Deleting student ide = 1");
//			session.createQuery("delete from Student where id=1").executeUpdate();
			
			//commit the transacction
			session.getTransaction().commit();
			
			System.out.println("Done!");

		} finally {
			factory.close();
		}

	}

}
