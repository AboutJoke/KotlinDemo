package example.sylvan.com.kotlindemo

/**
 * Created by sylvan on 2017/8/22.
 */
class Configuration(map: Map<String,Any?>) {
    val width: Int by map
    val height: Int by map
    val dp: Int by map
    val deviceName: String by map
}