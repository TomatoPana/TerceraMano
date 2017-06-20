/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import com.ceti.terceramano.Users;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 *
 * @author USUARIO
 */

public class LoginBean {

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        
    }
    
    private String username;
    private String password;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    


    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "com.ceti_TerceraMano_war_1.0-SNAPSHOTPU" );
        EntityManager entitymanager = emfactory.createEntityManager();
        
        Query query = entitymanager.createNamedQuery("Users.findByUserName", Users.class);
        
        query.setParameter("userName", this.username);
        List<Users> results = query.getResultList();
        for(Users x: results)
        {
        }
        if (results.size() < 1) {
            username = null;
            password = null;
            try {
                context.getExternalContext().redirect("/TerceraMano?error=log");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "/forms?error=log";
        } else {
            
            context.getExternalContext().getSessionMap().put("user", username);
            Users usuario = (Users) results.get(0);
            context.getExternalContext().getSessionMap().put("id", usuario.getIdusers());
            try {
                context.getExternalContext().redirect("/TerceraMano/faces/listaProductos.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("user");
        context.getExternalContext().getSessionMap().remove("id");
        try {
                context.getExternalContext().redirect("/TerceraMano/faces/index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        return "/TerceraMano";
    }
    
}
