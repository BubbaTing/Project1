package project1.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import project1.daos.ReimbursementDao;
import project1.model.Reimbursement;
import project1.model.User;

public class ResponseServlet extends HttpServlet {
	public void init() throws ServletException{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void service(HttpServletRequest request,
			HttpServletResponse response) throws
		IOException, ServletException{

		//add CORS headers
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "content-type");
		super.service(request, response);
	}

	ReimbursementDao crate = new ReimbursementDao();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, JsonParseException, JsonMappingException, IOException{
		int n = 0;
		ObjectMapper om = new ObjectMapper();
		User user = om.readValue(request.getReader(), User.class);
		System.out.println("From client:\t" + user.toString());
		ArrayList<Reimbursement> employee = new ArrayList<Reimbursement>();
		
		employee = crate.viewEmployeeRequest(user.getId());

		System.out.println("From SQL:");
		for(Reimbursement i: employee) {
			System.out.println(n + "..." + i.toString());
			n++;
		}
		om.writeValue(response.getWriter(), employee);

	}

}
