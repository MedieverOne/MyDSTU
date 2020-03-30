package com.mediever.softworks.mydstu.fullDepartment;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mediever.softworks.mydstu.entities.FullDepartment;
import com.mediever.softworks.mydstu.network.getData.NetworkFullDepartmentRepository;

public class FullDepartmentViewModel extends AndroidViewModel {
    private LiveData<FullDepartment> department;
    public FullDepartmentViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<FullDepartment> getDepartment(String depId) {
        if(department == null) {
            Log.d("HALO","department == null");
            department = NetworkFullDepartmentRepository.getInstance().getDepartment(depId);
        }
        return department;
    }
}
