/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 *
 * @author alanj
 */

@ManagedBean
@ViewScoped
public class imagesView {
     
    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        images.add("imagen3.jpg");
        images.add("imagen2.png");
        images.add("imagen1.jpg"); 
        images.add("imagen3.png");
        images.add("imagen5.png");
    }
 
    public List<String> getImages() {
        return images;
    }
}