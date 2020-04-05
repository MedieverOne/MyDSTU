package com.mediever.softworks.mydstu.network.getData;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mediever.softworks.mydstu.entities.User;
import com.mediever.softworks.mydstu.network.NetworkService;
import com.mediever.softworks.mydstu.network.models.ErrorMessage;
import com.mediever.softworks.mydstu.network.models.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkUserRepository {
        private static NetworkUserRepository instance;

        public static NetworkUserRepository getInstance() {
            if(instance == null) {
                instance = new NetworkUserRepository();
            }
            return instance;
        }

        public synchronized MutableLiveData<String> login(LoginModel loginModel, Context context) {
            final MutableLiveData<String> sessionId = new MutableLiveData<>();
            NetworkService.getInstance().getServerApi().login(loginModel).enqueue(new Callback<ErrorMessage>() {
                @Override
                public void onResponse(Call<ErrorMessage> call, Response<ErrorMessage> response) {
                    if(response.isSuccessful() && response.body().getAnswer().length() != 0) {
                        sessionId.setValue(response.headers()
                                .values("Set-Cookie").toString()
                                .replace("[", "").replace("]", ""));
                    }else{
                        sessionId.setValue(null);
                        Toast.makeText(context,response.body().getError(),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ErrorMessage> call, Throwable t) {
                    sessionId.setValue(null);
                }
            });
            return sessionId;
        }

        public synchronized MutableLiveData<User> getUser(String sessionId) {
            final MutableLiveData<User> user = new MutableLiveData<>();
            NetworkService.getInstance().getServerApi().getUser(sessionId).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful())
                            user.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        user.setValue(null);
                    }});
            return user;
        }
}
