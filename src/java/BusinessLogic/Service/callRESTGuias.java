/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Service;

import javax.json.JsonArray;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author JuanC Sexy
 */
public class callRESTGuias {

    Client client = ClientBuilder.newClient();
    
    WebTarget target = client.target("http://host:8080/context/rest/method");
    JsonArray response = target.request(MediaType.APPLICATION_JSON).get(JsonArray.class);
}
  