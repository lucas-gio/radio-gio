package com.gioia.radio.data.domains

import org.dizitart.no2.Document
import org.dizitart.no2.mapper.Mappable
import org.dizitart.no2.mapper.NitriteMapper
import org.dizitart.no2.objects.Id
import java.util.stream.Collectors

class Country(
    @Id
    var id: String,
    var name: String?,
    var radios: List<Radio>?
) : Mappable, Nameable {


    override fun nameValue(): String? {
        return name
    }

    override fun write(mapper: NitriteMapper?): Document {
        val document = Document()
        document["id"] = id
        document["name"] = name

        if (radios != null) {
            document["radios"] = radios!!
                .stream()
                .map { radioObject -> radioObject.write(mapper) }
                .collect(Collectors.toList())
        }
        return document
    }

    override fun read(mapper: NitriteMapper?, document: Document?) {
        if (document == null) {
            return
        }
        this.id = document["id"] as String
        this.name = document["name"] as String
        val radios: List<Document>? = document["radios"] as List<Document>?
        if (radios != null) {
            this.radios = radios
                    .stream()
                    .map { radio: Document? ->
                        val newRadioObject = Radio()
                        newRadioObject.read(mapper, radio)
                        newRadioObject
                    }
                .collect(Collectors.toList())
        }
    }
}