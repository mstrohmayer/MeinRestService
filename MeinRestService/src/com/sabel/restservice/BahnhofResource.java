package com.sabel.restservice;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/haltestelle")
public class BahnhofResource {
	
	private BahnhofService bs = new BahnhofService(); 
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/ping")
	public String ping(){
		return "Die aktuelle Serverzeit ist: " + new Date().toString();		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/{id}")
	public Bahnhof getBahnhof(@PathParam("id") int id){
		return bs.find(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Bahnhof> getAllBahnhof(){
		return bs.receiveAll();
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/new")
	public String createBahnhof(
			@FormParam("name") String name,
			@FormParam("lon") double lon,
			@FormParam("lat") double lat){
		Bahnhof bh = new Bahnhof();
		bh.setName(name);
		bh.setLat(lat);
		bh.setLon(lon);
		bs.save(bh);		
		return "<html><head></head><body><p>Erfolgreich hinzugef�gt</p></br>" + bh.toString() + "</body></html>";
	}
	
	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@Path("/{id}")
	public String deleteBahnhof(@PathParam("id") int id){
		bs.remove(id);
		return "<html><head></head><body><p>" + id + "Erfolgreich gel�scht</p></body></html>";
	}
}
