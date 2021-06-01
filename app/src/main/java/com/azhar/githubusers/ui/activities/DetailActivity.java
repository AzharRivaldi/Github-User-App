package com.azhar.githubusers.ui.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.azhar.githubusers.R;
import com.azhar.githubusers.adapter.ViewPagerAdapter;
import com.azhar.githubusers.database.DatabaseContract;
import com.azhar.githubusers.database.DatabaseHelper;
import com.azhar.githubusers.database.FavoriteHelper;
import com.azhar.githubusers.model.search.ModelSearchData;
import com.azhar.githubusers.model.user.ModelUser;
import com.azhar.githubusers.viewmodel.UserViewModel;
import com.bumptech.glide.Glide;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import static com.azhar.githubusers.database.DatabaseContract.FavoriteColoumn.TABLE_NAME;

public class DetailActivity extends AppCompatActivity {

    public static final String DETAIL_USER = "DETAIL_USER";
    private FavoriteHelper favoriteHelper;
    ArrayList<ModelUser> modelUserArrayList = new ArrayList<>();
    UserViewModel userViewModel;
    ModelSearchData modelSearchData;
    String strUsername;
    ImageView imageUser;
    TextView tvUsername, tvBio, tvFollowers, tvFollowing, tvRepository;
    TabLayout tabsLayout;
    Toolbar toolbar;
    ViewPager viewPager;
    MaterialFavoriteButton imageFavorite;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.toolbar);
        imageUser = findViewById(R.id.imageUser);
        tvUsername = findViewById(R.id.tvUsername);
        tvBio = findViewById(R.id.tvBio);
        tvFollowers = findViewById(R.id.tvFollowers);
        tvFollowing = findViewById(R.id.tvFollowing);
        tvRepository = findViewById(R.id.tvRepository);
        tabsLayout = findViewById(R.id.tabsLayout);
        viewPager = findViewById(R.id.viewPager);
        imageFavorite = findViewById(R.id.imageFavorite);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);

        toolbar.setTitle(null);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        favoriteHelper = FavoriteHelper.getFavoriteHelper(getApplicationContext());
        favoriteHelper.open();

        modelSearchData = getIntent().getBundle(DETAIL_USER);
        if (modelSearchData != null) {
            strUsername = modelSearchData.getLogin();
        }

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), modelSearchData));
        viewPager.setOffscreenPageLimit(2);
        tabsLayout.setupWithViewPager(viewPager);

        //method set viewmodel
        userViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);
        userViewModel.setUserDetail(strUsername);
        userViewModel.getUserList().observe(this, modelUser -> {
            Glide.with(getApplicationContext())
                    .load(modelUser.getAvatarUrl())
                    .into(imageUser);

            collapsingToolbarLayout.setTitle(modelUser.getName());
            tvUsername.setText(modelUser.getLogin() + " \u2022 " + modelUser.getLocation());
            tvBio.setText(modelUser.getBio());
            tvFollowers.setText(modelUser.getFollowers());
            tvFollowing.setText(modelUser.getFollowing());
            tvRepository.setText(modelUser.getPublicRepos());

            //method set favorite or unfavorite
            if (FavoriteExist(strUsername)) {
                imageFavorite.setFavorite(true);
                imageFavorite.setOnFavoriteChangeListener(
                        (buttonView, favorite) -> {
                            if (favorite) {
                                modelUserArrayList = favoriteHelper.getAllFavorites();
                                favoriteHelper.favoriteInsert(modelUser);
                                Toast.makeText(getApplicationContext(), "Ditambahkan Favorite", Toast.LENGTH_SHORT).show();
                            } else {
                                modelUserArrayList = favoriteHelper.getAllFavorites();
                                favoriteHelper.favoriteDelete(strUsername);
                                Toast.makeText(getApplicationContext(), "Dihapus Favorite", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                imageFavorite.setOnFavoriteChangeListener(
                        (buttonView, favorite) -> {
                            if (favorite) {
                                modelUserArrayList = favoriteHelper.getAllFavorites();
                                favoriteHelper.favoriteInsert(modelUser);
                                Toast.makeText(getApplicationContext(), "Ditambahkan Favorite", Toast.LENGTH_SHORT).show();
                            } else {
                                modelUserArrayList = favoriteHelper.getAllFavorites();
                                favoriteHelper.favoriteDelete(strUsername);
                                Toast.makeText(getApplicationContext(), "Dihapus Favorite", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    //method check data if exist
    public boolean FavoriteExist(String item) {
        String pilih = DatabaseContract.FavoriteColoumn.TITLE + " =?";
        String[] pilihArg = {item};
        String limit = "1";
        favoriteHelper = new FavoriteHelper(this);
        favoriteHelper.open();
        DatabaseHelper dataBaseHelper = new DatabaseHelper(DetailActivity.this);
        SQLiteDatabase database = dataBaseHelper.getWritableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, pilih, pilihArg, null, null, null, limit);
        boolean exists;
        exists = (cursor.getCount() > 0);
        cursor.close();

        return exists;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}