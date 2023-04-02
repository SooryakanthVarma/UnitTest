package com.example.myapplication

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val take = findViewById<Button>(R.id.take)

        take.setOnClickListener {
            takeScreenshot(window.decorView.rootView)
        }
    }
    val TAG = "Cakap"
    fun Any.log() {
        Log.v(TAG, this.toString())
    }
    fun takeScreenshot(view: View): File? {
        val date = Date()
        val dirpath =
            this@MainActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString()
        try {
            Log.e("dirpath", dirpath.toUri().toString())

            // File name : keeping file name unique using data time.
            val path = dirpath + "/" + date.getTime() + ".jpeg"
            Log.e("path", path)
            view.isDrawingCacheEnabled = true
            val bitmap = Bitmap.createBitmap(view.drawingCache)
            view.isDrawingCacheEnabled = false
            val imageurl = File(path)
            imageurl.log()
            Log.e("imageurl", imageurl.toString())
            val outputStream = FileOutputStream(imageurl)
            Log.e("outputStream", outputStream.toString())
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream)
            outputStream.flush()
            outputStream.close()
            return imageurl
            Log.e("imageurl//", imageurl.toString())
        } catch (io: FileNotFoundException) {
            io.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

}