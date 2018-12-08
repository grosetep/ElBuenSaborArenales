package com.estrategiamovilmx.eats.elbuensaborarenales.model;

import java.io.Serializable;

/**
 * Created by administrator on 10/07/2017.
 */
public class CategoryViewModel implements Serializable{
    private String idCategory;
    private String category;
    private String imageCategory;
    private String pathImageCategory;

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageCategory() {
        return imageCategory;
    }

    public void setImageCategory(String imageCategory) {
        this.imageCategory = imageCategory;
    }

    public String getPathImageCategory() {
        return pathImageCategory;
    }

    public void setPathImageCategory(String pathImageCategory) {
        this.pathImageCategory = pathImageCategory;
    }

    public CategoryViewModel(String idCategory, String category, String imageCategory, String pathImageCategory) {
        this.idCategory = idCategory;
        this.category = category;
        this.imageCategory = imageCategory;
        this.pathImageCategory = pathImageCategory;
    }
}
