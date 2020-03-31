package com.mediever.softworks.mydstu.profile;

import android.app.Application;

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

    public LiveData<User> getUser() {
        return NetworkUserRepository.getInstance().getUser();
    }

    public LiveData<String> getSessionId() {
        return NetworkUserRepository.getInstance().getSessionId();
    }

    public void login(LoginModel loginModel) {
        NetworkUserRepository.getInstance().login(loginModel);
    }
}
