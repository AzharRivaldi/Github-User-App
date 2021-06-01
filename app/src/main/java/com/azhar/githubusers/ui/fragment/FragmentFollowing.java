package com.azhar.githubusers.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.githubusers.R;
import com.azhar.githubusers.adapter.FollowAdapter;
import com.azhar.githubusers.model.search.ModelSearchData;
import com.azhar.githubusers.viewmodel.UserViewModel;

public class FragmentFollowing extends Fragment {

    ModelSearchData modelSearchData;
    UserViewModel followingViewModel;
    FollowAdapter followingAdapter;
    RecyclerView rvListFollowing;
    ConstraintLayout layoutEmpty;
    ProgressDialog progressDialog;
    String strUsername;

    public FragmentFollowing() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_following, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Mohon Tunggu...");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data");

        rvListFollowing = view.findViewById(R.id.rvListFollowing);
        layoutEmpty = view.findViewById(R.id.layoutEmpty);

        modelSearchData = this.getArguments().getBundle("modelSearchData");
        if (modelSearchData != null) {
            strUsername = modelSearchData.getLogin();
        }

        followingAdapter = new FollowAdapter(getContext());
        rvListFollowing.setLayoutManager(new LinearLayoutManager(getContext()));
        rvListFollowing.setAdapter(followingAdapter);
        rvListFollowing.setHasFixedSize(true);

        //method set viewmodel
        followingViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);
        followingViewModel.setFollowingUser(strUsername);
        progressDialog.show();
        followingViewModel.getFollowingUser().observe(getViewLifecycleOwner(), modelFollowing -> {
            if (modelFollowing.size() != 0) {
                layoutEmpty.setVisibility(View.GONE);
                followingAdapter.setFollowList(modelFollowing);
            } else {
                layoutEmpty.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Following Tidak Ditemukan!", Toast.LENGTH_SHORT).show();
            }
            progressDialog.dismiss();
        });

        return view;
    }

}