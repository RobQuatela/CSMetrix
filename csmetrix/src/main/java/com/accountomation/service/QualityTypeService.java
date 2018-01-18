package com.accountomation.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.accountomation.model.QualityType;
import com.accountomation.model.Score;
import com.accountomation.util.HibernateUtil;

public class QualityTypeService {

	public static void save(QualityType qt) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Score> criteria = cb.createQuery(Score.class);
		Root<Score> root = criteria.from(Score.class);
		criteria.select(root);
		Query<Score> query = session.createQuery(criteria);
		List<Score> scores = query.getResultList();
		
		for (Score score : scores)
			qt.addScore(score);
		
		session.save(qt);
		session.getTransaction().commit();
		session.close();
	}
	
	public static List<QualityType> retrieve() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<QualityType> criteria = cb.createQuery(QualityType.class);
		Root<QualityType> root = criteria.from(QualityType.class);
		criteria.select(root);
		Query<QualityType> query = session.createQuery(criteria);
		List<QualityType> qts = query.getResultList();
		//session.close();
		return qts;
	}
}
