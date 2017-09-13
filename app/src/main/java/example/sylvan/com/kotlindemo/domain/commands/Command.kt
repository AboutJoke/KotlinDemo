package example.sylvan.com.kotlindemo.domain.commands

/**
 * Created by sylvan on 2017/8/17.
 */
interface Command<T> {
    fun execute(): T
}