package beans;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@XmlRootElement
public class ItemBean {

	private int id;
	private String ItemName;
	private String ItemCode;
	private String Price;
	private String Brand;
	private String Color;
	private String Size;
	private String Meterial;
	private String Condition;
	private String Category;
	private String Type;
	private String ItemLocation;
	private String ItemDesc;
	private String username;
	private String password;
	
	
	
	public ItemBean(int id,String ItemName,String ItemCode,
			String Price,String Brand,String Color,String Size,String Meterial,
			String Condition,String Category,String Type,String ItemLocation,String ItemDesc) {
		
		this.id=id;
		this.ItemName=ItemName;
		this.ItemCode=ItemCode;
		this.Price=Price;
		this.Brand=Brand;
		this.Color=Color;
		this.Size=Size;
		this.Meterial=Meterial;
		this.Condition=Condition;
		this.Category=Category;
		this.Type=Type;
		this.ItemLocation=ItemLocation;
		this.ItemDesc =ItemDesc;
	
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public String getItemCode() {
		return ItemCode;
	}
	public void setItemCode(String itemCode) {
		ItemCode = itemCode;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	public String getSize() {
		return Size;
	}
	public void setSize(String size) {
		Size = size;
	}
	public String getMeterial() {
		return Meterial;
	}
	public void setMeterial(String meterial) {
		Meterial = meterial;
	}
	public String getCondition() {
		return Condition;
	}
	public void setCondition(String condition) {
		Condition = condition;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getItemLocation() {
		return ItemLocation;
	}
	public void setItemLocation(String itemLocation) {
		ItemLocation = itemLocation;
	}
	public String getItemDesc() {
		return ItemDesc;
	}
	public void setItemDesc(String itemDesc) {
		ItemDesc = itemDesc;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
}
