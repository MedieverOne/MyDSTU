package com.mediever.softworks.mydstu.ui.profile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.mediever.softworks.mydstu.MainActivity;
import com.mediever.softworks.mydstu.R;
import com.mediever.softworks.mydstu.network.models.LoginModel;
import com.mediever.softworks.mydstu.profile.UserViewModel;

import java.util.zip.Inflater;

public class LoginFragment extends Fragment implements View.OnClickListener {
    EditText etEmail;
    EditText etPassword;
    Button logButton;
    Button regButton;
    UserViewModel userViewModel;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile_login,container,false);
        logButton = root.findViewById(R.id.logButton_login);
        regButton = root.findViewById(R.id.regButton_login);
        etEmail = root.findViewById(R.id.etLogin_login);
        etPassword = root.findViewById(R.id.etPassword_login);
        logButton.setOnClickListener(this);
        regButton.setOnClickListener(this);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);

        if(!((MainActivity)getActivity()).getSessionId().equals("")) {
            navController.popBackStack();
        }
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

    @SuppressLint("ResourceType")
    private void logButtonClicked() {
        LoginModel loginModel = new LoginModel();
        loginModel.setEmail(etEmail.getText().toString());
        loginModel.setPassword(etPassword.getText().toString());
        userViewModel.login(loginModel,getContext()).observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s!=null) {
                    ((MainActivity) getActivity()).setSessionId(s);
                    Toast.makeText(getContext(),R.string.logSuccess,Toast.LENGTH_SHORT).show();
                    navController.popBackStack();
                }
            }
        });
    }

    void regButtonClicked() {
        NavController navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        navController.navigate(R.id.action_profile_login_to_registration);
    }

}
