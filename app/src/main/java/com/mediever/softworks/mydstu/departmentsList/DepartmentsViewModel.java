package com.mediever.softworks.mydstu.departmentsList;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.mediever.softworks.mydstu.entities.Department;
import com.mediever.softworks.mydstu.network.getData.NetworkDepartmentsRepository;
import com.mediever.softworks.mydstu.network.models.DepartmentsModel;

import java.util.List;

public class DepartmentsViewModel extends AndroidViewModel {

  //  private DepartmentsRepository departmentsRepository;
    private LiveData<DepartmentsModel> allDepartments;

    public DepartmentsViewModel(@NonNull Application application) {
        super(application);
       // departmentsRepository = new DepartmentsRepository(application);
      //  allDepartments = departmentsRepository.getAllDepartments();
        allDepartments = NetworkDepartmentsRepository.getInstance().getDepartments();
    }

    public LiveData<DepartmentsModel> getAllDepartments() {
        return allDepartments;
    }
}
