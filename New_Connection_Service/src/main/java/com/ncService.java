package com;

import model.nc; 

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Connection")

public class ncService 
{
	nc itemObj = new nc(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	
	public String readItems() 
	{ 
	 return itemObj.readItems(); 
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("ncID") String ncID, 
	 @FormParam("ncName") String ncName, 
	 @FormParam("ncEmail") String ncEmail, 
	 @FormParam("ncAddress") String ncAddress,
	 @FormParam("ncCity") String ncCity,
	 @FormParam("ncMobile") String ncMobile,
	 @FormParam("ncType") String ncType,
	 @FormParam("ncRegDate") String ncRegDate,
	 @FormParam("ncStatus") String ncStatus)
	{ 
	 String output = itemObj.insertItem(ncID, ncName, ncEmail, ncAddress, ncCity, ncMobile, ncType, ncRegDate, ncStatus); 
	return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String itemData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String ncID = itemObject.get("ncID").getAsString(); 
	 String ncName = itemObject.get("ncName").getAsString(); 
	 String ncEmail = itemObject.get("ncEmail").getAsString(); 
	 String ncAddress = itemObject.get("ncAddress").getAsString(); 
	 String ncCity = itemObject.get("ncCity").getAsString(); 
	 String ncMobile= itemObject.get("ncMobile").getAsString(); 
	 String ncType = itemObject.get("ncType").getAsString(); 
	 String ncRegDate = itemObject.get("ncRegDate").getAsString(); 
	 String ncStatus = itemObject.get("ncStatus").getAsString(); 
	 String output = itemObj.updateItem(ncID, ncName, ncEmail, ncAddress, ncCity, ncMobile, ncType, ncRegDate, ncStatus); 
	return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String itemData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String ncID = doc.select("ncID").text(); 
	String output = itemObj.deleteItem(ncID); 
	return output; 
	}
}
