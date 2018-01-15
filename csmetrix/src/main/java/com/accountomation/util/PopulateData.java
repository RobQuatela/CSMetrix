package com.accountomation.util;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import org.hibernate.Session;

import com.accountomation.model.Employee;
import com.accountomation.model.QualityType;
import com.accountomation.model.Recording;
import com.accountomation.model.Score;
import com.accountomation.service.QualityTypeService;

public class PopulateData {

	public static void createData() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ArrayList<QualityType> qts = new ArrayList<>();
		ArrayList<Score> scores = new ArrayList<>();
		int i = 1;
		while(i < 6) {
			scores.add(new Score(i));
			i++;
		}
		ArrayList<Employee> employees = new ArrayList<>();
		employees.add(new Employee("2221", "Rob 1", "A", "CSR"));
		employees.add(new Employee("2222", "Rob 2", "A", "CSR"));
		employees.add(new Employee("2223", "Rob 3", "A", "CSR"));
		
		
		qts.add(new QualityType("Personable", "Engaging with customer"));
		qts.add(new QualityType("Respectful", "Lots of respect"));

		for(Score score : scores)
			session.saveOrUpdate(score);
		session.getTransaction().commit();
		session.beginTransaction();
		for(QualityType qt : qts)
			QualityTypeService.save(qt);
		
		for(Employee emp : employees) {
			emp.addToRecording(new Recording(Date.valueOf(LocalDate.now()), "Recording: " + emp.getId(), emp,
					"These are notes bro"));
			session.saveOrUpdate(emp);
		}

		session.getTransaction().commit();
	}
}
