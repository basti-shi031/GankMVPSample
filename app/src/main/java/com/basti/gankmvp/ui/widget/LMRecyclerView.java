package com.basti.gankmvp.ui.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * 可下拉刷新的RecyclerView
 * Created by SHIBW-PC on 2016/1/29.
 */
public class LMRecyclerView extends RecyclerView {

    private LoadMoreListener mListener;
    private boolean scrollToBottom = true;
    public  interface LoadMoreListener{
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

    public void setLoadMoreListener(LoadMoreListener loadMoreListener){
        mListener = loadMoreListener;
    }

    @Override
    public void onScrolled(int dx, int dy) {
        scrollToBottom = dy > 0;
    }

    @Override
    public void onScrollStateChanged(int state) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) getLayoutManager();
        //空闲状态
        if (state == RecyclerView.SCROLL_STATE_IDLE){
            int lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition();
            int totalItemCount = layoutManager.getItemCount();
            if (lastVisibleItem == totalItemCount-1 && scrollToBottom){
                if (mListener != null){
                    mListener.loadMore();
                }
            }

        }
    }
}
