package com.gioia.radiogio.data.domains;

import org.dizitart.no2.IndexType;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

import java.io.Serializable;

@Indices({
        @Index(value = "name", type = IndexType.NonUnique),
        @Index(value = "isFavourite", type = IndexType.NonUnique)
})
public class RadioStation implements Serializable{
    public RadioStation(){
        // Empty for DB mapping use.
    }

    @Id
    private String name;
    private String countryCode;
    private String url;
    private String url2;
    private String url3;
    private String url4;
    private String url5;
    private String description;
    private String category;
    private String language;
    private Boolean isFavourite;

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getUrl2() {
        return this.url2;
    }

    public void setUrl2(final String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {
        return this.url3;
    }

    public void setUrl3(final String url3) {
        this.url3 = url3;
    }

    public String getUrl4() {
        return this.url4;
    }

    public void setUrl4(final String url4) {
        this.url4 = url4;
    }

    public String getUrl5() {
        return this.url5;
    }

    public void setUrl5(final String url5) {
        this.url5 = url5;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public Boolean getFavourite() {
        return this.isFavourite;
    }

    public void setFavourite(final Boolean favourite) {
        this.isFavourite = favourite;
    }
}