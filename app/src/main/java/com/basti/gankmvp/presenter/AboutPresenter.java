package com.basti.gankmvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.basti.gankmvp.view.IBaseView;

/**
 * Created by SHIBW-PC on 2016/1/28.
 */
public class AboutPresenter extends BasePresenter<IBaseView> {


    public AboutPresenter(Context context, IBaseView iView) {
        super(context, iView);
    }

    public void starInMarket() {
        Uri uri = Uri.parse(String.format("market://details?id=%s", mContext.getPackageName()));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (intent.resolveActivity(mContext.getPackageManager()) != null)
            mContext.startActivity(intent);
    }

    @Override
    public void release() {

    }
}
