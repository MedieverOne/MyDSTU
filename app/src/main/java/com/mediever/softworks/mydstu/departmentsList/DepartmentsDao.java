package com.mediever.softworks.mydstu.departmentsList;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mediever.softworks.mydstu.entities.Department;

import java.util.List;

@Dao
public interface DepartmentsDao {
    @Insert
    void insert(Department department);
    @Query("SELECT * FROM departments_table")
    LiveData<List<Department>> getAllDepartments();
}
