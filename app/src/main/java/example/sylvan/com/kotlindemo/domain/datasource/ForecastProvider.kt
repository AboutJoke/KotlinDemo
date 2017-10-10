package example.sylvan.com.kotlindemo.domain.datasource

import example.sylvan.com.kotlindemo.data.db.ForecastDb
import example.sylvan.com.kotlindemo.data.sever.ForecastServer
import example.sylvan.com.kotlindemo.domain.model.Forecast
import example.sylvan.com.kotlindemo.domain.model.ForecastList
import example.sylvan.com.kotlindemo.extensions.firstResult

/**
 * Created by sylvan on 2017/9/15.
 */

class ForecastProvider(private val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {

    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size >= days) res else null
    }

    fun requestForecast(id: Long): Forecast = requestToSources { it.requestDayForecast(id) }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?): T = sources.firstResult { f(it) }

}