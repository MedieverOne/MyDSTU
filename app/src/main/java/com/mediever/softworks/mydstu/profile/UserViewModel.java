package com.mediever.softworks.mydstu.profile;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mediever.softworks.mydstu.entities.User;
import com.mediever.softworks.mydstu.network.getData.NetworkUserRepository;
import com.mediever.softworks.mydstu.network.models.LoginModel;

public class UserViewModel extends AndroidViewModel {

    public UserViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<User> getUser(String sessionId) {
        return NetworkUserRepository.getInstance().getUser(sessionId);
    }

    public LiveData<String> login(LoginModel loginModel, Context context) {
        return NetworkUserRepository.getInstance().login(loginModel, context);
    }
}
