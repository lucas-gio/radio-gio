package com.gioia.radio.domains

import org.dizitart.no2.Document
import org.dizitart.no2.mapper.Mappable
import org.dizitart.no2.mapper.NitriteMapper

class Radio(
    private var name: String,
    private var url: String?,
    private var url2: String?,
    private var url3: String?,
    private var url4: String?,
    private var url5: String?,
    private var description: String?,
    private var category: String?,
    private var language: String?
) : Mappable, Nameable{

    constructor() : this("", "", "", "", "", "", "", "", "")

    override fun nameValue(): String {
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