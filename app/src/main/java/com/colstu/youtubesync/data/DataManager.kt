package com.colstu.youtubesync.data

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader

class DataManager(context: Context) {
    private val gson = Gson()
    private val context = context


    fun read(fileName: String, dataClass: Class<*>): Any? {
        val inputStream = FileInputStream(File(context.filesDir, fileName))
        val jsonString = BufferedReader(InputStreamReader(inputStream)).readText()
        val jsonObject = gson.fromJson(jsonString, JsonObject::class.java)
        val data = gson.fromJson(jsonObject, dataClass)
        Log.d("DataManager", "Data read from file: $fileName")
        return data as JsonObject

    }


    fun write(fileName: String, jsonObject: JsonObject) {
        val outputStream = FileOutputStream(File(context.filesDir, fileName))
        val jsonString = gson.toJson(jsonObject)
        outputStream.write(jsonString.toByteArray())
        outputStream.close()
        Log.d("DataManager", "Data written to file: $fileName")
        return

    }
    fun copyFileFromAssets(context: Context, assetFileName: String, destinationFile: File) {
        try {
            val inputStream = context.assets.open(assetFileName)
            val outputStream = FileOutputStream(destinationFile)

            inputStream.copyTo(outputStream)

            inputStream.close()
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}
