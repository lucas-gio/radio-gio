package com.gioia.radiogio.data.domains;

import lombok.Data;
import org.dizitart.no2.objects.Id;

import java.io.Serializable;

@Data
public class RadioStation implements Serializable{
    @Id
    public String name;
    public String countryCode;
    public String url;
    public String url2;
    public String url3;
    public String url4;
    public String url5;
    public String description;
    public String category;
    public String language;
    public Boolean isFavourite;
}