package com;

import java.util.*;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import beans.ItemBean;
import model.Item;

@Path("/Items")
@PermitAll
public class ItemService {

	Item objItem = new Item();

	// View list of Items
	//@RolesAllowed({ "item", "admin","Client" }) 
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ItemBean> viewDoctor() {
		return objItem.viewItems();
	}

	// View a item identified by id
	//@RolesAllowed({ "doctor", "admin","Client" })
	@GET
	@Path("/")
	// @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ItemBean viewItemById(@PathParam("id") int id) {
		return objItem.viewItemById(id);
	}

	// Insert a Item
	//@RolesAllowed("admin")
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDoctor(ItemBean ite) {

		String output = objItem.insertItem(ite);
		return output;

	}
	

	// Update a Item
	//@RolesAllowed({ "doctor", "admin"})
	@PUT
	@Path("/{did}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(ItemBean item,@PathParam("did") int id) {
		item.setId(id);
		String output = objItem.updateItem(item);
		return output;
	}

	// Remove a Doctor
	//@RolesAllowed({ "seller", "admin"})
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removeItem(String Item) {
		JsonObject DoctorObject = new JsonParser().parse(Item).getAsJsonObject();

		String ItemID = DoctorObject.get("id").getAsString();
		String output = objItem.removeItem(ItemID);

		return output;

	}

}
