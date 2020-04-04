package com.mediever.softworks.mydstu.network.getData;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.mediever.softworks.mydstu.entities.FullDepartment;
import com.mediever.softworks.mydstu.network.NetworkService;
import com.mediever.softworks.mydstu.network.models.PageFeedsModel;

import java.util.AbstractMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkFeedsRepository {
    private static NetworkFeedsRepository instance;
    public static NetworkFeedsRepository getInstance() {
        if(instance == null) {
            instance = new NetworkFeedsRepository();
        }
        return instance;
    }


    public synchronized MutableLiveData<PageFeedsModel> getFeeds(Integer page, String type, String date) {
        final MutableLiveData<PageFeedsModel> feedsData = new MutableLiveData<>();
        NetworkService.getInstance().getServerApi().getFeed(page,type,date)
                .enqueue(new Callback<PageFeedsModel>() {
                    @Override
                    public void onResponse(Call<PageFeedsModel> call, Response<PageFeedsModel> response) {
                        if(response.isSuccessful()) {
                            feedsData.setValue(response.body());
                        }else{
                            feedsData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<PageFeedsModel> call, Throwable t) {
                        feedsData.setValue(null);
                    }
                });
        return feedsData;
    }
}
