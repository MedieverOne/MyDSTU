package com.mediever.softworks.mydstu.ui.feeds;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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

import java.util.List;

import static android.view.View.GONE;

public class FeedsListFragment extends Fragment {
    private FeedsViewModel feedsViewModel;
    private RecyclerView feedsRecycler;
    private FrameLayout progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_feeds_list, container, false);
        progressBar = root.findViewById(R.id.feedsProgressBar);
        feedsRecycler = root.findViewById(R.id.feedsList);
        feedsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        feedsRecycler.setHasFixedSize(true);

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        final FeedListAdapter feedListAdapter = new FeedListAdapter(navController);
        feedsRecycler.setAdapter(feedListAdapter);

        feedsViewModel = ViewModelProviders.of(this).get(FeedsViewModel.class);
        feedsViewModel.getFeeds(0,"","").observe(getViewLifecycleOwner(), new Observer<PageFeedsModel>() {
            @Override
            public void onChanged(PageFeedsModel pageFeedsModel) {
                feedListAdapter.setData(pageFeedsModel.getData());
                progressBar.setVisibility(GONE);
            }
        });
        return root;
    }
}
