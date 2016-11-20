/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Service;

import DataAccess.Entity.User;
import DataAccess.DAO.ItemDAO;
import DataAccess.Entity.Item;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author niche
 */
@Path("products")

public class products {
    @Context
    UriInfo uriInfo;
    
    ItemDAO itemDAO;
    public products(){
        itemDAO = new ItemDAO();
    }
    @GET
    @Path("{prodId}")
    public Item getItem(@PathParam("prodId") int prodId){
        
        return itemDAO.consultItem(prodId).get(0);
    }
    
//    @GET
//    public ArrayList<Item> getAllItems() {
//        return itemDAO.consultItemAll();
//    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMemberList() {
        
        List<Item> list = itemDAO.consultItemAll();
        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(list) {};
        
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(entity)
                .build();
    }
    
    @OPTIONS
    @Path("{path : .*}")
    public Response options() {
        return Response.ok("")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .build();
    }
    
    @POST
    public Response createUser(Item item) {
        itemDAO.createItem(item);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(item.getId())).build();
        return Response.created(uri).build();
    }
    
    @PUT
    public Response updateItem(Item item) {
            return Response.ok(itemDAO.updateItem(item)).build();
    }
    
    @DELETE
    @Path("{itemId}")
    public Response deleteItem(@PathParam("itemId") int itemId) {
            return Response.ok(itemDAO.deleteItem(itemId)).build();
    }


    
//    @GET
//    public int getAllItems() {
//        //ItemDAO itemDAO = new ItemDAO();
//        //return itemDAO.consultItemAll();
//        
//        //return userService.getAllUsers(first, maxResult);
//        return 88;
//    }
    
}
