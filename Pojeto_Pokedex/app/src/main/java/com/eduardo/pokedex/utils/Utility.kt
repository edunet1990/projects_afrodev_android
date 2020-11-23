package com.eduardo.pokedex.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.eduardo.pokedex.R
import java.util.*

var name : String = ""
object Utility {

    private var progressBar: ProgressBar? = null

    fun idMask(id: Int): String {
        id.let{
            return when(id){
                in 0..10 -> "#00${id}"
                in 11..99 -> "#0${id}"
                else -> "#$id"
            }
        }
    }

    fun capitalize(string: String) : String{
        val result = string.substring(0, 1).toUpperCase(Locale.ROOT) + (string.substring(1));
        return result
    }

    fun getImageByString(c: Context, imageName: String?): Drawable? {
        val image = ContextCompat.getDrawable(c, c.resources.getIdentifier(imageName, "drawable", c.packageName));
        return image
    }

    fun Context.isInternetAvailable(): Boolean {
        try {
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return if (netInfo != null && netInfo.isConnected) {
                true
            } else {
                showErrorToast("Internet not available. Please try again!!")
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun Context.showErrorToast(message: String?) {

        try {
            val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)

            // set message color
            val textView = toast.view?.findViewById(android.R.id.message) as? TextView
            textView?.setTextColor(Color.WHITE)
            textView?.gravity = Gravity.CENTER

            // set background color
            toast.view?.setBackgroundColor(Color.RED)

            toast.setGravity(Gravity.TOP or Gravity.FILL_HORIZONTAL, 0, 0)

            toast.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    // show progressbar
    fun Context.showProgressBar() {
        try {
            val layout = (this as? Activity)?.findViewById<View>(android.R.id.content)?.rootView as? ViewGroup

            progressBar = ProgressBar(this, null, android.R.attr.progressBarStyleLarge)
            progressBar?.let { it1 ->
                it1.isIndeterminate = true

                val params = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
                )

                val rl = RelativeLayout(this)

                rl.gravity = Gravity.CENTER
                rl.addView(it1)

                layout?.addView(rl, params)

                it1.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // hide progressbar
    fun hideProgressBar() {
        try {
            progressBar?.let {
                it.visibility = View.GONE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getThemeForType(type: String) : Int{
        val returnId : Int

        when(type){
            "bug" -> returnId = R.style.bug
            "dark" -> returnId = R.style.dark
            "dragon" -> returnId = R.style.dragon
            "electric" -> returnId = R.style.electric
            "fairy" -> returnId = R.style.fairy
            "fighting" -> returnId = R.style.fighting
            "fire" -> returnId = R.style.fire
            "flying" -> returnId = R.style.flying
            "ghost" -> returnId = R.style.ghost
            "grass" -> returnId = R.style.grass
            "ground" -> returnId = R.style.ground
            "ice" -> returnId = R.style.ice
            "steel" -> returnId = R.style.steel
            "normal" -> returnId = R.style.normal
            "poison" -> returnId = R.style.poison
            "psychic" -> returnId = R.style.psychic
            "rock" -> returnId = R.style.rock
            "water" -> returnId = R.style.water
            else -> returnId = R.style.normal
        }

        return returnId
    }

}