package com.azhar.githubusers.model.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Azhar Rivaldi on 18-05-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

public class ModelSearch {

    @SerializedName("items")
    private List<ModelSearchData> modelSearchData;

    public List<ModelSearchData> getModelSearchData() {
        return modelSearchData;
    }

    public void setModelSearchData(List<ModelSearchData> modelSearchData) {
        this.modelSearchData = modelSearchData;
    }

}