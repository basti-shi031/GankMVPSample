package com.basti.gankmvp.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.basti.gankmvp.R;
import com.basti.gankmvp.presenter.BasePresenter;

import butterknife.ButterKnife;

/**
 * 基础Activity
 * Created by SHIBW-PC on 2016/1/28.
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    protected String Tag = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideContentViewId());

        ButterKnife.bind(this);
        initPresenter();
    }

    protected abstract void initPresenter();

    protected abstract int provideContentViewId();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
