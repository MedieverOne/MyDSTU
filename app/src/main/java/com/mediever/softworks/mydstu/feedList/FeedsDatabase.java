package com.mediever.softworks.mydstu.feedList;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mediever.softworks.mydstu.entities.Feed;

@Database(entities = {Feed.class}, version = 1)
public abstract class FeedsDatabase extends RoomDatabase {
    private static FeedsDatabase instance;
    public abstract FeedsDao feedsDao();
    private static final String databaseName = "feeds_database";

    public static synchronized FeedsDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FeedsDatabase.class,
                    databaseName)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
