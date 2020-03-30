package com.mediever.softworks.mydstu.departmentsList;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.mediever.softworks.mydstu.entities.Department;
import com.mediever.softworks.mydstu.network.getData.NetworkDepartmentsRepository;
import com.mediever.softworks.mydstu.network.getData.NetworkFeedsRepository;
import com.mediever.softworks.mydstu.network.models.DepartmentsModel;

import java.util.List;

public class DepartmentsRepository {
    private DepartmentsDao departmentsDao;
    private LiveData<List<Department>> allDepartments;

    public DepartmentsRepository(Application application) {
        DepartmentsDatabase departmentsDatabase = DepartmentsDatabase.getInstance(application);
        departmentsDao = departmentsDatabase.departmentsDao();
        allDepartments = departmentsDao.getAllDepartments();
    }

    public LiveData<List<Department>> getAllDepartments() {
        if(allDepartments == null) {
            NetworkDepartmentsRepository networkDepartmentsRepository = NetworkDepartmentsRepository.getInstance();
            networkDepartmentsRepository.getDepartments().observeForever(new Observer<DepartmentsModel>() {
                @Override
                public void onChanged(DepartmentsModel departmentsModel) {
                    for(int i = 0; i < departmentsModel.getList().size(); ++i)
                        insert(departmentsModel.getList().get(i));
                }
            });
        }
        return allDepartments;
    }

    public void insert(Department department) {
        new InsertDepartmentAsyncTask(departmentsDao).execute(department);
    }

    private static class InsertDepartmentAsyncTask extends AsyncTask<Department, Void, Void> {
        DepartmentsDao departmentsDao;

        public InsertDepartmentAsyncTask(DepartmentsDao departmentsDao) {
            this.departmentsDao = departmentsDao;
        }

        @Override
        protected Void doInBackground(Department... departments) {
            departmentsDao.insert(departments[0]);
            return null;
        }
    }
}
