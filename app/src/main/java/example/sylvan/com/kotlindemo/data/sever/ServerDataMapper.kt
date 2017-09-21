package example.sylvan.com.kotlindemo.data.sever

import example.sylvan.com.kotlindemo.domain.model.ForecastList
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import example.sylvan.com.kotlindemo.domain.model.Forecast as ModelForecast

/**
 * Created by sylvan on 2017/8/17.
 */
class ServerDataMapper {
    fun convertToDomain(zipCode: Long, forecast : ForecastResult) : ForecastList {
        return ForecastList(zipCode,forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>) : List<ModelForecast>{
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(forecast.dt, forecast.weather[0].description,
                forecast.temp.max.toInt(), forecast.temp.min.toInt(),generateIconUrl(forecast.
                weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String): String
            = "http://openweathermap.org/img/w/$iconCode.png"

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date)
    }

}