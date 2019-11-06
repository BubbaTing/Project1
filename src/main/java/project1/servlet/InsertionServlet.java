package project1.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import project1.daos.ReimbursementDao;
import project1.model.Reimbursement;

public class InsertionServlet extends HttpServlet{
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
	
	ReimbursementDao user = new ReimbursementDao();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws
		IOException, ServletException{
		
		ObjectMapper om = new ObjectMapper();
		Reimbursement container = om.readValue(request.getReader(), Reimbursement.class);
		
		//printout the package from angular
		System.out.println(container.toString());
		container.setPic(null);
		user.newRequest(container);
	}
}
