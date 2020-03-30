package com.mediever.softworks.mydstu.departmentsList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mediever.softworks.mydstu.R;
import com.mediever.softworks.mydstu.entities.Department;
import com.mediever.softworks.mydstu.feedList.FeedListAdapter;
import com.mediever.softworks.mydstu.network.NetworkService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DepartmentsAdapter extends RecyclerView.Adapter<DepartmentsAdapter.DepartmentsHolder> {

    private List<Department> mData = new ArrayList<>();
    private NavController navController;
    private static FeedListAdapter.OnItemClickListener mClickListener;

    public DepartmentsAdapter(NavController navController) {
        this.navController = navController;
    }

    @NonNull
    @Override
    public DepartmentsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.departments_list_item,parent, false);
        return new DepartmentsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentsHolder holder, int position) {
        Department department = mData.get(position);
        holder.title.setText(department.getTitle());
        Picasso.get().load(NetworkService.BASE_URL + "getPic?picname=" + department.getLogo()).into(holder.logo);
    }

    public void setData(List<Department> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class DepartmentsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView logo;
        private TextView title;
        public DepartmentsHolder(@NonNull View itemView) {
            super(itemView);
            logo = itemView.findViewById(R.id.depLogo);
            title = itemView.findViewById(R.id.depTitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null)
                mClickListener.onItemClick(getAdapterPosition(),v);

            Bundle args = new Bundle();
            args.putString("depId",mData.get(getAdapterPosition()).getId());
            navController.navigate(R.id.action_departments_list_to_department, args);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View v);
    }
}
