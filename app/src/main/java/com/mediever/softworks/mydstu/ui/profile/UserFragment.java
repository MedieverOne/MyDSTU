package com.mediever.softworks.mydstu.ui.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.mediever.softworks.mydstu.MainActivity;
import com.mediever.softworks.mydstu.R;
import com.mediever.softworks.mydstu.entities.User;
import com.mediever.softworks.mydstu.network.NetworkService;
import com.mediever.softworks.mydstu.network.models.ErrorMessage;
import com.mediever.softworks.mydstu.profile.UserViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFragment extends Fragment implements View.OnClickListener{
    private UserViewModel userViewModel;
    private String sessionId;
    ImageButton ibEdit;
    ImageButton ibExit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile_user,container,false);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        sessionId = ((MainActivity)getActivity()).getSessionId();
        userViewModel.getUser(sessionId).observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user != null) {
                    Toast.makeText(getContext(),"Success!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),R.string.connect_to_server_error,Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.ibEdit_user:
                NavController navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
                navController.navigate(R.id.action_profile_user_to_useredit);
                break;
            case R.id.ibExit_user:
                logout(sessionId);
                break;
        }
    }

    void logout(String sessionId) {
        NavController navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        NetworkService.getInstance()
                .getServerApi()
                .logout(sessionId)
                .enqueue(new Callback<ErrorMessage>() {
                    @Override
                    public void onResponse(Call<ErrorMessage> call, Response<ErrorMessage> response) {
                        if(response.code() == 405)
                            showToast("Ошибка выхода",Toast.LENGTH_SHORT);
                        else{
                            showToast("Вы успешно вышли из профиля", Toast.LENGTH_SHORT);
                            ((MainActivity)getActivity()).setSessionId("");
                            navController.popBackStack();
                        }
                    }

                    @Override
                    public void onFailure(Call<ErrorMessage> call, Throwable t) {
                        showToast(getString(R.string.connect_to_server_error), Toast.LENGTH_LONG);
                    }
                });
    }// logout

    private void showToast(String str,int length) {
        Toast toast = Toast.makeText(getContext(),str,length);
        toast.setGravity(Gravity.AXIS_CLIP,0,0);
        toast.show();
    }
}
