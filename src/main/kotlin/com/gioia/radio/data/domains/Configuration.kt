package com.gioia.radio.data.domains

import org.dizitart.no2.Document
import org.dizitart.no2.mapper.Mappable
import org.dizitart.no2.mapper.NitriteMapper
import org.dizitart.no2.objects.Id

class Configuration(
    @Id
    var key: String,
    var value: String,
    var description: String,
): Mappable{
    override fun write(mapper: NitriteMapper?): Document {
        val document = Document()
        document["key"] = key
        document["value"] = value
        document["description"] = description
        return document
    }

    override fun read(mapper: NitriteMapper?, document: Document?) {
        if (document == null) return

        this.key = document["key"] as String
        this.value = document["value"] as String
        this.description = document["description"] as String
    }
}