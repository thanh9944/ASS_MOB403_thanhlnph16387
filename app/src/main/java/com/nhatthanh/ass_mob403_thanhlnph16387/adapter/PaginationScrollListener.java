package com.nhatthanh.ass_mob403_thanhlnph16387.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {
    private final StaggeredGridLayoutManager staggeredGridLayoutManager;

    public PaginationScrollListener(StaggeredGridLayoutManager staggeredGridLayoutManager) {
        this.staggeredGridLayoutManager = staggeredGridLayoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount=staggeredGridLayoutManager.getChildCount();
        int totalItemCount=staggeredGridLayoutManager.getItemCount();
        int[] firstVisibleItemPosition=staggeredGridLayoutManager.findFirstVisibleItemPositions(null);
        if (isLoading() || isLastPage()){
            return;
        }
        if (firstVisibleItemPosition[0]>=0 && (visibleItemCount+firstVisibleItemPosition[0]) > totalItemCount){
            loadMoreItem();
        }
    }

    public abstract void loadMoreItem();
    public abstract boolean isLoading();
    public abstract boolean isLastPage();
}
