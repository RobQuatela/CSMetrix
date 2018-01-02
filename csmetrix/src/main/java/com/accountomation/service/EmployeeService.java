package com.accountomation.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.accountomation.model.Employee;
import com.accountomation.model.Recording;
import com.accountomation.util.HibernateUtil;

public class EmployeeService {

	public static List<Employee> retrieve() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
		Root<Employee> root = criteria.from(Employee.class);
		criteria.select(root);
		Query<Employee> query = session.createQuery(criteria);
		List<Employee> employees = query.getResultList();
		
		return employees;
	}
	
	public static Employee retrieve(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaQuery<Employee> criteria = session.getCriteriaBuilder().createQuery(Employee.class);
		Root<Employee> root = criteria.from(Employee.class);
		criteria
			.select(root)
			.where(session.getCriteriaBuilder().equal(root.get("id"), id));
		Query<Employee> query = session.createQuery(criteria);
		Employee emp = query.getSingleResult();
		
		return emp;
	}
	
	public static void addRecording(Employee emp, Recording rec) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		emp.addToRecording(rec);
		session.getTransaction().commit();
	}
}
