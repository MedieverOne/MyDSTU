package com.mediever.softworks.mydstu.departmentsList;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mediever.softworks.mydstu.entities.Department;

import java.sql.Timestamp;

@Database(entities = {Department.class}, version = 1)
public abstract class DepartmentsDatabase extends RoomDatabase {
    private static DepartmentsDatabase instance;
    public abstract DepartmentsDao departmentsDao();
    private static final String databaseName = "departments_database";

    public static synchronized DepartmentsDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DepartmentsDatabase.class,
                    databaseName)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
