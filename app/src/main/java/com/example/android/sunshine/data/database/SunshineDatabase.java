package com.example.android.sunshine.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

/**
 * Created by kalyandechiraju on 12/05/18.
 * Playo
 */

@Database(entities = {WeatherEntry.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class SunshineDatabase extends RoomDatabase {
    public abstract WeatherDao weatherDao();

    private static final String DATABASE_NAME = "weather";

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static volatile SunshineDatabase sunshineDatabase;

    public static SunshineDatabase getInstance(Context context) {
        if (sunshineDatabase == null) {
            synchronized (LOCK) {
                if (sunshineDatabase == null) {
                    sunshineDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            SunshineDatabase.class, SunshineDatabase.DATABASE_NAME).build();
                }
            }
        }
        return sunshineDatabase;
    }
}
