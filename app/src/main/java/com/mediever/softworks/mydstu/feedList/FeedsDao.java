package com.mediever.softworks.mydstu.feedList;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mediever.softworks.mydstu.entities.Feed;

import java.util.List;

@Dao
public interface FeedsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Feed> feeds);

    @Query("SELECT * FROM feeds_table WHERE type LIKE :type")
    LiveData<List<Feed>> getFeedsByType(String type);

    @Query("SELECT * FROM feeds_table")
    LiveData<List<Feed>> getAllFeeds();

    @Query("SELECT * FROM feeds_table WHERE date LIKE :date")
    LiveData<List<Feed>> getFeedsByDate(String date);

    @Query("SELECT * FROM feeds_table WHERE type LIKE :type AND date LIKE :date")
    LiveData<List<Feed>> getFeedsByTypeAndDate(String type, String date);

    @Query("DELETE FROM feeds_table")
    void deleteAllFeeds();
    @Query("SELECT * FROM feeds_table ORDER BY id LIMIT 1")
    LiveData<Feed> getLastFeed();
}
