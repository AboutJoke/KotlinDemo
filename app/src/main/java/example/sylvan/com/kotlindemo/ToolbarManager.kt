package example.sylvan.com.kotlindemo

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import example.sylvan.com.kotlindemo.extensions.ctx
import example.sylvan.com.kotlindemo.extensions.slideEnter
import example.sylvan.com.kotlindemo.extensions.slideExit
import org.jetbrains.anko.toast

/**
 * Created by sylvan on 2017/10/10.
 */
interface ToolbarManager {

    val toolbar: Toolbar

    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }

    fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> App.instance.toast("Settings")
                else -> App.instance.toast("Unknown option")
            }
            true
        }
    }

    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

    private fun createUpDrawable() = with(DrawerArrowDrawable(toolbar.ctx)) {
        progress = 1f
        this
    }

    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }
}