package com.robertomiranda.data

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class Utils {

    companion object{

        private const val STRING_ENCODING = "UTF-8"

        fun getStringFromFile(path: String): String {
            try {
                val resourceAsStream = Utils::class.java.classLoader!!.getResourceAsStream(path)
                return getStringFromStream(resourceAsStream)
            } catch (exception: IOException) {
                throw RuntimeException(exception)
            }
        }

        @Throws(IOException::class)
        fun getStringFromStream(input: InputStream): String {
            val inputStreamReader = InputStreamReader(input, STRING_ENCODING)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            var line: String? = bufferedReader.readLine()

            do {
                stringBuilder.append(line).append("\n")
                line = bufferedReader.readLine()
            } while (line != null)

            bufferedReader.close()
            inputStreamReader.close()
            return stringBuilder.toString()
        }
    }
}