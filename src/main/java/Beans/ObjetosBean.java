/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Database.Object1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Gustavo
 */
@ManagedBean
@ViewScoped
public class ObjetosBean {

  
    private Object1 obj;
    private List<Object1> lista;
    private List<Object1> misObjetos;
    private List<Object1> misDonaciones;
   

    public List<Object1> getMisObjetos() {
        return misObjetos;
    }

    public void setMisObjetos(List<Object1> misObjetos) {
        this.misObjetos = misObjetos;
    }

    public List<Object1> getMisDonaciones() {
        return misDonaciones;
    }

    public void setMisDonaciones(List<Object1> misDonaciones) {
        this.misDonaciones = misDonaciones;
    }

    public List<Object1> getLista() {
        return lista;
    }

    public void setLista(List<Object1> lista) {
        this.lista = lista;
    }
    
    public Object1 getObj() {
        return obj;
    }

    public void setObj(Object1 obj) {
        this.obj = obj;
    }
    /**
     * Creates a new instance of ObjetosBean
     */
    public ObjetosBean() {
    }
    
    @PostConstruct
    public void init()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        Integer userId = (Integer) context.getExternalContext().getSessionMap().get("id");
        if(userId != null)
        {
            obj = new Object1();
            lista = new ArrayList();
            misDonaciones = new ArrayList();
            misObjetos = new ArrayList();
            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persistence" );
            EntityManager entitymanager = emfactory.createEntityManager();
            Query query = entitymanager.createNamedQuery("Object1.findAll", Object1.class);
            List<Object1> aux = query.getResultList();
            for(Object1 x: aux)
            {
                if(x.getNewOwner()==null)
                {   
                    System.out.println(x.getNameObject());
                    lista.add(x);
                }
                if(x.getOrgOwner()==userId)
                { 
                    misDonaciones.add(x);
                }
                if(x.getNewOwner()== userId)
                {
                    misObjetos.add(x);
                }
            }

        }
        else
        {
            try {
                context.getExternalContext().redirect("/TerceraMano");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void saveObj()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        Integer userId = (Integer) context.getExternalContext().getSessionMap().get("id");
        obj.setOrgOwner(userId);
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persistence" );
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        entitymanager.persist(obj);
        entitymanager.getTransaction().commit();
        entitymanager.close();
        try {
            context.getExternalContext().redirect("/TerceraMano/faces/start.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mine(Integer id)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        Integer userId = (Integer) context.getExternalContext().getSessionMap().get("id");
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "persistence" );
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        Object1 x = entitymanager.find(Object1.class, id);
        x.setNewOwner(userId);
        
        Query query = entitymanager.createQuery("Update Object1 o SET o.newOwner = " + userId.toString() + " WHERE o.idobject = " + id.toString());
        query.executeUpdate();
        entitymanager.getTransaction().commit();
        try {
            context.getExternalContext().redirect("/TerceraMano/faces/start.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
