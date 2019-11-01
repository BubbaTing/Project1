package project1.service;

import project1.daos.UserDao;
import project1.model.User;

public class UserService {
	UserDao user = new UserDao();
	
	public boolean verify(String password) {
		User company = user.verify(password);
		if(company.getPassword().compareTo(password) == 0) {
			return true;
		}
		return false;
	}
	
}
