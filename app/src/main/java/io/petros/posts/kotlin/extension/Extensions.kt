package io.petros.posts.kotlin.extension

import android.app.Fragment
import android.content.Context
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.petros.posts.kotlin.R

fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Fragment.toast(message: CharSequence) = activity.toast(message)

fun ViewGroup.inflate(layoutRes: Int): View = LayoutInflater.from(context).inflate(layoutRes, this, false)

fun View.snack() = Snackbar.make(this, R.string.snackbar_text, Snackbar.LENGTH_LONG)
        .setAction(R.string.snackbar_action_text, {})
        .show()
