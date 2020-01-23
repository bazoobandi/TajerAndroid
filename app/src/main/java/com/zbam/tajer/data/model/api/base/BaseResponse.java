package com.zbam.tajer.data.model.api.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by z.bazoubandi on 7/18/2018.
 */

public class BaseResponse<T> {

    protected Settings settings;
    protected List<T> data = new ArrayList<>();

    public Settings getSettings() {
        return settings;
    }

    public List<T> getData() {
        return data;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
