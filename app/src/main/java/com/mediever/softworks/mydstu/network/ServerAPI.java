package com.mediever.softworks.mydstu.network;



import com.mediever.softworks.mydstu.entities.Feed;
import com.mediever.softworks.mydstu.entities.FeedItem;
import com.mediever.softworks.mydstu.entities.FullDepartment;
import com.mediever.softworks.mydstu.entities.Rating;
import com.mediever.softworks.mydstu.entities.User;
import com.mediever.softworks.mydstu.network.models.DepartmentsModel;
import com.mediever.softworks.mydstu.network.models.ErrorMessage;
import com.mediever.softworks.mydstu.network.models.LoginModel;
import com.mediever.softworks.mydstu.network.models.PageFeedsModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
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
    @POST("login")
    Call<ErrorMessage> login(@Body LoginModel loginModel);
    @POST("registration")
    Call<ErrorMessage> registration(@Body User user);
    @GET("getUser")
    Call<User> getUser(@Header("Cookie") String sessionid);
    @POST("logout")
    Call<ErrorMessage> logout(@Header("Cookie") String sessionid);
    @POST("editUser")
    Call<ErrorMessage> editUser(@Header("Cookie") String sessionid, @Body User user);
    @POST("setFeedRating")
    Call<ErrorMessage> setFeedRating(@Header("Cookie") String sessionId, @Body Rating rating);
}
