package com.mediever.softworks.mydstu.network.getData;

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
        private static MutableLiveData<User> user;
        private static MutableLiveData<String> sessionId;
        public static boolean loginCheck = false;

        private NetworkUserRepository() {
            user = new MutableLiveData<>();
            sessionId = new MutableLiveData<>();
            sessionId.setValue(" ");
        }

        public static NetworkUserRepository getInstance() {
            if(instance == null) {
                instance = new NetworkUserRepository();
            }
            return instance;
        }

        public synchronized void login(LoginModel loginModel) {
            NetworkService.getInstance().getServerApi().login(loginModel).enqueue(new Callback<ErrorMessage>() {
                @Override
                public void onResponse(Call<ErrorMessage> call, Response<ErrorMessage> response) {
                    if(response.isSuccessful() && response.body().getAnswer().length() != 0) {
                        sessionId.setValue(response.headers()
                                .values("Set-Cookie").toString()
                                .replace("[", "").replace("]", ""));
                        loginCheck = true;
                    }
                }

                @Override
                public void onFailure(Call<ErrorMessage> call, Throwable t) {

                }
            });
        }

        public synchronized MutableLiveData<User> getUser() {
            final MutableLiveData<User> user = new MutableLiveData<>();
            if(loginCheck) {
                NetworkService.getInstance().getServerApi().getUser(sessionId.getValue()).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()) {
                            user.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
            return user;
        }

        public synchronized LiveData<String> getSessionId() {
            return sessionId;
        }

}
