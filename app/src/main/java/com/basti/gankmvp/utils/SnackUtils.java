package com.basti.gankmvp.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by SHIBW-PC on 2016/2/3.
 */
public class SnackUtils {

    public static void show(View view, String message, String action, View.OnClickListener onClickListener) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).setAction(action, onClickListener).show();
    }

    public static void show(View view, String message){
        show(view,message,"",null);
    }

}
