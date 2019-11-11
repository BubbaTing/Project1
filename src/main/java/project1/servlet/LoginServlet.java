package project1.servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import project1.daos.UserDao;
import project1.model.Reimbursement;
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
		
		//hashing password
		String password = generateHash( employee.getPassword());
		//System.out.println(password);
		userService.hashedPassword(password, employee.getUsername());
		String username = employee.getUsername();
		
		
		
		if(userService.verify(password)) {
			//System.out.println("This is working");
			employee = userService.gatherInformation(username, password);
			//System.out.println(employee.toString());
			om.writeValue(response.getWriter(), employee);
		} else {
			System.out.println("This is not working");
			System.out.println(employee.toString());
			om.writeValue(response.getWriter(), employee);
		}
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws
		IOException, ServletException{
		System.out.println("This is the get method in the LoginServlet");
		ArrayList<User> employee = new ArrayList<User>();
		UserDao userService = new UserDao();
		employee = userService.getFristName();
		
		ObjectMapper om = new ObjectMapper();
		om.writeValue(response.getWriter(), employee);
	}

	private String generateHash(String password) {
		
		 StringBuilder sb = new StringBuilder();
		
		try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
           
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
//            //Get complete hashed password in hex format
//            generatedPassword = sb.toString();
           
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
		
		 return sb.toString();
	}
}
