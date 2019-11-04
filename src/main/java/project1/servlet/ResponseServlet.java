package project1.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import project1.model.User;

public class ResponseServlet extends HttpServlet {
	public void service(HttpServletRequest request,
			HttpServletResponse response) throws
		IOException, ServletException{
		
		//add CORS headers
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		response.setHeader("Access-Control-Allow-Headers", "content-type");
		super.service(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, JsonParseException, JsonMappingException, IOException{
		ObjectMapper om = new ObjectMapper();
		User employee = om.readValue(request.getReader(), User.class);
		System.out.println(employee.toString());
		
		om.writeValue(response.getWriter(), employee);
		
	}
	
}
