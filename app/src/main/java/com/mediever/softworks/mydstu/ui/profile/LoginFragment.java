package com.mediever.softworks.mydstu.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.mediever.softworks.mydstu.R;

public class LoginFragment extends Fragment implements View.OnClickListener {
    Button logButton;
    Button regButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile_login,container,false);
        logButton = root.findViewById(R.id.logButton_login);
        regButton = root.findViewById(R.id.regButton_login);
        logButton.setOnClickListener(this);
        regButton.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logButton_login: {
                logButtonClicked();
                break;
            }
            case R.id.regButton_login: {
                regButtonClicked();
                break;
            }
            default:
                break;
        }
    } // onClicked

    void logButtonClicked() {
        NavController navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        navController.navigate(R.id.action_profile_login_to_user);
    }

    void regButtonClicked() {
        NavController navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        navController.navigate(R.id.action_profile_login_to_registration);
    }

}
