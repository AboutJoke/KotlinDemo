package example.sylvan.com.kotlindemo.domain.commands

import example.sylvan.com.kotlindemo.domain.datasource.ForecastProvider
import example.sylvan.com.kotlindemo.domain.model.ForecastList

/**
 * Created by sylvan on 2017/8/17.
 */
class RequestForecastCommand(
        private val zipCode: Long,
        private val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<ForecastList> {

    companion object {
        val DAYS = 7
    }

    override fun execute() = forecastProvider.requestByZipCode(zipCode, DAYS)
}