package com.basti.gankmvp.http;

import com.basti.gankmvp.model.DailyData;
import com.basti.gankmvp.model.GankData;
import com.basti.gankmvp.model.MeiziData;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * 接口
 * Created by SHIBW-PC on 2016/1/29.
 */
public interface BastiGankRetrofit {

    @GET("data/福利/" + HttpConfig.MEIZIDATASIZE + "/{page}")
    Observable<MeiziData> fetchMeiziData(@Path("page") int page);

    @GET("data/休息视频/" + HttpConfig.MEIZIDATASIZE + "/{page}")
    Observable<GankData> getGankData(@Path("page") int page);

    @GET("day/{year}/{month}/{day}")
    Observable<DailyData> fetchDailyData(
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day);

    @GET("data/{type}/" + HttpConfig.GANKDATASIZE + "/{page}")
    Observable<GankData> fetchTypeData(@Path("type") String type, @Path("page") int page);

}
