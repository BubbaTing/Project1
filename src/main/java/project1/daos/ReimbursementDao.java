package project1.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project1.model.Reimbursement;
import project1.util.ConnectionToSQL;

public class ReimbursementDao {
	Reimbursement crate = new Reimbursement();
	
	public void newRequest(Reimbursement input) {
		try (Connection conn = ConnectionToSQL.getConnection()){
			String cmd = "insert into ers_reimbursement"
					+ "(reimb_amount, reimb_submitted, reimb_description,"
					+ " reimb_recipt, reimb_author, reimb_status_id, reimb_type_id)"
					+ "	values(?, current_date, ?, ?::bytea, 7, 3, ?)";
			PreparedStatement s = conn.prepareStatement(cmd);
			s.setBigDecimal(1, input.getAmount());
			s.setString(2, input.getDescription());
			s.setString(3, input.getPic());
			//s.setInt(4, input.getAuthor());
			s.setInt(4,input.getType());
			int check = s.executeUpdate();
			
			if(check > 0) {
				System.out.println("SQL inserted");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void viewEmployeeRequest(int employ_id) {
		try (Connection conn = ConnectionToSQL.getConnection()){
			String cmd = "select * from ers_reimbursement where reimb_author = ?";
			PreparedStatement s = conn.prepareStatement(cmd);
			s.setInt(1, employ_id);

			ResultSet check = s.executeQuery();
			
			if(check.next()) {
				System.out.println("SQL inserted");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
