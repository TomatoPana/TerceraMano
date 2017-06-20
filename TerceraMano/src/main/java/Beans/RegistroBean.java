/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import com.ceti.terceramano.Users;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author USUARIO
 */
public class RegistroBean {
    private Integer id;
    private String userName;
    private String password;
    private String nombre;
    private String confirmPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String usuario) {
        this.userName = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    
    public void registro() {
        if(password.equals(confirmPassword)){
            //System.out.println("contraseñas diferentes");
            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "com.ceti_TerceraMano_war_1.0-SNAPSHOTPU" );
            EntityManager entitymanager = emfactory.createEntityManager();
            entitymanager.getTransaction().begin();
                
                Users usuario = new Users();
                usuario.setName(nombre);
                usuario.setUserName(userName);
                usuario.setIdusers(id);
                usuario.setPassword(password);
                
                entitymanager.persist(usuario);
                entitymanager.getTransaction().commit();
                entitymanager.close();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Registro exitoso"));
            
        }else
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("nel perro escriba bien su password"));
        }
        
        
        }
        
  
}
