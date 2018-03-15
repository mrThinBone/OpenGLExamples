package my.vinhtv.openglexamples.util

import android.content.Context
import android.content.res.Resources
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Created by vinh.trinh on 3/15/2018.
 */
object TextResourceReader {

    fun readTextFileFromResource(context: Context, resourceID: Int): String {
        val body = StringBuilder()
        try {
            val inputStream = context.resources.openRawResource(resourceID)
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)

            var nextLine: String? = bufferedReader.readLine()
            while (nextLine != null) {
                body.append(nextLine)
                body.append("\n")
                nextLine = bufferedReader.readLine()
            }
        } catch (ioe: IOException) {
            throw RuntimeException("Could not open resource: $resourceID", ioe)
        } catch (nfe: Resources.NotFoundException) {
            throw RuntimeException("Resource not found $resourceID")
        }
        return body.toString()
    }

}