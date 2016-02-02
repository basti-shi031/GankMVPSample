package com.basti.gankmvp.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.basti.gankmvp.R;
import com.basti.gankmvp.model.Gank;
import com.basti.gankmvp.presenter.StarPresenter;
import com.basti.gankmvp.ui.adpater.StarViewPagerAdapter;
import com.basti.gankmvp.ui.base.ToolBarActivity;
import com.basti.gankmvp.view.IStarView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by SHIBW-PC on 2016/2/2.
 */
public class StarActivity extends ToolBarActivity<StarPresenter> implements IStarView {

    @Bind(R.id.tablayout)
    TabLayout tabLayout;

    @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected void initPresenter() {
        presenter = new StarPresenter(this, this);
        presenter.initPresenter();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_star;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showGankListView(List<Gank> listview) {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void init() {

        StarViewPagerAdapter starAdapter = new StarViewPagerAdapter(getSupportFragmentManager(), presenter.getTitles());
        viewPager.setAdapter(starAdapter);
        viewPager.setOffscreenPageLimit(presenter.getTitles().length - 1);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(starAdapter);
    }
}
