package com.accountomation.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.accountomation.model.QualityType;
import com.accountomation.service.QualityTypeService;
import com.accountomation.util.PopulateData;

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
		PopulateData.createData();
		
		List<QualityType> qts = QualityTypeService.retrieve();
		
		request.getSession().setAttribute("qts", qts);
		request.getRequestDispatcher("qualitytypes.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("txtName");
		String desc = request.getParameter("txtDescription");
		QualityType qt = new QualityType(name, desc);
		
		QualityTypeService.save(qt);
		
		doGet(request, response);
	}

}
