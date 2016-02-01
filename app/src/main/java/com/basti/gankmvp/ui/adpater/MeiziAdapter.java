package com.basti.gankmvp.ui.adpater;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.basti.gankmvp.R;
import com.basti.gankmvp.model.Meizi;
import com.basti.gankmvp.utils.DateUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SHIBW-PC on 2016/1/29.
 */
public class MeiziAdapter extends RecyclerView.Adapter<MeiziAdapter.MyViewHolder> {

    private List<Meizi> mList;
    private Context mContext;
    private int lastPosition = 0;

    public MeiziAdapter(List<Meizi> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_meizi, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Meizi meizi = mList.get(position);
        holder.card.setTag(meizi);
        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        holder.ivMeizi.setBackgroundColor(Color.argb(204, red, green, blue));
        /*Glide.with(context)
                .load(meizi.url)
                .crossFade()
                .into(holder.ivMeizi);*/
        holder.tvWho.setText(meizi.who);
        holder.tvAvatar.setText(meizi.who.substring(0, 1).toUpperCase());
        holder.tvDesc.setText(meizi.desc);
        holder.tvTime.setText(DateUtil.toDateTimeStr(meizi.publishedAt));
        holder.tvAvatar.setVisibility(View.GONE);
        showItemAnimation(holder, position);
    }

    private void showItemAnimation(MyViewHolder holder, int position) {
        if (position > lastPosition){
            lastPosition = position;

            ObjectAnimator.ofFloat(holder.card,"translationY",1f*holder.card.getHeight(),0f)
                    .setDuration(500)
                    .start();
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_meizi)
        ImageView ivMeizi;
        @Bind(R.id.tv_who)
        TextView tvWho;
        @Bind(R.id.tv_avatar)
        TextView tvAvatar;
        @Bind(R.id.tv_desc)
        TextView tvDesc;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.rl_gank)
        RelativeLayout rl;
        View card;

        @OnClick(R.id.rl_gank)
        void gankClick() {

        }

        @OnClick(R.id.iv_meizi)
        void meiziClick(){

        }
        public MyViewHolder(View itemView) {
            super(itemView);
            card = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
