package Malmo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Model {
	private static Model malmo = null;
	private String databaseUrl;
	private String sqlQuery;
	
	private Model() 
	{
		databaseUrl = "jdbc:sqlite:data/Malmo.db";
	}
	
	public static Model GetInstance()
	{
		if (malmo == null)
		{
			malmo = new Model();
		}
		
		return malmo;
	}
	
	//// LoginPage functions
	public Object[] checkValidUser(String userId, String password)
	{
		boolean isValidUser = false;
		String userName = "";
		
		sqlQuery = "SELECT * FROM Workers where Id='" + userId + "'";
		String Profession = "";
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(databaseUrl);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		try (Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlQuery)){
			if (rs.getString("Password").equals(password))
			{
				isValidUser = true;
				Profession = rs.getString("Profession");
				userName = rs.getString("FirstName");
			}
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		try 
		{
			conn.close();
		} 
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return new Object[] {isValidUser, Profession, userName};
	}
	
	//// Inventory functions
	public Object[][] getInventory()
	{
		sqlQuery = "SELECT * FROM Inventory";
		Connection conn = null;
		List<String[]> data = new ArrayList<String[]>();
		Object[][] result;
		int count = 0;
		int i = 0;
		
		try {
			conn = DriverManager.getConnection(databaseUrl);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		try (Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlQuery)){
			while (rs.next()) 
			{
				data.add(new String[] {rs.getString("ItemId"),rs.getString("ItemName"),rs.getString("ItemAmount")});
				count++;
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		try 
		{
			conn.close();
		} 
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		result = new Object[count + 1][];
		result[i] = new Object[3];
		result[i][0] = "Id";
		result[i][1] = "Name";
		result[i][2] = "Amount";
		i++;
		for (String[] strings : data) 
		{
			result[i] = new Object[strings.length];
			for(int j = 0; j < strings.length; j++)
			{
				result[i][j] = strings[j];
			}
			i++;
		}
		
		return result;
	}
	
////Medical Report functions
	public String CheckMedicalReportForm(String id ,String petId,String text)
	{
		String result ="Valid";
		sqlQuery = "SELECT * FROM Workers where Id='"+id+"'";
		Connection conn = null;
		
		//check if text not empty
		if(text.equals("")) {
			result = "Report";
		}
		
		try {
			conn = DriverManager.getConnection(databaseUrl);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		try (Statement stmt = conn.createStatement();
				
			//Check if worker exists
			ResultSet rsUsr = stmt.executeQuery(sqlQuery)){
			if(rsUsr.next()==false) {
				result =  "User";
			}
			
			//Check if pet exists
			sqlQuery = "SELECT * FROM 'ClientPets' WHERE PetId='"+petId+"'";
			ResultSet rsPet = stmt.executeQuery(sqlQuery);
			if(rsPet.next()==false) {
				result =  "Pet";
			}
			sqlQuery = "INSERT INTO MedicalReports VALUES(?,?,?,?)";
		        try (PreparedStatement ps = conn.prepareStatement(sqlQuery);) {
		            ps.setString(1, id); // First question mark will be replaced by name variable - String;
		            ps.setString(2, petId);
		            ps.setString(3,LocalDate.now().toString());
		            ps.setString(4, text); 
		            ps.executeUpdate();
		        } catch (SQLException e) {
		            e.printStackTrace();
		            result ="DB";
		        }
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return result;		
	}
	
	////Schedule Functions
	public Object[][] searchSchedule(String vetId)
	{
		Object[][] result;
		int count = 0;
		List<String[]> data = new ArrayList<String[]>();
		
		if(isVetInDatabase(vetId))
		{
			sqlQuery = "SELECT * FROM VetSchedules WHERE VetId='" + vetId + "' AND Date >= DATE('now') AND Date < DATE('now','+7 days')";
			Connection conn = null;
			
			try {
				conn = DriverManager.getConnection(databaseUrl);
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
			
			try (Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sqlQuery)){
				while (rs.next()) 
				{
					data.add(new String[] {rs.getString("Date"),rs.getString("StartTime")});
					count++;
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
			try 
			{
				conn.close();
			} 
			catch(SQLException ex)
			{
				System.out.println(ex.getMessage());
			}
			result = new Object[count][];
			count = 0;
			for (String[] strings : data) 
			{
				result[count] = new Object[strings.length];
				for(int j = 0; j < strings.length; j++)
				{
					result[count][j] = strings[j];
				}
				count++;
			}
		}
		else
		{
			result = null;
		}
		return result;
	}
	
	public boolean isPetInDatabase(String petId)
	{
		boolean result = false;
		sqlQuery = "SELECT count(*) FROM ClientPets WHERE PetId='" + petId + "'";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(databaseUrl);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sqlQuery)){
			if(rs.getString(1).toString().equals("1"))
			{
				result = true;
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		try 
		{
			conn.close();
		} 
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return result;
	}
	
	public boolean isVetInDatabase(String vetId)
	{
		boolean result = false;
		sqlQuery = "SELECT count(*) FROM Workers WHERE Profession='Veterinarian' AND Id='" + vetId + "'";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(databaseUrl);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sqlQuery)){
			if(rs.getString(1).toString().equals("1"))
			{
				result = true;
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		try 
		{
			conn.close();
		} 
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return result;
	}
	
	public boolean createAppointment(String vetId, String petId, String date, String time)
	{
		boolean appointmentMade = true;

		sqlQuery = "INSERT INTO VetSchedules VALUES('"+vetId+"','"+petId+"','"+date+"','"+time+"');";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(databaseUrl);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		try (PreparedStatement ps = conn.prepareStatement(sqlQuery);){
				ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			appointmentMade = false;
		}
		try 
		{
			conn.close();
		} 
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return appointmentMade;
	}
	
	public Object[][] getSchedule(String vetId)
	{
		Object[][] result;
		int count = 0;
		List<String[]> data = new ArrayList<String[]>();
		
		if(isVetInDatabase(vetId))
		{
			sqlQuery = "SELECT * FROM VetSchedules WHERE VetId='" + vetId + "' AND Date >= DATE('now') AND Date < DATE('now','+7 days')";
			Connection conn = null;
			
			try {
				conn = DriverManager.getConnection(databaseUrl);
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
			
			try (Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sqlQuery)){
				while (rs.next()) 
				{
					data.add(new String[] {rs.getString("Date"),rs.getString("StartTime"),rs.getString("PetId")});
					count++;
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
			try 
			{
				conn.close();
			} 
			catch(SQLException ex)
			{
				System.out.println(ex.getMessage());
			}
			result = new Object[count][];
			count = 0;
			for (String[] strings : data) 
			{
				result[count] = new Object[strings.length];
				for(int j = 0; j < strings.length; j++)
				{
					result[count][j] = strings[j];
				}
				count++;
			}
		}
		else
		{
			result = null;
		}
		
		return result;
	}
	
	public String getPetName(String petId)
	{
		String petName = "";
		sqlQuery = "SELECT PetName FROM ClientPets WHERE PetId='" + petId + "';";
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(databaseUrl);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sqlQuery)){
			petName = rs.getString("PetName");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		try 
		{
			conn.close();
		} 
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return petName;
	}
	
	public boolean deleteAppointment(String vetId, String appointmentDate, String appointmentTime)
	{
		boolean appointmentDeleted = true;
		
		sqlQuery = "DELETE FROM VetSchedules WHERE VetId='"+vetId+"' AND Date='"+appointmentDate+"' AND StartTime='"+appointmentTime+"';";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(databaseUrl);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		try (PreparedStatement ps = conn.prepareStatement(sqlQuery);){
				ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			appointmentDeleted = false;
		}
		try 
		{
			conn.close();
		} 
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		//System.out.println(sqlQuery);
		return appointmentDeleted;
	}
	
	
////Clients Details functions
	public Object[][] getClientsDetails()
	{
		sqlQuery = "SELECT * FROM Clients";
		Connection conn = null;
		List<String[]> data = new ArrayList<String[]>();
		Object[][] result;
		int count = 0;
		int i = 0;
		
		try {
			conn = DriverManager.getConnection(databaseUrl);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		try (Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlQuery)){
			while (rs.next()) 
			{
				data.add(new String[] {rs.getString("Id"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("PhoneNumber"),rs.getString("Address")});
				count++;
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		try 
		{
			conn.close();
		} 
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		result = new Object[count + 1][];
		result[i] = new Object[5];
		result[i][0] = "Id";
		result[i][1] = "First Name";
		result[i][2] = "Last Name";
		result[i][3] = "Phone";
		result[i][4] = "Address";
		i++;
		for (String[] strings : data) 
		{
			result[i] = new Object[strings.length];
			for(int j = 0; j < strings.length; j++)
			{
				result[i][j] = strings[j];
			}
			i++;
		}
		
		return result;
	}
	
	
	
	//// Search Item functions - check if trying to search for not exist item id in inventory
	public Object[] checkValidItemId(String itemId)
	{
		boolean isValidId = false;
		
		sqlQuery = "SELECT * FROM Inventory where ItemId='" + itemId + "'";
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(databaseUrl);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		try (Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlQuery)){
			if (rs.getString("ItemId").equals(itemId))
			{
				isValidId = true;
			}
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		try 
		{
			conn.close();
		} 
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return new Object[] {isValidId};
	}
	
	
	////Item Search Results functions
	public Object[][] getItemInformation(String itemId)
	{
		sqlQuery = "SELECT * FROM 'Inventory' Where itemId='" + itemId + "'";
		Connection conn = null;
		List<String[]> data = new ArrayList<String[]>();
		Object[][] result;
		int count = 0;
		int i = 0;
		
		try {
			conn = DriverManager.getConnection(databaseUrl);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		try (Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlQuery)){
			while (rs.next()) 
			{
				data.add(new String[] {rs.getString("ItemId"),rs.getString("ItemName"),rs.getString("ItemAmount")});
				count++;
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		try 
		{
			conn.close();
		} 
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		result = new Object[count + 1][];
		result[i] = new Object[3];
		result[i][0] = "Id";
		result[i][1] = "Name";
		result[i][2] = "Amount";

		i++;
		for (String[] strings : data) 
		{
			result[i] = new Object[strings.length];
			for(int j = 0; j < strings.length; j++)
			{
				result[i][j] = strings[j];
			}
			i++;
		}
		
		return result;
	}
	
	
	//// Search client functions - check if trying to search for not exist client id
	public Object[] isClientIdExist(String clientId)
	{
		boolean isIdExist = false;
		
		sqlQuery = "SELECT * FROM Clients where id='" + clientId + "'";
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(databaseUrl);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		try (Statement stmt = conn.createStatement();
				
		ResultSet rs = stmt.executeQuery(sqlQuery))
		{
			if (rs.getString("id").equals(clientId))
			{
				isIdExist = true;
			}
			
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		try 
		{
			conn.close();
		} 
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return new Object[] {isIdExist};
	}
	
	////Client Search Results functions
	public Object[][] getClientInformation(String clientId)
	{
		sqlQuery = "SELECT * FROM 'Clients' Where id='" + clientId + "'";
		Connection conn = null;
		List<String[]> data = new ArrayList<String[]>();
		Object[][] result;
		int count = 0;
		int i = 0;
		
		try {
			conn = DriverManager.getConnection(databaseUrl);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		try (Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlQuery))
		{
			while (rs.next()) 
			{
				data.add(new String[] {rs.getString("Id"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("PhoneNumber"),rs.getString("Address")});
				count++;
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		try 
		{
			conn.close();
		} 
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		result = new Object[count + 1][];
		result[i] = new Object[5];
		result[i][0] = "Id";
		result[i][1] = "First Name";
		result[i][2] = "Last Name";
		result[i][3] = "Phone";
		result[i][4] = "Address";
		
		i++;
		for (String[] strings : data) 
		{
			result[i] = new Object[strings.length];
			for(int j = 0; j < strings.length; j++)
			{
				result[i][j] = strings[j];
			}
			i++;
		}		
		return result;
	}
	
	
	public Object[] retrieveAllClientsId()
	{
		sqlQuery = "SELECT id FROM Clients";
		Connection conn = null;
		ArrayList<String> data = new ArrayList<String>(); // to store data from DB
		Object[] result;
		int count = 0; // amount of client's id
		int i = 0;
		
		
		try
		{
			conn = DriverManager.getConnection(databaseUrl);
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		// Add all client's id to the array list 
		try (Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlQuery))
		{
			while (rs.next()) 
			{
				data.add(rs.getString("Id"));
				count++;
			}
			
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		try 
		{
			conn.close();
		} 
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		// create array of Object that will contain all client's id
		result = new Object[count];
		for (String id : data) 
		{
			result[i] = id;
			i++;
		}
		return result;
	}
	
	
	
	//Get medical report for owner
	public Object[][] getAllOwnerReports(String ownerID)
	{
		sqlQuery = "SELECT * FROM 'MedicalReports' Where Fillerid='" + ownerID + "'";
		Connection conn = null;
		List<String[]> data = new ArrayList<String[]>();
		Object[][] result;
		int count = 0;
		int i = 0;
		
		try {
			conn = DriverManager.getConnection(databaseUrl);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		try (Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlQuery))
		{
			while (rs.next()) 
			{
				data.add(new String[] {rs.getString("Fillerid"),rs.getString("PetId"),rs.getString("ReportDate"),rs.getString("ReportText")});
				count++;
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		try 
		{
			conn.close();
		} 
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		result = new Object[count + 1][];
		result[i] = new Object[4];
		result[i][0] = "Fillerid";
		result[i][1] = "PetId";
		result[i][2] = "ReportDate";
		result[i][3] = "ReportText";
		
		
		i++;
		for (String[] strings : data) 
		{
			result[i] = new Object[strings.length];
			for(int j = 0; j < strings.length; j++)
			{
				result[i][j] = strings[j];
			}
			i++;
		}		
		return result;
}

	
	
	
}
