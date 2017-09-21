package example.sylvan.com.kotlindemo.data.sever

import example.sylvan.com.kotlindemo.data.db.ForecastDb
import example.sylvan.com.kotlindemo.domain.datasource.ForecastDataSource
import example.sylvan.com.kotlindemo.domain.model.ForecastList

/**
 * Created by sylvan on 2017/9/18.
 */
class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper(),
                     private val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }
}