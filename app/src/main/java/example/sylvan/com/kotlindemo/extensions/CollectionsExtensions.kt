package example.sylvan.com.kotlindemo.extensions

/**
 * Created by sylvan on 2017/9/14.
 */

fun <K, V : Any> Map<K, V?>.toVarargArray(): Array<out Pair<K, V>> =
        map({ Pair(it.key, it.value!!) }).toTypedArray()