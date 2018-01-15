package com.accountomation.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.accountomation.model.Employee;
import com.accountomation.model.QualityScore;
import com.accountomation.model.Recording;
import com.accountomation.util.HibernateUtil;

public class RecordingService {

	public static List<Recording> retrieve(Employee emp, LocalDate start, LocalDate end) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Recording> criteria = cb.createQuery(Recording.class);
		Root<Recording> root = criteria.from(Recording.class);
		criteria
			.select(root)
			.where(
				cb.equal(root.get("employee"), emp),
				cb.greaterThanOrEqualTo(root.get("date"), Date.valueOf(start)),
				cb.lessThanOrEqualTo(root.get("date"), Date.valueOf(end))		
				);
		Query<Recording> query = session.createQuery(criteria);
		List<Recording> recordings = query.getResultList();
		
		return recordings;
		
	}
	
	public static Recording retrieve(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaQuery<Recording> criteria = session.getCriteriaBuilder().createQuery(Recording.class);
		Root<Recording> root = criteria.from(Recording.class);
		criteria
			.select(root)
			.where(session.getCriteriaBuilder().equal(root.get("id"), id));
		Query<Recording> query = session.createQuery(criteria);
		Recording rec = query.getSingleResult();
		
		return rec;
	}
	
	public static void save(Recording rec) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(rec);
		
		session.getTransaction().commit();
	}
	
	public static void scoreRecording(long recId, long scoreId) {
		//Recording rec = retrieve(recId);
		//QualityScore qs = QualityScoreService.retrieve(scoreId);
		//rec.scoreRecording(qs);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Recording rec = session.load(Recording.class, recId);
		QualityScore qs = session.load(QualityScore.class, scoreId);
		rec.scoreRecording(qs);
		session.getTransaction().commit();
	}
}
