package project1.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import project1.daos.UserDao;
import project1.model.User;

public class LoginServlet extends HttpServlet {
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
	
	UserDao userService = new UserDao();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws
		IOException, ServletException{
		
		ObjectMapper om = new ObjectMapper();
		User employee = om.readValue(request.getReader(), User.class);
		
		//System.out.println(employee.toString());
		//System.out.println("2." + employee.getPassword());
		
		String password = employee.getPassword();
		String username = employee.getUsername();
		
		if(userService.verify(password)) {
			System.out.println("This is working");
			employee = userService.gatherInformation(username, password);
			System.out.println(employee.toString());
			om.writeValue(response.getWriter(), employee);
		} else {
			System.out.println("This is not working");
			System.out.println(employee.toString());
			om.writeValue(response.getWriter(), employee);
		}
	}
}
