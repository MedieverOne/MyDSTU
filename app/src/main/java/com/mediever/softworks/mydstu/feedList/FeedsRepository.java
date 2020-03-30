package com.mediever.softworks.mydstu.feedList;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.mediever.softworks.mydstu.entities.Feed;

import java.util.List;

public class FeedsRepository {
    private FeedsDao feedsDao;
    private LiveData<List<Feed>> allFeeds;

    public FeedsRepository(Application application) {
        FeedsDatabase feedsDatabase = FeedsDatabase.getInstance(application);
        feedsDao = feedsDatabase.feedsDao();
        allFeeds = feedsDao.getAllFeeds();
    }

    public void insert(Feed feed) {
        new FeedsRepository.InsertFeedAsyncTask(feedsDao).execute(feed);
    }
    public void deleteAllFeeds() {
        new FeedsRepository.DeleteFeedsAsyncTask(feedsDao).execute();
    }
    public LiveData<List<Feed>> getAllFeeds() {
        return allFeeds;
    }

    private static class InsertFeedAsyncTask extends AsyncTask<Feed,Void,Void> {
        private FeedsDao feedsDao;
        private InsertFeedAsyncTask(FeedsDao feedsDao) {
            this.feedsDao = feedsDao;
        }

        @Override
        protected Void doInBackground(Feed... feeds) {
            feedsDao.insert(feeds[0]);
            return null;
        }
    }

    private static class DeleteFeedsAsyncTask extends AsyncTask<Void,Void,Void> {
        private FeedsDao feedsDao;
        private DeleteFeedsAsyncTask(FeedsDao feedsDao) {
            this.feedsDao = feedsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            feedsDao.deleteAllFeeds();
            return null;
        }
    }
}
