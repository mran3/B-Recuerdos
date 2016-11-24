/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

/**
 *
 * @author ArqSoft
 */
public class LoginLdapController {

    private LDAPConnection lc = new LDAPConnection();

    public String login(String user, String password){

        if (connect()) {
            if (validatePassword(user, password)) {
                return "Your login was successful.";
            } else {
                return "Username or password is incorrect.";
            }
        } else {
            return "Conection to LDAP server failed..";
        }
    }

    public Boolean connect(){

        String ldapHost = "192.168.162.44";
        String dn = "cn=admin,dc=arqsoft,dc=unal,dc=edu,dc=co";
        String password = "sa2016ii";

        int ldapPort =  LDAPConnection.DEFAULT_PORT;
        int ldapVersion = LDAPConnection.LDAP_V3;

        try {
            lc.connect(ldapHost, ldapPort);
            System.out.println("==== Connecting to LDAP Server ====");
            lc.bind(ldapVersion, dn, password.getBytes("UTF8"));
            System.out.println("==== Authenticated in server ====");
            return true;
        } catch (LDAPException | UnsupportedEncodingException ex) {
            System.out.println("==== ERROR to connecto to the LDAP Server ====");
            return false;
        }

    }

    public Boolean validatePassword(String user, String password){

        String dn = "cn=" + user + ",ou=Recuerdos,dc=arqsoft,dc=unal,dc=edu,dc=co";

        try {
            lc.bind(dn, password);
            System.out.println("==== Password validated ====");
            return true;
        } catch (LDAPException ex) {
            System.out.println("==== ERROR to validate the password ====");
            return false;
        }
    }
     public LDAPEntry Datos(String idUser, String firstnameUser, String lastnameUser, String usernameUser, String passwordUser, String emailUser, String telephoneUser, String addressUser, String birthdateUser, String typeUseridtypeuser) {
        LDAPAttributeSet setAtr = new LDAPAttributeSet();
          setAtr.add(new LDAPAttribute("objectclass", new String("inetOrgPerson")));
          setAtr.add(new LDAPAttribute("objectclass", new String("posixAccount")));
          setAtr.add(new LDAPAttribute("objectclass", new String("top")));
          setAtr.add(new LDAPAttribute("cn", usernameUser));
          setAtr.add(new LDAPAttribute("givenname", firstnameUser));
          setAtr.add(new LDAPAttribute("sn", lastnameUser));
          setAtr.add(new LDAPAttribute("telephonenumber", telephoneUser));
          setAtr.add(new LDAPAttribute("mail", emailUser));
          setAtr.add(new LDAPAttribute("userpassword", passwordUser));
          setAtr.add(new LDAPAttribute("roomnumber", idUser));
          setAtr.add(new LDAPAttribute("employeetype", typeUseridtypeuser));
          setAtr.add(new LDAPAttribute("employeenumber", birthdateUser));
          setAtr.add(new LDAPAttribute("homepostaladdress", addressUser));
        String dn;
        if (typeUseridtypeuser.equals("1")){
            setAtr.add(new LDAPAttribute("objectclass", typeUseridtypeuser));
            dn = "cn=" + usernameUser + ",ou=Recuerdos,dc=arqsoft,dc=unal,dc=edu,dc=co";
        } else {
            setAtr.add(new LDAPAttribute("objectclass", typeUseridtypeuser));
            dn = "cn=" + usernameUser + ",ou=Recuerdos,dc=arqsoft,dc=unal,dc=edu,dc=co";
        }
        LDAPEntry newEntry = new LDAPEntry(dn, setAtr);
        return newEntry;
    }
     
        public String createUsers(String idUser, String firstnameUser, String lastnameUser, String usernameUser, String passwordUser, String emailUser, String telephoneUser, String addressUser, String birthdateUser, String typeUseridtypeuser){
        if (connect()) {
            try{
                LDAPEntry usuario = Datos(idUser, firstnameUser, lastnameUser, usernameUser, passwordUser, emailUser, telephoneUser, addressUser, birthdateUser, typeUseridtypeuser);
                lc.add(usuario);
                lc.disconnect();
                return "Usuario ingresado correctamente...";
            } catch(LDAPException ex){
                if (ex.getResultCode() == 68){
                    System.err.println("ERROR: El Usuario ya se encuentra ingresado");
                }
                Logger.getLogger(HandleUsers.class.getName()).log(Level.SEVERE,null, ex);
                return "ERROR: El usuario no se pudo agregar";
            }
        } else {
            return "Conexión al servidor LDAP fallida.";
        }
    }

}

