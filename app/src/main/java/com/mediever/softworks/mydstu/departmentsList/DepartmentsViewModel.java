package com.mediever.softworks.mydstu.departmentsList;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mediever.softworks.mydstu.entities.Department;

import java.util.List;

public class DepartmentsViewModel extends AndroidViewModel {

    private DepartmentsRepository departmentsRepository;
    private LiveData<List<Department>> allDepartments;

    public DepartmentsViewModel(@NonNull Application application) {
        super(application);
        departmentsRepository = new DepartmentsRepository(application);
        allDepartments = departmentsRepository.getAllDepartments();
    }

    public LiveData<List<Department>> getAllDepartments() {
        return allDepartments;
    }
}
