package com.gioia.radiogio.data.domains;

import org.dizitart.no2.IndexType;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

import java.io.Serializable;


@Indices({
        @Index(value = "code", type = IndexType.NonUnique),
})
public class Country implements Serializable {
    public Country(){
        // Empty for DB mapping use.
    }

    @Id
    private String code;

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }
}