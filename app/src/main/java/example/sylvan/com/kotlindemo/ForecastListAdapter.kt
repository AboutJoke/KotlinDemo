package example.sylvan.com.kotlindemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import example.sylvan.com.kotlindemo.domain.model.Forecast
import example.sylvan.com.kotlindemo.domain.model.ForecastList
import example.sylvan.com.kotlindemo.utils.ctx
import kotlinx.android.synthetic.main.item_forecast.view.*

/**
 * Created by sylvan on 2017/8/17.
 */
class ForecastListAdapter(val weekForecast: ForecastList,val itemClick: (Forecast) -> Unit) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    public interface OnItemClickListener {
        operator fun invoke(forecast: Forecast)
    }

    fun setOnClickListener(listener: (View) -> Unit) {}

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.size()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view,itemClick)
    }

    class ViewHolder(view: View, val itemClick: (Forecast) -> Unit) : RecyclerView.ViewHolder(view) {
//        private val iconView: ImageView = view.find(R.id.icon)
//        private val dateView: TextView = view.find(R.id.date)
//        private val descriptionView: TextView = view.find(R.id.description)
//        private val maxTemperatureView: TextView = view.find(R.id.maxTemperature)
//        private val minTemperatureView: TextView = view.find(R.id.minTemperature)

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Glide.with(itemView.ctx)
                        .load(iconUrl)
                        .into(itemView.icon)
                itemView.date.text = date
                itemView.description.text = description
                itemView.maxTemperature.text = "${high.toString()}º"
                itemView.minTemperature.text = "${low.toString()}º"
                itemView.setOnClickListener { itemClick(forecast) }
            }
        }
    }
}