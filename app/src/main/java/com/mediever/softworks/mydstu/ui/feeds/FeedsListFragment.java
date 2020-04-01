package com.mediever.softworks.mydstu.ui.feeds;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mediever.softworks.mydstu.R;
import com.mediever.softworks.mydstu.entities.Feed;
import com.mediever.softworks.mydstu.feedList.FeedListAdapter;
import com.mediever.softworks.mydstu.feedList.FeedsRepository;
import com.mediever.softworks.mydstu.feedList.FeedsViewModel;
import com.mediever.softworks.mydstu.network.models.PageFeedsModel;

import java.util.Calendar;
import java.util.List;

import static android.view.View.GONE;

public class FeedsListFragment extends Fragment implements View.OnClickListener {
    private FeedsViewModel feedsViewModel;
    private RecyclerView feedsRecycler;
    private FeedListAdapter feedListAdapter;

    private FrameLayout progressBar;
    private TextView title;
    private ImageButton buttonCalendar;

    private int page;
    private String date;
    private String type;

    Calendar dateAndTime= Calendar.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_feeds_list, container, false);
        progressBar = root.findViewById(R.id.feedsProgressBar);
        title = root.findViewById(R.id.filter_title_feeds);
        buttonCalendar = root.findViewById(R.id.filter_date_feeds);
        feedsRecycler = root.findViewById(R.id.feedsList);
        feedsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        feedsRecycler.setHasFixedSize(true);
        title.setOnClickListener(this);
        buttonCalendar.setOnClickListener(this);

        page=0;type="";date="";
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        feedListAdapter = new FeedListAdapter(navController);
        feedsRecycler.setAdapter(feedListAdapter);
        feedsViewModel = ViewModelProviders.of(this).get(FeedsViewModel.class);
        feedsViewModel.downloadFeeds();
        getFeeds(page,type,date);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.filter_title_feeds: {
                changeNewsType((TextView)v);
                break;
            }
            case R.id.filter_date_feeds: {
                changeDate();
                break;
            }
            default:
                break;
        }
    }

    private void changeNewsType(TextView view) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        popupMenu.inflate(R.menu.filter_popup_menu);
        view.setCompoundDrawablesWithIntrinsicBounds(0,0,android.R.drawable.arrow_up_float, 0);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                feedListAdapter.clearData();
                progressBar.setVisibility(View.VISIBLE);
                switch(item.getItemId()) {
                    case R.id.all_feeds_filter_menu: {
                        type = "";
                        view.setText(R.string.itemAllNews);
                        getFeeds(page,type,date);
                        return true;
                    }
                    case R.id.news_filter_menu: {
                        type = "news";
                        view.setText(R.string.itemNews);
                        getFeeds(page,type,date);
                        return true;
                    }
                    case R.id.meetings_filter_menu: {
                        type = "meeting";
                        view.setText(R.string.itemMeeting);
                        getFeeds(page,type,date);
                        return true;
                    }
                    case R.id.events_filter_menu: {
                        type = "event";
                        view.setText(R.string.itemEvent);
                        getFeeds(page,type,date);
                        return true;
                    }
                }
                return false;
            }
        });

        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                view.setCompoundDrawablesWithIntrinsicBounds(0,0,android.R.drawable.arrow_down_float, 0);
            }
        });

        popupMenu.show();
    }

    private void changeDate() {
        new DatePickerDialog(getContext(), AlertDialog.THEME_DEVICE_DEFAULT_DARK, dateSetListener, dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }


    // Вообще по-идее должен быть фильтр по дате. API кривое, мой код не лучше. Для презентации сойдет.
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, month);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        }
    };

    private void getFeeds(int page, String type, String date) {
        feedsViewModel.getAllFeeds(type,date).observe(getViewLifecycleOwner(), new Observer<List<Feed>>() {
        @Override
        public void onChanged(List<Feed> feeds) {
            if(feeds.size() != 0) {
                feedListAdapter.setData(feeds);
                progressBar.setVisibility(GONE);
            }
        }});
    }
}
