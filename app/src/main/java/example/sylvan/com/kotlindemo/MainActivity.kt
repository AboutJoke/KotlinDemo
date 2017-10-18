package example.sylvan.com.kotlindemo

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import example.sylvan.com.kotlindemo.domain.commands.RequestForecastCommand
import example.sylvan.com.kotlindemo.extensions.DelegatesExt
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() , ToolbarManager{

    private val zipCode: Long by DelegatesExt.preference(this, SettingsActivity.ZIP_CODE,
            SettingsActivity.DEFAULT_ZIP)

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()


//        val forecastList=findViewById<RecyclerView>(R.id.forecast_list)
        forecast_list.layoutManager= LinearLayoutManager(this)
        attachToScroll(forecast_list)
//        forecastList.adapter = ForecastListAdapter(items)

//        doAsync {
//            val result = RequestForecastCommand(94043).execute()
//            uiThread{
//                forecast_list.adapter = ForecastListAdapter(result) {
////                    toast(it.description)
//                    startActivity<DetailActivity>(DetailActivity.ID to it.id,
//                            DetailActivity.CITY_NAME to result.city)
//                }
//            }
//        }

//        doAsync {
//            val result = RequestForecastCommand(94043).execute()
//            uiThread {
//                val adapter =ForecastListAdapter(result){
//                    startActivity<DetailActivity>(DetailActivity.ID to it.id,
//                            DetailActivity.CITY_NAME to result.city)
//                }
//                forecast_list.adapter=adapter
//                toolbarTitle="${result.city}(${result.country})"
//            }
//        }


//        从Map中映射值
//        var conf = Configuration(mapOf(
//                "width" to 1080,
//                "height" to 720,
//                "dp" to 240,
//                "deviceName" to "mydevice"
//        ))

//        supportsLollipop {
//                    window.statusBarColor = Color.BLACK
//        }

//        val f1 = Forecast(Date(), 27.5f, "Shiny day")
//        val f2 = f1.copy(temperature = 30f)
//        val (date, temperature, details) = f1
    }

//    fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
//        Toast.makeText(this, message, duration).show()
//    }

    inline fun supportsLollipop(code: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            code() }
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() = doAsync {
        val result = RequestForecastCommand(zipCode).execute()
        uiThread {
            val adapter = ForecastListAdapter(result) {
                startActivity<DetailActivity>(DetailActivity.ID to it.id,
                        DetailActivity.CITY_NAME to result.city)
            }
            forecast_list.adapter = adapter
            toolbarTitle = "${result.city} (${result.country})"
        }
    }
}

