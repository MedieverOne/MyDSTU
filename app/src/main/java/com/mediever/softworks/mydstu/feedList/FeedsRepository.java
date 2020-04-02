package com.mediever.softworks.mydstu.feedList;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mediever.softworks.mydstu.R;
import com.mediever.softworks.mydstu.entities.Feed;
import com.mediever.softworks.mydstu.network.getData.NetworkFeedsRepository;
import com.mediever.softworks.mydstu.network.models.PageFeedsModel;

import java.util.List;

public class FeedsRepository {
    private FeedsDao feedsDao;
    private Context context;
    private Integer downloadedPages;
    private Integer totalPages;

    public FeedsRepository(Application application) {
        FeedsDatabase feedsDatabase = FeedsDatabase.getInstance(application);
        context = application.getApplicationContext();
        feedsDao = feedsDatabase.feedsDao();
        downloadedPages = 0;
        totalPages = 0;
    }

    public void insert(List<Feed> feeds) {
        new FeedsRepository.InsertFeedAsyncTask(feedsDao).execute(feeds);
    }

    public void deleteAllFeeds() {
        Log.d("HALO","deleteAllFeeds");
        new FeedsRepository.DeleteFeedsAsyncTask(feedsDao).execute();
    }

    public LiveData<List<Feed>> getAllFeeds() {
        return feedsDao.getAllFeeds();
    }

    public LiveData<List<Feed>> getAllFeeds(String type, String date) {
        if(type.isEmpty() && !date.isEmpty())
            return feedsDao.getFeedsByDate(date);
        else if(!type.isEmpty() && date.isEmpty())
            return feedsDao.getFeedsByType(type);
        else if(!type.isEmpty() && !date.isEmpty())
            return feedsDao.getFeedsByTypeAndDate(type,date);
        else
            return feedsDao.getAllFeeds();
    }

    private static class InsertFeedAsyncTask extends AsyncTask<List<Feed>,Void,Void> {
        private FeedsDao feedsDao;
        private InsertFeedAsyncTask(FeedsDao feedsDao) {
            this.feedsDao = feedsDao;
        }

        @Override
        protected Void doInBackground(List<Feed>... feeds) {
            feedsDao.insert(feeds[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
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

    public void downloadFeeds(int page) {
        if(downloadedPages < totalPages || totalPages == 0) {
            NetworkFeedsRepository.getInstance().getFeeds(page, "", "").observeForever(new Observer<PageFeedsModel>() {
                @Override
                public void onChanged(PageFeedsModel pageFeedsModel) {
                    if (pageFeedsModel != null) {
                        insert(pageFeedsModel.getData());
                        totalPages = pageFeedsModel.getTotalpages();
                        downloadedPages++;
                        downloadFeeds(downloadedPages);
                    } else {
                        Toast.makeText(context, R.string.connect_to_server_error, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


}
