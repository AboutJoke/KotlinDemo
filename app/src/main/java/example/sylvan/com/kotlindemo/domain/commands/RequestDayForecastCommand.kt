package example.sylvan.com.kotlindemo.domain.commands

import example.sylvan.com.kotlindemo.domain.datasource.ForecastProvider
import example.sylvan.com.kotlindemo.domain.model.Forecast

/**
 * Created by sylvan on 2017/10/10.
 */
class RequestDayForecastCommand(
        val id: Long,
        private val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<Forecast> {

    override fun execute() = forecastProvider.requestForecast(id)
}