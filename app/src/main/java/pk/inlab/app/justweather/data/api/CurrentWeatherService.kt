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

import kotlinx.coroutines.Deferred
import pk.inlab.app.justweather.OPEN_WEATHER_MAP_API
import pk.inlab.app.justweather.data.api.openweather.current.CurrentWeather
import pk.inlab.app.justweather.data.api.openweather.forecast.ForecastWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherService {

    @GET(value = "weather${OPEN_WEATHER_MAP_API}")
    fun getCurrentWeatherDataAsync(@Query(value = "q") query: String): Deferred<Response<CurrentWeather>>

    @GET(value = "forecast${OPEN_WEATHER_MAP_API}")
    fun getForecastWeatherDataAsync(@Query(value = "q") query: String): Deferred<Response<ForecastWeather>>

}