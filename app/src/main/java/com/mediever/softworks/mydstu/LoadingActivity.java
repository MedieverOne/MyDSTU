package com.mediever.softworks.mydstu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.google.gson.Gson;
import com.mediever.softworks.mydstu.network.NetworkService;
import com.mediever.softworks.mydstu.network.getData.NetworkFeedsRepository;
import com.mediever.softworks.mydstu.network.getData.NetworkFullFeedRepository;
import com.mediever.softworks.mydstu.network.models.PageFeedsModel;

import java.io.IOException;

import retrofit2.Response;

public class LoadingActivity extends AppCompatActivity {

    //private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
  //      start(); для тестирования без сервера
        NetworkFeedsRepository.getInstance().getFeeds(0,"","").observeForever(new Observer<PageFeedsModel>() {
            @Override
            public void onChanged(PageFeedsModel pageFeedsModel) {
                if(pageFeedsModel != null && pageFeedsModel.getData().size() != 0) {
                    Log.d("HALO","here = " + pageFeedsModel.getTotalpages());
                    Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                    intent.putExtra("totalPages", pageFeedsModel.getTotalpages());
                    startActivity(intent);
                    finish();
                }else {
                    showToast(getString(R.string.connect_to_server_error),Toast.LENGTH_SHORT);
                    finish();
                }
            }
        });
    }

    private void start() {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //loadAndStart();
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                intent.putExtra("totalPages", 0);
                startActivity(intent);
                //startActivity(new Intent(LoadingActivity.this, MainActivity.class));
                finish();
            }

        }, 5000);
    }
//
//    @SuppressLint("HandlerLeak")
//    void loadAndStart() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Response<PageFeedsModel> Feeds = NetworkService.getInstance().getServerApi().getFeed(0, "", "").execute();
//                    if(Feeds.code() == 503) {
//                        mHandler.sendEmptyMessage(-1);
//                    }else {
//                        Gson gson = new Gson();
//                        String json = gson.toJson(Feeds.body());
//                        Log.d("HALO", "add: onSuccess");
//                        mHandler.sendEmptyMessage(1);
//                    }
//                } catch (IOException e) {
//                    Log.d("HALO", "add: onSuccess" + e.toString());
//                    mHandler.sendEmptyMessage(-1);
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//        mHandler = new android.os.Handler() {
//            public void handleMessage(android.os.Message msg) {
//                if(msg.what == -1) {
//                    showToast(getString(R.string.connect_to_server_error), Toast.LENGTH_LONG);
//                    finish();
//                }else {
//                    Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
//                    intent.putExtra("totalPages",totalPages);
//                    startActivity(new Intent(LoadingActivity.this, MainActivity.class));
//                    finish();
//                }
//            }
//        };
//    }
    private void showToast(String str,int length) {
        Toast toast = Toast.makeText(getApplicationContext(),str,length);
        toast.setGravity(Gravity.AXIS_CLIP,0,0);
        toast.show();
    }
}
