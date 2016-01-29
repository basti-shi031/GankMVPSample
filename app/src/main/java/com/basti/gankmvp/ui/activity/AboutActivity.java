package com.basti.gankmvp.ui.activity;

import android.os.Bundle;
import android.widget.Button;

import com.basti.gankmvp.R;
import com.basti.gankmvp.presenter.AboutPresenter;
import com.basti.gankmvp.ui.base.ToolBarActivity;
import com.basti.gankmvp.view.IBaseView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by SHIBW-PC on 2016/1/28.
 */
public class AboutActivity extends ToolBarActivity<AboutPresenter> implements IBaseView {

    @Bind(R.id.star)
    Button star;

    @Override
    protected void initPresenter() {
        presenter = new AboutPresenter(this,this);
        presenter.initPresenter();
    }

    @OnClick(R.id.star)
    void startInMarket(){
        presenter.starInMarket();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_about;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init() {

    }
}
