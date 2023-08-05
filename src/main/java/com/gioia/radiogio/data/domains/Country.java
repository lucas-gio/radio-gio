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

@Data
/*@Indices({
        @Index(value = "code", type = IndexType.NonUnique),
})*/
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country implements Serializable {
    @Id
    private String code;
    private String name;
}