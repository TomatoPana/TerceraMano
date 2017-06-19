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
import javax.inject.Named;
/**
 *
 * @author alanj
 */

@ManagedBean
@Named(value = "imagesView")
@ViewScoped
public class imagesView {
     
    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<>();
        images.add("imagen1.jpg");
    }
 
    public List<String> getImages() {
        return images;
    }
}