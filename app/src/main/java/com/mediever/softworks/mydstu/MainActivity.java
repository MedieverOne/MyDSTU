package com.mediever.softworks.mydstu;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mediever.softworks.mydstu.feedList.FeedsRepository;
import com.mediever.softworks.mydstu.network.NetworkService;
import com.mediever.softworks.mydstu.network.models.ErrorMessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Integer totalPages = 0;
    private String sessionId = "";
    BottomNavigationView navView;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalPages = getIntent().getIntExtra("totalPages",0);
        navView = findViewById(R.id.nav_view);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        if(navView.getSelectedItemId() == R.id.navigation_profile)
            navController.navigate(R.id.navigation_profile);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        sessionId = "";
        totalPages = 0;
        FeedsRepository feedsRepository = new FeedsRepository(getApplication());
        feedsRepository.deleteAllFeeds();
        if(!sessionId.equals(""))
            logout();
        super.onDestroy();
    }

    public Integer getTotalPages() {
        return totalPages;
    }
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }

    void logout() {
        NetworkService.getInstance().getServerApi()
                .logout(sessionId).enqueue(new Callback<ErrorMessage>() {
            @Override
            public void onResponse(Call<ErrorMessage> call, Response<ErrorMessage> response) {

            }

            @Override
            public void onFailure(Call<ErrorMessage> call, Throwable t) {

            }
        });
    }
}
