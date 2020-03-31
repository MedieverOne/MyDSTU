package com.mediever.softworks.mydstu.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.mediever.softworks.mydstu.R;
import com.mediever.softworks.mydstu.profile.UserViewModel;

public class RootProfileFragment extends Fragment {
    UserViewModel userViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile_root,container,false);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        NavController navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        if(userViewModel.getSessionId().getValue().equals(" ")) {
            navController.navigate(R.id.action_profile_root_to_login);
        }else {
            navController.navigate(R.id.action_profile_root_to_user);
        }
        return root;
    }
}
