package com.nhatthanh.ass_mob403_thanhlnph16387.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhatthanh.ass_mob403_thanhlnph16387.R;
import com.nhatthanh.ass_mob403_thanhlnph16387.activity.DowLoadActivity;
import com.nhatthanh.ass_mob403_thanhlnph16387.api.CallBackPhoto;
import com.nhatthanh.ass_mob403_thanhlnph16387.databinding.ItemPhotoBinding;
import com.nhatthanh.ass_mob403_thanhlnph16387.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private  List<Photo> list;
    private CallBackPhoto callBackPhoto;

    public void setData(List<Photo> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public PhotoAdapter(List<Photo> list, CallBackPhoto callBackPhoto) {
        this.list = list;
        this.callBackPhoto = callBackPhoto;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemPhotoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Photo photo = list.get(position);
        holder.view.setText(photo.getViews()+" views");
        holder.photo.setMinimumHeight(photo.getHeightN());
        holder.photo.setMinimumWidth(photo.getWidthN());
        if (photo.getUrlN()!=null){
            Picasso.get().load(photo.getUrlN()).into(holder.photo);
        }
        holder.itemView.setOnClickListener(view -> callBackPhoto.callPhoto(photo));
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView view;
        private final ImageView photo;

        public ViewHolder(ItemPhotoBinding binding) {
            super(binding.getRoot());
            view = binding.tvViews;
            photo = binding.imgPhoto;
        }
    }
}
