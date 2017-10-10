package example.sylvan.com.kotlindemo.extensions

import android.content.Context
import android.support.v4.content.ContextCompat

/**
 * Created by sylvan on 2017/10/10.
 */
fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)
