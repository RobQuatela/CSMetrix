package com.accountomation.service;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.accountomation.model.QualityScore;
import com.accountomation.util.HibernateUtil;

public class QualityScoreService {

	public static QualityScore retrieve(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
/*		CriteriaQuery<QualityScore> criteria = session.getCriteriaBuilder().createQuery(QualityScore.class);
		Root<QualityScore> root = criteria.from(QualityScore.class);
		criteria
			.select(root)
			.where(session.getCriteriaBuilder().equal(root.get("id"), id));
		Query<QualityScore> query = session.createQuery(criteria);*/
		QualityScore qs = session.load(QualityScore.class, id);
		session.close();
		return qs;
	}
}
