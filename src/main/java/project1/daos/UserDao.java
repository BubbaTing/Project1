package project1.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project1.model.User;
import project1.util.ConnectionToSQL;

public class UserDao {
	public boolean verify(String password) {
		try (Connection conn = ConnectionToSQL.getConnection()){
			String cmd = "select count(\"ers_username\") from ers_users where ers_password = ?";
			PreparedStatement s = conn.prepareStatement(cmd);
			s.setString(1, password);
			ResultSet results = s.executeQuery();
			
			if(results.next()) {
				if(results.getInt("count") > 0) {
					return true;
				}
			}
			return false;		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public User gatherInformation(String username, String password) {
		try (Connection conn = ConnectionToSQL.getConnection()){
			String cmd = "select * from ers_users where ers_password = ? and ers_username = ?";
			PreparedStatement s = conn.prepareStatement(cmd);
			s.setString(1, password);
			s.setString(2, username);
			ResultSet results = s.executeQuery();
			
			if(results.next()) {
				User user = new User();
				user.setId(results.getInt("ers_user_id"));
				user.setUsername(username);
				user.setPassword(password);
				user.setFirstName(results.getString("user_first_name"));
				user.setLastName(results.getString("user_last_name"));
				user.setEmail(results.getString("user_email"));
				user.setRoleID(results.getInt("user_role_id"));
				return user;
			}
			return null;		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	//make a function to update the database with hash password

	public void hashedPassword(String password, String username) {

		try(Connection conn = ConnectionToSQL.getConnection()){
			
			//String cmd = "select * from ers_users where ers_password = ?";
			String cmd = "update ers_users set ers_password = ? "
					+ "where ers_username = ?";
			
			PreparedStatement s = conn.prepareStatement(cmd);
			s.setString(1, password);
			s.setString(2, username);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}














