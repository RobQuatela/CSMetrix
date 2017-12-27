package com.accountomation.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.accountomation.model.Employee;
import com.accountomation.model.QualityType;
import com.accountomation.util.HibernateUtil;

/**
 * Servlet implementation class QualityTypesServlet
 */
public class QualityTypesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QualityTypesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		QualityType qt = new QualityType();
		qt.setName("Personable");
		qt.setDescription("Engaging with customer");
		int id = (int) session.save(qt);
		session.getTransaction().commit();
		QualityType qtLoad = (QualityType)session.load(QualityType.class, id);
		request.getSession().setAttribute("employee", qtLoad);
		request.getRequestDispatcher("qualitytypes.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
