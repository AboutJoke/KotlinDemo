package example.sylvan.com.kotlindemo.extensions

import java.text.DateFormat
import java.util.*

/**
 * Created by sylvan on 2017/10/10.
 */
fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}