package com.mediever.softworks.mydstu.network.getData;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.mediever.softworks.mydstu.entities.Feed;
import com.mediever.softworks.mydstu.entities.FeedItem;
import com.mediever.softworks.mydstu.network.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkFullFeedRepository {
    private static NetworkFullFeedRepository instance;
    public static NetworkFullFeedRepository getInstance() {
        if(instance == null) {
            instance = new NetworkFullFeedRepository();
        }
        return instance;
    }

    public synchronized MutableLiveData<FeedItem> getFeed(String sessionId, String feedId) {
        final MutableLiveData<FeedItem> feed = new MutableLiveData<>();
        NetworkService.getInstance().getServerApi().getFeedItem(sessionId,feedId).enqueue(new Callback<FeedItem>() {
            @Override
            public void onResponse(Call<FeedItem> call, Response<FeedItem> response) {
                if(response.isSuccessful()) {
                    feed.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FeedItem> call, Throwable t) {
                feed.setValue(null);
            }
        });
        return feed;
    }
}
