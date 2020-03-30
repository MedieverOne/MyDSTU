package com.mediever.softworks.mydstu.fullFeed;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mediever.softworks.mydstu.entities.Feed;
import com.mediever.softworks.mydstu.entities.FeedItem;
import com.mediever.softworks.mydstu.network.getData.NetworkFeedsRepository;
import com.mediever.softworks.mydstu.network.getData.NetworkFullFeedRepository;

public class FullFeedViewModel extends AndroidViewModel {
    private LiveData<FeedItem> data;
    public FullFeedViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<FeedItem> getFeed(String sessionId, String feedId) {
        if(data == null)
            data = NetworkFullFeedRepository.getInstance().getFeed(sessionId,feedId);
        return data;
    }
}
