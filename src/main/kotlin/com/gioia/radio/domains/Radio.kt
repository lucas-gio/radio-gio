package com.gioia.radio.domains

import org.dizitart.no2.Document
import org.dizitart.no2.mapper.Mappable
import org.dizitart.no2.mapper.NitriteMapper

class Radio(
    var name: String?,
    var url: String?,
    var url2: String?,
    var url3: String?,
    var url4: String?,
    var url5: String?,
    var description: String?,
    var category: String?,
    var language: String?
) : Mappable, Nameable{

    constructor() : this("", "", "", "", "", "", "", "", "")

    override fun nameValue(): String? {
        return name
    }

    override fun write(mapper: NitriteMapper?): Document {
        val document = Document()
        document["name"] = name
        document["url"] = url
        document["url2"] = url2
        document["url3"] = url3
        document["url4"] = url4
        document["url5"] = url5
        document["description"] = description
        document["category"] = category
        document["language"] = language
        return document
    }

    override fun read(mapper: NitriteMapper?, document: Document?) {
        if (document == null) {
            return
        }
        this.name = (document["name"] as String)
        this.url = (document["url"] as String)
        this.url2 = (document["url2"] as String)
        this.url3 = (document["url3"] as String)
        this.url4 = (document["url4"] as String)
        this.url5 = (document["url5"] as String)
        this.description = (document["description"] as String)
        this.category = (document["category"] as String)
        this.language = (document["language"] as String)
    }
}