package example.sylvan.com.kotlindemo.domain.datasource

import example.sylvan.com.kotlindemo.data.db.ForecastDb
import example.sylvan.com.kotlindemo.data.sever.ForecastServer
import example.sylvan.com.kotlindemo.domain.model.ForecastList
import example.sylvan.com.kotlindemo.extensions.firstResult

/**
 * Created by sylvan on 2017/9/15.
 */

class ForecastProvider(private val sources: List<ForecastDataSource> = ForecastProvider.SOURCES as List<ForecastDataSource>) {

    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList
            = sources.firstResult { requestSource(it, days, zipCode) }

    private fun requestSource(source: ForecastDataSource, days: Int, zipCode: Long): ForecastList? {
        val res = source.requestForecastByZipCode(zipCode, todayTimeSpan())
        return if (res != null && res.size >= days) res else null
    }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

}