package com.akshay.weatherapp

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.akshay.weatherapp.database.DailyForecast
import com.akshay.weatherapp.database.DailyForecastDao
import com.akshay.weatherapp.database.WeatherDatabase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class WeatherDatabaseTest {
    private lateinit var dao: DailyForecastDao
    private lateinit var db: WeatherDatabase


    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, WeatherDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        dao = db.getDailyForecastDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetForecast() {
        val quote = DailyForecast(
            1,
            123456,
            "America/Chicago",
            -18000,
            1596972887,
            1597021708,
            1020.0F,
            89.0F,
            24.11F,
            34.93F
        )

        dao.insert(quote)
        val forecast = dao.getForecast(123456)

        Assert.assertEquals(true, forecast != null)
        Assert.assertEquals(1597021708L, forecast?.sunset)
    }
}