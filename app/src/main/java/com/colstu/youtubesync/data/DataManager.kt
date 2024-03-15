package com.colstu.youtubesync.data

import android.content.Context
import com.google.gson.Gson

class DataManager(context: Context) {

    private val gson = Gson()
    private val co = context
    fun writeDataToJson(data: Any, filename: String) {
        val jsonString = gson.toJson(data)
        val fileOutputStream = co.openFileOutput(filename, Context.MODE_PRIVATE)
        fileOutputStream.write(jsonString.toByteArray())
        fileOutputStream.close()
    }

    fun readDataFromJson(filename: String, clazz: Class<*>): Any? {
        val fileInputStream = co.openFileInput(filename)
        val dataString = String(fileInputStream.readBytes())
        fileInputStream.close()
        return gson.fromJson(dataString, clazz)
    }
}
