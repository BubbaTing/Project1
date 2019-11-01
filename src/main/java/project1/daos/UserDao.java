package project1.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project1.model.User;
import project1.util.ConnectionToSQL;

public class UserDao {
	public User verify(String password) {
		try (Connection conn = ConnectionToSQL.getConnection()){
			String cmd = "select * from ers_users where ers_password = ?";
			PreparedStatement s = conn.prepareStatement(cmd);
			s.setString(1, password);
			ResultSet results = s.executeQuery();
			User person = new User();
			
			if(results.next()) {
				person.setFirstName(results.getString("user_firstname"));
				person.setLastName(results.getString("user_lastname"));
				person.setRoleID(results.getInt("user_role_id"));
			}
			return person;	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
