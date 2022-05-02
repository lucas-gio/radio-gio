package com.gioia.radio.data

import org.dizitart.no2.Nitrite
import java.io.File

class Database private constructor(){
    companion object Db{
        private var dbName = "file.db"
        @Volatile private var instance: Nitrite? = null

        fun getInstance(): Nitrite? { // fixme: Llevarlo al d.i.
            if (instance == null) {
                synchronized(Nitrite::class.java) {
                    if (instance == null) {
                        instance = Nitrite
                            .builder()
                            .filePath("." + File.separator + dbName)
                            .openOrCreate()
                    }
                }
            }
            return instance
        }

        fun close() {
            if (!getInstance()?.isClosed()!!) {
                getInstance()?.close()
            }
        }
    }


}