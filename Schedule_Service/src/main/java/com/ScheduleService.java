package com;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


import model.Schedule;
@Path("/Schedules")


public class ScheduleService {
	
	
	Schedule scheduleObj = new Schedule();

	 
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
		return scheduleObj.readSchedule();
	 }
	
@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertSchedule(
		
  @FormParam("sPeriod") String sPeriod,
 @FormParam("sTotHrs") String sTotHrs,
 @FormParam("sFromTime") String sFromTime,
 @FormParam("sToTime") String sToTime,
 @FormParam("sArea") String sArea,
 @FormParam("sSub") String sSub,		
 @FormParam("sProvince") String sProvince)
{
 String output = scheduleObj.insertSchedule( sPeriod,  sTotHrs,  sFromTime, sToTime,sArea,  sSub, sProvince);
return output;
}
	

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateSchedule(String sData)
{
//Convert the input string to a JSON object
 JsonObject scheduleObject = new JsonParser().parse(sData).getAsJsonObject();
//Read the values from the JSON object
 String sID = scheduleObject.get("sID").getAsString();
 String sPeriod = scheduleObject.get("sPeriod").getAsString();
 String sTotHrs = scheduleObject.get("sTotHrs").getAsString();
 String sFromTime = scheduleObject.get("sFromTime").getAsString();
 String sToTime = scheduleObject.get("sToTime").getAsString();
 String sArea = scheduleObject.get("sArea").getAsString();
 String sSub = scheduleObject.get("sSub").getAsString();
 String sProvince = scheduleObject.get("sProvince").getAsString();
 String output = scheduleObj.updateSchedule( sID, sPeriod,  sTotHrs,  sFromTime,  sToTime,sArea,  sSub,sProvince);
return output;
}



@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteItem(String sData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(sData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String sID = doc.select("sID").text();
 String output = scheduleObj.deleteSchedule(sID);
return output;
}
}
