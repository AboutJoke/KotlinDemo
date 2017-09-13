package example.sylvan.com.kotlindemo.domain.commands

import example.sylvan.com.kotlindemo.data.ForecastRequest
import example.sylvan.com.kotlindemo.domain.mappers.ForecastDataMapper
import example.sylvan.com.kotlindemo.domain.model.ForecastList

/**
 * Created by sylvan on 2017/8/17.
 */
class RequestForecastCommand(private val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }

}