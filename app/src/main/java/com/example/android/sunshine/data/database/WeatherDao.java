package com.example.android.sunshine.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.Date;

/**
 * Created by kalyandechiraju on 12/05/18.
 *
 */

@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(WeatherEntry... weatherEntries);

    @Query("SELECT * from weather WHERE date = :date")
    LiveData<WeatherEntry> getWeatherByDate(Date date);

    @Query("SELECT COUNT(*) from weather WHERE date >= :currentDate")
    int countAllFutureWeather(Date currentDate);

    @Query("DELETE FROM weather WHERE date < :date")
    void deleteOldData(Date date);
}
