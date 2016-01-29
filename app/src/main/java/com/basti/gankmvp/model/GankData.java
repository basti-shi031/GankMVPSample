package com.basti.gankmvp.model;

import java.util.List;

/**
 * Created by SHIBW-PC on 2016/1/29.
 */
public class GankData extends BaseData{

    private List<Gank> gankData;

    public List<Gank> getGankData() {
        return gankData;
    }

    public void setGankData(List<Gank> gankData) {
        this.gankData = gankData;
    }
}
