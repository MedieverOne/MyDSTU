package com.mediever.softworks.mydstu.network;

import com.mediever.softworks.mydstu.entities.Feed;
import com.mediever.softworks.mydstu.entities.FeedItem;
import com.mediever.softworks.mydstu.entities.FullDepartment;
import com.mediever.softworks.mydstu.network.models.DepartmentsModel;
import com.mediever.softworks.mydstu.network.models.PageFeedsModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ServerAPI {
    @GET("getFeed")
    Call<PageFeedsModel> getFeed(@Query("page") Integer page, @Query("type") String type, @Query("date") String date);
    @GET("getPic")
    Call<ResponseBody> getPic(@Query("picname") String imageUrl);
    @GET("getDepartments")
    Call<DepartmentsModel> getDepartaments();
    @GET("getFeedItem")
    Call<FeedItem> getFeedItem(@Header("Cookie") String sessionId, @Query("id") String id);
    @GET("getDepartmentsItem")
    Call<FullDepartment> getDepartmentsItem(@Query("id") String id);

}
