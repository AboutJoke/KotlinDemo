package example.sylvan.com.kotlindemo

import android.util.Log
import java.net.URL

/**
 * Created by sylvan on 2017/8/17.
 */
public class Request(val url: String) {
    public fun run() {
        val forecastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, forecastJsonStr)
    }
}