package com.gioia.radiogio.data.domains;

import lombok.Data;
import org.dizitart.no2.objects.Id;

import java.io.Serializable;

@Data
public class Configuration implements Serializable {
    @Id
    public String key;
    public String value;
    public String description;
}