package com.assignment.testutils

import com.google.gson.Gson

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/*
 * Copyright 2018, Assignment/test project for some company
 *
 * @author Stefan Wyszynski
 */
class TestUtils {

    companion object {
        val INSTANCE = TestUtils()

        fun <jsonObj, jsonObjClass : Class<jsonObj>> loadJson(path: String, classT: jsonObjClass?): jsonObj {
            val json = getFileString(path)
            return Gson().fromJson(json, classT)
        }

        private fun getFileString(path: String): String {
            try {
                val sb = StringBuilder()
                val reader = BufferedReader(InputStreamReader(
                        INSTANCE.javaClass.getClassLoader()!!.getResourceAsStream(path)))
                var line: String?
                line = reader.readLine()
                while (line != null) {
                    sb.append(line)
                    line = reader.readLine()
                }
                return sb.toString()
            } catch (e: IOException) {
                throw IllegalArgumentException("Could not read from resource at: $path")
            }

        }
    }

}
