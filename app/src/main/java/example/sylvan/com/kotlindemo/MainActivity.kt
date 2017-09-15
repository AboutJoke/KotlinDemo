package example.sylvan.com.kotlindemo

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import example.sylvan.com.kotlindemo.domain.commands.RequestForecastCommand
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

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

//        val forecastList=findViewById<RecyclerView>(R.id.forecast_list)
        forecast_list.layoutManager= LinearLayoutManager(this)
//        forecastList.adapter = ForecastListAdapter(items)

        doAsync {
            val result = RequestForecastCommand(94043).execute()
            uiThread{
                forecast_list.adapter = ForecastListAdapter(result) { toast(it.description) }
            }
        }


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
}

