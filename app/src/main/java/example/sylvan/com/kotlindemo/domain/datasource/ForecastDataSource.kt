package example.sylvan.com.kotlindemo.domain.datasource

import example.sylvan.com.kotlindemo.domain.model.ForecastList

/**
 * Created by sylvan on 2017/9/15.
 */
interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
}