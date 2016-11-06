/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Service;

import BusinessLogic.Controller.BCheck;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author JuanC Sexy
 */
@WebService(serviceName = "BCheckWS")
public class BCheckWS {

    /**
     * This is a sample web service operation
     * Este servicio web expone si el numero de documento corresponde a algún cliente que tenga No de cedula que haya comprado algúna vez en la tienda
     */
    
    @WebMethod(operationName = "bCheck")
    public Integer hello(@WebParam(name = "document") Integer document) {
        return BCheck.bCheck(document); //Retorna 1 - 0, según este registrado
    }
}

