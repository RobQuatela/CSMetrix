package com.accountomation.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.accountomation.model.QualityType;
import com.accountomation.util.HibernateUtil;

/**
 * Servlet implementation class QualityTypesController
 */
public class QualityTypesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QualityTypesController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		QualityType qt = new QualityType();
		qt.setName("Personable");
		qt.setDescription("Engaging with customer");
		int id = (int) session.save(qt);
		int id2 = (int) session.save(new QualityType("Respectful", "Lots of Respect"));
		session.getTransaction().commit();
		QualityType qtLoad = (QualityType)session.load(QualityType.class, id);
		ArrayList<QualityType> qts = new ArrayList<>();
		qts.add(qtLoad);
		qts.add((QualityType)session.load(QualityType.class, id2));
		//session.close();
		//HibernateUtil.getSessionFactory().close();
		request.getSession().setAttribute("qts", qts);
		request.getRequestDispatcher("qualitytypes.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("txtName");
		String desc = request.getParameter("txtDescription");
		Session session = HibernateUtil.getSessionFactory().openSession();
		QualityType qt = new QualityType(name, desc);
		session.beginTransaction();
		session.save(qt);
		session.getTransaction().commit();
		response.sendRedirect("qualitytypes.jsp");
	}

}
