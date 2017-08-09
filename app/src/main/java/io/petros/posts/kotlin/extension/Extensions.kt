package io.petros.posts.kotlin.extension

import android.content.Context
import android.net.Uri
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import io.petros.posts.kotlin.R

fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_LONG)
        .show()

fun Fragment.toast(message: CharSequence) = activity.toast(message)

fun ViewGroup.inflate(resource: Int): View = LayoutInflater.from(context)
        .inflate(resource, this, false)

fun View.snack() = Snackbar.make(this, R.string.snackbar_text, Snackbar.LENGTH_LONG)
        .setAction(R.string.snackbar_action_text, {})
        .show()

fun View.snack(resId: Int) = Snackbar.make(this, resId, Snackbar.LENGTH_LONG)
        .setAction(R.string.snackbar_action_text, {})
        .show()

fun ImageView.loadUrl(url: String) {
    val uri = Uri.parse(url)
    Glide.with(context)
            .load(uri)
            .into(this)
}
