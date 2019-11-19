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

package pk.inlab.app.justweather.data.api

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import pk.inlab.app.justweather.OPEN_WEATHER_MAP_BASE_URL
import pk.inlab.app.justweather.data.api.openweather.current.CurrentWeather
import pk.inlab.app.justweather.data.api.openweather.forecast.ForecastWeather
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CurrentWeatherRepository(private val application: Application) {

    val currentWeatherLiveData = MutableLiveData<CurrentWeather>()
    val forecastWeatherLiveData = MutableLiveData<ForecastWeather>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            getLocationDataFromWebService()
        }
    }

    // will be called in background thread
    @WorkerThread
    suspend fun getLocationDataFromWebService(){
        if (networkAvailable()){
            val retrofit = Retrofit.Builder()
                .client(OkHttpClient.Builder().build())
                .baseUrl(OPEN_WEATHER_MAP_BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            val service = retrofit.create(CurrentWeatherService::class.java)

            val currentWeatherData = service.getCurrentWeatherDataAsync("London").await().body()
            val forecastWeatherData = service.getForecastWeatherDataAsync("London").await().body()

            currentWeatherLiveData.postValue(currentWeatherData)
            forecastWeatherLiveData.postValue(forecastWeatherData)
        }
    }

    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager

        val networkInfo = connectivityManager.activeNetworkInfo

        return networkInfo?.isConnectedOrConnecting ?: false
    }

}