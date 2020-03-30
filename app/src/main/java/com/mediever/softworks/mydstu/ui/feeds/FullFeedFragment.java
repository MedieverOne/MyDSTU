package com.mediever.softworks.mydstu.ui.feeds;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.mediever.softworks.mydstu.R;
import com.mediever.softworks.mydstu.entities.Feed;
import com.mediever.softworks.mydstu.entities.FeedItem;
import com.mediever.softworks.mydstu.fullFeed.FullFeedViewModel;

public class FullFeedFragment extends Fragment {
    private String feedId;
    private FullFeedViewModel fullFeedViewModel;
    private Feed feed;
    private WebView webView;
    private RatingBar ratingBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_full_feed, container,false);
        webView = root.findViewById(R.id.feedWebView);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setBackgroundColor(Color.TRANSPARENT);
        ratingBar = root.findViewById(R.id.feedRatingBar);

        feedId = getArguments().getString("feedId", "failure");
        fullFeedViewModel = ViewModelProviders.of(this).get(FullFeedViewModel.class);
        fullFeedViewModel.getFeed("",feedId).observe(getViewLifecycleOwner(), new Observer<FeedItem>() {
            @Override
            public void onChanged(FeedItem feed) {
                webView.loadData("<style>img{display: inline;height: auto;max-width: 100%;}</style>" + feed.getText(), "text/html", "utf-8");
                if(feed.getRating() != 0) {
                    ratingBar.setRating(feed.getRating());
                    ratingBar.setIsIndicator(true);
                }
            }
        });

        return root;
    }
}
