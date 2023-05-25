package com.gioia.radiogio.data.domains;

import lombok.Data;
import org.dizitart.no2.objects.Id;

import java.io.Serializable;

@Data
public class Country implements Serializable {
    @Id
    public String code;
}