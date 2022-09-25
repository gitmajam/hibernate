package com.hibernate.basic;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.build.AllowSysOut;

import com.hibernate.basic.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();

			// display the students
			displayStudents(theStudents);
			
			// query students: lastname='Gabriel'
			theStudents = session.createQuery("from Student s where s.lastName='Muñoz'").getResultList();
			System.out.println("\n\nStudents who have last name of Muñoz");
			displayStudents(theStudents);
			
			//students where email LIKE 'liv2code.com
			theStudents = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").getResultList();
			System.out.println("\n\nStudents who have luv2code.com in email");
			displayStudents(theStudents);
	
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");

		} finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		theStudents.stream().forEach(student -> System.out.println(student.getFirstName()));
	}

}
