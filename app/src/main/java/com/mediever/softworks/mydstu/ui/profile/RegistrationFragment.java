package com.mediever.softworks.mydstu.ui.profile;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mediever.softworks.mydstu.R;
import com.mediever.softworks.mydstu.entities.User;
import com.mediever.softworks.mydstu.network.NetworkService;
import com.mediever.softworks.mydstu.network.models.ErrorMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationFragment extends Fragment implements View.OnClickListener{
    private User user;
    private EditText etName;
    private EditText etSurname;
    private EditText etEmail;
    private EditText etPassword;
    private Context context;
    private static final int MAX_NAME_LENGTH = 12;
    private static final int MIN_NAME_LENGTH = 3;
    private static final int MAX_PASS_LENGTH  = 12;
    private static final int MIN_PASS_LENGTH = 5;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile_registration, container, false);
        user = new User();
        etName     = root.findViewById(R.id.etName_reg);
        etSurname  = root.findViewById(R.id.etSurname_reg);
        etEmail    = root.findViewById(R.id.etEmail_reg);
        etPassword = root.findViewById(R.id.etPassword_reg);
        return root;
    }

    @Override
    public void onClick(View v) {
        registration();
    }

    private void registration() {
        if(check_fields()) {
            user.setName(etName.getText().toString());
            user.setSurname(etSurname.getText().toString());
            user.setEmail(etEmail.getText().toString());
            user.setPassword(etPassword.getText().toString());
            NetworkService.getInstance().getServerApi().registration(user).enqueue(new Callback<ErrorMessage>() {
                @Override
                public void onResponse(Call<ErrorMessage> call, Response<ErrorMessage> response) {
                    try {
                        if(response.isSuccessful() && response.body() != null) {
                            String answer = response.body().getAnswer();
                            String error = response.body().getError();
                            if (answer.length() != 0) {
                                showToast(answer, Toast.LENGTH_SHORT);
                                onStop();
                            } else if (error.length() != 0) {
                                showToast(error, Toast.LENGTH_SHORT);
                            }
                        }
                    }catch(Exception e) {
                        Log.d("onResponse","Exception in registration");
                        showToast(getString(R.string.connect_to_server_error), Toast.LENGTH_SHORT);
                        onStop();
                    }
                }

                @Override
                public void onFailure(Call<ErrorMessage> call, Throwable t) {
                    showToast(getString(R.string.connect_to_server_error), Toast.LENGTH_SHORT);
                }
            });
        }
    }


    private boolean check_fields() {
        return check_name(etName) && check_surName(etSurname) && check_email(etEmail) && check_password(etPassword);
    }


    private boolean check_email(EditText view) {
        String text = view.getText().toString();
        if(text.equals("")) {
            showToast("Введите эл.почту",Toast.LENGTH_SHORT);
            return false;
        }else
            return true;
    }
    private boolean check_name(EditText view) {
        String text = view.getText().toString();
        if(text.equals("")) {
            showToast("Введите имя",Toast.LENGTH_SHORT);
            return false;
        }else if(text.length() >= MAX_NAME_LENGTH) {
            showToast("Имя должно содержать менее " + MAX_NAME_LENGTH + " символов",Toast.LENGTH_SHORT);
            return false;
        }else if(text.length() <= MIN_NAME_LENGTH) {
            showToast("Имя должно содержать более " + MIN_NAME_LENGTH + " символов",Toast.LENGTH_SHORT);
            return false;
        }else
            return true;
    }
    private boolean check_surName(EditText view) {
        String text = view.getText().toString();
        if(text.equals("")) {
            showToast("Введите фамилию",Toast.LENGTH_SHORT);
            return false;
        }else if(text.length() >= MAX_NAME_LENGTH) {
            showToast("Фамилия должна содержать менее " + MAX_NAME_LENGTH + " символов",Toast.LENGTH_SHORT);
            return false;
        }else if(text.length() <= MIN_NAME_LENGTH) {
            showToast("Фамилия должна содержать более " + MIN_NAME_LENGTH + " символов",Toast.LENGTH_SHORT);
            return false;
        }else
            return true;
    }
    private boolean check_password(EditText view) {
        String text = view.getText().toString();
        if(view.getText().toString().equals("")) {
            showToast("Введите пароль",Toast.LENGTH_SHORT);
            return false;
        }else if(text.length() >= MAX_PASS_LENGTH) {
            showToast("Пароль должен содержать менее " + MAX_PASS_LENGTH + " символов",Toast.LENGTH_SHORT);
            return false;
        }else if(text.length() <= MIN_PASS_LENGTH) {
            showToast("Пароль должен содержать более " + MIN_PASS_LENGTH + " символов",Toast.LENGTH_SHORT);
            return false;
        }else
            return true;
    }



    private void showToast(String str, int length) {
        Toast toast = Toast.makeText(getContext(),str,length);
        toast.setGravity(Gravity.AXIS_CLIP,0,0);
        toast.show();
    }

}
