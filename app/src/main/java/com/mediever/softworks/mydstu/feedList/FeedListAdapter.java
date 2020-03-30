package com.mediever.softworks.mydstu.feedList;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.mediever.softworks.mydstu.R;
import com.mediever.softworks.mydstu.entities.Feed;
import com.mediever.softworks.mydstu.network.NetworkService;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.FeedsViewHolder> {
    private List<Feed> mData = new ArrayList<>();
    private NavController navController;
    private static OnItemClickListener mClickListener;
    public FeedListAdapter(NavController navController) {
        this.navController = navController;
    }
    @NonNull
    @Override
    public FeedsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feeds_list_item, parent, false);
        return new FeedsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedsViewHolder holder, int position) {
        Feed feed = mData.get(position);
        holder.tvTitle.setText(feed.getTitle());
        holder.tvDate.setText(feed.getDate());
        Picasso.get().load(NetworkService.BASE_URL + "getPic?picname="+feed.getPreview().getUrl()).into(holder.ivPreview);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<Feed> feeds) {
        this.mData = feeds;
        notifyDataSetChanged();
    }

    public class FeedsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle;
        private TextView tvDate;
        private ImageView ivPreview;
        public FeedsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.feedsItemTitle);
            ivPreview = itemView.findViewById(R.id.feedsItemPreview);
            tvDate    = itemView.findViewById(R.id.feedsItemDate);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null)
                mClickListener.onItemClick(getAdapterPosition(),v);

            Bundle args = new Bundle();
            args.putString("feedId",mData.get(getAdapterPosition()).getId());
            navController.navigate(R.id.action_to_feed, args);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position, View v);
    }
}
