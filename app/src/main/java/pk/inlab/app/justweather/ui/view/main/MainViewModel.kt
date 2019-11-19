package pk.inlab.app.justweather.ui.view.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pk.inlab.app.justweather.data.api.CurrentWeatherRepository


class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val currentWeatherDataRepo = CurrentWeatherRepository(app)
    val currentWeatherData = currentWeatherDataRepo.currentWeatherLiveData
    val forecastWeatherData = currentWeatherDataRepo.forecastWeatherLiveData
}
