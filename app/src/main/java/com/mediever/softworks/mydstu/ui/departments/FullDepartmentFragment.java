package com.mediever.softworks.mydstu.ui.departments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.mediever.softworks.mydstu.R;
import com.mediever.softworks.mydstu.entities.FullDepartment;
import com.mediever.softworks.mydstu.fullDepartment.FullDepartmentViewModel;

public class FullDepartmentFragment extends Fragment {
    FullDepartmentViewModel departmentViewModel;
    String depId;
    WebView webView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_full_department, container, false);
        webView = root.findViewById(R.id.depItemWebView);
        webView.setBackgroundColor(Color.TRANSPARENT);
        departmentViewModel = ViewModelProviders.of(this).get(FullDepartmentViewModel.class);
        depId = getArguments().getString("depId","failure");
        departmentViewModel.getDepartment(depId).observe(getViewLifecycleOwner(), new Observer<FullDepartment>() {
            @Override
            public void onChanged(FullDepartment fullDepartment) {
                Log.d("HALO", "Content has been initialized");
                Log.d("HALO","department != null + " + fullDepartment.getTitle());
                webView.loadData("<style>img{display: inline;height: auto;max-width: 100%;}</style>" + fullDepartment.getText(),"text/html", "utf-8");
            }
        });
        return root;
    }
}
