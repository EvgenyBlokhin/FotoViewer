package ru.uj.fotoviewer;

import java.io.Serializable;


import android.location.Location;

import java.io.Serializable;

/**
 * Created by Блохин Евгений on 23.10.2017.
 */

public class Foto implements Serializable {
    private String id;

    private String imageUrl;

    private String description;

    private String name;


    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

}
