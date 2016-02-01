package com.basti.gankmvp.ui.widget;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 * 可下拉刷新的RecyclerView
 * Created by SHIBW-PC on 2016/1/29.
 */
public class LMRecyclerView extends RecyclerView {

    private LoadMoreListener mListener;
    private boolean scrollToBottom = true;
    private FloatingActionButton mFab;

    public interface LoadMoreListener {
        void loadMore();
    }

    public LMRecyclerView(Context context) {
        super(context);
    }

    public LMRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LMRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        mListener = loadMoreListener;
    }

    @Override
    public void onScrolled(int dx, int dy) {
        scrollToBottom = dy > 0;

        if (mFab != null){
            if(scrollToBottom){
                if (mFab.isShown()){
                    mFab.hide();
                }
                }else {
                if (!mFab.isShown())
                    mFab.show();
            }
        }

    }

    public void setFloatingActionButton(FloatingActionButton fab){
        mFab = fab;
    }

    @Override
    public void onScrollStateChanged(int state) {

        if (getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) getLayoutManager();
            //空闲状态
            if (state == RecyclerView.SCROLL_STATE_IDLE) {
                int lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition();
                int totalItemCount = layoutManager.getItemCount();
                if (lastVisibleItem == totalItemCount - 1 && scrollToBottom) {
                    if (mListener != null) {
                        mListener.loadMore();
                    }
                }
            }
        } else if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) getLayoutManager();

            if (state == RecyclerView.SCROLL_STATE_IDLE) {
                int[] lastVisibleItems = new int[2];
                lastVisibleItems = layoutManager.findLastCompletelyVisibleItemPositions(lastVisibleItems);
                int lastVisibleItem = Math.max(lastVisibleItems[0],lastVisibleItems[1]);
                int totalItemCount = layoutManager.getItemCount();
                if (lastVisibleItem == totalItemCount - 1 && scrollToBottom) {
                    if (mListener != null) {
                        mListener.loadMore();
                    }
                }
            }
        }
    }
}
