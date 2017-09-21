package example.sylvan.com.kotlindemo.domain.commands

import example.sylvan.com.kotlindemo.data.sever.ForecastByZipCodeRequest
import example.sylvan.com.kotlindemo.data.sever.ServerDataMapper
import example.sylvan.com.kotlindemo.domain.model.ForecastList

/**
 * Created by sylvan on 2017/8/17.
 */
class RequestForecastCommand(private val zipCode: Long) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastByZipCodeRequest(zipCode)
        return ServerDataMapper().convertToDomain(zipCode,forecastRequest.execute())
    }

}