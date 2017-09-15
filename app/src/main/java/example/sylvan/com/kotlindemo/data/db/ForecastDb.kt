package example.sylvan.com.kotlindemo.data.db

import example.sylvan.com.kotlindemo.domain.model.ForecastList
import example.sylvan.com.kotlindemo.extensions.clear
import example.sylvan.com.kotlindemo.extensions.parseList
import example.sylvan.com.kotlindemo.extensions.parseOpt
import example.sylvan.com.kotlindemo.extensions.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * Created by sylvan on 2017/9/14.
 */
class ForecastDb(val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
        val dataMapper: DbDataMapper = DbDataMapper()) {

    fun requestForecastByZipCode(zipCode:Long,date:Long) = forecastDbHelper.use {
        val dailyRequest ="${DayForecastTable.CITY_ID}=?"+"and ${DayForecastTable.DATE} >=?"
        val dailyForecast=select(DayForecastTable.NAME).whereSimple(dailyRequest,zipCode.toString(),date.toString())
                .parseList{DayForecast(HashMap(it)) }

        val city=select(CityForecastTable.NAME).whereSimple("${CityForecastTable.ID}=?",zipCode.toString())
                .parseOpt{CityForecast(HashMap(it),dailyForecast)}

        if (city != null) dataMapper.convertToDomain(city) else null
    }

    fun saveForecast(forecast: ForecastList)=forecastDbHelper.use {
        clear(DayForecastTable.NAME)
        clear(CityForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)){
            insert(CityForecastTable.NAME,*map.toVarargArray())
            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }
}