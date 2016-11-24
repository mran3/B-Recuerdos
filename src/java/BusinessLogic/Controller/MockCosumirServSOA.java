/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import Presentation.Bean.SessionBean;


/**
 *
 * @author jsdelgadoc
 */
public class MockCosumirServSOA {

    public MockCosumirServSOA() {
    }
    
    
    public Integer random(){
        SessionBean sb = new SessionBean();
        return null;       
    }
    
    public Integer consumirSOA(int number){
        
        Integer descuento;
        
        //para restaurantes
        if (number == 3 || number == 2){
            descuento = 7;            
        } else 
            if (number == 5 || number == 4){
                descuento = 3;
        } else 
            if (number == 7 || number == 6){
                descuento = 10;
        } else descuento = 0;
        return descuento;
    }
    
    public String msm (int descuento){
    
        String message;
        
        switch (descuento) {
            case 3:
                message = "Descuento aplicado por consumir en la app Guias Turisticos";
                break;
            case 7:
                message = "Descuento aplicado por consumir en la app Restaurantes";
                break;
            case 10:
                message = "Descuento aplicado por consumir en la app Restaurantes y Guias Turisticos";
                break;
            default:
                message = "No hay bonos disponibles";
                break;
        }
        
        return message;
    }    

    private static int secureESBOperation(int document) {
        BusinessLogic.Service.Wsdl.SecureESBService service = new BusinessLogic.Service.Wsdl.SecureESBService();
        BusinessLogic.Service.Wsdl.SecureESBPortType port = service.getSecureESBPort();
        return port.secureESBOperation(document);
    }
}