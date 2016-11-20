/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPException;
import java.io.UnsupportedEncodingException;

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

        String dn = "cn=" + user + ",ou=Bank,dc=arqsoft,dc=unal,dc=edu,dc=co";

        try {
            lc.bind(dn, password);
            System.out.println("==== Password validated ====");
            return true;
        } catch (LDAPException ex) {
            System.out.println("==== ERROR to validate the password ====");
            return false;
        }
    }
}

