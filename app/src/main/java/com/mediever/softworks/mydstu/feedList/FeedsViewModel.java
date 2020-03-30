package com.mediever.softworks.mydstu.feedList;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mediever.softworks.mydstu.network.getData.NetworkFeedsRepository;
import com.mediever.softworks.mydstu.network.models.PageFeedsModel;


public class FeedsViewModel extends AndroidViewModel {
    private LiveData<PageFeedsModel> allFeeds;
    public FeedsViewModel(@NonNull Application application) {
        super(application);
        allFeeds = NetworkFeedsRepository.getInstance().getFeeds(0,"","");
    }

    public LiveData<PageFeedsModel> getFeeds(Integer page, String type, String date) {
        return allFeeds;
    }
}
