package example.sylvan.com.kotlindemo

import android.app.Application

/**
 * Created by sylvan on 2017/8/21.
 */
class App : Application() {
//    companion object {
//        private var instance: Application? = null
//        fun instance() = instance!!
//    }
//    override fun onCreate() {
//        super.onCreate()
//        instance = this
//    }

    companion object {
        var instance :App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}