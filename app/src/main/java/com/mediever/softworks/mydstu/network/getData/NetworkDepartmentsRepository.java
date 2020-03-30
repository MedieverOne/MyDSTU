package com.mediever.softworks.mydstu.network.getData;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mediever.softworks.mydstu.network.NetworkService;
import com.mediever.softworks.mydstu.network.models.DepartmentsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkDepartmentsRepository {
    public static NetworkDepartmentsRepository instance;

    public static NetworkDepartmentsRepository getInstance() {
        if(instance == null) {
            instance = new NetworkDepartmentsRepository();
        }
        return instance;
    }

    public synchronized MutableLiveData<DepartmentsModel> getDepartments() {
        final MutableLiveData<DepartmentsModel> departmentsData = new MutableLiveData<>();
        NetworkService.getInstance().getServerApi().getDepartaments().enqueue(new Callback<DepartmentsModel>() {
            @Override
            public void onResponse(Call<DepartmentsModel> call, Response<DepartmentsModel> response) {
                if(response.isSuccessful())
                    departmentsData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DepartmentsModel> call, Throwable t) {
                departmentsData.setValue(null);
            }
        });
        return departmentsData;
    }

}
