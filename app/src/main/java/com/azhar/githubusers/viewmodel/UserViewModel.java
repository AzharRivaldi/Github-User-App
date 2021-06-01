package com.azhar.githubusers.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.azhar.githubusers.model.follow.ModelFollow;
import com.azhar.githubusers.model.search.ModelSearch;
import com.azhar.githubusers.model.search.ModelSearchData;
import com.azhar.githubusers.model.user.ModelUser;
import com.azhar.githubusers.networking.ApiClient;
import com.azhar.githubusers.networking.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Azhar Rivaldi on 19-05-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

public class UserViewModel extends ViewModel {

    private MutableLiveData<ArrayList<ModelSearchData>> modelSearchMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ModelFollow>> modelFollowersMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ModelFollow>> modelFollowingMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ModelUser> modelUserMutableLiveData = new MutableLiveData<>();
    public static String strApiKey = "mau API KEY? Tonton video tutorialnya ya^^";

    //method search user
    public void setSearchUser(String query) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ModelSearch> call = apiService.searchUser(strApiKey, query);
        call.enqueue(new Callback<ModelSearch>() {
            @Override
            public void onResponse(Call<ModelSearch> call, Response<ModelSearch> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    ArrayList<ModelSearchData> items = new ArrayList<>(response.body().getModelSearchData());
                    modelSearchMutableLiveData.setValue(items);
                }
            }

            @Override
            public void onFailure(Call<ModelSearch> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    //method view detail user
    public void setUserDetail(String strUsername) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ModelUser> call = apiService.detailUser(strUsername);
        call.enqueue(new Callback<ModelUser>() {

            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    modelUserMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    //method get followers
    public void setFollowersUser(String strUsername) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<ModelFollow>> call = apiService.followersUser(strApiKey, strUsername);
        call.enqueue(new Callback<ArrayList<ModelFollow>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelFollow>> call, Response<ArrayList<ModelFollow>> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    modelFollowersMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ModelFollow>> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    //method get following
    public void setFollowingUser(String strUsername) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<ModelFollow>> call = apiService.followingUser(strApiKey, strUsername);
        call.enqueue(new Callback<ArrayList<ModelFollow>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelFollow>> call, Response<ArrayList<ModelFollow>> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    modelFollowingMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ModelFollow>> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    public LiveData<ArrayList<ModelSearchData>> getResultList() {
        return modelSearchMutableLiveData;
    }

    public LiveData<ModelUser> getUserList() {
        return modelUserMutableLiveData;
    }

    public LiveData<ArrayList<ModelFollow>> getFollowersUser() {
        return modelFollowersMutableLiveData;
    }

    public LiveData<ArrayList<ModelFollow>> getFollowingUser() {
        return modelFollowingMutableLiveData;
    }

}