package com.gioia.radio.services

import com.gioia.domains.Country
import com.gioia.radio.domains.Country
import com.gioia.radio.domains.Radio
import org.dizitart.no2.Nitrite
import org.dizitart.no2.filters.Filters
import sun.jvm.hotspot.oops.ObjectHeap
import java.io.BufferedReader
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.ArrayList

object DatabaseService {
    @Volatile
    private var instance: Nitrite? = null
    fun db(): Nitrite? {
        if (instance == null) {
            synchronized(Nitrite::class.java) {
                if (instance == null) {
                    instance = Nitrite
                        .builder()
                        .filePath("." + File.separator + "test.db") //fixme: En constante.
                        .openOrCreate()
                }
            }
        }
        return instance
    }

    fun close() {
        if (!db().isClosed()) {
            db().close()
        }
    }

    @Throws(IOException::class)
    fun insertDefaultData() {
        db().getRepository(Country::class.java).remove(Filters.ALL as ObjectHeap.ObjectFilter) //fixme ¿enserio?
        val reader: BufferedReader =
            Files.newBufferedReader(Paths.get("src/main/resources/com/gioia/initialRadios/radios.json"))
        val line: String = reader.readLine()
        val countriesWithRadios: List<Country> = ArrayList<Country>()
        val radioMap: MutableMap<String?, MutableList<Radio>> = HashMap<String?, MutableList<Radio>>()
        val gson = Gson()
        val radios: List<Map<String?, String?>> = gson.fromJson(line, MutableList::class.java)
        for (radio in radios) {
            radioMap[radio["4"]] = radioMap[radio["4"]]!!.add(
                Radio.builder()
                    .name(radio["1"])
                    .description(radio["2"])
                    .category(radio["3"])
                    .language(radio["5"])
                    .url(radio["6"])
                    .url2(radio["7"])
                    .url3(radio["8"])
                    .url4(radio["9"])
                    .url5(radio["10"])
                    .build()
            )
        }
        val a = 0
        /*
        db()
           .getRepository(Country.class)
            .insert(
                new Country("ar", "Argentina", null),
                new Country("uy", "Uruguay", null),
                new Country("br", "Brasil", List.of(
                    new Radio("radio 1 br", "http://centova.radios.pt:9538/stream"),
                    new Radio("radio 2 br", "http://centova.radios.pt:9538/stream"),
                    new Radio("radio 3 br", "http://centova.radios.pt:9538/stream"),
                    new Radio("radio 4 br", "http://centova.radios.pt:9538/stream")
                )),
                new Country("pt", "Portugal", null)
            );*/
    }
}