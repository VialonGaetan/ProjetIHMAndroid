package com.polytech.ihm.projetihmandroid.model;

import android.text.format.DateFormat;
import com.polytech.ihm.projetihmandroid.model.eventInfo.CategoryEvent;

import java.util.Date;

/**
 * Created by Vialon Gaetan
 * on 22/04/2017.
 */

public class Event{

    private CategoryEvent categoryEvent;
    private String name;
    private String description;
    private String image;
    private Date date;
    private String dateString;


    public Event(String name, String description, CategoryEvent categoryEvent, String image, Date date,String dateString) {
        this.categoryEvent = categoryEvent;
        this.dateString = dateString;
        this.date=date;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public CategoryEvent getCategoryEvent() {
        return categoryEvent;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }

    public String getDateString() {
        return dateString;
    }
}
