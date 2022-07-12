package com.debduttapanda.androidndkemboss

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.debduttapanda.androidndkemboss.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ///////////////////////////////////
        // Load the bitmap into an array
        // Load the bitmap into an array
        val b = BitmapFactory.decodeResource(
            resources,
            R.drawable.fish
        )
        var pix: IntArray? = IntArray(b.width * b.height)
        b.getPixels(
            pix, 0, b.width, 0, 0, b.width,
            b.height
        )

        // Call the native method 'ndkEmboss'

        // Call the native method 'ndkEmboss'
        ndkEmboss(pix, b.width, b.height)

        // Create a new bitmap with the result array

        // Create a new bitmap with the result array
        val out = Bitmap.createBitmap(
            b.width, b.height,
            Bitmap.Config.ARGB_8888
        )
        out.setPixels(
            pix, 0, b.width, 0, 0, b.width,
            b.height
        )
        binding.ivEmboss.setImageBitmap(out)

        // Clean up

        // Clean up
        pix = null
        b.recycle()
    }

    external fun ndkEmboss(
        data: IntArray?, width: Int,
        height: Int
    )

    companion object {
        // Used to load the 'androidndkemboss' library on application startup.
        init {
            System.loadLibrary("androidndkemboss")
        }
    }
}