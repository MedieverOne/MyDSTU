package com.mediever.softworks.mydstu.feedList;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;

import com.mediever.softworks.mydstu.entities.Feed;
import com.mediever.softworks.mydstu.network.getData.NetworkFeedsRepository;
import com.mediever.softworks.mydstu.network.models.PageFeedsModel;

import java.util.List;


public class FeedsViewModel extends AndroidViewModel {
    private FeedsRepository feedsRepository;

    public FeedsViewModel(@NonNull Application application) {
        super(application);
        feedsRepository = new FeedsRepository(application);
    }

    public LiveData<List<Feed>> getAllFeeds() {
        return feedsRepository.getAllFeeds();
    }

    public void deleteAllFeeds() { feedsRepository.deleteAllFeeds(); }

    public LiveData<List<Feed>> getAllFeeds(String type, String date) {
        return feedsRepository.getAllFeeds(type,date);
    }

    public void getFeedsPage(Integer page) {
        feedsRepository.downloadFeeds(page);
    }

}
