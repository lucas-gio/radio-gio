package com.gioia.radiogio.data.domains;

import org.dizitart.no2.IndexType;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

import java.io.Serializable;

@Indices({
        @Index(value = "key", type = IndexType.Unique),
})
public class Configuration implements Serializable {

    public Configuration(){
        // Empty for DB mapping use.
    }

    @Id
    private String key;
    private String value;
    private String description;

    public String getKey() {
        return this.key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}