package com.azhar.githubusers.networking;

import com.azhar.githubusers.model.follow.ModelFollow;
import com.azhar.githubusers.model.search.ModelSearch;
import com.azhar.githubusers.model.user.ModelUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Azhar Rivaldi on 18-05-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

public interface ApiInterface {

    @GET("users/{username}")
    Call<ModelUser> detailUser(@Path("username") String username);

    @GET("/search/users")
    Call<ModelSearch> searchUser(@Header("Authorization") String authorization,
                                 @Query("q") String username);

    @GET("users/{username}/followers")
    Call<List<ModelFollow>> followersUser(@Header("Authorization") String authorization,
                                               @Path("username") String username);

    @GET("users/{username}/following")
    Call<List<ModelFollow>> followingUser(@Header("Authorization") String authorization,
                                          @Path("username") String username);

}
