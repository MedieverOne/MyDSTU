package com.mediever.softworks.mydstu.network.getData;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.mediever.softworks.mydstu.entities.FullDepartment;
import com.mediever.softworks.mydstu.network.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkFullDepartmentRepository {
    private static NetworkFullDepartmentRepository instance;
    public static NetworkFullDepartmentRepository getInstance() {
        if(instance == null) {
            instance = new NetworkFullDepartmentRepository();
        }
        return instance;
    }

    public synchronized MutableLiveData<FullDepartment> getDepartment(String id) {
        final MutableLiveData<FullDepartment> department = new MutableLiveData<>();
        NetworkService.getInstance().getServerApi().getDepartmentsItem(id).enqueue(new Callback<FullDepartment>() {
            @Override
            public void onResponse(Call<FullDepartment> call, Response<FullDepartment> response) {
                if(response.isSuccessful())
                    department.setValue(response.body());
            }

            @Override
            public void onFailure(Call<FullDepartment> call, Throwable t) {
                department.setValue(null);
            }
        });
        return department;
    }
}
