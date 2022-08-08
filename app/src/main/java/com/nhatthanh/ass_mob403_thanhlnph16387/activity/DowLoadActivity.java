package com.nhatthanh.ass_mob403_thanhlnph16387.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.nhatthanh.ass_mob403_thanhlnph16387.databinding.ActivityDowloadBinding;
import com.nhatthanh.ass_mob403_thanhlnph16387.model.Photo;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class DowLoadActivity extends AppCompatActivity {
    private ActivityDowloadBinding binding;
    private Photo photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDowloadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setData();

        binding.btnDl1.setOnClickListener(view -> checkPermission(1));
        binding.btnDl2.setOnClickListener(view -> checkPermission(2));
        binding.btnDl3.setOnClickListener(view -> checkPermission(3));
    }

    private void setData() {
        photo = (Photo) getIntent().getSerializableExtra("photo");
        Picasso.get().load(photo.getUrlN()).into(binding.imgDl);
        if (photo.getWidthC() == null && photo.getHeightC() == null) {
            binding.btnDl1.setVisibility(View.GONE);
        } else {
            binding.btnDl1.setLabelText(photo.getWidthC() + " x " + photo.getHeightC());
        }
        if (photo.getWidthL() == null && photo.getHeightL() == null) {
            binding.btnDl2.setVisibility(View.GONE);
        } else {
            binding.btnDl2.setLabelText(photo.getWidthL() + " x " + photo.getHeightL());
        }
        if (photo.getWidthO() == null && photo.getHeightO() == null) {
            binding.btnDl3.setVisibility(View.GONE);
        } else {
            binding.btnDl3.setLabelText(photo.getWidthO() + " x " + photo.getHeightO());
        }
    }

    private void checkPermission(int resolution) {
        Dexter.withContext(DowLoadActivity.this)
                .withPermissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if (multiplePermissionsReport.areAllPermissionsGranted()) {
                            switch (resolution) {
                                case 1:
                                    dowloadImage(photo.getUrlC());
                                    break;
                                case 2:
                                    dowloadImage(photo.getUrlL());
                                    break;
                                case 3:
                                    dowloadImage(photo.getUrlO());
                                    break;
                            }
                        } else {
                            Toast.makeText(DowLoadActivity.this,
                                    "Please allow all permissions to dowload !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                    }
                }).check();
    }


    private void dowloadImage(String url) {
        try {
            DownloadManager downloadManager;
            downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

            Uri dowloadUri = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(dowloadUri);

            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI
                            | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setTitle("Mesut Ozil image here")
                    .setMimeType("image/jpeg")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, File.separator + "Mesut Ozil image here" + ".jpg");

            downloadManager.enqueue(request);

            Toast.makeText(DowLoadActivity.this, "Dowloading...", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(DowLoadActivity.this,
                    "Dowload Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}