package com.accountomation.web;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.accountomation.model.Employee;
import com.accountomation.model.QualityType;
import com.accountomation.model.Recording;
import com.accountomation.model.RecordingQualityScore;
import com.accountomation.service.EmployeeService;
import com.accountomation.service.QualityTypeService;
import com.accountomation.service.RecordingService;

/**
 * Servlet implementation class RecordingsController
 */
public class RecordingsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordingsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Employee> employees = EmployeeService.retrieve();
		Employee employee;
		LocalDate start;
		LocalDate end;
		int recId;
		
		try {
			employee = EmployeeService.retrieve(request.getParameter("lstEmployee"));
		} catch(Exception e) {
			employee = employees.get(0);
		}
		try {
			start = LocalDate.parse(request.getParameter("dtpStart"));
		} catch(Exception e) {
			start = LocalDate.now();
		}
		try {
			end = LocalDate.parse(request.getParameter("dtpEnd"));
		} catch(Exception e) {
			end = LocalDate.now();
		}
		try {
			recId = Integer.parseInt(request.getParameter("hRecordingSearch"));
		} catch(Exception e) {
			recId = 1;
		}
		
		List<RecordingQualityScore> scores = RecordingService.retrieve(recId).getRecordingQualityScores();
		List<Recording> recordings = RecordingService.retrieve(employee, start, end);
		List<QualityType> qts = QualityTypeService.retrieve();
		
		request.setAttribute("empID", employee.getId());
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("employees", employees);
		request.setAttribute("recordings", recordings);
		request.setAttribute("qualities", qts);
		request.setAttribute("scores", scores);
		request.getRequestDispatcher("recordings.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String btnInsertRec = request.getParameter("btnInsertRec");
		String btnInsertScores = request.getParameter("btnInsertScores");
		
		if(btnInsertRec != null) {
			Employee emp = EmployeeService.retrieve(request.getParameter("hEmployee"));
			Recording rec = new Recording(Date.valueOf(LocalDate.parse(request.getParameter("dtpDate"))),
					request.getParameter("txtName"), request.getParameter("txtNotes"));

			EmployeeService.addRecording(emp, rec);
			doGet(request, response);
		}
		
		if(btnInsertScores != null) {
			String[] qs = request.getParameterValues("ddlQualityScores");
			long recId = Long.parseLong(request.getParameter("hRecording"));
			
			for(String q : qs) {
				long qid = Long.parseLong(q);
				RecordingService.scoreRecording(recId, qid);
			}
			
			doGet(request, response);
		}
	}

}
