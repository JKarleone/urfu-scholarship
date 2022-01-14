package ru.intelligency.scholarship.data.portfolio

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.Date

class ImageProvider(private val context: Context) {

    fun createNewImage(imageBitmap: Bitmap): String {
        val currentTime = Date().time
        val fileName = "document_${currentTime}.png"
        val file = File(context.filesDir, fileName)
        val oStream = BufferedOutputStream(FileOutputStream(file))
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, oStream)
        oStream.close()
        return fileName
    }

    fun getDocumentByName(fileName: String): Bitmap {
        val file = File(context.filesDir, fileName)
        return BitmapFactory.decodeFile(file.path)
    }

    fun getImageFile(fileName: String): File {
        return File(context.filesDir, fileName)
    }
}
