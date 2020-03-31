package com.mediever.softworks.mydstu.feedList;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.mediever.softworks.mydstu.network.getData.NetworkFeedsRepository;
import com.mediever.softworks.mydstu.network.models.PageFeedsModel;


public class FeedsViewModel extends AndroidViewModel {
    //private LiveData<PageFeedsModel> allFeeds;
    public FeedsViewModel(@NonNull Application application) {
        super(application);
        //allFeeds = NetworkFeedsRepository.getInstance().getFeeds(0,"","");
    }

    public LiveData<PageFeedsModel> getFeeds(Integer page, String type, String date) {
        MutableLiveData<PageFeedsModel> feeds = new MutableLiveData<>();
        NetworkFeedsRepository.getInstance().getFeeds(page,type,date).observeForever(new Observer<PageFeedsModel>() {
            @Override
            public void onChanged(PageFeedsModel pageFeedsModel) {
                feeds.setValue(pageFeedsModel);
            }
        });
        return feeds;
    }
}
