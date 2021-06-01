package com.azhar.githubusers.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.azhar.githubusers.model.search.ModelSearchData;
import com.azhar.githubusers.ui.fragment.FragmentFollowers;
import com.azhar.githubusers.ui.fragment.FragmentFollowing;

/**
 * Created by Azhar Rivaldi on 19-05-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    ModelSearchData modelSearchData;

    public ViewPagerAdapter(FragmentManager fragmentManager, ModelSearchData modelSearchData) {
        super(fragmentManager);
        this.modelSearchData = modelSearchData;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("modelSearchData", modelSearchData);
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentFollowers();
                fragment.setArguments(bundle);
                break;
            case 1:
                fragment = new FragmentFollowing();
                fragment.setArguments(bundle);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Followers";
                break;
            case 1:
                title = "Following";
                break;
        }
        return title;
    }

}
