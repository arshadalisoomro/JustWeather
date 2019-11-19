/*
 *    Copyright 2019, In Lab Pakistan
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License
 */

package pk.inlab.app.justweather.ui.view.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_forecast.view.*
import pk.inlab.app.justweather.R
import pk.inlab.app.justweather.data.api.openweather.forecast.ForecastItem

class ForecastAdapter(val context: Context,
                      val forecastList: List<ForecastItem>
): RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    init {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_forecast, parent, false)

        return ForecastViewHolder(view)
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecast = forecastList[position]
        with(holder){
            forecastDayName?.let {
                it.text = forecast.weather[0].main
            }

            forecastTempValue?.let {
                it.text = "${forecast.main.temp}"
            }
        }
    }

    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val forecastDayName = itemView.tv_forecast_day_name
        val forecastConditionIcon = itemView.iv_forecast_condition_icon
        val forecastTempValue = itemView.tv_forecast_temp_value
    }
}