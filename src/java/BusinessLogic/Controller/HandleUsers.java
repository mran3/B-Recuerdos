package BusinessLogic.Controller;

import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;
import com.novell.ldap.LDAPSearchResults;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Grupo 6 - Transportes de Carga
 */

public class HandleUsers {

    public static final String USER_SESSION_KEY = "user";
    private LDAPConnection lc = new LDAPConnection();
    private String sessionOK;

    public String getSessionOK() {
        return sessionOK;
    }

    public void setSessionOK(String sessionOK) {
        this.sessionOK = sessionOK;
    }

    public Boolean connect(){
        String ldapHost = "192.168.0.20";
        String dn = "cn=admin,dc=arqsoft,dc=unal,dc=edu,dc=co";
        String password = "1234";
        int ldapPort =  LDAPConnection.DEFAULT_PORT;
        int ldapVersion = LDAPConnection.LDAP_V3;
        try {
            lc.connect(ldapHost, ldapPort);
            System.out.println("==== Conectado al servidor LDAP ====");
            lc.bind(ldapVersion, dn, password.getBytes("UTF8"));
            System.out.println("==== Autenticado en el servidor ====");
            return true;
        } catch (LDAPException | UnsupportedEncodingException ex) {
            System.out.println("==== ERROR al conectarse al servidor LDAP ====");
            return false;
        }
    }
    
    public String validateUser(String usernameUser, String passwordUser) {   
        FacesContext context = FacesContext.getCurrentInstance();
        if (connect()) {
            if (validatePassword(usernameUser, passwordUser)) {
		System.out.println("Inicio de sesión satisfactorio.");
                return sessionOK;
            } else {
		return "Usuario y/o contraseña incorrectos.";
            }
        } else {
            return "Conexión al servidor LDAP fallida.";
        }
    }

    public Boolean validatePassword(String user, String password){
        String dn;
        dn = "cn=" + user + ",cn=administradorUB,ou=ubre,dc=arqsoft,dc=unal,dc=edu,dc=co";
        try {
            lc.bind(dn, password);
            System.out.println("==== Contraseña validada administrador ====");
            sessionOK = "main-admin";
            return true;
        } catch (LDAPException ex) {
            dn = "cn=" + user + ",cn=operadorUB,ou=ubre,dc=arqsoft,dc=unal,dc=edu,dc=co";
            try {
                lc.bind(dn, password);
                System.out.println("==== Contraseña validada operador ====");
                sessionOK = "main-operator";
                return true;
            } catch (LDAPException ex2) {
                System.out.println("==== ERROR al validar la contraseña admin y/o operador====");
                return false;
            }
        }
    }
    
    public String logoutUsers() {
        HttpSession session = (HttpSession)
             FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            try{
                lc.disconnect();
                System.out.println("Conexion Cerrada Correctamente...");
                session.invalidate();
            } catch (LDAPException ex){
                System.out.println(ex);
            }
        }
        return "main-index";
    }

    public String createUsers(String idUser, String firstnameUser, String lastnameUser, String usernameUser, String passwordUser, String emailUser, String telephoneUser, String addressUser, String birthdateUser, String typeUseridtypeuser){
        FacesContext context = FacesContext.getCurrentInstance();
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
            dn = "cn=" + usernameUser + ",cn=administradorUB,ou=ubre,dc=arqsoft,dc=unal,dc=edu,dc=co";
        } else {
            setAtr.add(new LDAPAttribute("objectclass", typeUseridtypeuser));
            dn = "cn=" + usernameUser + ",cn=operadorUB,ou=ubre,dc=arqsoft,dc=unal,dc=edu,dc=co";
        }
        LDAPEntry newEntry = new LDAPEntry(dn, setAtr);
        return newEntry;
    }
     
    public List listManyUsers(Integer typesearch, String search){
        List listUsers = new ArrayList();
        String filtro = null;
        LDAPSearchResults searchResults;
        String searchBase = "ou=ubre,dc=arqsoft,dc=unal,dc=edu,dc=co";
        int searchScope = LDAPConnection.SCOPE_SUB;
        switch (typesearch){
            case 1:
                filtro = "(roomnumber=" + search + ")";
                break;
            case 2:
                filtro = "(givenname=" + search + ")";
                break;
            case 3:
                filtro = "(sn=" + search + ")";
                break;
            case 4:
                filtro = "(cn=" + search + ")";
                break;
        }
        if (connect()){
            try {
                searchResults = lc.search(searchBase, searchScope, filtro, null, false);
                while (searchResults.hasMore()){
                    LDAPEntry nextEntry = null;
                    try {
                        nextEntry = searchResults.next();
                    } catch (LDAPException e) {
                        System.out.println("Error: " + e.toString());
                        continue;
                    }
                    List singleUser = new ArrayList();
                    LDAPAttributeSet attributeSet = nextEntry.getAttributeSet();
                    Iterator allAttributes = attributeSet.iterator();
                    while (allAttributes.hasNext()){
                        LDAPAttribute attribute = (LDAPAttribute) allAttributes.next();
                        String attributeName = attribute.getName();
                        Enumeration allvalues = attribute.getStringValues();
                        if (allvalues != null){
                            while (allvalues.hasMoreElements()){
                                String value = (String) allvalues.nextElement();
                                System.out.println(attributeName + ": " + value);
                                singleUser.add(value);
                            }
                        }
                    }
                    System.err.println("-------------------------");
                    listUsers.add(singleUser);
                }
                lc.disconnect();
            } catch (LDAPException ex){
                Logger.getLogger(HandleUsers.class.getName()).log(Level.SEVERE,null, ex);
            }
            return listUsers;
        } else {
            System.out.println("Conexión al servidor LDAP fallida.");
            return listUsers;
        }
    }

    public List checkUsers(String cnDelete){
        List listUsers = new ArrayList();
        String filtro = "(cn=" + cnDelete +")";
        LDAPSearchResults searchResults;
        String searchBase = "ou=ubre,dc=arqsoft,dc=unal,dc=edu,dc=co";
        int searchScope = LDAPConnection.SCOPE_SUB;
        if (connect()){
            try {
                searchResults = lc.search(searchBase, searchScope, filtro, null, false);
                while (searchResults.hasMore()){
                    LDAPEntry nextEntry = null;
                    try {
                        nextEntry = searchResults.next();
                    } catch (LDAPException e) {
                        System.out.println("Error: " + e.toString());
                        continue;
                    }
                    List singleUser = new ArrayList();
                    LDAPAttributeSet attributeSet = nextEntry.getAttributeSet();
                    Iterator allAttributes = attributeSet.iterator();
                    while (allAttributes.hasNext()){
                        LDAPAttribute attribute = (LDAPAttribute) allAttributes.next();
                        String attributeName = attribute.getName();
                        Enumeration allvalues = attribute.getStringValues();
                        if (allvalues != null){
                            while (allvalues.hasMoreElements()){
                                String value = (String) allvalues.nextElement();
                                System.out.println(attributeName + ": " + value);
                                singleUser.add(value);
                            }
                        }
                    }
                    System.err.println("-------------------------");
                    listUsers.add(singleUser);
                }
                lc.disconnect();
            } catch (LDAPException ex){
                Logger.getLogger(HandleUsers.class.getName()).log(Level.SEVERE,null, ex);
            }
            return listUsers;
        } else {
            System.out.println("Conexión al servidor LDAP fallida.");
            return listUsers;
        }
    }
    
    public String deleteUsers(String cnDelete, String typeDelete){
        String dn = null;
        if (typeDelete.equals("1")){
            dn = "cn=" + cnDelete + ",cn=administradorUB,ou=ubre,dc=arqsoft,dc=unal,dc=edu,dc=co";
        } else if (typeDelete.equals("2")){
            dn = "cn=" + cnDelete + ",cn=operadorUB,ou=ubre,dc=arqsoft,dc=unal,dc=edu,dc=co";
        }
        if (connect()){
            try {
                lc.delete(dn);
                System.out.println("El usuario fue eliminado correctamente.");
                lc.disconnect();
                return "main-admin";
            } catch (LDAPException e){
                if (e.getResultCode() == LDAPException.NO_SUCH_OBJECT){
                    return "Error: NO existe ese usuario...";
                } else if (e.getResultCode() == LDAPException.INSUFFICIENT_ACCESS_RIGHTS) {
                    return "Error: NO tiene permisos suficientes para realizar esta acción";
                } else {
                    System.out.println("Error: " + e.toString());
                    return "No se pudo realizar la acción.";
                }
            }
        } else {
            System.out.println("Conexión al servidor LDAP fallida.");
            return "Conexión al servidor LDAP fallida.";
        }
    }
    
    public String editUsers(String idUser, String firstnameUser, String lastnameUser, String usernameUser, String passwordUser, String emailUser, String telephoneUser, String addressUser, String birthdateUser, String typeuser){
        String dn = null;
        if (typeuser.equals("1")){
            dn = "cn=" + usernameUser + ",cn=administradorUB,ou=ubre,dc=arqsoft,dc=unal,dc=edu,dc=co";
        } else if (typeuser.equals("2")){
            dn = "cn=" + usernameUser + ",cn=operadorUB,ou=ubre,dc=arqsoft,dc=unal,dc=edu,dc=co";
        }
        if (connect()){
            try {
                LDAPAttribute atributo;
                atributo = new LDAPAttribute("cn", usernameUser);
                lc.modify(dn, new LDAPModification (LDAPModification.REPLACE, atributo));
                atributo = new LDAPAttribute("givenname", firstnameUser);
                lc.modify(dn, new LDAPModification (LDAPModification.REPLACE, atributo));
                atributo = new LDAPAttribute("sn", lastnameUser);
                lc.modify(dn, new LDAPModification (LDAPModification.REPLACE, atributo));
                atributo = new LDAPAttribute("telephonenumber", telephoneUser);
                lc.modify(dn, new LDAPModification (LDAPModification.REPLACE, atributo));
                atributo = new LDAPAttribute("mail", emailUser);
                lc.modify(dn, new LDAPModification (LDAPModification.REPLACE, atributo));
                atributo = new LDAPAttribute("userpassword", passwordUser);
                lc.modify(dn, new LDAPModification (LDAPModification.REPLACE, atributo));
                atributo = new LDAPAttribute("roomnumber", idUser);
                lc.modify(dn, new LDAPModification (LDAPModification.REPLACE, atributo));
                atributo = new LDAPAttribute("employeenumber", birthdateUser);
                lc.modify(dn, new LDAPModification (LDAPModification.REPLACE, atributo));
                atributo = new LDAPAttribute("homepostaladdress", addressUser);
                lc.modify(dn, new LDAPModification (LDAPModification.REPLACE, atributo));
                System.out.println("Atributo Modificado OK...");
                return "main-admin";
            } catch (LDAPException ex) {
                if (ex.getResultCode() ==  LDAPException.INSUFFICIENT_ACCESS_RIGHTS) {
                    System.err.println("Error: NO tiene permisos suficientes para realizar esta transaccion...");
                }
                return "No se pudo realizar el cambio.";
            }
        } else {
            System.out.println("Conexión al servidor LDAP fallida.");
            return "Conexión al servidor LDAP fallida.";
        }
    }

    public List listUsers(){
        List listUsers = new ArrayList();
        String filtro = null;
        LDAPSearchResults searchResults;
        String searchBase = "ou=ubre,dc=arqsoft,dc=unal,dc=edu,dc=co";
        int searchScope = LDAPConnection.SCOPE_SUB;
        if (connect()){
            try {
                searchResults = lc.search(searchBase, searchScope, null, null, false);
                while (searchResults.hasMore()){
                    LDAPEntry nextEntry = null;
                    try {
                        nextEntry = searchResults.next();
                    } catch (LDAPException e) {
                        System.out.println("Error: " + e.toString());
                        continue;
                    }
                    List singleUser = new ArrayList();
                    LDAPAttributeSet attributeSet = nextEntry.getAttributeSet();
                    Iterator allAttributes = attributeSet.iterator();
                    while (allAttributes.hasNext()){
                        LDAPAttribute attribute = (LDAPAttribute) allAttributes.next();
                        String attributeName = attribute.getName();
                        Enumeration allvalues = attribute.getStringValues();
                        if (allvalues != null){
                            while (allvalues.hasMoreElements()){
                                String value = (String) allvalues.nextElement();
                                System.out.println(attributeName + ": " + value);
                                singleUser.add(value);
                            }
                        }
                    }
                    System.err.println("-------------------------");
                    listUsers.add(singleUser);
                }
                lc.disconnect();
            } catch (LDAPException ex){
                Logger.getLogger(HandleUsers.class.getName()).log(Level.SEVERE,null, ex);
            }
            listUsers.remove(0);
            listUsers.remove(0);
            listUsers.remove(0);
            return listUsers;
        } else {
            System.out.println("Conexión al servidor LDAP fallida.");
            return listUsers;
        } 
    }
}
