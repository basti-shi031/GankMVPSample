package com.basti.gankmvp.ui.adpater;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.basti.gankmvp.R;
import com.basti.gankmvp.model.Gank;
import com.basti.gankmvp.ui.activity.WebViewActivity;
import com.basti.gankmvp.utils.StringStyleUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SHIBW-PC on 2016/2/2.
 */
public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder>{

    private List<Gank> mGankList;
    private Context mContext;
    public static final String WEBTAG = "web";

    public StarAdapter(List<Gank> gankList,Context context){
        mGankList = gankList;
        mContext = context;
    }

    @Override
    public StarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_star, parent, false);
        return new StarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StarViewHolder holder, int position) {
        Gank gank = mGankList.get(position);
        holder.tvBattery.setTag(gank);
        holder.tvBattery.setText(StringStyleUtil.getGankStyleStr(gank));
    }

    @Override
    public int getItemCount() {
        return mGankList.size();
    }

    class StarViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.tv_battery)
        TextView tvBattery;
        @OnClick(R.id.ll_battery)
        void toWebClick(){
            //WebActivity.loadWebViewActivity(context,(Gank)tvBattery.getTag());
            Intent intent = new Intent(mContext, WebViewActivity.class);
            intent.putExtra(WEBTAG,(Gank)tvBattery.getTag());
            mContext.startActivity(intent);
        }

        public StarViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
