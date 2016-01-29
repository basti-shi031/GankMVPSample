package com.basti.gankmvp.ui.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.basti.gankmvp.R;
import com.basti.gankmvp.presenter.BasePresenter;

import butterknife.Bind;

/**
 * Created by SHIBW-PC on 2016/1/28.
 */
public abstract class ToolBarActivity<T extends BasePresenter> extends BaseActivity {

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;
    protected T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(canBack());

    }

    protected boolean canBack(){
        return true;
    }
}
