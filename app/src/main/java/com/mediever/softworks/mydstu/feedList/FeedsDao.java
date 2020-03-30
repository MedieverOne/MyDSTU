package com.mediever.softworks.mydstu.feedList;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mediever.softworks.mydstu.entities.Feed;

import java.util.List;

@Dao
public interface FeedsDao {
    @Insert
    void insert(Feed feeds);
    @Query("SELECT * FROM feeds_table")
    LiveData<List<Feed>> getAllFeeds();
    @Query("DELETE FROM feeds_table")
    void deleteAllFeeds();
    @Query("SELECT * FROM feeds_table ORDER BY id LIMIT 1")
    LiveData<Feed> getLastFeed();
}
