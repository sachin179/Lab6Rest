package model;

import java.sql.*;
import java.util.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.google.gson.JsonObject;

import beans.ItemBean;
import util.DBConnection;

public class Item {
		
	
	
	
public String insertItem(ItemBean item) {
	String output = "";
	try {
		Connection con = DBConnection.connect();
		if (con == null) {
			return "Error while connecting to the database for inserting.";
		}
// create a prepared statement

		String query = "INSERT INTO `items`"
						+"(`id`, `ItemName`, `ItemCode`, `Price`, `Brand`, `Color`, `Size`, `Meterial`, `Condition`, `Category`, `Type`, `ItemLocation`, `ItemDesc`)" 
						+"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

		
		PreparedStatement preparedStmt = con.prepareStatement(query);

// binding values
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, item.getItemName());
		preparedStmt.setString(3, item.getItemCode());
		preparedStmt.setString(4, item.getPrice());
		preparedStmt.setString(5, item.getBrand());
		preparedStmt.setString(6, item.getColor());
		preparedStmt.setString(7, item.getSize());
		preparedStmt.setString(8, item.getMeterial());
		preparedStmt.setString(9, item.getCondition());
		preparedStmt.setString(10, item.getCategory());
		preparedStmt.setString(11, item.getType());
		preparedStmt.setString(12, item.getItemLocation());
		preparedStmt.setString(13, item.getItemDesc());
		
		
	

			
// execute the statement
		preparedStmt.execute();
		
		JsonObject msg = new JsonObject(); 
		msg.addProperty("id", keyGen());
		msg.addProperty("username", item.getUsername());
		msg.addProperty("password", item.getPassword());
		msg.addProperty("role", "buyer");
		
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "admin");
	    Client client = ClientBuilder.newBuilder().register(feature).build();
		WebTarget webTarget = client.target("http://localhost:8081/AuthService/AuthService").path("users");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(msg.toString(), MediaType.APPLICATION_JSON));
		

		con.close();
		output = "Successfully Inserted a item to the System";
		System.out.println(output);
	} catch (Exception e) {
		output = "Error while inserting a Item.";
		System.err.println(e.getMessage());
	}
	return output;
}

	
	

	//view list of doctors	
	public List<ItemBean> viewItems() {
		
		return	viewItems(0);

	}
	
	//View the Doctor by ID
	public ItemBean viewItemById(int id) {
	List<ItemBean> list =viewItems(id);
		if(!list.isEmpty()) {
			return	list.get(0);
		}
		return null;
	}
	
	
	
	
	//view method
	public List<ItemBean> viewItems(int id) {
			List <ItemBean> itemList = new ArrayList<>();
			
		try 
		{
			Connection con = DBConnection.connect();
			if (con == null) {
				
				System.out.println("Error While reading from database");
				return itemList;
			}

			String query;
			
			if(id==0) {
			query = "select * from Items";
			}
			else {
				query = "select * from items where id="+id;	
			}
			Statement stmt = con.createStatement();
			ResultSet results = stmt.executeQuery(query);

			while (results.next()) {
				ItemBean it = new ItemBean(
												results.getInt("id"),
												results.getString("ItemCode"),
												results.getString("Price"),
												results.getString("Brand"),
												results.getString("Color"),
												results.getString("Size"),
												results.getString("Meterial"),
												results.getString("Condition"),
												results.getString("Category"),
												results.getString("Type"),
												results.getString("ItemLocation"),
												results.getString("ItemDes"), query
												
												
											);
				
				itemList.add(it);

			}
			con.close();

		}
		catch (Exception e) {
			System.out.println("Error While Reading");
			System.err.println(e.getMessage());
		}
		
		return itemList;
	}
	
	

	//Update Doctor Details
	public String updateItem(ItemBean ite) {
		String output = "";
		
		try 
		{
			Connection con = DBConnection.connect();
			
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			
			//Prepared Statement
			
			String query = "UPDATE `items` SET"
							+"`ItemName`=?,`ItemCode`=?,`Price`=?,"
							+"`Brand`=?,`Color`=?,`Size`=?,`Meterial`=?,"
							+"`Condition`=?,`Category`=?,`Type`=?,`ItemLocation`=?,`ItemDesc`=?,"
							+"WHERE `id`=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			
			// binding values
			
			preparedStmt.setString(1, ite.getItemName());
			preparedStmt.setString(2, ite.getItemCode());
			preparedStmt.setString(3, ite.getPrice());
			preparedStmt.setString(3, ite.getBrand());
			preparedStmt.setString(4, ite.getColor());
			preparedStmt.setString(5, ite.getSize());
			preparedStmt.setString(6, ite.getMeterial());
			preparedStmt.setString(7, ite.getCondition());
			preparedStmt.setString(8, ite.getCategory());
			preparedStmt.setString(9, ite.getType());
			preparedStmt.setString(10, ite.getItemLocation());
			preparedStmt.setString(11, ite.getItemDesc());
			
			preparedStmt.setInt(12, ite.getId());
			
			

			// Prepared Statement Execution
			preparedStmt.execute();
			con.close();
			output = "Successfully Updated "+ ite.getItemName();
			
		} 
		catch (Exception e) {
			output = "Error while updating the items.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	

	public String removeItem(String ItemID) {
		String output = "";
		
		try 
		{
			Connection con = DBConnection.connect();
		
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			
			// Prepared Statement
			String query = "delete from items where id=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);

			
			// Binding values
			preparedStmt.setInt(1, Integer.parseInt(ItemID));

			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
			
		} 
		catch (Exception e) {
			output = "Error while deleting the items.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public int keyGen() {
		List<ItemBean> list ;
		list = viewItems();
		int num = list.size()+10;
		return num;
		
	}


}
