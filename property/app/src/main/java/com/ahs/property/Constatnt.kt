package com.ahs.property

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Constatnt {
    companion object{
        @Throws(IOException::class)
        fun downloadUrl(myurl: String?): String? {
            var `is`: InputStream? = null
            return try {
                val url = URL(myurl)
                val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
                conn.setReadTimeout(10000)
                conn.setConnectTimeout(15000)
                conn.setRequestMethod("GET")
                conn.setDoInput(true)
                conn.connect()
                `is` = conn.getInputStream()
                readIt(`is`)
            } finally {
                if (`is` != null) {
                    `is`.close()
                }
            }
        }

        @Throws(IOException::class)
        fun readIt(stream: InputStream?): String {
            val reader = BufferedReader(InputStreamReader(stream, "UTF-8"))
            val sb = StringBuilder()
            /*var line: String
            while (reader.readLine().also { line = it } != null) {
                if (line.contains("fmt_stream_map")) {
                    sb.append(
                        """
                        $line

                        """.trimIndent()
                    )
                    break
                }
            }*/
            reader.close()
            val result: String = decodeFile(sb.toString()).toString()
            val url = result.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
            return url.toString()
        }

        fun decodeFile(`in`: String): String? {
            var working = `in`
            var index: Int
            index = working.indexOf("\\u")
            while (index > -1) {
                val length = working.length
                if (index > length - 6) break
                val numStart = index + 2
                val numFinish = numStart + 4
                val substring = working.substring(numStart, numFinish)
                val number = substring.toInt(16)
                val stringStart = working.substring(0, index)
                val stringEnd = working.substring(numFinish)
                working = stringStart + number.toChar() + stringEnd
                index = working.indexOf("\\u")
                System.out.println("videofile " + working)
            }
            return working
        }
    }
}