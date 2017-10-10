package example.sylvan.com.kotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.bumptech.glide.Glide
import example.sylvan.com.kotlindemo.domain.commands.RequestDayForecastCommand
import example.sylvan.com.kotlindemo.domain.model.Forecast
import example.sylvan.com.kotlindemo.extensions.color
import example.sylvan.com.kotlindemo.extensions.textColor
import example.sylvan.com.kotlindemo.extensions.toDateString
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import java.text.DateFormat

/**
 * Created by sylvan on 2017/10/10.
 */
class DetailActivity : AppCompatActivity() ,ToolbarManager{

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    companion object {
        val ID = "DetailActivity:id"
        val CITY_NAME = "DetailActivity:cityName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initToolbar()

        toolbarTitle = intent.getStringExtra(CITY_NAME)
        enableHomeAsUp { onBackPressed() }

        title=intent.getStringExtra(CITY_NAME)


        doAsync {
            val result = RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute()
            uiThread { bindForecast(result) }
        }
    }

    private fun bindForecast(forecast: Forecast) = with(forecast) {
        Glide.with(ctx).load(iconUrl).into(icon)
        toolbar.subtitle = date.toDateString(DateFormat.FULL)
        weatherDescription.text = description
        bindWeather(high to maxTemperature, low to minTemperature)
    }

    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first}º"
        it.second.textColor = color(when (it.first) {
            in -50..0 -> android.R.color.holo_red_dark
            in 0..15 -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_green_dark
        })
    }
}