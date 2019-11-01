package project1.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import project1.model.User;
import project1.service.UserService;

public class LoginServlet extends HttpServlet {
	public void init() throws ServletException{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	UserService userService = new UserService();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws
		IOException, ServletException{

		ObjectMapper om = new ObjectMapper();
		User employee = om.readValue(request.getReader(), User.class);
		
		String password = employee.getPassword();
		
		if(userService.verify(password)) {
			System.out.println("This is working");
		} else {
			System.out.println("This is not working");
		}
	}
}
