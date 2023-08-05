package com.gioia.radiogio.data.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*@Indices({
        @Index(value = "key", type = IndexType.Unique),
})*/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Configuration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String code;
    private String val;
    private String description;
}