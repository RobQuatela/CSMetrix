import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import com.accountomation.model.Employee;
import com.accountomation.model.Team;
import com.accountomation.model.TeamStart;
import com.accountomation.model.TeamStop;
import com.accountomation.util.HibernateUtil;

public class Main {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Employee emp = new Employee("2222", "Rob Q", "A", "CSR");
		Employee emp1 = new Employee("2223", "Rob 2", "A", "CSR");
		Team team = new Team();
		team.setName("Test Team");
		TeamStart start = new TeamStart();
		TeamStop stop = new TeamStop();
		start.setStart(Date.valueOf(LocalDate.now()));
		stop.setDate(Date.valueOf(LocalDate.now()));
		team.setStart(start);
		team.setStop(stop);
		emp.makeTeamLead(team);
		session.save(emp);
		session.save(emp1);
		//Employee emp1 = session.load(Employee.class, "2222");
		//team.setLead(emp1.getId());
		//session.save(team);
		//session.save(start);
		session.getTransaction().commit();
		Employee empLoad = (Employee) session.load(Employee.class, "2223");
		Employee teamMember = (Employee) session.load(Employee.class, "2222");
		List<Team> empTeams = empLoad.getTeams();
		System.out.println(empLoad.getName());
		//for(Team empTeam : empTeams)
		//	System.out.println(empTeam.getName());
		session.beginTransaction();
		empLoad.makeTeamLead(team);
		team.addMember(teamMember);
		session.getTransaction().commit();
		
		Scanner input = new Scanner(System.in);
		String hey = input.nextLine();
		session.close();
		HibernateUtil.getSessionFactory().close();
	}
}
