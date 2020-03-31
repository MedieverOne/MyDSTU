package com.mediever.softworks.mydstu.ui.departments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mediever.softworks.mydstu.R;
import com.mediever.softworks.mydstu.departmentsList.DepartmentsAdapter;
import com.mediever.softworks.mydstu.departmentsList.DepartmentsViewModel;
import com.mediever.softworks.mydstu.entities.Department;
import com.mediever.softworks.mydstu.network.models.DepartmentsModel;

import java.util.List;

public class DepartmentsListFragment extends Fragment {
    DepartmentsViewModel departmentsViewModel;
    private RecyclerView departmentsRecycler;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_departments_list,container,false);
        departmentsRecycler = root.findViewById(R.id.departmentsList);
        progressBar = root.findViewById(R.id.pbEmptyDepartments);
        departmentsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        departmentsRecycler.setHasFixedSize(true);

        final DepartmentsAdapter departmentsAdapter = new DepartmentsAdapter(Navigation.findNavController(getActivity(), R.id.nav_host_fragment));
        departmentsRecycler.setAdapter(departmentsAdapter);

        departmentsViewModel = ViewModelProviders.of(this).get(DepartmentsViewModel.class);
        departmentsViewModel.getAllDepartments().observe(getViewLifecycleOwner(), new Observer<DepartmentsModel>() {
            @Override
            public void onChanged(DepartmentsModel departments) {
                    departmentsAdapter.setData(departments.getList());
                    progressBar.setVisibility(View.GONE);
            }
        });

        return root;
    }
}
