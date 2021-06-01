package com.azhar.githubusers.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.azhar.githubusers.R;
import com.azhar.githubusers.model.user.ModelUser;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Azhar Rivaldi on 23-05-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private ArrayList<ModelUser> modelUserArrayList = new ArrayList<>();

    public void setFavoriteUserList(ArrayList<ModelUser> data) {
        this.modelUserArrayList.clear();
        this.modelUserArrayList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public FavoriteAdapter.FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_data, parent, false);
        return new FavoriteAdapter.FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        ModelUser item = modelUserArrayList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(item.getAvatarUrl())
                .into(holder.imageUser);

        holder.tvUsername.setText(item.getLogin());
        holder.tvUrl.setText(item.getHtmlUrl());
        /*holder.cvListUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.DETAIL_USER, modelFollowersArrayList.get(position));
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return modelUserArrayList.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername, tvUrl;
        ImageView imageUser;

        public FavoriteViewHolder(View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvUrl = itemView.findViewById(R.id.tvUrl);
            imageUser = itemView.findViewById(R.id.imageUser);
        }
    }

}
