package com.gioia.radiogio.data.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
/*@Indices({
        @Index(value = "name", type = IndexType.NonUnique),
        @Index(value = "isFavourite", type = IndexType.NonUnique)
})*/
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RadioStation implements Serializable{
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
}