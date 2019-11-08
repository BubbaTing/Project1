package project1.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project1.model.Reimbursement;
import project1.util.ConnectionToSQL;

public class ReimbursementDao {
	
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
	
	public ArrayList<Reimbursement> viewEmployeeRequest(int employ_id) {
		try (Connection conn = ConnectionToSQL.getConnection()){
			String cmd = "select * from ers_reimbursement where reimb_author = ?";
			PreparedStatement s = conn.prepareStatement(cmd);
			s.setInt(1, employ_id);
			ResultSet check = s.executeQuery();
			
			ArrayList<Reimbursement> list = new ArrayList<Reimbursement>();
			while(check.next()) {
				Reimbursement container = new Reimbursement();
				container.setReimb_id(check.getInt("reimb_id"));
				container.setAmount(check.getBigDecimal("reimb_amount"));
				container.setSubmit(check.getString("reimb_submitted"));
				container.setResolve(check.getString("reimb_resolved"));
				container.setDescription(check.getString("reimb_description"));
				container.setPic(check.getString("reimb_recipt"));
				container.setAuthor(check.getInt("reimb_author"));
				container.setResolver(check.getInt("reimb_reolver"));
				container.setStatus(check.getInt("reimb_status_id"));
				container.setType(check.getInt("reimb_type_id"));
				list.add(container);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Manager function
	public ArrayList<Reimbursement> viewAllRequest() {
		try (Connection conn = ConnectionToSQL.getConnection()){
			String cmd = "select * from ers_reimbursement";
			PreparedStatement s = conn.prepareStatement(cmd);
			ResultSet check = s.executeQuery();
			ArrayList<Reimbursement> list = new ArrayList<Reimbursement>();
			
			while(check.next()) {
				Reimbursement container = new Reimbursement();
				container.setReimb_id(check.getInt("reimb_id"));
				container.setAmount(check.getBigDecimal("reimb_amount"));
				container.setSubmit(check.getString("reimb_submitted"));
				container.setResolve(check.getString("reimb_resolved"));
				container.setDescription(check.getString("reimb_description"));
				container.setPic(check.getString("reimb_recipt"));
				container.setAuthor(check.getInt("reimb_author"));
				container.setResolver(check.getInt("reimb_reolver"));
				container.setStatus(check.getInt("reimb_status_id"));
				container.setType(check.getInt("reimb_type_id"));
				list.add(container);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateRequest(int author, int status) {
		try (Connection conn = ConnectionToSQL.getConnection()){
			String cmd = "update ers_reimbursement set reimb_status_id = ? where reimb_id = ?; ";
			PreparedStatement s = conn.prepareStatement(cmd);
			s.setInt(1, author);
			s.setInt(2, status);
			int check = s.executeUpdate();
			
			if(check > 0) {
				System.out.println("Update success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
