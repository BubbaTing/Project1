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

public class UpdateServlet extends HttpServlet {
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
		ObjectMapper om = new ObjectMapper();
		Reimbursement user = om.readValue(request.getReader(), Reimbursement.class);
		
		System.out.println("From client:\t" + user.toString());
		
		crate.updateRequest(user.getAuthor(), user.getStatus());
		
	}
}
