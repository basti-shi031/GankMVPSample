package com.basti.gankmvp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SHIBW-PC on 2016/2/1.
 */
public class Result {

    @SerializedName("Android")
    public List<Gank> androidList;
    @SerializedName("休息视频")
    public List<Gank> videoList;
    @SerializedName("iOS")
    public List<Gank> iOSList;
    @SerializedName("福利")
    public List<Gank> meiziList;
    @SerializedName("拓展资源")
    public List<Gank> resourceList;
    @SerializedName("瞎推荐")
    public List<Gank> recommendList;
    @SerializedName("App")
    public List<Gank> appList;
    @SerializedName("前端")
    public List<Gank> webList;

}
