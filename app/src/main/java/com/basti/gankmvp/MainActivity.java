package com.basti.gankmvp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
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

public class MainActivity extends ToolBarActivity<MainPresenter> implements IMainView, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recycler_view)
    LMRecyclerView mRecyclerView;

    @Bind(R.id.fab)
    FloatingActionButton mFab;

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<Meizi> meizis;
    private MeiziAdapter adapter;
    private int page = 1;
    private boolean isRefresh = true;
    private boolean canLoading = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initPresenter() {
        presenter = new MainPresenter(this,this);
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

    }

    @Override
    public void hideProgressView() {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showNoMoreData() {

    }

    @Override
    public void showMeiziList(List<Meizi> meiziList) {
        Log.i("TAG",meiziList.size()+"");
    }

    @Override
    public void init() {

        if (meizis == null){
            meizis = new ArrayList<>();
        }
        adapter = new MeiziAdapter(meizis,this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary, R.color.colorAccent, R.color.blue);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                presenter.fetchMeiziData(page);
            }
        });
    }

    @Override
    protected boolean canBack() {
        return false;
    }

    @Override
    public void onRefresh() {

    }
}
