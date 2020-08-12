package com.akshay.weatherapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.akshay.weatherapp.network.OpenWeatherApiInterface
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(AndroidJUnit4::class)
class NetworkApiTest {

    lateinit var apiInterface: OpenWeatherApiInterface
    private val baseUrl = "https://api.openweathermap.org/data/2.5/"

    @Before
    fun createApi() {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(baseUrl)
            .build()

        apiInterface = retrofit.create(OpenWeatherApiInterface::class.java)
    }

    @Test
    fun testApiCall() {
        val qMap = mapOf(
            "lat" to "33.44",
            "lon" to "-94.037689",
            "appid" to "aaf3b45b1177933f4f78bc631caf557f"
        )
        runBlocking {
            val forecast = apiInterface.getForecast(qMap).await()

            Assert.assertNotNull(forecast)
            Assert.assertNotNull(forecast.daily)
            Assert.assertEquals(8, forecast.daily.size)
        }



    }

}