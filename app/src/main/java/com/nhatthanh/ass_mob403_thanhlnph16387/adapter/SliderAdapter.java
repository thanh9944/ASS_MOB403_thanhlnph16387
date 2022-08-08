package com.nhatthanh.ass_mob403_thanhlnph16387.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;
import com.nhatthanh.ass_mob403_thanhlnph16387.databinding.ItemPhotoBinding;
import com.nhatthanh.ass_mob403_thanhlnph16387.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {

    private List<Photo> list;
    private ViewPager2 viewPager2;

    public SliderAdapter(List<Photo> list, ViewPager2 viewPager2) {
        this.list = list;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemPhotoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Photo photo= list.get(position);
        holder.layout.setVisibility(View.GONE);
        Picasso.get().load(photo.getUrlN()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final RoundedImageView imageView;
        private final LinearLayout layout;

        public ViewHolder(ItemPhotoBinding binding) {
            super(binding.getRoot());
            imageView = binding.imgPhoto;
            layout = binding.layout;
        }
    }
}
