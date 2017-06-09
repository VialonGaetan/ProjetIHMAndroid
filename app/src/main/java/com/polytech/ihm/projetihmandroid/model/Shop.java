package com.polytech.ihm.projetihmandroid.model;

import com.polytech.ihm.projetihmandroid.model.shopInfo.CategoryShop;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Vialon Gaetan
 * on 18/04/2017.
 */

public class Shop{

    private List<CategoryShop> categoryShop;
    private String name;
    private String description;
    private String fullDescription;
    private String mainImage;
    private List<String> images;

    public Shop(String name, String description, String fullDescription, String mainImage, List<String> images, CategoryShop... categoryShop) {
        this.categoryShop = Arrays.asList(categoryShop);
        this.name = name;
        this.description = description;
        this.mainImage = mainImage;
        this.fullDescription = fullDescription;
        this.images = images;
    }

    public List<CategoryShop> getCategoryShop() {
        return categoryShop;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return mainImage;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public List<String> getImages() {
        return images;
    }
}
