package com.gioia.radio.data.domains

import org.dizitart.no2.Document
import org.dizitart.no2.mapper.Mappable
import org.dizitart.no2.mapper.NitriteMapper
import org.dizitart.no2.objects.Id

class Country(
    @Id
    var code: String
) : Mappable{

    override fun write(mapper: NitriteMapper?): Document {
        return Document(
            mapOf("code" to code)
        )
    }

    override fun read(mapper: NitriteMapper?, document: Document?) {
        if (document == null) {
            return
        }
        this.code = document["code"] as String
    }
}