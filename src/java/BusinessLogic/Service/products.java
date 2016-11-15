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
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
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
    
    @GET
    public ArrayList<Item> getAllItems() {
        return itemDAO.consultItemAll();
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
