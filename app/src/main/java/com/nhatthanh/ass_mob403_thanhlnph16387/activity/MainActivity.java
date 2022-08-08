package com.nhatthanh.ass_mob403_thanhlnph16387.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nhatthanh.ass_mob403_thanhlnph16387.R;
import com.nhatthanh.ass_mob403_thanhlnph16387.adapter.PaginationScrollListener;
import com.nhatthanh.ass_mob403_thanhlnph16387.adapter.PhotoAdapter;
import com.nhatthanh.ass_mob403_thanhlnph16387.api.APIService;
import com.nhatthanh.ass_mob403_thanhlnph16387.databinding.ActivityMainBinding;
import com.nhatthanh.ass_mob403_thanhlnph16387.model.FPhoto;
import com.nhatthanh.ass_mob403_thanhlnph16387.model.Photo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, View.OnClickListener {
    private ActivityMainBinding binding;
    private List<Photo> list = new ArrayList<>();
    List<Photo> photoList = new ArrayList<>();
    private PhotoAdapter adapter;
    private boolean isLoading;
    private boolean isLastPage;
    private final int totalPage = 4;
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.popMenu.setOnClickListener(this);
        binding.rvData.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        binding.rvData.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        binding.rvData.setLayoutManager(staggeredGridLayoutManager);

        APIService.API_SERVICE.getListPhoto(1,10)
                .enqueue(new Callback<FPhoto>() {
                    @Override
                    public void onResponse(@NonNull Call<FPhoto> call, @NonNull Response<FPhoto> response) {
                        FPhoto fPhoto = response.body();
                        if (fPhoto != null) {
                            list = fPhoto.getPhotos().getPhoto();
                            binding.rvData.addOnScrollListener(new PaginationScrollListener(staggeredGridLayoutManager) {
                                @Override
                                public void loadMoreItem() {

                                }

                                @Override
                                public boolean isLoading() {
                                    return isLoading;
                                }

                                @Override
                                public boolean isLastPage() {
                                    return isLastPage;
                                }
                            });
                            adapter = new PhotoAdapter(list, photo -> {
                                Intent intent = new Intent(MainActivity.this, DowLoadActivity.class);
                                intent.putExtra("photo", photo);
                                startActivity(intent);
                            });
                            binding.rvData.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<FPhoto> call, @NonNull Throwable t) {
                        Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.about) {
            startActivity(new Intent(this, AboutActivity.class));
        } else {
            startActivity(new Intent(this, GroupActivity.class));
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.item);
        popupMenu.show();
    }
}