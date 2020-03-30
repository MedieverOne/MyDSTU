package com.mediever.softworks.mydstu.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mediever.softworks.mydstu.R;

public class RegistrationFragment extends Fragment implements View.OnClickListener{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile_registration, container, false);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.regButton_reg:{
                Toast.makeText(getContext(), "Вы успешно зарегистрировались!", Toast.LENGTH_SHORT).show();
                onStop();
                break;
            }
            default: break;
        }
    }
}
