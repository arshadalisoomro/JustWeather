package pk.inlab.app.justweather.ui.view.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.main_fragment.view.*

import pk.inlab.app.justweather.R
import pk.inlab.app.justweather.timeToDate
import pk.inlab.app.justweather.ui.view.main.adapter.ForecastAdapter
import java.util.*

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.main_fragment, container, false)

        view.rv_forecast.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true)

        //Using view model
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.currentWeatherData.observe(this, Observer {
            val weather = it.weather[0]
            val main = it.main
            val wind = it.wind

            // data to views
            view.tv_city_name.text = it.name
            view.tv_date.text = timeToDate(Date())
            view.tv_temperature.text = "${main.temp}"
            view.tv_humidity_value.text = "${main.humidity}"
            view.tv_max_temp_value.text = "${main.tempMax}"

            view.tv_main_condition.text = weather.main
            view.tv_wind_speed_value.text = "${wind.speed}"
        })

        viewModel.forecastWeatherData.observe(this, Observer {
            val adapter = ForecastAdapter(requireContext(), it.list)
            view.rv_forecast.adapter = adapter
        })

        return view
    }

}
