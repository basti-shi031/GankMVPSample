package com.basti.gankmvp.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.basti.gankmvp.R;
import com.basti.gankmvp.model.Gank;
import com.basti.gankmvp.presenter.StarPresenter;
import com.basti.gankmvp.ui.adpater.StarAdapter;
import com.basti.gankmvp.ui.base.BaseFragment;
import com.basti.gankmvp.ui.widget.LMRecyclerView;
import com.basti.gankmvp.view.IStarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by SHIBW-PC on 2016/2/2.
 */
public class StarFragment extends BaseFragment<StarPresenter> implements IStarView, LMRecyclerView.LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String TYPE = "type";
    private String type = "";

    private List<Gank> gankList;
    private StarAdapter adapter;
    private int page = 1;
    private boolean isRefresh = true;


    @Bind(R.id.recycler_view)
    LMRecyclerView recyclerView;

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void initPresenter() {
        presenter = new StarPresenter(getActivity(), this);
        presenter.initPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_star;
    }

    public static StarFragment newInstance(String type) {

        StarFragment fragment = new StarFragment();

        Bundle args = new Bundle();
        args.putString(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(TYPE);
        }Log.i("TAG","onCreate");
    }

    @Override
    public void showGankListView(List<Gank> list) {
        if (isRefresh){
            gankList.clear();
        }
        gankList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {
        if (!swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void hideProgressBar() {
        if (swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void init() {
        gankList = new ArrayList<>();
        adapter = new StarAdapter(gankList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setLoadMoreListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.blue);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);
        presenter.fetchGankData(type, page);

    }

    @Override
    public void loadMore() {
        isRefresh = false;
        presenter.fetchGankData(type,++page);
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        page = 1;
        presenter.fetchGankData(type,page);
    }
}
