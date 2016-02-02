package com.basti.gankmvp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.basti.gankmvp.model.Meizi;
import com.basti.gankmvp.presenter.MainPresenter;
import com.basti.gankmvp.ui.adpater.MeiziAdapter;
import com.basti.gankmvp.ui.base.ToolBarActivity;
import com.basti.gankmvp.ui.widget.LMRecyclerView;
import com.basti.gankmvp.view.IMainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends ToolBarActivity<MainPresenter> implements IMainView, SwipeRefreshLayout.OnRefreshListener, LMRecyclerView.LoadMoreListener {

    @Bind(R.id.recycler_view)
    LMRecyclerView mRecyclerView;

    @Bind(R.id.fab)
    FloatingActionButton mFab;

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @OnClick(R.id.fab)
    void toStarActivity(){
        presenter.toStarActivity();
    }
    private List<Meizi> meizis;
    private MeiziAdapter adapter;
    private int page = 1;
    private boolean isRefresh = true;
    private boolean canLoading = true;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initPresenter() {
        presenter = new MainPresenter(this, this);
        presenter.initPresenter();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showProgressView() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgressView() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showErrorView(Throwable throwable) {
        Log.i("TAG", throwable.toString());
    }

    @Override
    public void showNoMoreData() {
        Log.i("TAG", "showNoMoreData");
    }

    @Override
    public void showMeiziList(List<Meizi> meiziList) {
        canLoading = true;
        if (isRefresh){
            meizis.clear();
        }
        meizis.addAll(meiziList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void init() {

        if (meizis == null) {
            meizis = new ArrayList<>();
        }
        adapter = new MeiziAdapter(meizis, this);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLoadMoreListener(this);
        mRecyclerView.setFloatingActionButton(mFab);

        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary, R.color.colorAccent, R.color.blue);
        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.setRefreshing(true);
        presenter.fetchMeiziData(page);
    }

    @Override
    protected boolean canBack() {
        return false;
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        page = 1;
        presenter.fetchMeiziData(page);
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        page++;
        presenter.fetchMeiziData(page);
    }
}
